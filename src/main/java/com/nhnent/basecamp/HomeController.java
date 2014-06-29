package com.nhnent.basecamp;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nhnent.basecamp.dao.VisitorBookDao;
import com.nhnent.basecamp.model.VisitorBook;

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	public VisitorBookDao visitorBookDao;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		VisitorBook visitorBook = visitorBookDao.get(1);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		model.addAttribute("test", visitorBook.getName() );
		
		
		
		return "home";
	}

	public VisitorBookDao getVisitorBookDao() {
		return visitorBookDao;
	}

	public void setVisitorBookDao(VisitorBookDao visitorBookDao) {
		this.visitorBookDao = visitorBookDao;
	}
	
}
