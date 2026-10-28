package com.ssafy.live.model.dto;

import java.util.Set;
//TODO: 06. 검색 조건을 유지하기 위한 SearchCondition에 대해 살펴보자. 
public class SearchCondition {
    private static final Set<String> ALLOWED_KEYS = Set.of("name", "email");
    // 검색에 사용할 조건
    private String key;
    private String word;
    private int currentPage; // 1부터 시작하는 현재 페이지 번호
    private int itemsPerPage = 5;

    public SearchCondition() {}


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

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getItemsPerPage() {
        return itemsPerPage;
    }

    public void setItemsPerPage(int itemsPerPage) {
        this.itemsPerPage = itemsPerPage;
    }

    @Override
    public String toString() {
        return "SearchCondition [key=" + key + ", word=" + word + ", currentPage=" + currentPage + ", itemsPerPage=" + itemsPerPage + "]";
    }

}
