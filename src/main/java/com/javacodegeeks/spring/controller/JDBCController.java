package com.javacodegeeks.spring.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javacodegeeks.spring.util.JdbcResultSetStreamer;

@RestController
public class JDBCController {
	private JdbcTemplate jdbcTemplate;

	public JDBCController(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@GetMapping(value = "/jsonstream")
	public void streamJdbcResultSetToJson(HttpServletResponse response) throws IOException {
		JdbcResultSetStreamer jdbcResultSetStreamer = new JdbcResultSetStreamer(jdbcTemplate,
				response.getOutputStream());
		jdbcResultSetStreamer.writeToJson();
	}

	@GetMapping(value = "/csvstream")
	public void streamJdbcResultSetToCsv(HttpServletResponse response) throws IOException {
		JdbcResultSetStreamer jdbcResultSetStreamer = new JdbcResultSetStreamer(jdbcTemplate,
				response.getOutputStream());
		jdbcResultSetStreamer.writeToCsv();
	}
}