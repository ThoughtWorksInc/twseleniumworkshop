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
import static org.hamcrest.Matchers.is;


// Silly implementation with lots of code duplication.
// The implementation below is just for basic learning purposes.
// For a decent implementation of the code below, please look inside the PageObject directory.

public class Exercicio4 {
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
    public void criaNovaPostagem() {
        driver.get("http://twseleniumworkshop.wordpress.com/wp-admin");
        WebElement email = driver.findElement(By.id("user_login"));
        WebElement senha = driver.findElement(By.id("user_pass"));
        WebElement envia = driver.findElement(By.id("wp-submit"));
        email.sendKeys("twseleniumworkshop");
        senha.sendKeys("twseleniumworkshop!");
        envia.click();

        driver.get("http://twseleniumworkshop.wordpress.com/wp-admin/edit.php");
        WebElement novaPostagem = driver.findElement(By.linkText("Add New"));
        novaPostagem.click();

        driver.switchTo().frame("content_ifr");
        WebElement corpoPostagem = driver.findElement(By.id("tinymce"));
        corpoPostagem.sendKeys("This is a description.");
        driver.switchTo().defaultContent();
        WebElement tituloPostagem = driver.findElement(By.id("title"));
        tituloPostagem.sendKeys("My First Post");

        WebElement publicar = driver.findElement(By.id("publish"));
        publicar.click();

        Boolean tituloExistente = driver.getPageSource().contains("My First Post");

        assertThat(tituloExistente, equalTo(Boolean.TRUE));
    }

    @Test
    public void editaPostagem() {
        driver.get("http://twseleniumworkshop.wordpress.com/wp-admin");
        WebElement email = driver.findElement(By.id("user_login"));
        WebElement senha = driver.findElement(By.id("user_pass"));
        WebElement envia = driver.findElement(By.id("wp-submit"));
        email.sendKeys("twseleniumworkshop");
        senha.sendKeys("twseleniumworkshop!");
        envia.click();

        driver.get("http://twseleniumworkshop.wordpress.com/wp-admin/edit.php");
        WebElement postagem = driver.findElement(By.linkText("My First Post"));
        postagem.click();
        driver.switchTo().frame("content_ifr");
        WebElement novoCorpoPostagem = driver.findElement(By.id("tinymce"));
        novoCorpoPostagem.clear();
        novoCorpoPostagem.sendKeys("This is a NEW description.");
        driver.switchTo().defaultContent();
        WebElement atualiza = driver.findElement(By.id("publish"));
        atualiza.click();

        String corpoPostagemAtualizado = driver.findElement(By.cssSelector("#message p")).getText();

        assertThat(corpoPostagemAtualizado, is("Post updated. View post"));
    }

    @Test
    public void deletaPostagem() {
        driver.get("http://twseleniumworkshop.wordpress.com/wp-admin");
        WebElement email = driver.findElement(By.id("user_login"));
        WebElement senha = driver.findElement(By.id("user_pass"));
        WebElement envia = driver.findElement(By.id("wp-submit"));
        email.sendKeys("twseleniumworkshop");
        senha.sendKeys("twseleniumworkshop!");
        envia.click();

        driver.get("http://twseleniumworkshop.wordpress.com/wp-admin/edit.php");
        WebElement postagem = driver.findElement(By.linkText("My First Post"));
        postagem.click();
        WebElement deletaPostagem = driver.findElement(By.linkText("Move to Trash"));
        deletaPostagem.click();

        String mensagemDeletadaPostagem = driver.findElement(By.cssSelector("#message p")).getText();

        assertThat(mensagemDeletadaPostagem, is("1 post moved to the Trash. Undo"));
    }
}
