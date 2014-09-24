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

public class EditaPostagemTeste {
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
	public void editaPostagem() {
		LoginAdminPage loginAdminPage = PageFactory.initElements(driver, LoginAdminPage.class);
		loginAdminPage.login();
		
		TodasPostagensPage todasPostagensPage = PageFactory.initElements(driver, TodasPostagensPage.class);
		todasPostagensPage.editaPostagem("My First Post", "This is a NEW description.");
		
		String mensagemPostagemAtualizada = todasPostagensPage.recuperaMensagemPostagemAdicionada();
		
		assertThat(mensagemPostagemAtualizada, is("Post updated. View post"));
	}
}
