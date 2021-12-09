package com.cos.web01.domain.boards;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;
import com.cos.web01.domain.user.Users;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@DynamicUpdate
@Entity
public class Boards {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// 글의 그룹
	@ColumnDefault("0")
	private int ref;

	// 글의 순서
	@ColumnDefault("0")
	private int restep;

	// 글의 레벨
	@ColumnDefault("0")
	private int relevel;

	@Column(length = 200, nullable = false)
	private String title;

	@Column(columnDefinition = "TEXT", nullable = false)
	private String content;

	@ColumnDefault("null")
	private Long parentId;

	// Boards 엔티티의 멤버 변수 author는 Users 엔티티의 객체를 자료형으로 가지고 있음
	@ManyToOne // 하나의 Users author는 여러개의 Boards를 가질 수 있음
	@JoinColumn(name = "author_id") // 연관 관계 매핑할 때 새 테이블을 만들지 않고 칼럼으로 만듬, 칼럼 이름은 author_id로 정함
	private Users author;

	// @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL) // 하나의 Boards는
	// 여러개의 Images를 가질 수 있음
	// @JoinColumn(name = "boardId") // 연관 관계 매핑할 때 새 테이블을 만들지 않고 칼럼으로 만듬, 칼럼 이름은
	// boardId로 정함
	// private List<Images> images = new ArrayList<Images>();

	@CreationTimestamp
	private LocalDateTime createdDate;

	@UpdateTimestamp
	private LocalDateTime updatedDate;
}
