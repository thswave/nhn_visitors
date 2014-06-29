package com.nhnent.basecamp;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class VisitorBookDao {

	private DataSource dataSource;

	private JdbcTemplate jdbcTemplate;

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public VisitorBook get(int id) {

		String sql = "select id, name, password, content, email, created_at from guest_book where id = ?";

		RowMapper mapper = new RowMapper() {
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				VisitorBook visitorBook = new VisitorBook();
				visitorBook.setId(rs.getInt("id"));
				visitorBook.setName(rs.getString("name"));
				visitorBook.setPassword(rs.getString("password"));
				visitorBook.setContent(rs.getString("content"));
				visitorBook.setEmail(rs.getString("email"));
				visitorBook.setCreated_at(rs.getString("created_at"));
				return visitorBook;
			}
		};

		Object[] args = { id };
		return (VisitorBook) jdbcTemplate.queryForObject(sql, args, mapper);
	}

	public void add(VisitorBook visitorBook) {
		final String sql = "INSERT INTO "
				+ "guest_book( name, password, email, content, created_at, updated_at) "
				+ "values(?, ?, ?, ?, current_timestamp, current_timestamp)";
		String[] params = new String[] { visitorBook.getName(),
				visitorBook.getPassword(), visitorBook.getEmail(),
				visitorBook.getContent(), };
		jdbcTemplate.update(sql, params);
	}

	public int getLastInsertedVisitorBookId() {
		String sql = "select MAX(id) from guest_book";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}

}
