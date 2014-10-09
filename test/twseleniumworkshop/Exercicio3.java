package twseleniumworkshop;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;

public class Exercicio3 {
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
    public void criaNovoUsuario() {
        driver.get("http://www.flipkart.com/");
        driver.findElement(By.className("signup-link")).click();
        driver.findElement(By.id("signup-email")).sendKeys("pedro-" + geraNumeroRandomico() + "@gmail.com");
        driver.findElement(By.id("signup-password")).sendKeys("Tartaruga01");
        driver.findElement(By.name("repeat-password")).sendKeys("Tartaruga01");
        driver.findElement(By.cssSelector("input[value = 'Sign Up Now!']")).click();
        boolean resultado = driver.findElement(By.className("greeting-link")).isEnabled();

        assertThat(resultado, equalTo(Boolean.TRUE));
    }

    /* O teste abaixo foi propositalmente alterado para falhar. Que tal consertar? ;)
      
      Teste para editar detalhes do usuário.
      1. Acessar http://www.flipkart.com/account
      2. Preencher campo de email com: bruno@gmail.com
      3. Preencher campo de senha com: Tartaruga01
      4. Clicar no botão de login
      5. Verificar se o usuário realizou login com sucesso
    */

    @Test
    public void loginUsuario() {
        driver.findElement(By.id("http://www.flipkart.com/account"));
        driver.findElement(By.id("bruno@gmail.com")).sendKeys("login_email_id1");
        driver.getTitle().equals("login_password1");
        driver.findElement(By.id("login-cta"));
        String mensagem = driver.findElement(By.className("fk-font-verybig")).getText();

        assertThat(mensagem, not("Login successful!"));
    }

    public int geraNumeroRandomico() {
        Random numeroRandomico = new Random();
        return numeroRandomico.nextInt(9999);
    }
}
