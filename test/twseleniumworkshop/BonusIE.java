package twseleniumworkshop;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class BonusIE {
	private WebDriver driver;

	@Before
	public void setUp() throws Exception {
		// Choose the browser, version, and platform to test
		DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
		capabilities.setCapability("version", "8.0");
		capabilities.setCapability("platform", Platform.VISTA);
		// Create the connection to Sauce Labs to run the tests
		this.driver = new RemoteWebDriver(
				new URL("http://usuario:chavepublica@ondemand.saucelabs.com:80/wd/hub"),
				capabilities);
	}
	
	@After
	public void tearDown() {
		driver.quit();
	}
	
	@Ignore
	@Test
	public void verificaTituloAmazon() {
		driver.get("http://www.amazon.com/");
		assertThat("Amazon.com: Online Shopping for Electronics, Apparel, Computers, Books, DVDs & more", equalTo(driver.getTitle()));
	}
}

