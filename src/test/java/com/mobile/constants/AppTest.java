package com.mobile.constants;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.screenrecording.CanRecordScreen;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.*;

/**
 * Unit test for simple App.
 */
public class AppTest {

    private static AppiumDriver driver;
    private static AppiumDriverLocalService service;

    @Test
    public void readYamlFile() throws IOException {

        File file = new File(System.getProperty("user.dir") + "/src/test/resources/environment.yml");
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        Map<String, Object> map = objectMapper.readValue(file, Map.class);
        System.out.println(map);
        ArrayList<Map<String, Object>> map1 = (ArrayList<Map<String, Object>>) map.get("platforms");
        System.out.println(map1);
        System.out.println(map1.size());


    }

    @BeforeMethod
    public void setUp() {
        //TODO SetUp
        service = AppiumDriverLocalService.buildDefaultService();
        service.start();
        UiAutomator2Options options = new UiAutomator2Options()
                .setDeviceName("pixel_6_pro")
                .setAppPackage("com.saucelabs.mydemoapp.rn")
                .setAppActivity("com.saucelabs.mydemoapp.rn.MainActivity")
                .setAutomationName("UiAutomator2")
                .setSkipLogCapture(true)
                .setNoReset(false);
        try {
            driver = new AndroidDriver(
                    new URL("http://127.0.0.1:4723"), options
            );
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        ((CanRecordScreen) driver).startRecordingScreen();
    }

    @Test
    public void testA() throws MalformedURLException {


        //TODO LOGIN
        try {
            driver.findElement(AppiumBy.accessibilityId("open menu")).click();
            //Thread.sleep(20000);
            driver.findElement(AppiumBy.accessibilityId("menu item log in")).click();
            driver.findElement(AppiumBy.accessibilityId("Username input field")).sendKeys("bob@example.com");
            driver.findElement(AppiumBy.accessibilityId("Password input field")).sendKeys("10203040");
            driver.findElement(AppiumBy.accessibilityId("Login button")).click();
            System.out.println("Logged in");
            driver.findElement(AppiumBy.accessibilityId("cart badge")).click();
            List<WebElement> lst = driver.findElements(AppiumBy.xpath("//android.view.ViewGroup[@content-desc=\"product row\"]"));
            System.out.println("Size is " + lst.size());
            if (lst.size() > 0) {
                driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"Remove Item\"]")).click();
                System.out.println("Item Removed");
            }
            driver.navigate().back();
            clickElement(driver, driver.findElement(AppiumBy.xpath("//android.widget.TextView[@content-desc=\"store item text\" and @text=\"Sauce Labs Bike Light\"]")));
            Thread.sleep(2000);
            System.out.println(driver.findElement(AppiumBy.xpath("//android.widget.TextView[@content-desc=\"product price\"]")).getText());

            driver.findElement(AppiumBy.xpath("//android.view.ViewGroup[@content-desc=\"Add To Cart button\"]")).click();
            driver.findElement(AppiumBy.accessibilityId("cart badge")).click();
            List<WebElement> lst1 = driver.findElements(AppiumBy.xpath("//android.view.ViewGroup[@content-desc=\"product row\"]"));
            System.out.println("Size is " + lst1.size());

            driver.findElement(AppiumBy.accessibilityId("Proceed To Checkout button")).click();
            // Thread.sleep(2000);

            //TODO Checkout
            driver.findElement(AppiumBy.accessibilityId("Full Name* input field")).sendKeys("Testing");
            driver.findElement(AppiumBy.accessibilityId("Address Line 1* input field")).sendKeys("Address 1");
            driver.findElement(AppiumBy.accessibilityId("City* input field")).sendKeys("Mysore");
            driver.findElement(AppiumBy.accessibilityId("State/Region input field")).sendKeys("Karnataka");
            driver.findElement(AppiumBy.accessibilityId("Zip Code* input field")).sendKeys("571401");
            driver.findElement(AppiumBy.accessibilityId("City* input field")).sendKeys("Mysore");
            driver.findElement(AppiumBy.accessibilityId("Country* input field")).sendKeys("India");
            driver.findElement(AppiumBy.accessibilityId("To Payment button")).click();
            Thread.sleep(2000);
            driver.findElement(AppiumBy.accessibilityId("Full Name* input field")).sendKeys("Testing");
            driver.findElement(AppiumBy.accessibilityId("Card Number* input field")).sendKeys("1234 5678 8888 8888");
            driver.findElement(AppiumBy.accessibilityId("Expiration Date* input field")).sendKeys("03/25");
            driver.findElement(AppiumBy.accessibilityId("Security Code* input field")).sendKeys("123");
            driver.findElement(AppiumBy.accessibilityId("Review Order button")).click();
            driver.findElement(AppiumBy.accessibilityId("Review Order button")).click();
            Thread.sleep(2000);

            driver.findElement(AppiumBy.accessibilityId("Place Order button")).click();
            Thread.sleep(2000);
            System.out.println(driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"Thank you for your order\"]")).isDisplayed());
            System.out.println(driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"Your new swag is on its way\"]")).isDisplayed());

            /*clickElement(driver,elementTap);
            Point loc = elementTap.getLocation();
            Dimension dim = elementTap.getSize();

            Point centreOfEle = getCentreOfElement(loc, dim);
            PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
            Sequence sequence = new Sequence(finger1, 1).addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), centreOfEle))
                    .addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                    .addAction(new Pause(finger1, Duration.ofMillis(500)))
                    .addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
            driver.perform(Collections.singletonList(sequence));
            System.out.println("Anand");*/


        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    @AfterMethod
    public void tearDown() {
        String base64String = ((CanRecordScreen) driver).stopRecordingScreen();
        byte[] data = Base64.getDecoder().decode(base64String);
        String
                destinationPath = "target/filename.mp4";
        Path path = Paths.get(destinationPath);
        try {
            Files.write(path, data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        driver.quit();
        service.stop();
    }

    private Point getCentreOfElement(Point loc, Dimension dim) {
        return new Point(loc.getX() + dim.getWidth() / 2, loc.getX() + dim.getHeight() / 2);
    }

    public void clickElement(AppiumDriver driver, WebElement element) {
        driver.executeScript("mobile: clickGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) element).getId()
        ));
    }

    public void swipe(AppiumDriver driver, WebElement element) {
        driver.executeScript("mobile: clickGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) element).getId()
        ));
    }
}
