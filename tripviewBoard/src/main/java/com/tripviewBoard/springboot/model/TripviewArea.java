package com.tripviewBoard.springboot.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "areas")
public class TripviewArea {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;			//여행지 코드
	private String name;		//여행지 명
	private String value;		//여행지 세부사항
	
	
	@Builder
	public TripviewArea(Long id,String name, String value) {
		this.id = id;
		this.name = name;
		this.value = value;
	}
	
	@JsonBackReference
	@OneToMany(mappedBy = "area", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<TripviewBoard> boards;
	
	
	public void connectBoard(TripviewBoard board) {
		this.boards.add(board);
	}
	
}
