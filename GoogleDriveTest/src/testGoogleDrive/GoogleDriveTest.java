package testGoogleDrive;

import org.testng.annotations.Test;

import pages.HomePage;
import pages.LoginPage;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Sleeper;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class GoogleDriveTest {

	public WebDriver driver;
	String baseurl;
	LoginPage loginpage;
	HomePage homepage;

	@BeforeTest
	@Parameters("browser")//Browser parameter is passed to test on multiple browser. Setup method create instance of various browser
	public void setup(String browser) throws Exception{
		baseurl = "https://accounts.google.com/ServiceLogin?service=wise&passive=1209600&continue=https://drive.google.com/%23&followup=https://drive.google.com/&ltmpl=drive&emr=1#identifier";

		//Test in firefox browser
		if(browser.equalsIgnoreCase("firefox")){

			//create firefox instance

			driver = new FirefoxDriver();

		}
		//Check if parameter passed as 'chrome'

		else if(browser.equalsIgnoreCase("chrome")){

			//set path to chromedriver.exe You may need to download it from http://code.google.com/p/selenium/wiki/ChromeDriver

			System.setProperty("webdriver.chrome.driver","chromedriver.exe");

			//create chrome instance

			driver = new ChromeDriver();

		}

		else if(browser.equalsIgnoreCase("ie")){

			//set path to IEdriver.exe You may need to download it from

			// 32 bits http://selenium-release.storage.googleapis.com/2.42/IEDriverServer_Win32_2.42.0.zip

			// 64 bits http://selenium-release.storage.googleapis.com/2.42/IEDriverServer_x64_2.42.0.zip

			System.setProperty("webdriver.ie.driver","IEdriverServer.exe");

			//create chrome instance

			driver = new InternetExplorerDriver();

		}

		else{

			//If no browser passed throw exception

			throw new Exception("Browser is not correct");

		}

		loginpage = new LoginPage(driver);//object of loginPage class
		homepage = new HomePage(driver);

		driver.get(baseurl);

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

	}
	
//To test when user enters invalid emailid
	@Test(priority=1)
	public void VerifyInvalidEmailId() {
		String Expectederrmsg = "Sorry, Google doesn't recognize that email.";
		loginpage.setEmailId("test");	
		loginpage.clickNext();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		loginpage.sleep();
		String Actualerrmsg = loginpage.invalidEmailIDErrmsg();
		System.out.println("Message  "+Actualerrmsg);
		Assert.assertEquals(Actualerrmsg, Expectederrmsg);


		// driver.quit();
	}
	
	//to Test when user enters invalid password
	@Test(priority=2)
	public void VerifyInvalidPassword(){
		String Expectederrmsg = "The email and password you entered don't match.";
		loginpage.clearEmailId();
		loginpage.setEmailId("testdrive31051990");		
		loginpage.clickNext();
		loginpage.setPassword("avf");
		loginpage.clickSignbutton();	  
		String Actualerrmsg = loginpage.invalidPasswordErrmsg();
		Assert.assertEquals(Actualerrmsg, Expectederrmsg);

	}
	
	//To test when user enters valid email amd password.
	@Test(priority=3)
	public void ValidUserIDPassword(){
		loginpage.clearPassword();
		
		loginpage.setPassword("Testing@123");
		loginpage.sleep();
		loginpage.clickSignbutton();	  

	}
// to test if the file is successfully uploaded
	@Test(priority=4)
	public void FilePressenceTest(){
		String toUploadFileName = "Testing.txt";
		homepage.createNewFile(toUploadFileName);// create a new file
		System.out.println("The file is created");
		loginpage.sleep();
		driver.navigate().refresh();
		loginpage.sleep();
		driver.navigate().refresh();
		boolean FileName= homepage.FileToSearch(toUploadFileName);
		Assert.assertTrue(FileName);
		System.out.println("The File Name is "+FileName);
	}
	
//	To test whether the rename is successfull 
	@Test(priority=5)
	public void FileRenameTest(){
		String toUploadFileName = "Testing555.txt";
		String NewFileName = "Testing123.txt";
		homepage.createNewFile(toUploadFileName);// create a new file
		driver.navigate().refresh();
		loginpage.sleep();
		driver.navigate().refresh();
		boolean FileName= homepage.FileToSearch(toUploadFileName);
		Assert.assertTrue(FileName);
		homepage.renameFile(toUploadFileName, NewFileName);//rename the file created above
		loginpage.sleep();
		driver.navigate().refresh();
		boolean renamedFileName= homepage.FileToSearch(NewFileName);
		Assert.assertTrue(renamedFileName);
		System.out.println("The File Name is "+FileName);
	}
	
	// Test for no internet connection
	@Test(priority=5)
	public void NoInternetConnection() {
		
		String Message= homepage.NoInternetConnection();
		System.out.println("The message Is "+Message);

	}

	//@AfterTest
	//public void terminateBrowser(){
	//	
	//	driver.quit();
	//}

}
