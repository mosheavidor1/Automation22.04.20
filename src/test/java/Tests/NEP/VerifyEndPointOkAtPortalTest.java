package Tests.NEP;

import Actions.NepActions;
import Applications.SeleniumBrowser;
import Tests.GenericTest;
import Utils.PropertiesFile.PropertiesFile;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

public class VerifyEndPointOkAtPortalTest extends GenericTest {
    private NepActions action;

    @Factory(dataProvider = "getData")
    public VerifyEndPointOkAtPortalTest(Object dataToSet) {
        super(dataToSet);
        action = new NepActions();

    }

    @Test(alwaysRun = true)
    public void VerifyEndPointStatusAtPortalTest () throws Exception {


        action.LaunchApplication(data.get("Browser"));
        action.SetApplicationUrl(PropertiesFile.getCurrentClusterLink());

        action.Login(PropertiesFile.getUserName(), PropertiesFile.getPassword());

        action.GotoCentComSearch(PropertiesFile.getCurrentClusterLink());
        action.CheckEndPointOkInCentCom(data.get("Customer"));

    }

    @AfterTest
    public void Close(){
        action.CloseApplication();

    }

}
