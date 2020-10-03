package bdd.sdet.test.CucumberProject.stepsdef;

import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CRMActivityStepsDef {

	WebDriver driver;
	WebDriverWait wait;

	/***************************** Counting dashlets and Creating Leads ****************************************/

	@Given("^User navigate to the login page$")
	public void goTologin() {
		//Setup instances 
		driver = new ChromeDriver(); 
		wait = new WebDriverWait(driver, 10);

		driver.manage().window().maximize(); //Open browser
		driver.get("https://alchemy.hguy.co/crm/index.php?action=Login&module=Users"); 
	}

	@When("^User submit username \"(.*)\" and password \"(.*)\"$")
	public void login(String userName,String password) {
		driver.findElement(By.id("user_name")).sendKeys(userName);
		driver.findElement(By.id("username_password")).sendKeys(password);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.MILLISECONDS);
		driver.findElement(By.cssSelector("input#bigbutton")).click();
	}

	@Then("^User should be logged in$")
	public void user_should_be_logged_in() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.MILLISECONDS);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("#dashletPanel > tbody > tr:nth-child(1)")));
		assertTrue(driver.findElement(By.cssSelector("#dashletPanel > tbody > tr:nth-child(1)")).isDisplayed());
	}

	@Then("^User count the number of Dashlets on the homepage$")
	public void countNumOfDashlets() {
		List<WebElement> elem = driver.findElements(By.cssSelector("#dashletPanel > tbody > tr:nth-child(1)"));
		System.out.println(elem.size());
		//numOfDashlets = Integer.parseInt();
	}

	@Then("^User print the number and title of each Dashlet into the console$")
	public void printNumAndTitleOfEachDashlet() {
		List<WebElement> elem = driver.findElements(By.cssSelector("#dashletPanel > tbody > tr:nth-child(1)"));
		System.out.println("Number of total dashlets on the page: "+ elem.size());
		for (int i = 1; i < elem.size(); i++) {
			System.out.println(driver.findElement(By.cssSelector("#dashletPanel > tbody > tr:nth-child("+i+")")).getText());
		}	

	}

	@Then("^User navigate to Sales -> Leads -> Create Lead$")
	public void navigateToCreateLead() {
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("grouptab_0")));
		//click on Sales
		driver.findElement(By.id("grouptab_0")).click();
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("ul.dropdown-menu")));
		//click on Leads
		driver.findElement(By.cssSelector("li.topnav:nth-child(2) > span:nth-child(2) > ul:nth-child(3) > li:nth-child(5) > a:nth-child(1)")).click();
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("li.actionmenulinks:nth-child(2) > a:nth-child(1)")));
		//Click on Create Leads
		driver.findElement(By.cssSelector("li.actionmenulinks:nth-child(2) > a:nth-child(1)")).click();
	}
	@Then("^User enters \"(.*)\",\"(.*)\",\"(.*)\",\"(.*)\" to create lead accounts$")
	public void userEnterDetails(String firstName,String lastName,String email,String phone) {
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("input#first_name")));
		driver.findElement(By.cssSelector("input#first_name")).sendKeys(firstName);
		driver.findElement(By.cssSelector("input#last_name")).sendKeys(lastName);
		driver.findElement(By.cssSelector("input#Leads0emailAddress0")).sendKeys(email);
		driver.findElement(By.cssSelector("input#phone_work")).sendKeys(phone);
		driver.findElement(By.cssSelector("div.buttons:nth-child(17) > input:nth-child(1)")).click();
	}

	@Then("^user navigate to the View Leads page to see result \"(.*)$")
	public void user_navigate_to_the_view_leads_page_to_see_result(String name) {
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("span#full_name")));
		driver.findElement(By.cssSelector(".navbar-brand")).click();
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("#dashletPanel > tbody > tr:nth-child(1)")));
		System.out.println(driver.findElement(By.cssSelector("#dashlet_entire_868c0dc5-f408-88c4-f963-5db0c1314e6b > div:nth-child(2) > div:nth-child(2) > table:nth-child(1) > tbody:nth-child(2) > tr:nth-child(1)")).getText());
		driver.findElement(By.cssSelector("#dashlet_entire_868c0dc5-f408-88c4-f963-5db0c1314e6b > div:nth-child(2) > div:nth-child(2) > table:nth-child(1) > tbody:nth-child(2) > tr:nth-child(1)")).getText().contains(name);
		driver.close();
	}
	/*
	 * @Then("^User navigate to Sales -> Leads -> Create Lead$") public void
	 * navigateToCreateLead() {
	 * wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id(
	 * "grouptab_0"))); driver.findElement(By.id("grouptab_0")).click(); }
	 */

	/***************************** SchedulemeetingAndInviteMembersStepsDef ******************************************/


	@Given("User log in to website using username {string} and password {string} and validate")
	public void loginAndValidate(String userName,String password) {
		//Setup instances 
		driver = new ChromeDriver(); 
		wait = new WebDriverWait(driver, 10);

		driver.manage().window().maximize(); //Open browser
		driver.get("https://alchemy.hguy.co/crm/index.php?action=Login&module=Users"); 
		driver.findElement(By.id("user_name")).sendKeys(userName);
		driver.findElement(By.id("username_password")).sendKeys(password);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.MILLISECONDS);
		driver.findElement(By.cssSelector("input#bigbutton")).click();

		//User is logged in
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.MILLISECONDS);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("#dashletPanel > tbody > tr:nth-child(1)")));
		assertTrue(driver.findElement(By.cssSelector("#dashletPanel > tbody > tr:nth-child(1)")).isDisplayed());
	}

	@Then("^User navigates to Activities -> Meetings -> Schedule a Meeting$")
	public void navigateToScheduleAMeeting() {
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("grouptab_3")));
		//click on Activities
		driver.findElement(By.id("grouptab_3")).click();
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("ul.dropdown-menu")));
		//click on Meetings
		driver.findElement(By.cssSelector("#moduleTab_9_Meetings")).click();
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("li.actionmenulinks:nth-child(2) > a:nth-child(1) > div:nth-child(2)")));
		//Click on Schedule Meeting
		driver.findElement(By.cssSelector("li.actionmenulinks:nth-child(2) > a:nth-child(1) > div:nth-child(2)")).click();
	}

	@Then("User enters details {string} of the meeting")
	public void user_enters_details_of_the_meeting(String subject) {
		//waits for the text field to be visible
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("input#name")));
		//Enter subject
		driver.findElement(By.cssSelector("input#name")).sendKeys(subject);


	}
	@Then("User search for members and add them to the meeting")
	public void user_search_for_members_and_add_them_to_the_meeting() {
		//Clicks on the Search invitees button
		driver.findElement(By.cssSelector("input#invitees_search")).click();
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("tr.oddListRowS1:nth-child(2)")));
		driver.findElement(By.cssSelector("input#invitees_add_1")).click();
		//click save
		driver.findElement(By.cssSelector("div.buttons:nth-child(11) > input:nth-child(1)")).click();
	}
	@Then("User confirms creation of the meeting with subject {string}")
	public void user_confirms_creation_of_the_meeting(String subject) {
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("#tab-content-0 > div:nth-child(1) > div:nth-child(1) > div[class*='inlineEdit']")));
		driver.findElement(By.cssSelector("li.actionmenulinks:nth-child(3) > a:nth-child(1) > div:nth-child(2)")).click();
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("tr.oddListRowS1:nth-child(1) > td:nth-child(4) > b:nth-child(1) > a:nth-child(1)")));
		System.out.println(driver.findElement(By.cssSelector("tr.oddListRowS1:nth-child(1) > td:nth-child(4) > b:nth-child(1) > a:nth-child(1)")).getText());
		driver.findElement(By.cssSelector("tr.oddListRowS1:nth-child(1) > td:nth-child(4) > b:nth-child(1) > a:nth-child(1)")).getText().contains(subject);
	}
	@Then("ends")
	public void endBrowser() {
		driver.close();
	}

	/**************************************** CreatingAProductStepsDef ******************************************/


	@Given("User logs into CRM website using username {string} and password {string} and validate")
	public void loginToCRMAndValidate(String userName,String password) {
		//Setup instances 
		driver = new ChromeDriver(); 
		wait = new WebDriverWait(driver, 10);

		driver.manage().window().maximize(); //Open browser
		driver.get("https://alchemy.hguy.co/crm/index.php?action=Login&module=Users"); 
		driver.findElement(By.id("user_name")).sendKeys(userName);
		driver.findElement(By.id("username_password")).sendKeys(password);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.MILLISECONDS);
		driver.findElement(By.cssSelector("input#bigbutton")).click();

		//User is logged in
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.MILLISECONDS);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("#dashletPanel > tbody > tr:nth-child(1)")));
		assertTrue(driver.findElement(By.cssSelector("#dashletPanel > tbody > tr:nth-child(1)")).isDisplayed());
	}

	@Then("^User navigates to All -> Products-> Create Product$")
	public void navigateToCreateProduct() {
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("grouptab_5")));
		//click on All
		driver.findElement(By.id("grouptab_5")).click();
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("ul.dropdown-menu")));
		//click on Products
		driver.findElement(By.cssSelector("li.topnav:nth-child(7) > span:nth-child(2) > ul:nth-child(3) > li:nth-child(25) > a:nth-child(1)")).click();
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("li.actionmenulinks:nth-child(2) > a:nth-child(1) > div:nth-child(2)")));
		//Click on Create Product
		driver.findElement(By.cssSelector("li.actionmenulinks:nth-child(2) > a:nth-child(1) > div:nth-child(2)")).click();
	}

	@Then("User enters details {string} product name and price {string}")
	public void user_enters_details_of_the_meeting(String productName,String price) {
		//waits for the text field to be visible
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("input#name")));
		//Enter product name
		driver.findElement(By.cssSelector("input#name")).sendKeys(productName);
		driver.findElement(By.cssSelector("input#price")).sendKeys("100");
		//Click Save
		driver.findElement(By.cssSelector("input.button:nth-child(1)")).click();


	}

	@Then("User to the View Products page to see the product listed {string}")
	public void user_confirms_creation_of_the_product(String subject) {
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("#tab-content-0 > div:nth-child(1) > div:nth-child(1) > div[class*='inlineEdit']")));
		driver.findElement(By.cssSelector("li.actionmenulinks:nth-child(3) > a:nth-child(1) > div:nth-child(2)")).click();
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.partialLinkText("test221")));
		System.out.println(driver.findElement(By.partialLinkText("test221")).getText());
		driver.findElement(By.partialLinkText("test221")).getText().contains(subject);
	}
	@Then("end browser")
	public void ends() {
		driver.close();
	}

}
