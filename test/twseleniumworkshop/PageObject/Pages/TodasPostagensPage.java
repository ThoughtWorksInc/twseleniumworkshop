package twseleniumworkshop.PageObject.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TodasPostagensPage {
	WebDriver driver;

	@FindBy(id="title")
	WebElement postTitle;
	
	@FindBy(id="tinymce")
	WebElement postBody;
	
	@FindBy(id="publish")
	WebElement publish;
	
	public TodasPostagensPage(WebDriver driver) {
		this.driver = driver;
		driver.get("http://twseleniumworkshop.wordpress.com/wp-admin/edit.php");
	}

	public void novaPostagem(String postTitle, String postBody) {
		WebElement novaPostagem = driver.findElement(By.linkText("Add New"));
		novaPostagem.click();
		driver.switchTo().frame("content_ifr");
		this.postBody.sendKeys(postBody);
		driver.switchTo().defaultContent();
		this.postTitle.sendKeys(postTitle);
		publish.click();
	}
	
	public void editaPostagem(String title, String newPostBody) {
		WebElement post = driver.findElement(By.linkText(title));
		post.click();
		driver.switchTo().frame("content_ifr");
		postBody.clear();
		postBody.sendKeys(newPostBody);
		driver.switchTo().defaultContent();
		publish.click();
	}
	
	public void deletaPostagem(String title) {
		WebElement post = driver.findElement(By.linkText(title));
		post.click();
		WebElement deletePost = driver.findElement(By.linkText("Move to Trash"));
		deletePost.click();
	}
	
	public Boolean verificaSePostFoiAdicionado(String post) {
		return driver.getPageSource().contains(post);
	}
	
	public String recuperaMensagemPostagemAdicionada() {
        return driver.findElement(By.cssSelector("#message p")).getText();
	}
	
	public String recuperaMensagemPostagemDeletada() {
        return driver.findElement(By.cssSelector("#message p")).getText();
	}
}
