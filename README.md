README

Proceso de automatización de la página https://www.demoblaze.com/

dicho proceso contara con los siguientes pasos de ejecución.

1. Ingresara a la URL otorgada
2. Iniciara el proceso de construccion de un usuario en el Menú Sign up
3. Se logueara a la pltaforma en el Menú Lon in con las credenciales previamente contruidas en Sign up
4. Ira al menú CATEGORIES y selecionara:
* . Phones = se seleccionó el "Samsung galaxy s6". adjuntandolo al carrito de compras
de nuevo al Home
* . Laptops = se seleccionó el "Sony vaio i5". adjuntandolo al carrito de compras
de nuevo al Home
* . Monitors = se seleccionó el "Apple monitor 24". adjuntandolo al carrito de compras
5. Ira al Menú y seleccionara Cart, validara que se visualice las compras
6. LLenara toda la información pertinente a la compra y presiona el boton OK para terminar el proceso
7. Al final del proceso de automatización indicara la suma, los productos comprados y la comparación de precios
solicitados. 

Este proceso esta desarrollado tecnológico esta construido en Selenium bajo el patrón de diseño POM

Con la siguiente estructura

demoblaze-automation
│   pom.xml
└── src
    └── test
        └── java
            ├── pages
            │   ├── HomePage.java
            │   ├── LoginPage.java
			│   ├── CartPage.java
			│	├── ProductUtils.java
			│   └── SignUpPage.java
            └── tests
                └── HomePageTest.java
└── resources
    └── drivers
        └── chromedriver.exe
		
El IDE desarrollo utilizado es Intellij con las Librerías de MAVEN

Estos son cada uno de los archivos Utilizados

pom.xml

// definición y estructura basica del archivo
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">

    // modelo del proyecto
    <modelVersion>4.0.0</modelVersion>

    <groupId>org.example</groupId>
    <artifactId>cargaequipos</artifactId>
    <version>1.0-SNAPSHOT</version>

    // las dependencias que se utilizaran y bibliotecas para que el proceso de automatización funcione
    <dependencies>
        <dependency>
            <groupId>org.seleniumhq.selenium</groupId>
            <artifactId>selenium-api</artifactId>
            <version>3.141.59</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.testng</groupId>
            <artifactId>testng</artifactId>
            <version>7.4.0</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.seleniumhq.selenium</groupId>
            <artifactId>selenium-chrome-driver</artifactId>
            <version>3.141.59</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.seleniumhq.selenium</groupId>
            <artifactId>selenium-support</artifactId>
            <version>3.141.59</version>
            <scope>test</scope>
        </dependency>

        <dependency>
            <groupId>org.testng</groupId>
            <artifactId>testng</artifactId>
            <version>7.4.0</version>
            <scope>test</scope>
        </dependency>

    </dependencies>

    // aca se cargan los procesos maven para que compile la automatización
    <properties>
        <maven.compiler.source>8</maven.compiler.source>
        <maven.compiler.target>8</maven.compiler.target>
    </properties>

</project>


HomePage

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


LoginPage


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


HomePageTest

package tests;

// importacion de los paquetes
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.HomePage;
import pages.LoginPage;
import pages.SignUpPage;

// Clase principal
public class HomePageTest {

    // Declaración de variables
    WebDriver driver;
    HomePage homePage;
    LoginPage loginPage;
    SignUpPage signUpPage;
    CartPage cartPage;

    // declaracion variable para almacenar el precio total de los productos seleccionados
    double totalPrice;

    @BeforeMethod
    public void setup() {
        // hace una carga inicial y de localización del ChromeDriver
        System.setProperty("webdriver.chrome.driver", "C:/Users/User/IdeaProjects/cargaequipos/resources/drivers/chromedriver.exe");

        // crea una instancia de WebDriver gracias a driver
        driver = new ChromeDriver();

        // Maximiza la ventana de ejecución
        driver.manage().window().maximize();

        // Captura la Url de Trabajo
        driver.get("https://www.demoblaze.com/");

        // instancia de HomePage
        homePage = new HomePage(driver);

        // instancia de LoginPage
        loginPage = new LoginPage(driver);

        // Instancia de SignUpPAGE
        signUpPage = new SignUpPage(driver);

        // Instancia de CartPage
        cartPage = new CartPage(driver);
    }

    // Test inicial y carga de la pagina de trabajo
    @Test(priority = 1)
    public void testHomePageTitle() {
        String title = homePage.getTitle();
        // Validación del nombre de la página
        Assert.assertEquals(title, "STORE");
    }

    @Test(priority = 2)
    public void testSignUpAndLogin() throws InterruptedException {
        // acá se crea el usuario
        homePage.clickSignUpLink();
        Thread.sleep(1000);
        signUpPage.setUsername("testuser29145");
        signUpPage.setPassword("testpassword29145");
        signUpPage.clickSignUpButton();
        Thread.sleep(1000);

        // Manejo de alertas, ya que existen ventanas flotantes en JavaScript
        // Alert es una referenca ed selenium - signUpAlert almacena un dato que viene de la vertana flotante
        Alert signUpAlert = driver.switchTo().alert();

        // se captura el texto que viene de signUpAlert con alertText - devuelve el texto con signUpAlert.getText();
        String alertText = signUpAlert.getText();

        // Se imprieme el mensaje de texto
        System.out.println("Sign up alert text: " + alertText);

        // trae la referencia de signUpAlert
        signUpAlert.accept();

        // tiempo de espera
        Thread.sleep(1000);


        // esta parrafo de lineas hace referencia al login de la página
        // valido si fue creado o ya existe el usuario
        if (alertText.equals("Sign up successful.") || alertText.equals("This user already exist.")) {
            // click en el menu Log in
            homePage.clickLoginLink();
            Thread.sleep(1000);
            // ingreso las credenciales mismas de las creación
            loginPage.setUsername("testuser29145");
            loginPage.setPassword("testpassword29145");
            loginPage.clickLoginButton();
            Thread.sleep(3000);
        } else {
            // datos errados y falla el proceso
            Assert.fail("Unexpected alert text: " + alertText);
        }

        // Adjunto productos al carrito y capturo los precios
        // este es el del telefono
        double phonePrice = addProductToCartAndCapturePrice("Phones", "Samsung galaxy s6");
        homePage.clickHomeLink();
        Thread.sleep(3000);

        // Este es el del portatil
        double laptopPrice = addProductToCartAndCapturePrice("Laptops", "Sony vaio i5");
        homePage.clickHomeLink();
        Thread.sleep(3000);

        //Este es el del monitor
        double monitorPrice = addProductToCartAndCapturePrice("Monitors", "Apple monitor 24");

        // Sumo los precios e imprimo los detalles de la compra
        totalPrice = phonePrice + laptopPrice + monitorPrice;
        System.out.println("Product: Samsung galaxy s6, Price: $" + phonePrice);
        System.out.println("Product: Sony vaio i5, Price: $" + laptopPrice);
        System.out.println("Product: Apple monitor 24, Price: $" + monitorPrice);
        System.out.println("Total Price: $" + totalPrice);

        // voy a la pagina principal y del menu selecciono Cart
        homePage.clickCartLink();
        Thread.sleep(3000);

        // Verifico los precios del carrito Vs los que tengo en la compra
        double cartTotalPrice = cartPage.getCartTotalPrice();
        System.out.println("Cart total price: $" + cartTotalPrice);
        // comparo los precios del carro Vs. la compra
        Assert.assertEquals(cartTotalPrice, totalPrice, "Cart total price should match the sum of product prices");

        // Realizo el Pedido en el carrito
        // Presiono el boton de inicio de la compra
        cartPage.clickPlaceOrderButton();
        Thread.sleep(2000);
        // ingreso cada uno de los datos en la solicito de compras
        cartPage.fillOrderForm("Emilio Mendoza", "Colombia", "Medellín", "9874-5678-1234", "July", "2024");
        // presiono el boton Purchase
        cartPage.clickPurchaseButton();
        Thread.sleep(3000);

        // Verifico el valor de la compra
        String purchaseAmount = cartPage.getPurchaseAmount();
        System.out.println("Purchase amount from alert: $" + purchaseAmount);
        // valido que el valor capturado no debe de ser nulo
        Assert.assertNotNull(purchaseAmount, "Purchase amount should not be null");
        // Mensaje de compra debe de ser iagual al precio total
        Assert.assertTrue(Double.parseDouble(purchaseAmount) <= totalPrice, "Purchase amount should be equal to total price");
        System.out.println("Purchase completed successfully!");
    }

    //Este método automatiza el proceso de selección de un producto, captura su precio y lo añade al carrito de compras
    //private: Metodo privado
    //double: retorna un valor de punto flotante (double).
    //addProductToCartAndCapturePrice: Nombre del método.
    //String category y String product: Parámetros de categoría y el nombre del producto
    //throws InterruptedException: Indica que este método puede lanzar una excepción InterruptedException.


    private double addProductToCartAndCapturePrice(String category, String product) throws InterruptedException {
        homePage.clickCategoryLink(category);
        Thread.sleep(3000);
        homePage.clickProductLink(product);
        Thread.sleep(3000);
        String priceText = homePage.getProductPrice();
        double price = Double.parseDouble(priceText.replace("$", ""));
        homePage.clickAddToCartButton();
        Thread.sleep(3000);

        // Para el manejo de los menus flotantes
        Alert alert = driver.switchTo().alert();
        alert.accept();
        Thread.sleep(1000);

        return price;
    }

    // despues de cierre
    @AfterMethod
    public void teardown() {
        driver.quit();
    }
}


CartPage


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


SignUpPage


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


ProductUtils


package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
// esta es mas de visualización y esperar muy utilizado WebDriverWait
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

// es utiliza para números fijo aritméticamente exactos
import java.math.BigDecimal;
import java.math.RoundingMode;

// este es para trabajr list - Lo utilizo para captur el precio desde la pagina
import java.util.List;

public class ProductUtils {

    public static BigDecimal getTotalPrice(WebDriver driver) {
        // Maximizar la ventana del navegador si no está maximizada
        if (driver.manage().window().getPosition().getX() == 0) {
            driver.manage().window().maximize();
        }

        // Encontrar todos los elementos que contienen precios
        List<WebElement> priceElements = driver.findElements(By.xpath("//span[contains(@class,'price')]"));

        // Inicializa el total a 0
        BigDecimal totalPrice = new BigDecimal("0");

        // comparo sobre cada elemento para obtener el precio y sumarlo al total
        for (WebElement element : priceElements) {
            String priceText = element.getText().replaceAll("[^\\d.,]", ""); // Eliminar caracteres no numéricos
            BigDecimal price = new BigDecimal(priceText.replace(",", ".")); // Convertir a BigDecimal

            totalPrice = totalPrice.add(price); // Sumar al total
        }

        // Redondeo el total a dos decimales
        totalPrice = totalPrice.setScale(2, RoundingMode.HALF_UP);

        // Presionar el botón OK y esperar a que termine el proceso
        WebElement okButton = driver.findElement(By.id("id_del_boton_ok")); // Reemplazar con el ID correcto TENGO DUDA CON ESTE
        okButton.click();

        // Esperar hasta que el proceso termine - NO ME ESTA CARGANDO EL FIN DEL PROCESO
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("id_del_boton_ok"))); // Esperar a que el botón OK desaparezca

        return totalPrice;
    }
}




Para que el proceso funcione correctamente el usuario debera de ingresar credenciales nuevas tanto para Sign up y Log in

Estos se encuentran ubicados en la clase HomePageTest.java

en esta instrucción se encuentra la creación del usuario

@Test(priority = 2)
    public void testSignUpAndLogin() throws InterruptedException {
        // acá se crea el usuario
        homePage.clickSignUpLink();
        Thread.sleep(1000);
        signUpPage.setUsername("testuser29145");
        signUpPage.setPassword("testpassword29145");
        signUpPage.clickSignUpButton();
        Thread.sleep(1000);



como se puede observar para este ejemplo la siguiente instrucción será

signUpPage.setUsername("testuser29146");
signUpPage.setPassword("testpassword29146");
		
En esta proxima instrucción el acceso


if (alertText.equals("Sign up successful.") || alertText.equals("This user already exist.")) {
            // click en el menu Log in
            homePage.clickLoginLink();
            Thread.sleep(1000);
            // ingreso las credenciales mismas de las creación
            loginPage.setUsername("testuser29145");
            loginPage.setPassword("testpassword29145");
            loginPage.clickLoginButton();
            Thread.sleep(3000);


Para esta instrucción se debe de utilizar con los mismo valores previamente construidos en el parrafo anterior.

loginPage.setUsername("testuser29146");
loginPage.setPassword("testpassword29146");

Luego de realizar estas dos pasos ejecutar RUN. el proceso inicializara automatización.
