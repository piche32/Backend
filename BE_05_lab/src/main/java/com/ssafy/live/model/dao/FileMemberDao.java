package com.ssafy.live.model.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.ssafy.live.model.dto.Member;

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
            load(); // 파일이 존재하면 로드

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int insert(Member member) {
        System.out.println(member);
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
            members.add(new Member(i, String.format("%03d", length - i), String.format("%03d@ssafy.com", i), "1234", "USER"));
        }
        members.add(new Member(100, "관리자", "admin@ssafy.com", "1234", "ADMIN"));
        System.out.println("멤버 초기화 완료 " + members.size() + " :" + members.get(0));
    }

    @Override
    public synchronized List<Member> search() {
        // 완전히 새로운 리스트 생성으로 thread safety 보장, 읽기 전용으로 설정
        return Collections.unmodifiableList(new ArrayList<>(members));
    }


    @Override
    public Member login(String email, String password) {
        System.out.println(email + ", " + password);
        synchronized (members) {
            return members.stream()
                    .filter(member -> member.getEmail().equals(email) && member.getPassword().equals(password))
                    .findFirst()
                    .orElse(null);
        }
    }
}
