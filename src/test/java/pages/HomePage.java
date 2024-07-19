package pages;

// importacion de los paquetes
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

// el nombre de la clase principal y carga del driver
public class HomePage {
    WebDriver driver;

    // la carga de los localizadores o elementos
    By loginLink = By.id("login2");
    By signUpLink = By.id("signin2");
    By categoriesLink = By.id("cat");
    By homeLink = By.xpath("//a[@href='index.html']");
    By cartLink = By.id("cartur");


    // Constructor HomePage recibe un objeto WebDriver y lo asigna como atributo driver
    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    // El metodo getTitle retorna el titulo de la página
    public String getTitle() {
        return driver.getTitle();
    }

    // Este metodo hace click en LoginLink localizado en la linea 12
    public void clickLoginLink() {
        driver.findElement(loginLink).click();
    }

    // Este metodo hace click en SignUpLink localizado en la linea 13
    public void clickSignUpLink() {
        driver.findElement(signUpLink).click();
    }

    // Este metodo hace click en CategoryLink localizado en la linea 14
    public void clickCategoryLink(String category) {
        driver.findElement(By.linkText(category)).click();
    }

    public void clickProductLink(String product) {
        driver.findElement(By.linkText(product)).click();
    }

    // Este metodo hace click en addToCartButton localizado en la linea 16
    public void clickAddToCartButton() {
        driver.findElement(By.xpath("//a[text()='Add to cart']")).click();
    }

    // Este metodo hace click en el menú Home de la página
    public void clickHomeLink() {
        driver.findElement(homeLink).click();
    }

    // Este metodo hace click en el menú Cart de la página
    public void clickCartLink() {
        driver.findElement(cartLink).click();
    }

    // este captura el precio del producto por medio de un CSS y retorna solo numeros eliminando textos
    public String getProductPrice() {
        String priceText = driver.findElement(By.cssSelector("h3.price-container")).getText();
        return priceText.replaceAll("[^0-9.]", "");
    }
}
