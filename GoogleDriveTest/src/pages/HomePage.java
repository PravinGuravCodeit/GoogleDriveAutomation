package pages;

import java.io.File;
import java.io.FileWriter;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

	WebDriver driver;
	By FileToFind = By.className("k-ta-P-x");
	By NoNetworkMessage = By.className("a-wb-ra-s");


	public HomePage(WebDriver driver){

		this.driver=driver;

	}

	public boolean FileToSearch(String FileName){
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='"+FileName+"']")));
		return driver.findElement(By.xpath("//span[text()='"+FileName+"']")).isDisplayed();

	}

	public String NoInternetConnection() {
		return driver.findElement(NoNetworkMessage).getText();	
	}
		
	//to create and store a file on Client drive
	public void createNewFile(String sFileName){
		File f = null;

	      try{      
	         // create new File objects
	         f = new File("C:\\Users\\dell\\Google Drive\\"+sFileName); 
	         f.getParentFile().mkdirs();
	         FileWriter writer = new FileWriter(f);
	         writer.close();
	      }catch(Exception e){
	         // if any error occurs
	         e.printStackTrace();
	      }
	}
	
	//To rename the existing file
	public void renameFile(String sOldFileName, String sNewFileName){
		File f = null;
		File f1 = null;
		
	      try{      
	    	  f = new File("C:\\Users\\dell\\Google Drive\\"+sOldFileName);
	    	  f1 = new File("C:\\Users\\dell\\Google Drive\\"+sNewFileName);
	          
	          // rename file
	          f.renameTo(f1);
	          
	      }catch(Exception e){
	         // if any error occurs
	         e.printStackTrace();
	      }
	}

}
