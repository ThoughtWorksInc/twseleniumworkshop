package twseleniumworkshop;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class Exercicio1 {
    WebDriver driver;

    @Before
    public void setup() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @After
    public void tearDown() {
        driver.close();
    }

    @Test
    public void realizaBusca() {
        driver.get("http://www.google.com");

        WebElement searchInput = driver.findElement(By.id("gbqfq"));
        searchInput.sendKeys("Pão de Queijo Mineiro");

        WebElement submitButton = driver.findElement(By.id("gbqfb"));
        submitButton.click();

        WebElement link = driver.findElement(By.linkText("Pão de Queijo Mineiro"));
        assertThat(link.isDisplayed(), equalTo(Boolean.TRUE));
    }
}
