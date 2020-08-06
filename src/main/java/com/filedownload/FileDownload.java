package com.filedownload;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class FileDownload {
	public static WebDriver driver;
	private static String downloadPath = "C:\\Users\\Preety\\Desktop\\Oxford\\SeleniumDownloads";
	public static String fileName;
	public static String filePath = "C:\\Users\\Preety\\Desktop\\Oxford\\Trail2.xlsx";
	
	public static void main(String[] args) {
		try {
			System.setProperty("webdriver.chrome.driver", "./Drivers/chromedriver.exe");
			driver = new ChromeDriver(chromeProfile(downloadPath));
			driver.get("http://localhost:9997/index.html#/login");
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.findElement(By.id("login-email")).sendKeys("raghu.r@testyantra.in");
			driver.findElement(By.id("login-password")).sendKeys("Admin@5ty", Keys.ENTER);
			selectProjectByName("OX_Web");
			//driver.switchTo().frame("Manage Test Data");
			driver.findElement(By.linkText("Manage Test Data")).click();
			WebElement wb = driver.findElement(By.xpath("//td/descendant::span[text()='WorkBooks']"));
			driver.findElement(By.xpath("//tbody/descendant::span[text()='WorkBooks']")).click();;
			Actions ac = new Actions(driver);
			ac.doubleClick(wb).perform();
			System.out.println("Double clicked");
			
			WebElement fileNameElement = driver.findElement(By.linkText("Book1"));
			fileNameElement.click();
			Thread.sleep(5000);
			fileName=fileNameElement.getText();
			boolean file = isFileDownloaded(downloadPath, fileName+".xlsx");
			System.out.println(file);
			
			File lastModifiedFile=getLastModifiedFile(downloadPath);
			System.out.println(lastModifiedFile);
			
			/*try {
				driver.findElement(By.xpath("//button[text()='Upload files']")).click();
				Thread.sleep(2000);
				driver.findElement(By.xpath("//h4[text()='Please select the files']/../..//input[@type='file']")).sendKeys(filePath);
				JavascriptExecutor executor = (JavascriptExecutor)driver;
				executor.executeScript("arguments[0].click();", upload);
				//System.out.println("Text ->"+str);
				uploadFile(filePath);
				driver.findElement(By.xpath("//button[.='Submit']")).click();
				
			} catch (Exception e) {
				e.printStackTrace();
			}*/
			
			//driver.quit();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		driver.quit();

	}

	public static ChromeOptions chromeProfile(String downloadPath) {
		ChromeOptions options = null;
		try {
			Map<String, Object> prefsMap = new HashMap<String, Object>();
			prefsMap.put("profile.default_content_settings.popups", 0);
			prefsMap.put("download.default_directory", downloadPath);
			options = new ChromeOptions();
			options.setExperimentalOption("prefs", prefsMap);
			options.addArguments("--test-type");
			options.addArguments("--disable-extensions");
		} catch (Exception e) {
			e.printStackTrace();
		}


		return options;


	}
	public static File directory = new File(downloadPath);
	
	
	public static boolean isFileDownloaded(String downloadPath, String fileName) {
		boolean flag = false;
		try {
			File[] listFiles = directory.listFiles();
			if (listFiles.length==0 || listFiles==null) {
				System.out.println("The Directory is empty");
			}
			else {
				for (File listFile : listFiles) {
	                if (listFile.getName().equals(fileName)) {
	                    System.out.println(fileName + " is present");
	                    flag = true;
	                    break;
	                }
	            }
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	public static File getLastModifiedFile(String downloadPath) {
		File lastModifiedFile = null;
		try {
			File directory = new File(downloadPath);
			File[] listOfFiles = directory.listFiles();
			if (listOfFiles.length==0 || listOfFiles==null) {
				System.out.println("The Directory is empty");
			}
			lastModifiedFile = listOfFiles[0];
			for (int i = 1; i < listOfFiles.length; i++) {
				if (lastModifiedFile.lastModified()<listOfFiles[i].lastModified()) {
					lastModifiedFile=listOfFiles[i];
					System.out.println(lastModifiedFile);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lastModifiedFile;
	}
	
	public static void selectProjectByName(String projectName) {
		Select sc = new Select(driver.findElement(By.id("userProject")));
		sc.selectByVisibleText(projectName);
	}
	
	public static void uploadFile(String filePath) {
		try {
			StringSelection stringSelection = new StringSelection(filePath);
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
			Robot r = new Robot();
			Thread.sleep(4000);
			r.keyPress(KeyEvent.VK_CONTROL);
		    r.keyPress(KeyEvent.VK_V);
		    r.keyRelease(KeyEvent.VK_V);
		    r.keyRelease(KeyEvent.VK_CONTROL);
		    r.delay(1000);
		    r.keyPress(KeyEvent.VK_ENTER);
			r.keyRelease(KeyEvent.VK_ENTER);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
