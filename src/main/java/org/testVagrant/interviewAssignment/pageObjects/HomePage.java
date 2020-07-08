package org.testVagrant.interviewAssignment.pageObjects;

import org.openqa.selenium.support.PageFactory;


public class HomePage extends MasterPage {
	

	
	public HomePage initializeElements() {
		PageFactory.initElements(MasterPage.driver, this);
		return this;
	}

	
}
