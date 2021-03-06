package com.jqueryui.utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CommonMethods extends PageInitializer {
    public CommonMethods(){
        PageFactory.initElements(MyDriver.get(), this);
    }

    /**
     * This method accepts pop up alerts.
     */

    public static void acceptAlert() {
        try {
            Alert alert = MyDriver.get().switchTo().alert();
            alert.accept();
        } catch (NoAlertPresentException e) {
            System.out.println("No Alert is present");
        }
    }

    public static void dismissAlert() {
        try {
            Alert alert = MyDriver.get().switchTo().alert();
            alert.dismiss();
        } catch (NoAlertPresentException e) {
            System.out.println("No Alert is present");
        }
    }

    public static String getAlertText() {
        String alertText = null;
        try {
            Alert alert = MyDriver.get().switchTo().alert();
            alertText = alert.getText();
        } catch (NoAlertPresentException e) {
            System.out.println("No Alert is present");
        }
        return alertText;
    }

    public static void switchToFrame(String nameOrId) {
        try {
            MyDriver.get().switchTo().frame(nameOrId);
        } catch (NoSuchFrameException e) {
            System.out.println("Frame is not present");
        }
    }

    public static void switchToFrame(WebElement element) {
        try {
            MyDriver.get().switchTo().frame(element);
        } catch (NoSuchFrameException e) {
            System.out.println("Frame is not present");
        }
    }

    public static void switchToFrame(int index) {
        try {
            MyDriver.get().switchTo().frame(index);
        } catch (NoSuchFrameException e) {
            System.out.println("Frame is not present");
        }
    }

    public static void jsClick(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) MyDriver.get();
        js.executeScript("arguments[0].click();", element);
    }

    public static void scrollIntoElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) MyDriver.get();
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public static void scrollDown(WebElement element, int pixel) {
        JavascriptExecutor js = (JavascriptExecutor) MyDriver.get();
        js.executeScript("window.scrollBy(0," + pixel + ")");
    }

    public static void scrollUp(WebElement element, int pixel) {
        JavascriptExecutor js = (JavascriptExecutor) MyDriver.get();
        js.executeScript("window.scrollBy(0,-" + pixel + ")");
    }

    /**
     * This method will take screenshot and save it with the given file name
     *
     * @param fileName
     */
    public static byte[] takeScreenShot(String fileName) {

        Date date=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy_MMdd_HHmmss");
        String timeStamp=sdf.format(date.getTime());

        TakesScreenshot ts = (TakesScreenshot) MyDriver.get();

        byte[] picture=ts.getScreenshotAs(OutputType.BYTES);

        File screen=ts.getScreenshotAs(OutputType.FILE);
        String scrshotFile = Constants.SCREENSHOTS_FILEPATH + fileName + "_" + getTime() + ".png";
        try {
            FileUtils.copyFile(screen, new File(scrshotFile));
        } catch (IOException e) {
            System.out.println("Cannot take screenshot");
        }
        return picture;
    }

//	/**
//	 * will take screenshot
//	 * @param fileName
//	 */
//	public static void takeScreenshot(String fileName) {
// 		TakesScreenshot ts=(TakesScreenshot)MyDriver.get();
// 		File file=ts.getScreenshotAs(OutputType.FILE);
// 		try {
// 		FileUtils.copyFile(file, new File("screenshots/"+fileName+".png"));
// 		}catch(IOException e) {
// 			e.printStackTrace();
// 		}
//	}

    /**
     * This method will send text to given webelement
     *
     * @param element
     * @param text
     */

    public static void sendText(WebElement element, String text) {
//		waitForClickability(element);
        element.clear();
        element.sendKeys(text);
    }

    /**
     * This method will create an Object of WebDriverWait
     *
     * @return WebDriverWait
     */

    public static WebDriverWait getWaitObject() {
        WebDriverWait wait = new WebDriverWait(MyDriver.get(), Constants.EXPLICIT_WAIT_TIME);
        return wait;
    }

    /**
     * This method will wait until element becomes clickable
     *
     * @param element
     */
    public static void waitForClickability(WebElement element) {
        getWaitObject().until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * This method will wait until element becomes visible
     *
     * @param element
     */
    public static void waitForVisibility(WebElement element) {
        getWaitObject().until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * This method will wait until element becomes invisible
     *
     * @param element
     */
    public static void waitForInvisibility(WebElement element) {
        getWaitObject().until(ExpectedConditions.invisibilityOf(element));
    }

    /**
     * This method until the visibility of the given element and clicks on it
     *
     * @param element
     */
    public static void click(WebElement element) {
        waitForClickability(element);
        element.click();
    }

    /**
     * This method will move the mouse to given element
     *
     * @param target
     */
    public static void moveTo(WebElement target) {
        Actions action = new Actions(MyDriver.get());
        action.moveToElement(target);
    }

    /**
     * This element does an action click to given element
     *
     * @param target
     */
    public static void actionsClick(WebElement target) {
        Actions action = new Actions(MyDriver.get());
        action.click(target);
    }

    /**
     * This method submits a form an waits the next page to be loaded
     *
     * @param element
     */
    public static void submit(WebElement element) {
        element.submit();
    }

    public static String getTime() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
        String formattedDate = sdf.format(date.getTime());
        return formattedDate;
    }

    /**
     * This method select an option in drop down list by visible text
     *
     * @param element
     * @param visibleText
     */
    public static void selectByVisibleText(WebElement element, String visibleText) {
        Select select = new Select(element);
        select.selectByVisibleText(visibleText);
    }

    /**
     * This method select an option in drop down list by value attribute
     *
     * @param element
     * @param value
     */
    public static void selectByValue(WebElement element, String value) {
        Select select = new Select(element);
        select.selectByValue(value);
    }

    /**
     * This method select an option in drop down list by index
     *
     * @param element
     * @param index
     */
    public static void selectByIndex(WebElement element, int index) {
        Select select = new Select(element);
        select.selectByIndex(index);
    }

    /**
     * This methods selects date from the calender which is defined in the HRMS application
     * @param element
     * @param year
     * @param month
     * @param day
     */

    public void selectDate(WebElement element, String year, String month, String day) {

        element.clear();
        element.click();

        WebElement mnth = MyDriver.get().findElement(By.cssSelector("select.ui-datepicker-month"));
        Select mSelect = new Select(mnth);
        mSelect.selectByVisibleText(month);

        WebElement years= MyDriver.get().findElement(By.xpath("//select[@class='ui-datepicker-year']"));
        Select ySelect = new Select(years);
        ySelect.selectByVisibleText(year);

        List<WebElement> dayList = MyDriver.get().findElements(By.xpath("//table[@class='ui-datepicker-calendar']/tbody/tr" +
                "/td"));

        for (WebElement dy : dayList) {
            if (dy.getText().equals(day)) {
                dy.click();
                break;
            }
        }
    }

    /**
     * This method select an option in drop down list by value attribute
     *
     * @param element
     * @param value
     */
    public static void selectDDText(WebElement element, String value) {
        Select select = new Select(element);
        List<WebElement> options=select.getOptions();

        boolean isFound=false;

        for(WebElement option:options) {
            if(option.getText().equals(value)) {
                select.selectByVisibleText(value);
                isFound=true;
                break;
            }else if(option.getAttribute("value").contains(value)) {
                select.selectByValue(value);
                isFound=true;
                break;
            }
        }


        if(!isFound) {
            System.out.println(value+" is not found in the Dropdown List");
        }

    }

    public static String jsonFile;
    /**
     * This methods receives the filename of the jSon file and returns it in String format
     * @param fileName
     * @return
     */
    public static String readJson(String fileName) {
        try {
            jsonFile = new String(Files.readAllBytes(Paths.get(fileName)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonFile;
    }

    public static void slpBrowser(int timer){
        try {
            Thread.sleep(timer);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
