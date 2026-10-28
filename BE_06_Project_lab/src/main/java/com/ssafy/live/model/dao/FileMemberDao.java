package com.ssafy.live.model.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.ssafy.live.model.dto.Member;
import com.ssafy.live.model.dto.Page;
import com.ssafy.live.model.dto.SearchCondition;

public class FileMemberDao implements MemberDao {
    private static final FileMemberDao instance = new FileMemberDao();
    private final File dataFile;
    private List<Member> members;

    public static FileMemberDao getInstance() {
        return instance;
    }

    private FileMemberDao() {
        // file 초기화
        String path = FileMemberDao.class.getResource("/").getPath() + "member.dat";
        dataFile = new File(path);

        try {
            if (!dataFile.exists()) {
                dataFile.createNewFile();
            }
            load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int insert(Member member) {
        synchronized (members) {
            if (members.stream().anyMatch(m -> m.getEmail().equals(member.getEmail()))) {
                throw new RuntimeException("이미 가입된 회원입니다: " + member.getEmail());
            }
            member.setMno(System.currentTimeMillis()); // mno는 자동 설정
            members.add(member);
            System.out.println(member);
            return 1;
        }
    }

    // load와 save는 언제 호출하는게 좋을지 생각해보세요.
    private void load() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(dataFile))) {
            members = (List) ois.readObject();
            System.out.println("회원 정보 로딩 완료: " + members.size() + "명");
        } catch (Exception e) {
            System.out.println("저장된 회원 정보가 없습니다.");
            members = Collections.synchronizedList(new ArrayList<>());
        }
    }

    public void save() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(dataFile))) {
            oos.writeObject(members);
            System.out.println("회원 정보 저장 완료");
        } catch (Exception e) {
            throw new RuntimeException("회원 정보 저장 실패", e);
        }
    }

    public synchronized void reset() {
        int length = 100;
        members.clear();
        for (int i = 0; i < length; i++) {
            members.add(new Member(i, String.format("%03d", length - i), String.format("%03d@ssafy.com", i), "1234",
                    "USER"));
        }
        members.add(new Member(100, "관리자", "admin@ssafy.com", "1234", "ADMIN"));
        System.out.println("멤버 초기화 완료 " + members.size() + " :" + members.get(0));
    }

    // TODO: 08. 검색 조건에 부합하는 회원이 몇 명인지 반환해보자. 
    @Override
    public synchronized int getTotalCount(SearchCondition condition) {
        if (condition == null) {
            return members.size();
        }

        return (int) members.stream().filter(member -> matchesCondition(member, condition)).count();
    }

    // TODO: 09. 검색 조건에 부합하는 회원 목록을 반환해보자. 페이지에 근거하여 반환해보자. 
    @Override
    public Page<Member> search(SearchCondition condition) {
        List<Member> selected = null;
        synchronized (members) {
            if (condition == null) {
                selected = new ArrayList<>(members);
            } else {
                selected = members.stream().filter(member -> matchesCondition(member, condition))
                        .skip(condition.getItemsPerPage() * (condition.getCurrentPage() - 1))
                        .limit(condition.getItemsPerPage()).collect(Collectors.toList());
            }
        }
        return new Page<>(condition, getTotalCount(condition), selected);
    }

    private boolean matchesCondition(Member member, SearchCondition condition) {
        // 검색어가 없으면 통과
        if (!condition.hasKeyword()) {
            return true;
        }
        String key = condition.normalizedKey();
        String word = condition.getWord();

        // 검색 조건에 따라 필터링
        return switch (key) {
            case "name" -> member.getName().contains(word);
            case "email" -> member.getEmail().contains(word);
            default -> false; // 잘못된 키는 제외 처리
        };
    }

    @Override
    public Member login(String email, String password) {
        synchronized (members) {
            return members.stream()
                    .filter(member -> member.getEmail().equals(email) && member.getPassword().equals(password))
                    .findFirst().orElse(null);
        }
    }

    @Override
    public Member findByEmail(String email) {
        synchronized (members) {
            return members.stream().filter(member -> member.getEmail().equals(email)).findFirst().orElse(null);
        }
    }

    @Override
    public Member memberModify(Member member) {
        synchronized (members) {
            return members.stream().filter(m -> m.getEmail().equals(member.getEmail())).findFirst().map(m -> {
                m.setName(member.getName());
                m.setPassword(member.getPassword());
                m.setRole(member.getRole());
                return m;
            }).orElse(null);
        }
    }

    @Override
    public boolean memberDelete(String email) {
        synchronized (members) {
            return members.removeIf(m -> m.getEmail().equals(email));
        }
    }
}
