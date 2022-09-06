import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.touch.TouchActions;

import java.io.IOException;

public class AppActions {

    private String applicationId = "com.gkonstantakis.productsapp";

    public AppActions() {
    }

    public int pressHomeButton() throws IOException, InterruptedException {
        String cmd = "adb shell input keyevent 3";
        Process exec = Runtime.getRuntime().exec(cmd); //press home button key
        exec.waitFor();
        return exec.exitValue();
    }

    public int pressBackButton() throws IOException, InterruptedException {
        String cmd = "adb shell input keyevent 4";
        Process exec = Runtime.getRuntime().exec(cmd); //press back button key
        exec.waitFor();
        return exec.exitValue();
    }

    public void clickButtonById(AndroidDriver<MobileElement> driver, String elementId) {
        driver.findElement(By.id(elementId)).click();
    }


}
