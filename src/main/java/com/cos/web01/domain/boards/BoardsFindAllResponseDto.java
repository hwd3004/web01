package com.cos.web01.domain.boards;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BoardsFindAllResponseDto {

	private Long id;
	private String title;
	private Long authorId;
	private int depth;
	private String authorName;
	private LocalDateTime createdDate;

	// 필요한 것만 보내줄 dto
//	public void converEntityToDto(Boards boards) {
//		this.id = boards.getId();
//		this.title = boards.getTitle();
//		this.authorId = boards.getAuthor() == null ? null : boards.getAuthor().getId();
//		this.depth = boards.getDepth();
//		this.authorName = boards.getAuthor() == null ? null : boards.getAuthor().getUserName();
//		this.createdDate = boards.getCreatedDate();
//	}

}
