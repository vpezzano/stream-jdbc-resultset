package com.javacodegeeks.spring.util;

import java.io.OutputStream;
import org.springframework.jdbc.core.JdbcTemplate;

import com.javacodegeeks.spring.util.JdbcResultSetToCsvStreamer;
import com.javacodegeeks.spring.util.JdbcResultSetToJsonStreamer;

public class JdbcResultSetStreamer {
	private JdbcTemplate jdbcTemplate;
	private OutputStream outputStream;
	private static final String query = "SELECT name, age from PERSON";

	public JdbcResultSetStreamer(JdbcTemplate jdbcTemplate, OutputStream outputStream) {
		this.jdbcTemplate = jdbcTemplate;
		this.outputStream = outputStream;
	}

	public void writeToJson() {
		jdbcTemplate.query(query, new JdbcResultSetToJsonStreamer(outputStream));
	}

	public void writeToCsv() {
		jdbcTemplate.query(query, new JdbcResultSetToCsvStreamer(outputStream));
	}
}