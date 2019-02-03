package com.javacodegeeks.spring;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.javacodegeeks.spring.util.JdbcResultSetStreamer;

@Component
public class StreamJdbcResultSetCommandLineRunner implements CommandLineRunner {
	private JdbcTemplate jdbcTemplate;

	public StreamJdbcResultSetCommandLineRunner(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void run(String... args) throws Exception {
		writeToJson();
		writeToCsv();
	}

	private void writeToJson() throws FileNotFoundException {
		File jsonFile = new File("resultset.json");
		OutputStream jsonOutputStream = new FileOutputStream(jsonFile);
		JdbcResultSetStreamer jdbcResultSetStreamer = new JdbcResultSetStreamer(jdbcTemplate, jsonOutputStream);
		jdbcResultSetStreamer.writeToJson();
	}

	private void writeToCsv() throws FileNotFoundException {
		File csvFile = new File("resultset.csv");
		OutputStream csvOutputStream = new FileOutputStream(csvFile);
		JdbcResultSetStreamer jdbcResultSetStreamer = new JdbcResultSetStreamer(jdbcTemplate, csvOutputStream);
		jdbcResultSetStreamer.writeToCsv();
	}
}