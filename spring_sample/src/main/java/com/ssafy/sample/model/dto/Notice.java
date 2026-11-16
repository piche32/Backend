package com.ssafy.sample.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// TODO 1: 명세서에 맞게 필드(9개)와 Lombok 어노테이션을 완성하시오.
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Notice {
	int noticeId;
	int catId;
	int mno;
	String title;
	String content;
	int viewCount;
	boolean pinned;
	String status;
	String createdAt;
}