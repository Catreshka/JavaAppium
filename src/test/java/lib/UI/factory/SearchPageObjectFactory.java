package lib.UI.factory;

import lib.Platform;
import lib.UI.SearchPageObject;
import lib.UI.android.AndroidSearchPageObject;
import lib.UI.ios.iOSSearchPageObject;
import lib.UI.mobile_web.MWSearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class SearchPageObjectFactory {
    public static SearchPageObject get(RemoteWebDriver driver)
    {
        if(Platform.getInstance().isAndroid()) {
            return new AndroidSearchPageObject(driver);
        } else if (Platform.getInstance().isiOS()){
            return new iOSSearchPageObject(driver);
        } else {
            return new MWSearchPageObject(driver);
        }
    }
}
