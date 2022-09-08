package com.akshay.expenseTracker.models;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Category {
	
	@Id
	@SequenceGenerator(name = "category_seq", sequenceName = "category_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "category_seq")
	private Long id;
	private String name;
	private String description;
	private LocalDateTime addedDateTime;
	private LocalDateTime updatedDatetime;
	
}
