package zengoT;

import java.io.File;
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
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class test {

	
static WebDriver driver;

//@FindBy(xpath="//div//h1")
//static WebElement HeaderHomePage;
//@FindBy(xpath="//DIV[@CLASS='elementor-image']//img[@title='Frame(1)']\n")
//static WebElement QRImg;
//@FindBy(xpath="//li[@id='menu-item-12609']//a[@class='dropdown']")
//static WebElement AssetsMenu;
//@FindBy(className="Ethereum (ETH)")
//static WebElement Ethereum;
//@FindBy(xpath="//h1[@class='elementor-heading-title elementor-size-default']")
//static WebElement HeaderEthereumPage;

  @BeforeClass(alwaysRun=true)
  public static void beforeClass()  throws InterruptedException{
	  WebDriverManager.chromedriver().setup();
	  driver=new ChromeDriver();
	  driver.manage().window().maximize();
	  driver.get("https://zengo.com/");
	  Thread.sleep(1000);
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
		  if((HeaderHomePage.equals("The Simple & Secure Crypto Wallet")) && (HomePageTitle.equals("ZenGo - Simple & Secure Crypto Wallet App"))){ 
			  System.out.println("Right page");
		  }
		  else {
			  System.out.println("Wrong page");
		  }
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
  		Thread.sleep(2000);
  		WebElement Ethereum =  driver.findElement(By.xpath("//li[@id='menu-item-13963']"));
  		actions.click(Ethereum);
  		actions.perform();
  		Thread.sleep(2000);	
  }
  
  @Test (dependsOnMethods = "test2")
  public void test3() {
	  try {
		  String HeaderEthereumPage = driver.findElement(By.xpath("//h1[@class='elementor-heading-title elementor-size-default']")).getText();
		  String EthereumPageTitle = driver.getTitle();
		  if((HeaderEthereumPage.equals("Ethereum Wallet")) && (EthereumPageTitle.contains("Ethereum"))) {
			  System.out.println("Right page");
		  }
		  else {
			  System.out.println("Wrong page");
		  }
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
