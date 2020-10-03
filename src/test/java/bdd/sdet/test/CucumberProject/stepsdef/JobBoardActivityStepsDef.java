package bdd.sdet.test.CucumberProject.stepsdef;

import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class JobBoardActivityStepsDef {

	WebDriver driver;
	WebDriverWait wait;
	Select select;
	
	/************* SearchForJobsUsingXPathStepsDef ******************/
	
	@Given("^User on the Jobs page$")
	public void UserOnJobsPage() {

		//Setup instances
		driver = new ChromeDriver();
		wait = new WebDriverWait(driver, 10);

		driver.manage().window().maximize();
		//Open browser
		driver.get("https://alchemy.hguy.co/jobs/");
	}
	@When("^User types in keywords for jobs and change job type$")
	public void enterKeywordsForJobAndChangeJobType() throws InterruptedException {
		driver.findElement(By.xpath("//*[@id=\"menu-item-24\"]/a")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.MILLISECONDS);
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("search_keywords")));
		Thread.sleep(1000);
		
		driver.findElement(By.id("search_keywords")).sendKeys("QA");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.MILLISECONDS);
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"post-7\"]/div/div/form/div[1]/div[4]/input")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.MILLISECONDS);
		Thread.sleep(1000);
		//change job type by unchecking freelance
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"job_type_freelance\"]")));
		//driver.findElement(By.xpath("//*[@id=\"job_type_freelance\"]")).click();
		Thread.sleep(1000);
		
	}

	@Then("^User filters the job type to show only Full Time jobs$")
	public void filterJobToShowFullTimeJobs() throws InterruptedException {
		//wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\\\"job_type_internship\\\"]")));
		driver.findElement(By.xpath("//*[@id=\"job_type_internship\"]")).click();
		Thread.sleep(1000);
		//wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\\\"job_type_part-time\\\"]")));
		driver.findElement(By.xpath("//*[@id=\"job_type_part-time\"]")).click();
		Thread.sleep(1000);
		//wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\\\"job_type_temporary\\")));
		driver.findElement(By.xpath("//*[@id=\"job_type_temporary\"]")).click();
		
		
		
	}

	@Then("^User clicks on a job from the list to see job details$")
	public void findJobDetails() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.MILLISECONDS);
		driver.findElement(By.cssSelector("ul.job_listings >  li > a[href")).click();
		//see job details
		assertTrue(driver.findElement(By.cssSelector("div.single_job_listing")).isDisplayed());
		
	}
	@Then("^User finds the title of the job listing and prints it to console$")
	public void findJobTitleAndPrints() {
		String jobTitle = driver.findElement(By.cssSelector("h1.entry-title")).getText();
		System.out.println("Job title : "+jobTitle);
	}

	@Then("^User clicks on Apply for job$")
	public void applyForJob() {
		driver.findElement(By.xpath("//*[@id=\"post-1665\"]/div/div/div/div[3]/input")).click();
		driver.close();
	}
	
	
	/************* PostingJobUsingParameterizationStepsDef ******************/

	@Given("^User is on Job page$") 
	public void test() {

		//Setup instances 
		driver = new ChromeDriver(); wait = new
				WebDriverWait(driver, 10);

		driver.manage().window().maximize(); //Open browser
		driver.get("https://alchemy.hguy.co/jobs/"); 
	}

	@When("^User goes to Post page$") 
	public void navigateToPostJobs(){ 
		//wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("ul#primary-menu"))); driver.manage().timeouts().implicitlyWait(10,TimeUnit.MILLISECONDS);
		driver.findElement(By.xpath("//*[@id=\"menu-item-26\"]/a")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.MILLISECONDS);
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("search_keywords"))); 
	}

	@When("^User enters \"(.*)\" and \"(.*)\" and \"(.*)\" and selects \"(.*)\" and \"(.*)\" and \"(.*)\" and \"(.*)\"$")
	public void enterDetails(String email,String jobTitle,String location,String jobType,String desc,String application,String companyName){
		driver.findElement(By.id("create_account_email")).sendKeys(email+getRandomNumber()+"@test.com");
		driver.findElement(By.id("job_title")).sendKeys(jobTitle);
		driver.findElement(By.id("job_location")).sendKeys(location);
		select = new Select(driver.findElement(By.id("job_type")));
		select.selectByVisibleText(jobType);
		driver.findElement(By.id("mceu_16")).sendKeys(desc);
		driver.findElement(By.id("application")).sendKeys(application);
		driver.findElement(By.id("company_name")).sendKeys(companyName);

		driver.findElement(By.cssSelector("#submit-job-form > p > input[type='Submit'][name='save_draft']")).click();

	}

	@When("^User navigates to job page and verifies job \"(.*)\" listing on page$")
	public void verifyJobListing(String jobTitledata){
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.MILLISECONDS);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.job-manager-info a[href*='dashboard']")));
		driver.findElement(By.cssSelector("div.job-manager-info a[href*='dashboard']")).click();
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#job-manager-job-dashboard > table > tbody > tr > td.job_title")));
		String jobTitle = driver.findElement(By.cssSelector("#job-manager-job-dashboard > table > tbody > tr > td.job_title")).getText();
		Boolean result = jobTitle.contains(jobTitledata)?true:false;
		assertTrue("Job is posted",result==true);
		driver.close();
	}


	/*
	 * Generates random integers
	 */
	public static double getRandomNumber(){
		double x = Math.random();
		return x;
		/*int x = (int)(Math.random()*100);
		  return x;*/
	}
	
	/************* CreateNewUserStepsDef ******************/


	@Given("^User is on Login page$")
	public void loginPage() {
		//Setup instances
		driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
        
        driver.manage().window().maximize();
        //Open browser
        driver.get("https://alchemy.hguy.co/jobs/wp-admin");
	}

	
	@Then("^User enter \"(.*)\" and \"(.*)\" and login$")
	public void enterCredentials(String username,String password) {
		driver.findElement(By.id("user_login")).sendKeys(username);
		driver.findElement(By.id("user_pass")).sendKeys(password);
		driver.findElement(By.id("wp-submit")).click();
	}
	
	@Then("^User click on the Users link and Add New button$")
	public void navigateToNewUserDetails(){
		driver.findElement(By.xpath("//*[@id=\"menu-users\"]/a/div[3]")).click();
		driver.findElement(By.xpath("//*[@id=\"wpbody-content\"]/div[4]/a")).click();
	}
	
	@Then("User enters {string} and {string} and {string} and {string}")
	public void enterCredentials(String Email,String FirstName,String LastName,String Website){
		driver.findElement(By.id("user_login")).sendKeys("testUsername"+getRandomNumber());
		driver.findElement(By.id("email")).sendKeys(Email+getRandomNumber()+"@test.com");
		driver.findElement(By.id("first_name")).sendKeys(FirstName);
		driver.findElement(By.id("last_name")).sendKeys(LastName);
		driver.findElement(By.id("url")).sendKeys(Website);
		driver.findElement(By.id("createusersub")).click();
	}
	
	@Then("^User verify that the user is created$")
	public void verifyUserCreated(){
		String newuserVerification = driver.findElement(By.xpath("//*[@id=\"message\"]/p")).getText();
		Boolean result = newuserVerification.contains("New user created")?true:false;
		assertTrue("New user is created!", result);
		driver.close();
	}
	
	/************************* UsingExamplesTableToPostJobStepsDef *****************************/
	
	@Given("^User on the Job page$")
	public void loginToPage() {

		//Setup instances
		driver = new ChromeDriver();
		wait = new WebDriverWait(driver, 10);

		driver.manage().window().maximize();
		//Open browser
		driver.get("https://alchemy.hguy.co/jobs/");
	}
	@When("^User navigates to Post page$")
	public void goToPostJobs(){
	//	wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("ul#primary-menu")));
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.MILLISECONDS);
		driver.findElement(By.xpath("//*[@id=\"menu-item-26\"]/a")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.MILLISECONDS);
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("search_keywords")));
	}
	@When("User enters {string} and {string} and {string} and {string} and {string} and {string} and {string}")
	public void enterDetailsOnPage(String email,String jobTitle,String location,String jobType,String desc,String application,String companyName){
		driver.findElement(By.id("create_account_email")).sendKeys(email+getRandomNumber()+"@test.com");
		driver.findElement(By.id("job_title")).sendKeys(jobTitle);
		driver.findElement(By.id("job_location")).sendKeys(location);
		select = new Select(driver.findElement(By.id("job_type")));
		select.selectByVisibleText(jobType);
		driver.findElement(By.id("mceu_16")).sendKeys(desc);
		driver.findElement(By.id("application")).sendKeys(application);
		driver.findElement(By.id("company_name")).sendKeys(companyName);
		
		driver.findElement(By.cssSelector("#submit-job-form > p > input[type='Submit'][name='save_draft']")).click();
		
	}
	
	@When("User goes to job page and verifies job {string} listing on page")
	public void verifyJobListingUsingExamples(String jobTitledata){
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.MILLISECONDS);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.job-manager-info a[href*='dashboard']")));
		driver.findElement(By.cssSelector("div.job-manager-info a[href*='dashboard']")).click();
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#job-manager-job-dashboard > table > tbody > tr > td.job_title")));
		String jobTitle = driver.findElement(By.cssSelector("#job-manager-job-dashboard > table > tbody > tr > td.job_title")).getText();
		Boolean result = jobTitle.contains(jobTitledata)?true:false;
		assertTrue("Job is posted",result==true);
		driver.close();
	}
	
}
