package com.micro.cors.dao;

import java.time.LocalDate;

import lombok.Data;

@Data
public class SimpleLoanDTO {

	private int bookId;
	private int customerId;
	private LocalDate beginDate;
	private LocalDate endDate;
}
