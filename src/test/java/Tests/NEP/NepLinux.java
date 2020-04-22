package Tests.NEP;




import Actions.CloudActions;

import Tests.GenericTest;
import Utils.PropertiesFile.PropertiesFile;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.InputStream;


public class NepLinux {



    @Test
    public static void WindowsToLinux() throws Exception {

        Runtime runtime = Runtime.getRuntime();
        try {
            Process p1 = runtime.exec(NepLinuxConst.clusterToTest);
            InputStream is = p1.getInputStream();
            int i = 0;
            while ((i = is.read()) != -1) {
                System.out.print((char) i);
            }
        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        }


    }
}


