package com.nhnent.basecamp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

import java.text.SimpleDateFormat;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class TestVisitorsBook {
	
	
	private VisitorBookDao visitorBookDao;
	private VisitorBook testVisitorBook;

	@Before
	public void setUp(){
		ApplicationContext context = new GenericXmlApplicationContext("daoFactory.xml");
		visitorBookDao = context.getBean("visitorBookDao",VisitorBookDao.class);
		
		testVisitorBook = new VisitorBook();
		testVisitorBook.setName("name_test");
		testVisitorBook.setPassword("1111");
		testVisitorBook.setContent("content_test");
		testVisitorBook.setEmail("test@nhnent.com");
	}
	
	@Test
	public void testFail(){
		assertFalse(false);
	}
	
	@Test
	public void testGet(){
		int id = 1;
		VisitorBook visitorBook = visitorBookDao.get(id);
		assertEquals(1, visitorBook.getId());
		assertEquals("손창원", visitorBook.getName());
		assertEquals("1234", visitorBook.getPassword());
		assertEquals("test", visitorBook.getContent());
		assertEquals("test@nhnent.com", visitorBook.getEmail());
		assertEquals("2014-06-24 18:02:25", visitorBook.getCreated_at());
	}
	
	@Test
	public void testAdd(){
		
		visitorBookDao.add(testVisitorBook);
		int lastInsertedVisitorBookId = visitorBookDao.getLastInsertedVisitorBookId();
		VisitorBook addedVisitorBook = visitorBookDao.get(lastInsertedVisitorBookId);
		
		assertEquals(testVisitorBook.getName(), addedVisitorBook.getName());
		assertEquals(testVisitorBook.getPassword(), addedVisitorBook.getPassword());
		assertEquals(testVisitorBook.getContent(), addedVisitorBook.getContent());
		assertEquals(testVisitorBook.getEmail(), addedVisitorBook.getEmail());
		
	}
	
	@Test
	public void testDelete(){
		visitorBookDao.add(testVisitorBook);
		int lastInsertedVisitorBookId = visitorBookDao.getLastInsertedVisitorBookId();
		visitorBookDao.delete(lastInsertedVisitorBookId);
		
		assertNull(visitorBookDao.get(lastInsertedVisitorBookId));
	}
	
	@Test
	public void testUpdate(){
		
	}
	
}
