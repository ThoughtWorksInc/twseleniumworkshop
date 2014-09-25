package twseleniumworkshop;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Ignore;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class BonusAndroid {
	private WebDriver driver;

	@Before
	 public void setUp() throws Exception {
		DesiredCapabilities capabilities = DesiredCapabilities.android();
		capabilities.setCapability("version", "4.4");
		capabilities.setCapability("platform", Platform.ANDROID);
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
		assertThat("Amazon.com", equalTo(driver.getTitle()));
	}
}

