package com.ietsting;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class IEBRowserTesting {
	public static WebDriver driver;
public static void main(String[] args) {
	System.setProperty("webdriver.ie.driver", "./IE/IEDriverServer.exe");
	DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
	caps.setCapability("ignoreZoomSetting", true);
	caps.setCapability(InternetExplorerDriver.NATIVE_EVENTS, true);
	caps.setCapability("unexpectedAlertBehaviour", "accept");
	caps.setCapability("ignoreProtectedModeSettings", true);
	caps.setCapability("disable-popup-blocking", true);
	caps.setCapability("enablePersistentHover", true);
	driver = new InternetExplorerDriver(caps);
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	driver.get("https://demo.actitime.com");
	driver.findElement(By.id("username")).sendKeys("admin");
	driver.findElement(By.name("pwd")).sendKeys("manager", Keys.ENTER);
	driver.quit();
	/*JavascriptExecutor js = (JavascriptExecutor)driver;
	js.executeScript("document.getElementById('username').value='admin';");*/
}
public static DesiredCapabilities setCapForIE(DesiredCapabilities caps) {
	try {
		caps.setCapability("ignoreZoomSetting", true);
		caps.setCapability(InternetExplorerDriver.NATIVE_EVENTS, true);
		caps.setCapability("unexpectedAlertBehaviour", "accept");
		caps.setCapability("ignoreProtectedModeSettings", true);
		caps.setCapability("disable-popup-blocking", true);
		caps.setCapability("enablePersistentHover", true);
	} catch (Exception e) {
		e.printStackTrace();
	}
	return caps;
}
}
