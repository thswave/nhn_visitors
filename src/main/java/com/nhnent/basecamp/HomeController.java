package com.nhnent.basecamp;

import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.nhnent.basecamp.dao.VisitorBookDao;
import com.nhnent.basecamp.model.VisitorBook;

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	public VisitorBookDao visitorBookDao;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home(Locale locale, Model model) {
		List<VisitorBook> visitorBookList = visitorBookDao.findAll();
		
		model.addAttribute("visitorBookList", visitorBookList );
		ModelAndView mav = new ModelAndView("home");
		return mav;
	}

	public VisitorBookDao getVisitorBookDao() {
		return visitorBookDao;
	}

	public void setVisitorBookDao(VisitorBookDao visitorBookDao) {
		this.visitorBookDao = visitorBookDao;
	}
	
}
