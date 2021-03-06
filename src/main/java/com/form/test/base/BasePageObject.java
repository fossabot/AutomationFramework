package com.form.test.base;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.log4testng.Logger;

public class BasePageObject<T> {
	protected WebDriver driver;
	protected WebDriverWait wait;
	protected static Logger log;
	
	@SuppressWarnings("static-access")
	protected BasePageObject(WebDriver driver, Logger log){
		this.driver = driver;
		this.log = log;
		wait = new WebDriverWait (driver,40);
	}
	
	@SuppressWarnings("unchecked")
	protected T getPage(String url){
		driver.get(url);
		return (T) this;
	}
	
	protected void type(String text, By element){
		find(element).sendKeys(text);
	}
	
	protected void clickBy(By element){
		find(element).click();
	}

	private WebElement find(By element) {
		return driver.findElement(element);
	}
	
	protected void waitForVisibilityOf(By locator, Integer... timeOutInSecconds) {
		int attempts = 0;
		while (attempts <2) {
			try {
				waitFor (ExpectedConditions.visibilityOfElementLocated(locator), (timeOutInSecconds.length >0 ? timeOutInSecconds[0] : null));
				break;
			}
			catch (StaleElementReferenceException e){
				}
			attempts++;
		}
	}
	
	private void waitFor (ExpectedCondition<WebElement> condition, Integer timeOutInSeconds){
		timeOutInSeconds = timeOutInSeconds != null ? timeOutInSeconds :30;
		WebDriverWait wait = new WebDriverWait (driver, timeOutInSeconds);
		wait.until(condition);
	}
	
	public String getTitle() {
		return driver.getTitle();
	}
	
	protected String getText(By element) {
		return find(element).getText();
	}

	
}
