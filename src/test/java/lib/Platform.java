package lib;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Platform
{
    private static final String PLATFORM_ANDROID = "android";
    private static final String PLATFORM_IOS = "ios";
    private static final String PLATFORM_MOBILE_WEB = "mobile_web";
    private static final String APPIUM_URL = "http://127.0.0.1:4723/wd/hub";

    private static Platform instance;

    private Platform(){}

    public static Platform getInstance(){
        if(instance == null){
            instance = new Platform();
        }
        return instance;
    }

    public RemoteWebDriver getDriver1() throws Exception{
        if(this.isAndroid()){
            return new AndroidDriver(new URL(APPIUM_URL), this.getAndroidDesiredCapabilities());
        } else if(this.isMW()) {
            return new ChromeDriver(this.getMWChromeOptions());
        } else {
            throw new Exception("Cannot detect type of the Driver. Platform value is " + this.getPlatformVar());
        }
    }

    public RemoteWebDriver getDriver() throws Exception{
        URL URL = new URL(APPIUM_URL);
        String currentPlatform = this.getPlatformVar();
        switch (currentPlatform){
            case PLATFORM_ANDROID :
                return new AndroidDriver(URL, this.getAndroidDesiredCapabilities());
            case PLATFORM_MOBILE_WEB :
                return new ChromeDriver(this.getMWChromeOptions());
            default :
                throw new Exception("Cannot detect type of the Driver. Platform value is " + this.getPlatformVar());
        }
    }

    public boolean isAndroid(){
        return isPlatform(PLATFORM_ANDROID);
    }

    public boolean isMW(){
        return isPlatform(PLATFORM_MOBILE_WEB);
    }

    private DesiredCapabilities getAndroidDesiredCapabilities(){
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "AndroidDevice");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("platformVersion", "8.0");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "D:\\JavaAppiumAutomation\\apks\\org.wikipedia_50417_apps.evozi.com.apk");
        return capabilities;
    }

    private ChromeOptions getMWChromeOptions(){
        Map<String, Object> deviceMetrics = new HashMap<>();
        deviceMetrics.put("width", 360);
        deviceMetrics.put("height", 1080);
        deviceMetrics.put("pixelRatio", 3.0);

        Map<String, Object> mobileEmulator = new HashMap<>();
        mobileEmulator.put("deviceMetrics", deviceMetrics);
        mobileEmulator.put("userAgent", deviceMetrics);

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("window-size=340,1080");
        return chromeOptions;
    }

    private boolean isPlatform(String platform){
        String currentPlatform = this.getPlatformVar();
        return platform.equals(currentPlatform);
    }

    public String getPlatformVar(){
        return System.getenv("PLATFORM");
    }
}
