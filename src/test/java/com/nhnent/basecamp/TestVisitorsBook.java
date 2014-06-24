package com.nhnent.basecamp;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestVisitorsBook {
	
	@Test
	public void testFail(){
		assertFalse(false);
	}
	
	@Test
	public void testGetVisitorsBook(){
		int id = 1;
		VisitorBook visitorBook = VisitorBookDao.get(id);
		assertEquals(1, visitorBook.getId());
		assertEquals("손창원", visitorBook.getName());
		assertEquals("1234", visitorBook.getPassword());
		assertEquals("test", visitorBook.getContent());
	}

}
