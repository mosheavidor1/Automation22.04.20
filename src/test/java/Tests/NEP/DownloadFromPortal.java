package Tests.NEP;

import Actions.NepActions;
import Applications.SeleniumBrowser;
import Tests.GenericTest;
import Utils.PropertiesFile.PropertiesFile;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

public class DownloadFromPortal extends GenericTest {
    private NepActions action;

    @Factory(dataProvider = "getData")
    public DownloadFromPortal(Object dataToSet) {
        super(dataToSet);
        action = new NepActions();

    }

    @Test(alwaysRun = true)
    public void DownloadTest () throws Exception {

        action.CreateAndCleanDownloadFolder();

        action.LaunchApplication(data.get("Browser"));
        action.SetApplicationUrl(PropertiesFile.getCurrentClusterLink());

        action.Login(PropertiesFile.getUserName(), PropertiesFile.getPassword());

        action.GotoPortalFileCabinetPage(PropertiesFile.getCurrentClusterLink());
        action.SelectCustomer(data.get("Customer"));
        action.DeleteAllDownloads();


        action.GotoCentComSearch(PropertiesFile.getCurrentClusterLink());
        action.PublishNewDownloads(data.get("Customer"),Integer.parseInt(data.get("Wait for publish to be completed")));


        action.GotoPortalFileCabinetPage(PropertiesFile.getCurrentClusterLink());
        action.SelectCustomer(data.get("Customer"));

        action.DownloadFilesFromTrustWaveEndPointFolder(data.get("Wait files ready to download timeout"),data.get("Wait because File is still being processed (virus scanned and stored)"));

        action.VerifyFilesExist(Integer.parseInt(data.get("Wait files to be downloaded timeout in seconds")));

        action.VerifyInstallerSignature();


    }

    @AfterTest
    public void Close(){
        action.CloseApplication();

    }



}
