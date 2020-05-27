package main;

import Servicos.FileReaderManager;
import com.vimalselvam.cucumber.listener.Reporter;
import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.AfterClass;

import java.io.File;


@CucumberOptions(
        strict = true,
        monochrome = true,
        features = "src/test/java/features",
        glue = "steps",
        plugin = { "com.vimalselvam.cucumber.listener.ExtentCucumberFormatter:src/test/Report/report.html"})

public class RunCucumberFeatures extends AbstractTestNGCucumberTests {
    @AfterClass
    public static void writeExtentReport() {
        Reporter.loadXMLConfig(new File(FileReaderManager.getInstance().getConfigReader().getReportConfigPath()));
    }
}