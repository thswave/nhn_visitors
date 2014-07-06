package com.nhnent.basecamp.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nhnent.basecamp.vo.VisitorBook;

@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class VisitorBookServiceImp implements VisitorBookService {
	
	@Autowired
	public SqlSession sqlSession;

	@Override
	public VisitorBook selectById(final int id) {
		return sqlSession.selectOne("VisitorBook.selectVisitorBookById",id);
	}

	@Override
	public List<VisitorBook> selectAll(){
		return sqlSession.selectList("VisitorBook.selectAllVisitorBook");
	}
	
//	public void add(VisitorBook visitorBook) {
//		final String sql = "INSERT INTO "
//				+ "guest_book( name, password, email, content, created_at, updated_at) "
//				+ "values(?, ?, ?, ?, current_timestamp, current_timestamp)";
//		Object[] params = new String[] { visitorBook.getName(),
//				visitorBook.getPassword(), visitorBook.getEmail(),
//				visitorBook.getContent(), };
//		jdbcTemplate.update(sql, params);
//	}
//
//	public int getLastAddedVisitorBookId() {
//		String sql = "SELECT MAX(id) from guest_book";
//		return jdbcTemplate.queryForObject(sql, Integer.class);
//	}
//
//	public void deleteById(int id) {
//		final String sql = "DELETE FROM guest_book WHERE id = ?";
//		Object[] params = new Integer[] { id };
//		jdbcTemplate.update(sql, params);
//		
//	}
//
//	public void update(Object[] updateData) {
//		final String sql = "UPDATE guest_book SET name = ?, email = ?, content = ?, password = ?,"
//				+ " updated_at = current_timestamp  WHERE id = ?";
//		Object[] params = updateData;
//		jdbcTemplate.update(sql, params);
//	}

}
