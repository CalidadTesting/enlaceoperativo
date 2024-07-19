package pages;

// importacion de los paquetes
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

// Clase principal
public class SignUpPage {
    WebDriver driver;

    By usernameField = By.id("sign-username");
    By passwordField = By.id("sign-password");
    By signUpButton = By.xpath("//button[text()='Sign up']");

    // Constructor que recibe una instancia de WebDriver
    public SignUpPage(WebDriver driver) {
        this.driver = driver;
    }

    // Método para establecer el nombre de usuario en el campo de usuario
    public void setUsername(String username) {
        driver.findElement(usernameField).sendKeys(username);
    }

    // Método para establecer la contraseña en el campo de contraseña
    public void setPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    // Método para hacer clic en el botón "Sign up"
    public void clickSignUpButton() {
        driver.findElement(signUpButton).click();
    }
}