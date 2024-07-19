package pages;

// importacion de los paquetes
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

// Clase principal
public class CartPage {
    WebDriver driver;

    // estos son los localizadores de la compra dl carrito
    By placeOrderButton = By.xpath("//button[text()='Place Order']");
    By nameField = By.id("name");
    By countryField = By.id("country");
    By cityField = By.id("city");
    By creditCardField = By.id("card");
    By monthField = By.id("month");
    By yearField = By.id("year");
    By purchaseButton = By.xpath("//button[text()='Purchase']");

    // Constructor que recibe una instancia de WebDriver
    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    // Método para hacer clic en el botón "Place Order"
    public void clickPlaceOrderButton() {
        driver.findElement(placeOrderButton).click();
    }

    // Método para llenar el formulario de pedido con la información proporcionada
    public void fillOrderForm(String name, String country, String city, String creditCard, String month, String year) {
        driver.findElement(nameField).sendKeys(name);
        driver.findElement(countryField).sendKeys(country);
        driver.findElement(cityField).sendKeys(city);
        driver.findElement(creditCardField).sendKeys(creditCard);
        driver.findElement(monthField).sendKeys(month);
        driver.findElement(yearField).sendKeys(year);
    }

    // Método para hacer clic en el botón "Purchase"
    public void clickPurchaseButton() {
        driver.findElement(purchaseButton).click();
    }

    // Método para obtener el monto de la compra desde la alerta ---- ESTE NO ME ESTA DANDO NO ME CIERRA EL OK
    public String getPurchaseAmount() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 30);  // Increased the wait time
            System.out.println("Waiting for alert to be present...");
            wait.until(ExpectedConditions.alertIsPresent());
            Alert alert = driver.switchTo().alert();
            System.out.println("Alert detected, extracting purchase amount...");
            String purchaseDetails = alert.getText();
            System.out.println("Alert text: " + purchaseDetails);
            String amountText = purchaseDetails.split("Amount: ")[1].split(" ")[0];
            alert.accept();
            return amountText;
        } catch (NoAlertPresentException e) {
            System.out.println("No alert present");
            return null;
        } catch (TimeoutException e) {
            System.out.println("Timeout waiting for alert");
            return null;
        }
    }

    // Método para obtener el precio total del carrito
    public double getCartTotalPrice() {
        WebElement totalPriceElement = driver.findElement(By.id("totalp"));
        String totalPriceText = totalPriceElement.getText();
        return Double.parseDouble(totalPriceText);
    }


    // Si hay una ventana emergente el indica si esta abierta o cerrada
    public void acceptAlert() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.alertIsPresent());
            Alert alert = driver.switchTo().alert();
            alert.accept();
        } catch (NoAlertPresentException e) {
            System.out.println("No alert present to accept");
        } catch (TimeoutException e) {
            System.out.println("Timeout waiting for alert");
        }
    }
}
