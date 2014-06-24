package com.nhnent.basecamp;

public class VisitorBookDao {

	public static VisitorBook get(int id) {
		VisitorBook visitorBook = new VisitorBook();
		visitorBook.setId(1);
		visitorBook.setName("손창원");
		visitorBook.setPassword("1234");
		visitorBook.setContent("test");
		return visitorBook;
	}

}
