package BaseSetup;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseSetup {
	private WebDriver driver;
	
	public WebDriver getDriver() {
		return driver;
	}
	
	private void setWebDriver(String browserType) {
		switch (browserType) {
		case "chrome":
			driver = new ChromeDriver();
			break;
		case "firefox":
			driver = new FirefoxDriver();
			break;
		case "edge":
			driver = new EdgeDriver();
			break;
		default:
			System.out.println("Invalid type of " +browserType);
			break;
		}
	}
	
	public void NavigateToWebsite() {
		setWebDriver("chrome");
		
		driver.manage().window().maximize();
		
		driver.get("https://opensource-demo.orangehrmlive.com/");
		
		String PageName = driver.getTitle();
		
		System.out.print("I am at " +PageName);
	}
	
	public void Login() throws InterruptedException {
		NavigateToWebsite();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Username']")));
		
		WebElement usernameField = driver.findElement(By.name("username"));
		usernameField.sendKeys("Admin");
		
		WebElement passwordField = driver.findElement(By.name("password"));
		passwordField.sendKeys("admin123");
				
		WebElement loginButton = driver.findElement(By.xpath("//button[@type='submit']"));
		loginButton.click();
		
		//WebElement loginAlert = driver.findElement(By.xpath("//p[text()='Invalid credentials']"));
		if (driver.findElements(By.xpath("//p[text()='Invalid credentials']")).size()!=0) {
			
		} else {
			//Get profile name
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//span[@class='oxd-userdropdown-tab']")));
			
			WebElement profile = driver.findElement(By.xpath("//span[@class='oxd-userdropdown-tab']/p[@class='oxd-userdropdown-name']"));
			String profileName = profile.getText();
			System.out.println("You are accessing in a profile name " +profileName);
			
			Thread.sleep(3000);
		}
	}
	
	public void GetProfileName() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Username']")));
		
		WebElement usernameField = driver.findElement(By.name("username"));
		usernameField.sendKeys("Admin");
		
		WebElement passwordField = driver.findElement(By.name("password"));
		passwordField.sendKeys("admin123");
				
		WebElement loginButton = driver.findElement(By.xpath("//button[@type='submit']"));
		loginButton.click();
		//WebElement loginAlert = driver.findElement(By.xpath("//p[text()='Invalid credentials']"));
		if (driver.findElements(By.xpath("//p[text()='Invalid credentials']")).size()!=0) {
			//Assert.fail("Cannot login");
		} else {
			//Get profile name
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//span[@class='oxd-userdropdown-tab']")));
			
			WebElement profile = driver.findElement(By.xpath("//span[@class='oxd-userdropdown-tab']/p[@class='oxd-userdropdown-name']"));
			String profileName = profile.getText();
			System.out.println("You are accessing in a profile name " +profileName);
			
			Thread.sleep(3000);
		}
	}
	
	public void GetToAdminPage() throws InterruptedException {
		//Login step
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Username']")));
				
				WebElement usernameField = driver.findElement(By.xpath("//input[@placeholder='Username']"));
				usernameField.sendKeys("Admin123");
				
				WebElement passwordField = driver.findElement(By.name("password"));
				passwordField.sendKeys("admin123");
						
				WebElement loginButton = driver.findElement(By.xpath("//button[@type='submit']"));
				loginButton.click();
				//WebElement loginAlert = driver.findElement(By.xpath("//p[text()='Invalid credentials']"));
				if (driver.findElements(By.xpath("//p[text()='Invalid credentials']")).size()!=0) {
					//Assert.fail("Cannot login");
				} else {
					//Verify the profile name
					wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//span[@class='oxd-userdropdown-tab']")));
					
					WebElement profile = driver.findElement(By.xpath("//span[@class='oxd-userdropdown-tab']/p[@class='oxd-userdropdown-name']"));
					String profileName = profile.getText();
					System.out.println("You are accessing in a profile name " +profileName);
					
					Thread.sleep(3000);
				}
				
				//Go to Admin page
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='oxd-main-menu-item' and @href='/web/index.php/admin/viewAdminModule']")));
				
				WebElement AdminPage = driver.findElement(By.xpath("//a[@class='oxd-main-menu-item' and @href='/web/index.php/admin/viewAdminModule']"));
				AdminPage.click();
				
				String AdminBreadcrumb = driver.findElement(By.xpath("//h6[@class='oxd-text oxd-text--h6 oxd-topbar-header-breadcrumb-module']")).getText();
				//Assert.assertEquals(AdminBreadcrumb, "Admin");
				
				System.out.println("You are at " +AdminBreadcrumb+ " page");
				
				Thread.sleep(3000);
	}
}
