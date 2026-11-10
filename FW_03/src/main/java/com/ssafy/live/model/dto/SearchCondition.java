package com.ssafy.live.model.dto;

import java.util.Set;

import lombok.Data;

@Data
public class SearchCondition {
    private static final Set<String> ALLOWED_KEYS = Set.of("name", "email");
    // 검색에 사용할 조건
    private String key;
    private String word;
    private int currentPage;
    private int itemsPerPage = 5;

    public SearchCondition() {
    }

    public SearchCondition(String key, String word, int currentPage) {
        this.key = key;
        this.word = word;
        this.currentPage = currentPage;

    }

    /**
     * 페이징을 위해 현재 페이지의 offset 반환
     * 
     * @return
     */
    public int getOffset() {
        return (currentPage - 1) * itemsPerPage;
    }

    /**
     * SearchCondition에 key와 word가 모두 설정되어있는지 확인
     * 
     * @param condition
     * @return
     */
    public boolean hasKeyword() {
        return normalizedKey() != null && word != null && !word.isBlank();
    }

    public String normalizedKey() {
        if (key == null || key.isBlank()) {
            return null;
        }
        String normalized = key.trim().toLowerCase();
        return ALLOWED_KEYS.contains(normalized) ? normalized : null;
    }

}
