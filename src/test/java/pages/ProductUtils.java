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