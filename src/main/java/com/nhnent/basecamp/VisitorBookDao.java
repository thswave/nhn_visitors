package com.nhnent.basecamp;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
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

	public VisitorBook get(final int id) {

		String sql = "select id, name, password, content, email, created_at from guest_book where id = ?";
		Object[] params = { id };
		VisitorBook visitorBook = null;
		
		try {
			visitorBook  = jdbcTemplate.queryForObject(sql, params, new RowMapper<VisitorBook>(){
				public VisitorBook mapRow(ResultSet rs, int rowNum) throws SQLException {
					VisitorBook visitorBook = new VisitorBook();
					visitorBook.setId(rs.getInt("id"));
					visitorBook.setName(rs.getString("name"));
					visitorBook.setPassword(rs.getString("password"));
					visitorBook.setContent(rs.getString("content"));
					visitorBook.setEmail(rs.getString("email"));
					visitorBook.setCreated_at(rs.getString("created_at"));
					return visitorBook;
				}
			});
		} catch (EmptyResultDataAccessException e) {
			e.printStackTrace();
		}
		
		return visitorBook;
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
		String sql = "SELECT MAX(id) from guest_book";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}

	public void delete(int id) {
		final String sql = "DELETE FROM guest_book WHERE id = ?";
		Integer[] params = new Integer[] { id };
		jdbcTemplate.update(sql, params);
		
	}

}
