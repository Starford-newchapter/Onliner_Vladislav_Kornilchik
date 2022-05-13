package TestngUtills;

import Configuration.PropertyReader;
import org.testng.ITestContext;
import org.testng.ITestListener;

import java.util.Properties;

import static BaseObjects.DriverCreation.createDriver;

public class Listener implements ITestListener {
    private static ITestContext context;
    private static Properties properties;

    @Override
    public void onStart(ITestContext context) {
        this.context = context;
        new PropertyReader(context.getSuite().getParameter("config")==null?System.getProperty("config"):context.getSuite().getParameter("config"));
        this.properties = PropertyReader.getProperties();
        createDriver(properties.getProperty("browser") == null ? "chrome" : properties.getProperty("browser"));
    }
}
