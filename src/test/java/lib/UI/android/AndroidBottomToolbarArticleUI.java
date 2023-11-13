package lib.UI.android;

import lib.UI.BottomToolbarArticleUI;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidBottomToolbarArticleUI extends BottomToolbarArticleUI {

     static {
         BUTTON_PAGE_SAVE = "id:org.wikipedia:id/page_save";
         BUTTON_ADD_TO_LIST = "xpath://*[@resource-id='org.wikipedia:id/snackbar_action'][@text='Add to list']";
         INPUT_NAME_OF_FOLDER = "xpath://*[contains(@text,'Name of this list')]";
         BUTTON_OK = "xpath://*[@resource-id='android:id/button1'][@text='OK']";
         NAME_OF_EXIST_LIST_TPL = "xpath://*[@resource-id='org.wikipedia:id/item_title'][@text='{SUBSTRING_NAME_OF_LIST}']";
         BUTTON_VIEW_LIST = "xpath://*[@resource-id='org.wikipedia:id/snackbar_action'][@text='View list']";
     }
    public AndroidBottomToolbarArticleUI (RemoteWebDriver driver){
        super(driver);
    }
}
