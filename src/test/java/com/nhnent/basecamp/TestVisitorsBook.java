package com.nhnent.basecamp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.text.SimpleDateFormat;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class TestVisitorsBook {
	
	
	private VisitorBookDao visitorBookDao;

	@Before
	public void setUp(){
		ApplicationContext context = new GenericXmlApplicationContext("daoFactory.xml");
		visitorBookDao = context.getBean("visitorBookDao",VisitorBookDao.class);
	}
	
	@Test
	public void testFail(){
		assertFalse(false);
	}
	
	@Test
	public void testGetVisitorsBook(){
		int id = 1;
		VisitorBook visitorBook = visitorBookDao.get(id);
		assertEquals(1, visitorBook.getId());
		assertEquals("손창원", visitorBook.getName());
		assertEquals("1234", visitorBook.getPassword());
		assertEquals("test", visitorBook.getContent());
		assertEquals("test@nhnent.com", visitorBook.getEmail());
	}
	
	@Test
	public void testAddVisitorsBook(){
		VisitorBook visitorBook = new VisitorBook();
		String name = "name_test";
		String password = "1111";
		String email = "test@nhnent.com";
		String content = "content_test";
		
		visitorBook.setName(name);
		visitorBook.setPassword(password);
		visitorBook.setContent(content);
		visitorBook.setEmail(email);
		
		visitorBookDao.add(visitorBook);
		VisitorBook addedVisitorBook = visitorBookDao.getLastIdVisitorBook();
		
		
		assertEquals(name, addedVisitorBook.getName());
		assertEquals(password, addedVisitorBook.getPassword());
		assertEquals(content, addedVisitorBook.getContent());
		assertEquals(email, addedVisitorBook.getEmail());
		
	}
	

}
