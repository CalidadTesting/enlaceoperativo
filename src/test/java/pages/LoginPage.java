package pages;

// importacion de los paquetes
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

// el nombre de la clase principal y carga del driver sido driver una instancia de WebDriver
public class LoginPage {
    WebDriver driver;

    // localizadores del proceso login
    By usernameField = By.id("loginusername");
    By passwordField = By.id("loginpassword");
    By loginButton = By.xpath("//button[text()='Log in']");

    // Constructor LoginPage recibe un objeto WebDriver y lo asigna como atributo driver
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // Con este metodo se ingresa el username de la linea 12
    public void setUsername(String username) {
        driver.findElement(usernameField).sendKeys(username);
    }

    // Con este metodo se ingresa el password de la linea 13
    public void setPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    // Con este metodo se da click al boton de la linea 14
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }
}