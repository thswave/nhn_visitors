package com.nhnent.basecamp;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.nhnent.basecamp.common.EmailValidator;
import com.nhnent.basecamp.dao.VisitorBookDao;
import com.nhnent.basecamp.model.VisitorBook;

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	public VisitorBookDao visitorBookDao;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home(Model model) {
		List<VisitorBook> visitorBookList = visitorBookDao.findAll();
		
		model.addAttribute("visitorBookList", visitorBookList );
		ModelAndView mav = new ModelAndView("home");
		return mav;
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView add(HttpServletRequest request , HttpServletResponse response,
			@RequestParam(value="name",required=true, defaultValue="") String name,
			@RequestParam(value="email",required=true, defaultValue="") String email,
			@RequestParam(value="password",required=true, defaultValue="") String password,
			@RequestParam(value="content",required=true, defaultValue="") String content) {
		
		VisitorBook visitorBook = new VisitorBook();
		visitorBook.setName(name);
		visitorBook.setPassword(password);
		visitorBook.setEmail(email);
		visitorBook.setContent(content);
		
		String notify = null;
		logger.debug(name);
		logger.info(name);
		ModelAndView mav = new ModelAndView("home");
		if (EmailValidator.validate(email)){
			notify ="success";
			visitorBookDao.add(visitorBook);
		}else
			notify ="fail";
		
		mav.addObject(notify);
		List<VisitorBook> visitorBookList = visitorBookDao.findAll();
		mav.addObject(visitorBookList);
		return mav;
	}

	public VisitorBookDao getVisitorBookDao() {
		return visitorBookDao;
	}

	public void setVisitorBookDao(VisitorBookDao visitorBookDao) {
		this.visitorBookDao = visitorBookDao;
	}
	
}
