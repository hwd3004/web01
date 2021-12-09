package com.cos.web01.dto.board;

import java.time.LocalDateTime;

import com.cos.web01.domain.user.Users;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BoardResponseDto {

	private Long id;

	// 글의 그룹
	private int ref;

	// 글의 순서
	private int restep;

	// 글의 레벨
	private int relevel;

	private String title;

	private String content;

	private Long parentId;

	private Users author;

	private LocalDateTime createdDate;

	private LocalDateTime updatedDate;

}
