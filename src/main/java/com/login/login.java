package com.login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;

import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.numberOfWindowsToBe;

public class login {

    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver", "C:\\Chromedriver\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();

        driver.get("http://desarrollodtic.uv.cl/loginDesarrollo/");
        driver.manage().window().maximize();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String originalWindow = driver.getWindowHandle();
        assert driver.getWindowHandles().size() == 1;
        // Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // Ingresar RUT
        driver.findElement(By.cssSelector(".p-inputnumber-input")).sendKeys("8425294");
        // Seleccionar PORTAL
        driver.findElement(By.xpath("//div[@id='pn_id_21']/span")).sendKeys("pregrado");
        // WebElement portal =
        // driver.findElement(By.xpath("//p-dropdownitem[2]/li/div/div/div"));
        WebElement portal = driver.findElement(By.xpath("//li[@id='pn_id_21_0']"));
        js.executeScript("arguments[0].click();", portal);
        // Seleccionar TIPO INGRESO
        driver.findElement(By.xpath("//div[@id='pn_id_23']/span")).sendKeys("s");
        WebElement ingreso = driver.findElement(By.xpath("//li[@id='pn_id_23_0']"));
        js.executeScript("arguments[0].click();", ingreso);
        // Seleccionar LDAP
        driver.findElement(By.xpath("//div[@id='pn_id_25']/span")).sendKeys("s");
        WebElement ldap = driver.findElement(By.xpath("//li[@id='pn_id_25_0']"));
        js.executeScript("arguments[0].click();", ldap);
        // boton LOGIN
        // wait.until(d -> ldap.isDisplayed());
        WebElement login = driver.findElement(By.cssSelector("p-button[label='Login']"));
        js.executeScript("arguments[0].click();", login);
        // espera para login
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions
                .elementToBeClickable(By.cssSelector(".ng-star-inserted > div > .p-element > .p-ripple")));
        // Seleccionar Sistema
        driver.findElement(By.xpath("//div[@id='pn_id_29']/span")).sendKeys("moa");
        WebElement sistema = driver.findElement(By.xpath("//li[@id='pn_id_29_0']"));
        js.executeScript("arguments[0].click();", sistema);
        // escribir URL
        driver.findElement(By.cssSelector("#pn_id_31 > .p-dropdown-label"))
                .sendKeys("http://desarrollodtic.uv.cl/moaDivacad/");
        // boton INGRESAR
        driver.findElement(By.cssSelector(".p-button-success")).click();
        // manejo nueva ventana
        wait.until(numberOfWindowsToBe(2));
        for (String windowHandle : driver.getWindowHandles()) {
            if (!originalWindow.contentEquals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".text-white")));
        driver.quit();
    }
}
