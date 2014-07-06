package com.nhnent.basecamp.service;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;

import com.nhnent.basecamp.vo.VisitorBook;

@MapperScan
public interface VisitorBookService {

	public List<VisitorBook> selectAll();
	public VisitorBook selectById(final int id);
	public int getLastAddedVisitorBookId();
	public void deleteById(int id);
	void add(VisitorBook visitorBook);
}