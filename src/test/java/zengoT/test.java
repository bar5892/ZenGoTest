package zengoT;

import java.io.File;
import java.time.Duration;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

public class test {

static WebDriver driver;

  @BeforeClass(alwaysRun=true)
  public static void beforeClass()  throws InterruptedException{
	  WebDriverManager.chromedriver().setup();
	  driver=new ChromeDriver();
	  driver.manage().window().maximize();
	  driver.get("https://zengo.com/");
	  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
  }

  @AfterClass
  public static void afterClass() {
	 driver.quit();
  }
  
  @Test
  public void test1() {
	  try {
		  String HeaderHomePage = driver.findElement(By.xpath("//div//h1")).getText();
		  String HomePageTitle = driver.getTitle();
		  Assert.assertEquals(HeaderHomePage, "The Simple & Secure Crypto Wallet");
		  Assert.assertEquals(HomePageTitle, "ZenGo - Simple & Secure Crypto Wallet App");
	  }
	  catch (Exception e) {
		  System.out.println("test 1: Error");
	  }
  }
  
  @Test
  public void test2() throws InterruptedException {
  		Actions actions = new Actions(driver);
  		WebElement AssetsMenu =  driver.findElement(By.xpath("//li[@id='menu-item-12609']//a[@class='dropdown']"));
  		actions.moveToElement(AssetsMenu).perform();
  		Thread.sleep(500);
  		WebElement Ethereum =  driver.findElement(By.xpath("//li[@id='menu-item-13963']"));
  		actions.click(Ethereum);
  		actions.perform();	
  }
  
  @Test (dependsOnMethods = "test2")
  public void test3() {
	  try {
		  String HeaderEthereumPage = driver.findElement(By.xpath("//h1[@class='elementor-heading-title elementor-size-default']")).getText();
		  String EthereumPageTitle = driver.getTitle();
		  Assert.assertEquals(HeaderEthereumPage, "Ethereum Wallet");
		  Assert.assertEquals(EthereumPageTitle, "Ethereum (ETH) Mobile Wallet - ZenGo");
	  }
	  catch (Exception e) {
		  System.out.println("test 3: Error");
	  }
  }

  @Test (dependsOnMethods = "test3")
  public void test4() {
	  try {
		  WebElement logo = driver.findElement(By.xpath("//img[@class='no-lazy-load']"));
		  File pic = logo.getScreenshotAs(OutputType.FILE);
		  FileUtils.copyFile(pic,new File("d://logo.png"));
	  }
	  catch (Exception e) {
		  System.out.println("test 4: Error");
	  }
  }
}
