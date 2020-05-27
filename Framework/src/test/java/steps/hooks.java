package steps;

import Servicos.Funcionalidades;
import com.vimalselvam.cucumber.listener.Reporter;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import io.appium.java_client.AppiumDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.AfterSuite;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.sql.Driver;

public class hooks {

    //TestContext testContext;
    @Before
    public void beforeScenario() throws Exception
    {
        if (Funcionalidades.Driver != null)
        {
            Funcionalidades.Driver.navigate().to("https://www.sicredi.com.br/html/ferramenta/simulador-investimento-poupanca/");
            //((AppiumDriver)Funcionalidades.Driver).resetApp();
            //Funcionalidades.VerificarQdoObjetoDesaparecer(HomePage.Spash());
        }
        else
        {
            //Funcionalidades.Inicializar("");
            //Funcionalidades.VerificarQdoObjetoDesaparecer(HomePage.Spash());
        }
    }

    @After
    public void afterScenario(Scenario scenario) {
        if (scenario.isFailed()) {
            String screenshotName = scenario.getName().replaceAll(" ", "_");
            try {
                //This takes a screenshot from the driver at save it to the specified location
                File sourcePath = ((TakesScreenshot)Funcionalidades.Driver).getScreenshotAs(OutputType.FILE);

                //Building up the destination path for the screenshot to save
                //Also make sure to create a folder 'screenshots' with in the cucumber-report folder
                File destinationPath = new File(System.getProperty("user.dir") + "/src/test/Report/screenshots/" + screenshotName + ".png");

                //Copy taken screenshot from source location to destination location
                FileUtils.copyFile(sourcePath, new File(destinationPath.toString()));

                //This attach the specified screenshot to the test
                Reporter.addScreenCaptureFromPath(destinationPath.toString());


            } catch (IOException e) {
            }
        }
    }


    @AfterSuite
    public void afterSuite()
    {
        Funcionalidades.Driver.quit();
        Funcionalidades.Driver.close();
        Funcionalidades.Driver.quit();
    }
}