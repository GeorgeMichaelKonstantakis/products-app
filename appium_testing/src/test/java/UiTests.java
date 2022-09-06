import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URL;

import static io.appium.java_client.touch.offset.PointOption.point;

public class UiTests {

    public UiTests() {
    }

    private final static String APP_PACKAGE_NAME = "com.gkonstantakis.productsapp";
    private final static String APP_ACTIVITY_NAME = "com.gkonstantakis.productsapp.products.activities.MainActivity";
    private AndroidDriver<MobileElement> driver;
    private final String emulatorId = "emulator-5554";

    private final CsvWriter csvWriter = new CsvWriter();
    private final AppActions appActions = new AppActions();

    private Boolean appNotInstalled = true;

    @BeforeTest
    public void setup() throws IOException {

        DesiredCapabilities dc = new DesiredCapabilities();
        if (appNotInstalled) {
            dc.setCapability("app", "/Users/george/Desktop/productsApplication-debug.apk");
        }
        dc.setCapability(MobileCapabilityType.DEVICE_NAME, emulatorId);
        dc.setCapability("platformName", Platform.ANDROID);
        dc.setCapability(MobileCapabilityType.NO_RESET, true);
        dc.setCapability(MobileCapabilityType.UDID, emulatorId);
        dc.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, APP_PACKAGE_NAME);
        dc.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, APP_ACTIVITY_NAME);
        dc.setCapability("autoGrantPermissions", true);
        dc.setCapability("autoDismissAlerts", true);
        driver = new AndroidDriver<>(new URL("http://localhost:4723/wd/hub"), dc);
    }

    @Test
    public void appInstallation() {
        ResultsCsv resultsCsv = new ResultsCsv();
        resultsCsv.setTestName("installation");
        resultsCsv.setTimestamp(driver.getDeviceTime());
        try {
            Thread.sleep(5000);
            resultsCsv.setTestPassed(true);
            csvWriter.toCsv(resultsCsv);
        } catch (Exception e) {
            e.printStackTrace();
            resultsCsv.setTestPassed(false);
            resultsCsv.setTestResultMessage(e.toString());
            csvWriter.toCsv(resultsCsv);
        }
    }

    @Test
    public void clickReloadButton() {
        ResultsCsv resultsCsv = new ResultsCsv();
        resultsCsv.setTestName("clickReloadButton");
        resultsCsv.setTimestamp(driver.getDeviceTime());
        try {
            Thread.sleep(5000);
            appActions.clickButtonById(driver,"reload_button");
            Thread.sleep(5000);
            resultsCsv.setTestPassed(true);
            csvWriter.toCsv(resultsCsv);
        } catch (Exception e) {
            e.printStackTrace();
            resultsCsv.setTestPassed(false);
            resultsCsv.setTestResultMessage(e.toString());
            csvWriter.toCsv(resultsCsv);
        }
    }

    @Test
    public void moveToRandomProductScreen() {
        ResultsCsv resultsCsv = new ResultsCsv();
        resultsCsv.setTestName("moveToProductScreen");
        resultsCsv.setTimestamp(driver.getDeviceTime());
        try {
            Thread.sleep(5000);
            TouchAction action = new TouchAction(driver);
            action.tap(point(195, 1123)).perform();
            Thread.sleep(5000);
            action.tap(point(538, 1123)).perform();
            Thread.sleep(5000);
            resultsCsv.setTestPassed(true);
            csvWriter.toCsv(resultsCsv);
        } catch (Exception e) {
            e.printStackTrace();
            resultsCsv.setTestPassed(false);
            resultsCsv.setTestResultMessage(e.toString());
            csvWriter.toCsv(resultsCsv);
        }
    }
}
