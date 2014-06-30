package com.nhnent.basecamp.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.nhnent.basecamp.model.VisitorBook;

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

	public VisitorBook findById(final int id) {

		String sql = "SELECT id, name, password, content, email, created_at FROM guest_book WHERE id = ?";
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
		}
		
		return visitorBook;
	}

	public List<VisitorBook> findAll(){
		String sql = "SELECT id, name, password, content, email, created_at, updated_at"
				+ " FROM guest_book ORDER BY updated_at DESC";
		List<VisitorBook> visitorBookList = null;
		
		visitorBookList  = jdbcTemplate.query(sql, new BeanPropertyRowMapper<VisitorBook>(VisitorBook.class)); 
		
		return visitorBookList;
	}
	
	public void add(VisitorBook visitorBook) {
		final String sql = "INSERT INTO "
				+ "guest_book( name, password, email, content, created_at, updated_at) "
				+ "values(?, ?, ?, ?, current_timestamp, current_timestamp)";
		Object[] params = new String[] { visitorBook.getName(),
				visitorBook.getPassword(), visitorBook.getEmail(),
				visitorBook.getContent(), };
		jdbcTemplate.update(sql, params);
	}

	public int getLastAddedVisitorBookId() {
		String sql = "SELECT MAX(id) from guest_book";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}

	public void deleteById(int id) {
		final String sql = "DELETE FROM guest_book WHERE id = ?";
		Object[] params = new Integer[] { id };
		jdbcTemplate.update(sql, params);
		
	}

	public void update(Object[] updateData) {
		final String sql = "UPDATE guest_book SET name = ?, email = ?, content = ?, password = ?,"
				+ " updated_at = current_timestamp  WHERE id = ?";
		Object[] params = updateData;
		jdbcTemplate.update(sql, params);
	}
	
	

}
