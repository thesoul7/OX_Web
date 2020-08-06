package com.twofactor;

import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class TwoFactor {
	public static WebDriver driver;
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		String userName = "thesoul7";
		String password ="9900109583";
		String domainName = "localhost:443";
		System.setProperty("webdriver.ie.driver", "./Drivers/IEDriverServer.exe");
		/*
		 * FirefoxOptions firefox = new FirefoxOptions();
		 * firefox.setAcceptInsecureCerts(true); driver = new FirefoxDriver(firefox);
		 */
		/*ChromeOptions co = new ChromeOptions();
		co.setAcceptInsecureCerts(true);
		driver = new ChromeDriver(co);
		*/
		DesiredCapabilities cap  = DesiredCapabilities.internetExplorer();
		cap.setAcceptInsecureCerts(true);
		cap.setCapability(CapabilityType.BROWSER_NAME, "internet explorer");
		driver = new InternetExplorerDriver(cap);
		
		twoFactorAuthentication(driver,userName, password, domainName);
	}
	public static WebDriver twoFactorAuthentication(WebDriver driver,String userName, String password, String domainName) {
		try {
			driver.get("https://"+userName+":"+password+"@"+domainName+"");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return driver;
	}
	/*public static WebDriver openBrowser() {
		WebDriver driver = null;
		try {
			ChromeOptions co = new ChromeOptions();
			co.setAcceptInsecureCerts(true);
			driver = new ChromeDriver(co);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return driver;
	}*/
	public static DesiredCapabilities accesptInsecureCerts(DesiredCapabilities cap) {
		try {
			cap.setAcceptInsecureCerts(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return  cap;
	}
}
