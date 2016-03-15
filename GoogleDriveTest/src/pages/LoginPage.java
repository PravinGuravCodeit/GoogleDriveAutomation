package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
	
	WebDriver driver;
	
	By emailId = By.id("Email"); // locater for email/username field
	 
    By passWord = By.id("Passwd"); // locater for password field
 
    By nextButton =By.id("next"); // locater for next button after entering email/username field
 
    By signButton = By.id("signIn"); // locater for Signin button after entering password field
    
    By invalidEmailIdErrMsg =By.id("errormsg_0_Email"); // locater for the error message after entering invalid email id field
    
    By invalidPasswordErrMsg = By.id("errormsg_0_Passwd"); // locater for the error message after entering invalid password id field
	
    //constructor for LoginPage class
    public LoginPage(WebDriver driver){
    
     this.driver=driver;
     
    }
    
    
    //Set EmailId/Username
    public void setEmailId(String UseremailId){
    	
    	driver.findElement(emailId).sendKeys(UseremailId);
    	
    }
    
    //to click on next button after entering the emailid
public void clickNext(){
    	
    	driver.findElement(nextButton).click();
    	
    }
//Set Password
public void setPassword(String userPassword){
    	
    	driver.findElement(passWord).sendKeys(userPassword);
    	
    }

//to click on signin button after entering the password
public void clickSignbutton(){
  	
  	driver.findElement(signButton).click();
  	
  }

// When the user enter in valid Email Id
public String invalidEmailIDErrmsg(){
	
	return driver.findElement(invalidEmailIdErrMsg).getText();
	
}

// When User enter invalid Password
public String invalidPasswordErrmsg(){
	
	return driver.findElement(invalidPasswordErrMsg).getText();
	
}

public void clearEmailId(){
	
	driver.findElement(emailId).clear();;
	
}

public void clearPassword(){
	
	driver.findElement(passWord).clear();;
	
}
    
    public void sleep(){
    	try{
    		Thread.sleep(5000);
    	}catch(Exception e){}
    }
    
}
