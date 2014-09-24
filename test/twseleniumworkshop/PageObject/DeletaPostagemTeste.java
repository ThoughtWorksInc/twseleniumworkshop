package twseleniumworkshop.PageObject;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import twseleniumworkshop.PageObject.Pages.LoginAdminPage;
import twseleniumworkshop.PageObject.Pages.TodasPostagensPage;

public class DeletaPostagemTeste {
	WebDriver driver;
	
	@Before
	public void setUp() {
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@After
	public void tearDown() {
		driver.close();
	}
	
	@Test
	public void deletaPostagem() {
		LoginAdminPage loginAdminPage = PageFactory.initElements(driver, LoginAdminPage.class);
		loginAdminPage.login();
		
		TodasPostagensPage todasPostagensPage = PageFactory.initElements(driver, TodasPostagensPage.class);
		todasPostagensPage.deletaPostagem("My First Post");
		
		String mensagemPostagemDeletada = todasPostagensPage.recuperaMensagemPostagemDeletada();
		
		assertThat(mensagemPostagemDeletada, is("1 post moved to the Trash. Undo"));
	}
}
