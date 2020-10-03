package bdd.sdet.test.CucumberProject.stepsdef;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class HRMActivityStepsDef {

	String firstName;
	String lastName;
	String vacancyName;
	WebDriver driver;
	WebDriverWait wait;
	Select select;
	Random rand = new Random(); 
	
	/***************************** CreatingAVacancyStepsDef ******************************/
	
	
	@Given("^User is in the login page$") 
	public void LoginPage() {

		//Setup instances 
		driver = new ChromeDriver(); wait = new
				WebDriverWait(driver, 10);

		driver.manage().window().maximize(); //Open browser
		driver.get("http://alchemy.hguy.co:8080/orangehrm/symfony/web/index.php/auth/login"); 
	}

	@Then("^User logs in with credentials \"(.*)\",\"(.*)\"$") 
	public void LoginToHRM(String userName,String password) {
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("txtUsername")));
		driver.findElement(By.id("txtUsername")).sendKeys(userName);
		driver.findElement(By.id("txtPassword")).sendKeys(password);
		driver.findElement(By.id("btnLogin")).click();
	}
	
	@Then("^User goes to the Recruitement page$") 
	public void goToRecruitementPage() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.MILLISECONDS);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#wrapper > div.menu > ul")));
		//Navigate to recruitment page
		driver.findElement(By.cssSelector("#wrapper > div.menu > ul > li:nth-child(5) a[href*='viewRecruitment']")).click();

	}
	@Then("^User goes to the Vacancies page$") 
	public void goToVacanciesPage() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.MILLISECONDS);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"menu_recruitment_viewJobVacancy\"]")));
		//Navigate to Vacancies page
		driver.findElement(By.xpath("//*[@id=\"menu_recruitment_viewJobVacancy\"]")).click();
		driver.findElement(By.xpath("//*[@id=\"btnAdd\"]")).click();

	}
	@Then("^User selects \"(.*)\" and enters \"(.*)\",\"(.*)\",\"(.*)\",\"(.*)\"$") 
	public void enterdetailsinVacanciesPage(String jobTitle,String VacancyName,String HiringManager,String numOfPos,String desc) {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.MILLISECONDS);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("addJobVacancy_jobTitle")));
		
		//select value from dropdown 
		select = new Select(driver.findElement(By.id("addJobVacancy_jobTitle")));
		select.selectByVisibleText(jobTitle);
		
		vacancyName = VacancyName+getRandomNumber();
		//Enter details
		driver.findElement(By.id("addJobVacancy_name")).sendKeys(vacancyName);
		driver.findElement(By.id("addJobVacancy_hiringManager")).sendKeys(HiringManager);
		driver.findElement(By.id("addJobVacancy_noOfPositions")).sendKeys(numOfPos);
		
		driver.findElement(By.id("addJobVacancy_description")).sendKeys(desc);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("btnSave")));
		driver.findElement(By.id("btnSave")).click();
	}
	@Then("^User verifies that the vacancy for \"(.*)\" is created$") 
	public void verifyVacancyCreated(String jobTitle) {
		//click on back button
		wait.until(ExpectedConditions.elementToBeClickable(By.id("btnBack"))).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.MILLISECONDS);
		
		//select the job title to search
		select = new Select(driver.findElement(By.id("vacancySearch_jobTitle")));
		select.selectByVisibleText(jobTitle);
		
		System.out.println(jobTitle);
		System.out.println(vacancyName);
		
		//Search in the result table for the newly created vacancy
		driver.findElement(By.id("resultTable")).getText().contains(vacancyName);
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
	
	/******************************* AddingACandidateForRecruitementStepsDef ********************************/
	

	@Given("^User opens the HRM page and logs in with credentials \"(.*)\" and \"(.*)\"$")
	public void LogintoHRM(String userName,String password) {

		//Setup instances 
		driver = new ChromeDriver(); wait = new
				WebDriverWait(driver, 10);

		driver.manage().window().maximize(); //Open browser
		driver.get("http://alchemy.hguy.co:8080/orangehrm/symfony/web/index.php/auth/login"); 

		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("txtUsername")));
		driver.findElement(By.id("txtUsername")).sendKeys(userName);
		driver.findElement(By.id("txtPassword")).sendKeys(password);
		driver.findElement(By.id("btnLogin")).click();
	}

	@Then("^User navigates to the Recruitment page and click on the Add button to add candidate information$")
	public void navigatesToRecruitment() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.MILLISECONDS);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#wrapper > div.menu > ul")));
		//Navigate to recruitment page
		driver.findElement(By.cssSelector("#wrapper > div.menu > ul > li:nth-child(5) a[href*='viewRecruitment']")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("btnAdd"))).click();

	}

	@Then("^On the next page fill in the details of the candidate firstname \"(.*)\",last name \"(.*)\" and email \"(.*)\"$")
	public void fillDetailsCandidate(String firstName,String lastName,String email) {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.MILLISECONDS);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("frmAddCandidate")));
		firstName = firstName+rand.nextInt();

		driver.findElement(By.id("addCandidate_firstName")).sendKeys(firstName);
		lastName = lastName+rand.nextInt();
		driver.findElement(By.id("addCandidate_lastName")).sendKeys(lastName);
		driver.findElement(By.id("addCandidate_email")).sendKeys(email+rand.nextInt()+"@test.com");
	}
	@Then("User upload a resume \\(docx or pdf) to the form")
	public void uploadResume() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.MILLISECONDS);

		/*
		 * File f = new File("HRM.pdf"); // Get the absolute path of file f String
		 * absolute = f.getAbsolutePath();
		 * 
		 * // Display the file path of the file object // and also the file path of
		 * absolute file System.out.println("Original  path: " + f.getPath());
		 * System.out.println("Absolute  path: " + absolute);
		 */
		//Uploads the pdf
		driver.findElement(By.id("addCandidate_resume")).sendKeys("C:\\SDETcucumberworkspace\\CucumberProject\\src\\test\\resources\\HRM.pdf");
		driver.findElement(By.id("btnSave")).click();
	}

	@Then("^User navigate back to the Recruitments page to confirm candidate entry$")
	public void verifyCandidateEntry() {

		//click on back button
		wait.until(ExpectedConditions.elementToBeClickable(By.id("btnBack"))).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.MILLISECONDS);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("frmList_ohrmListComponent")));

		//Search in the result table for the newly created vacancy
		driver.findElement(By.id("resultTable")).getText().contains(firstName+" "+ lastName);

	}

	@Then("^Close the browser$")
	public void closeBrowser() {
		driver.close();
	}

	
	/******************************* AddMultipleEmployeesStepsDef ********************************/

	@Given("^User opens the ​OrangeHRM​ page and login with credentials \"(.*)\" and \"(.*)\" provided$")
	public void Login(String userName,String password) {

		//Setup instances 
		driver = new ChromeDriver();
		wait = new WebDriverWait(driver, 10);

		driver.manage().window().maximize(); //Open browser
		driver.get("http://alchemy.hguy.co:8080/orangehrm/symfony/web/index.php/auth/login"); 

		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("txtUsername")));
		driver.findElement(By.id("txtUsername")).sendKeys(userName);
		driver.findElement(By.id("txtPassword")).sendKeys(password);
		driver.findElement(By.id("btnLogin")).click();
	}


	@Then("^User find the PIM option in the menu and click it$")
	public void findPIMandClick() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.MILLISECONDS);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#wrapper > div.menu > ul")));

		//Navigate to PIM page
		driver.findElement(By.cssSelector("#wrapper > div.menu > ul > li:nth-child(2) a[href*='PimModule']")).click();


	}
	@Then("^User click the Add button to add a new Employee$")
	public void addNewEmployee() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.MILLISECONDS);
		Thread.sleep(1000);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.top #btnAdd"))).click();

	}
	@Then("^User make sure the “Create Login Details” checkbox is checked$")
	public void verifyCreateLoginDetailsCheckBoxSelected() {
		assertFalse("Create Login Detail checkBox is NOT selected",driver.findElement(By.id("chkLogin")).isSelected());
		driver.findElement(By.id("chkLogin")).click();

	}


	@Then("User fill in the required fields {string},{string},{string},{string} until all the employees and their accounts have been created and verify that the employee are created")
	public void enterDetails(String firstName,String lastName,String employeeID,String employeeUserName) {


		driver.findElement(By.id("firstName")).sendKeys(firstName+rand.nextInt());
		driver.findElement(By.id("lastName")).sendKeys(lastName+rand.nextInt());
		driver.findElement(By.id("employeeId")).sendKeys(employeeID+rand.nextInt());
		driver.findElement(By.id("user_name")).sendKeys(employeeUserName+rand.nextInt());

		driver.findElement(By.id("btnSave")).click();
		assertTrue(driver.findElement(By.id("employee-details")).isDisplayed());
	}

	@Then("^Close the Browser$")
	public void endBrowser() {
		driver.close();
	}
	
	/******************************* CreateMultipleVacancyStepsDef ********************************/
	

	@Given("^User on login page$") 
	public void gotoLoginPage() {

		//Setup instances 
		driver = new ChromeDriver(); wait = new
				WebDriverWait(driver, 10);

		driver.manage().window().maximize(); //Open browser
		driver.get("http://alchemy.hguy.co:8080/orangehrm/symfony/web/index.php/auth/login"); 
	}

	@Then("^User logs in with \"(.*)\",\"(.*)\"$") 
	public void LoginToHRMApp(String userName,String password) {
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("txtUsername")));
		driver.findElement(By.id("txtUsername")).sendKeys(userName);
		driver.findElement(By.id("txtPassword")).sendKeys(password);
		driver.findElement(By.id("btnLogin")).click();
	}

	@Then("^User goes to the Recruitement -- Vacancies page$") 
	public void navigateToRecruitementPage() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.MILLISECONDS);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#wrapper > div.menu > ul")));
		//Navigate to recruitment page
		driver.findElement(By.cssSelector("#wrapper > div.menu > ul > li:nth-child(5) a[href*='viewRecruitment']")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.MILLISECONDS);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"menu_recruitment_viewJobVacancy\"]")));
		//Navigate to Vacancies page
		driver.findElement(By.xpath("//*[@id=\"menu_recruitment_viewJobVacancy\"]")).click();
		driver.findElement(By.xpath("//*[@id=\"btnAdd\"]")).click();

	}

	@Then("User now selects {string} and enters {string},{string},{string},{string}") 
	public void enterdetailsInVacanciesPagefromExamples(String jobTitle,String VacancyName,String HiringManager,String numOfPos,String desc) {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.MILLISECONDS);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("addJobVacancy_jobTitle")));

		//select value from dropdown 
		select = new Select(driver.findElement(By.id("addJobVacancy_jobTitle")));
		select.selectByVisibleText(jobTitle);

		vacancyName = VacancyName+getRandomNumber();
		//Enter details
		driver.findElement(By.id("addJobVacancy_name")).sendKeys(vacancyName);
		driver.findElement(By.id("addJobVacancy_hiringManager")).sendKeys(HiringManager);
		driver.findElement(By.id("addJobVacancy_noOfPositions")).sendKeys(numOfPos);

		driver.findElement(By.id("addJobVacancy_description")).sendKeys(desc);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("btnSave")));
		driver.findElement(By.id("btnSave")).click();
	}
	@Then("User now verifies that the vacancy for {string} is created") 
	public void verifyVacanciesCreated(String jobTitle) {
		//click on back button
		wait.until(ExpectedConditions.elementToBeClickable(By.id("btnBack"))).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.MILLISECONDS);

		//select the job title to search
		select = new Select(driver.findElement(By.id("vacancySearch_jobTitle")));
		select.selectByVisibleText(jobTitle);

		System.out.println(jobTitle);
		System.out.println(vacancyName);

		//Search in the result table for the newly created vacancy
		driver.findElement(By.id("resultTable")).getText().contains(vacancyName);

	}

	@Then("^Close browser$")
	public void exit() {
		driver.close();
	}
}

