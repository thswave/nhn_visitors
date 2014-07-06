package com.nhnent.basecamp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.nhnent.basecamp.common.EmailValidator;
import com.nhnent.basecamp.dao.VisitorBookDao;
import com.nhnent.basecamp.model.VisitorBook;

public class TestVisitorsBook {
	
	
	private VisitorBookDao visitorBookDao;
	private VisitorBook testVisitorBook;

	@SuppressWarnings("resource")
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
	public void testFind(){
		int id = 1;
		VisitorBook visitorBook = visitorBookDao.findById(id);
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
		int lastInsertedVisitorBookId = visitorBookDao.getLastAddedVisitorBookId();
		VisitorBook addedVisitorBook = visitorBookDao.findById(lastInsertedVisitorBookId);
		
		assertEquals(testVisitorBook.getName(), addedVisitorBook.getName());
		assertEquals(testVisitorBook.getPassword(), addedVisitorBook.getPassword());
		assertEquals(testVisitorBook.getContent(), addedVisitorBook.getContent());
		assertEquals(testVisitorBook.getEmail(), addedVisitorBook.getEmail());
		
	}
	
	@Test
	public void testDelete(){
		visitorBookDao.add(testVisitorBook);
		int lastInsertedVisitorBookId = visitorBookDao.getLastAddedVisitorBookId();
		visitorBookDao.deleteById(lastInsertedVisitorBookId);
		
		assertNull(visitorBookDao.findById(lastInsertedVisitorBookId));
	}
	
	@Test
	public void testUpdate(){
		visitorBookDao.add(testVisitorBook);
		int lastInsertedVisitorBookId = visitorBookDao.getLastAddedVisitorBookId();
		Object[] updateData = {"ChangeName", "ChangeEmail@change.com", "ChangeContent", "ChangePW" , lastInsertedVisitorBookId,};
		visitorBookDao.update(updateData);
		VisitorBook visitorBook = visitorBookDao.findById(lastInsertedVisitorBookId);
		assertEquals(updateData[0] , visitorBook.getName());
		assertEquals(updateData[1] , visitorBook.getEmail());
		assertEquals(updateData[2] , visitorBook.getContent());
	}
	
	@Test
	public void testEmailValidate(){
		String[] validEmailList = { "nhnent@yahoo.com",
				"nhnent-100@yahoo.com", "nhnent.100@yahoo.com",
				"nhnent111@nhnent.com", "nhnent-100@nhnent.net",
				"nhnent.100@nhnent.com.au", "nhnent@1.com",
				"nhnent@gmail.com.com", "nhnent+100@gmail.com",
				"nhnent-100@yahoo-test.com" };
		
		String[] invalidEmailList = { "nhnent", "nhnent@.com.my",
				"nhnent123@gmail.a", "nhnent123@.com", "nhnent123@.com.com",
				".nhnent@nhnent.com", "nhnent()*@gmail.com", "nhnent@%*.com",
				"nhnent..2002@gmail.com", "nhnent.@gmail.com",
				"nhnent@nhnent@gmail.com", "nhnent@gmail.com.1a" };
		
		for (String temp : validEmailList) {
			boolean valid = EmailValidator.validate(temp);
			System.out.println("Email is valid : " + temp + " , " + valid);
			assertEquals(valid, true);
		}
		
		for (String temp : invalidEmailList) {
			boolean valid = EmailValidator.validate(temp);
			System.out.println("Email is invalid : " + temp + " , " + valid);
			assertEquals(valid, false);
		}
	}
	
}
