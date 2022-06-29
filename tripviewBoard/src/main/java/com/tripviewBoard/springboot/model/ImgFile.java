package com.tripviewBoard.springboot.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="files")
public class ImgFile extends BaseTimeEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String origFilename;
	
	private String filePath;
	
	private Long FileSize;
	
	@ManyToOne
	@JoinColumn(name = "board_id")
	@JsonManagedReference
	private TripviewBoard board;
	
	
	
	@Bean
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
		commonsMultipartResolver.setDefaultEncoding("UTF-8");
		commonsMultipartResolver.setMaxUploadSize(50 * 1024* 1024);
		return commonsMultipartResolver;
	}
	
	
//	@Builder
//    public ImgFile(String origFilename, String filePath, Long FileSize) {
//        this.origFilename = origFilename;
//        this.filePath = filePath;
//        this.FileSize = FileSize;
//    }
//
//    
//    public void setBoard(TripviewBoard board) {
//        this.board = board;
//        
//        if(!board.getImgfiles().contains(this))
//        	
//        	board.getImgfiles().add(this);
//    }
}
