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
	
	@Override
	public void add(VisitorBook visitorBook) {
		sqlSession.insert("VisitorBook.insertVisitorBook", visitorBook);
	}
	
	@Override
	public void update(VisitorBook updateVisitorBook) {
		sqlSession.update("VisitorBook.updateVisitorBook", updateVisitorBook);
	}
	
	@Override
	public int getLastAddedVisitorBookId() {
		return sqlSession.selectOne("VisitorBook.getLastAddedVisitorBookId");
	}

	@Override
	public void deleteById(int id) {
		sqlSession.delete("VisitorBook.deleteVisitorBookById", id);
	}


}
