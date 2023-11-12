package lib.UI.factory;

import lib.Platform;
import lib.UI.BottomToolbarArticleUI;
import lib.UI.android.AndroidBottomToolbarArticleUI;
import lib.UI.ios.iOSBottomToolbarArticleUI;
import lib.UI.mobile_web.MWBottomToolbarArticle;
import org.openqa.selenium.remote.RemoteWebDriver;

public class BottomToolbarArticleUIFactory {
    public static BottomToolbarArticleUI get(RemoteWebDriver driver)
    {
        if(Platform.getInstance().isAndroid()) {
            return new AndroidBottomToolbarArticleUI(driver);
        } else if (Platform.getInstance().isiOS()) {
            return new iOSBottomToolbarArticleUI(driver);
        } else {
            return new MWBottomToolbarArticle(driver);
        }
    }
}
