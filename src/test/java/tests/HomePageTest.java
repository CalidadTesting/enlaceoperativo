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
        signUpPage.setUsername("testuser29161");
        signUpPage.setPassword("testpassword29161");
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
            loginPage.setUsername("testuser29161");
            loginPage.setPassword("testpassword29161");
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
