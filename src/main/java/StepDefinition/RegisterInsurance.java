package StepDefinition;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.json.simple.parser.ParseException;
import java.io.FileNotFoundException;
import java.io.IOException;

import MiHCM.HomePage;
import MiHCM.LoginPage;
import MiHCM.SignIn;
import Utility.*;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class RegisterInsurance {
	
	WebDriver driver;
	
	LoginPage objLogin;
	HomePage objHomePage;
	SignIn objsignin;
	ReadExcel excel = new ReadExcel();
	
	

	
	@Given("^I navigate to Insurance Page and go Register$")
	public void i_navigate_to_Insurance_Page_and_go_Register() throws Throwable {
		System.setProperty("webdriver.chrome.driver","C:\\seldrv\\chromedriver.exe");
		
	    driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.get("http://demo.guru99.com/test/newtours/");
        
        driver.findElement(By.linkText("Insurance Project")).click();
        
        objLogin = new LoginPage(driver);
        
        objLogin.ClickRegiterButtonToRegister();
        
               
	    
	}

	@When("^I extract data from excel and submit  the user details$")
	public void i_extract_data_from_excel_and_submit_the_user_details() throws Throwable {
	
		objLogin = new LoginPage(driver);
		

		String FullOrProviInsu = (JSONReader.ReadJSONFile("FullOrProvi", "./Data/data.json")); // data reading from excel stored to string variable
		switch (FullOrProviInsu) {
	    case "Full":
	    	objLogin.FullLicenceType();
	    	 break;
	    case "Provisional":
	    	
	        // explicit wait condition
	        WebDriverWait w = new WebDriverWait(driver,15);
	        // presenceOfElementLocated condition
	        w.until(ExpectedConditions.presenceOfElementLocated (By.xpath("//input[@value='Provisional']")));
	        // get text of element and print
	      //  System.out.println("Element present");
	    	
	        objLogin.ProvisionalLicenceType();
	    	//System.out.println("Selected Provisional");
	    	break;
		}
	    objLogin.RegisterUser(JSONReader.ReadJSONFile("Title", "./Data/data.json"),JSONReader.ReadJSONFile("firstName", "./Data/data.json"),JSONReader.ReadJSONFile("Surname", "./Data/data.json"),JSONReader.ReadJSONFile("phone", "./Data/data.json"),JSONReader.ReadJSONFile("DoBYear", "./Data/data.json"),JSONReader.ReadJSONFile("DOBMonth", "./Data/data.json"),JSONReader.ReadJSONFile("DOBDate", "./Data/data.json"),JSONReader.ReadJSONFile("LicencePeriod", "./Data/data.json"),JSONReader.ReadJSONFile("Occupation", "./Data/data.json"),JSONReader.ReadJSONFile("Street", "./Data/data.json"),JSONReader.ReadJSONFile("City", "./Data/data.json"),JSONReader.ReadJSONFile("Country", "./Data/data.json"),JSONReader.ReadJSONFile("postalCode", "./Data/data.json"),JSONReader.ReadJSONFile("email", "./Data/data.json"),JSONReader.ReadJSONFile("password", "./Data/data.json"),JSONReader.ReadJSONFile("confirmPassword", "./Data/data.json"));
	    //objLogin.loginToGuru99(JSONReader.ReadJSONFile("User_Name", "./Data/data.json"), JSONReader.ReadJSONFile("Password", "./Data/data.json"));


		//objLogin.RegisterUser(excel.readExcel(1,1, ".\\Data\\data.xlsx","Sheet1"), excel.readExcel(1,2, ".\\Data\\data.xlsx","Sheet1"),excel.readExcel(1,3, ".\\Data\\data.xlsx","Sheet1"), excel.readExcel(1,4, ".\\Data\\data.xlsx","Sheet1"), excel.readExcel(1,5, ".\\Data\\data.xlsx","Sheet1"), excel.readExcel(1,6, ".\\Data\\data.xlsx","Sheet1"), excel.readExcel(1,7, ".\\Data\\data.xlsx","Sheet1"), excel.readExcel(1,9, ".\\Data\\data.xlsx","Sheet1"), excel.readExcel(1,10, ".\\Data\\data.xlsx","Sheet1"),excel.readExcel(1,11, ".\\Data\\data.xlsx","Sheet1"),excel.readExcel(1,12, ".\\Data\\data.xlsx","Sheet1"),excel.readExcel(1,13, ".\\Data\\data.xlsx","Sheet1"),excel.readExcel(1,14, ".\\Data\\data.xlsx","Sheet1"),excel.readExcel(1,15, ".\\Data\\data.xlsx","Sheet1"),excel.readExcel(1,16, ".\\Data\\data.xlsx","Sheet1"),excel.readExcel(1,17, ".\\Data\\data.xlsx","Sheet1"));
	//
	}
	


	@Then("^I should successfully registered$")
	public void i_should_successfully_registered() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		objHomePage = new HomePage(driver);

		   //Verify home page

	    Assert.assertTrue(objHomePage.getHomePageDashboardUserName().contains("Login"));
		    
	    
	}

	@Then("^Login with registered login$")
	public void login_with_registered_login() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		
		
	objsignin = new SignIn(driver);
	objsignin.SignInToPage(JSONReader.ReadJSONFile("email", "./Data/dataLogin.json"),JSONReader.ReadJSONFile("password", "./Data/dataLogin.json"));
		Assert.assertTrue(objsignin.LoginSuccessfully().contains("Broker Insurance WebPage"));
		//driver.close();
		
	   
	}

}
