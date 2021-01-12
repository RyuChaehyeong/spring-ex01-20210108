package org.zerock.domain;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.beans.PropertyChangeListener;
import java.beans.PropertyEditor;
import java.beans.PropertyEditorSupport;

public class CustomerBookEditor extends PropertyEditorSupport {
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		//text: java-spring
		
		int dash = text.indexOf("-");
		String title = text.substring(0, dash);
		String writer = text.substring(dash + 1);
		
		Book book = new Book();
		book.setTitle(title);
		book.setWriter(writer);
		
		setValue(book);
	}
}
