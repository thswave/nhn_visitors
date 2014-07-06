package com.nhnent.basecamp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nhnent.basecamp.common.EmailValidator;
import com.nhnent.basecamp.service.VisitorBookService;
import com.nhnent.basecamp.vo.VisitorBook;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/root-context.xml", 
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
		"file:src/main/webapp/WEB-INF/mybatis/mybatis-context.xml"})
public class TestVisitorsBook {
	
	@Autowired
	private VisitorBookService visitorBookService;
	private VisitorBook testVisitorBook;

	@Before
	public void setUp(){
//		ApplicationContext context = new GenericXmlApplicationContext("mybatis-context.xml");
//		visitorBookDao = context.getBean("visitorBookDao",VisitorBookDao.class);
		
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
		VisitorBook visitorBook = visitorBookService.selectById(id);
		assertEquals(1, visitorBook.getId());
		assertEquals("손창원", visitorBook.getName());
		assertEquals("1234", visitorBook.getPassword());
		assertEquals("test", visitorBook.getContent());
		assertEquals("test@nhnent.com", visitorBook.getEmail());
		assertEquals("2014-06-24 18:02:25", visitorBook.getCreated_at());
	}
	
	@Test
	public void testAdd(){
		
		visitorBookService.add(testVisitorBook);
		int lastInsertedVisitorBookId = visitorBookService.getLastAddedVisitorBookId();
		VisitorBook addedVisitorBook = visitorBookService.selectById(lastInsertedVisitorBookId);
		
		assertEquals(testVisitorBook.getName(), addedVisitorBook.getName());
		assertEquals(testVisitorBook.getPassword(), addedVisitorBook.getPassword());
		assertEquals(testVisitorBook.getContent(), addedVisitorBook.getContent());
		assertEquals(testVisitorBook.getEmail(), addedVisitorBook.getEmail());
		
	}
	
	@Test
	public void testDelete(){
		visitorBookService.add(testVisitorBook);
		int lastInsertedVisitorBookId = visitorBookService.getLastAddedVisitorBookId();
		visitorBookService.deleteById(lastInsertedVisitorBookId);
		
		assertNull(visitorBookService.selectById(lastInsertedVisitorBookId));
	}
	
	@Test
	public void testUpdate(){
		visitorBookService.add(testVisitorBook);
		int lastInsertedVisitorBookId = visitorBookService.getLastAddedVisitorBookId();
		testVisitorBook.setName("ChangeName");
		testVisitorBook.setPassword("ChangePW");
		testVisitorBook.setPassword("ChangeEmail@change.com");
		testVisitorBook.setContent("ChangeContent");
		testVisitorBook.setId(lastInsertedVisitorBookId);
		visitorBookService.update(testVisitorBook);
		VisitorBook visitorBook = visitorBookService.selectById(lastInsertedVisitorBookId);
		assertEquals(testVisitorBook.getName() , visitorBook.getName());
		assertEquals(testVisitorBook.getEmail() , visitorBook.getEmail());
		assertEquals(testVisitorBook.getContent() , visitorBook.getContent());
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
