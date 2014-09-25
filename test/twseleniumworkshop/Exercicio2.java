package twseleniumworkshop;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class Exercicio2 {
    WebDriver driver;

    @Before
    public void setup() {
        System.setProperty("webdriver", "firefox");

        if ("firefox".equals(System.getProperty("webdriver"))) {
            driver = new FirefoxDriver();
            driver.manage().window().maximize();
        } else {
            driver = new PhantomJSDriver();
        }
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @After
    public void tearDown() {
        driver.close();
    }

    @Test
    public void preencheEnviaFormulario() {
        driver.get("http://tinyurl.com/twseleniumworkshop");

        WebElement nomeCandidato = driver.findElement(By.id("entry_1050252143"));
        nomeCandidato.sendKeys("Capitão Caverna");

        Select linguagemProgramacao = new Select(driver.findElement(By.id("entry_2043435478")));
        linguagemProgramacao.selectByValue("Java");

        WebElement conhecimentoWebdriver = driver.findElement(By.cssSelector("input[value= 'What is Selenium-WebDriver?']"));
        conhecimentoWebdriver.click();

        WebElement browser = driver.findElement(By.cssSelector("input[value= 'Firefox']"));
        browser.click();

        WebElement botaoSubmit = driver.findElement(By.id("ss-submit"));
        botaoSubmit.click();

        Boolean resposta = driver.findElement(By.className("ss-resp-message")).isDisplayed();
        assertThat(resposta, is(true));
    }
}
