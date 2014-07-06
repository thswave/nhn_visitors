package com.nhnent.basecamp.service;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;

import com.nhnent.basecamp.vo.VisitorBook;

@MapperScan
public interface VisitorBookService {

	public abstract List<VisitorBook> selectAll();
	public abstract VisitorBook selectById(final int id);
}