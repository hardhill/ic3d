package pfr.center.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ICPropertyApp {
    Properties properties;
    public ICPropertyApp(String fileproperty) {
        String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        String appConfigPath = rootPath + fileproperty;


        Properties appProps = new Properties();
        try {
            appProps.load(new FileInputStream(appConfigPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        properties = appProps;
    }

    public Properties getProperties(){
        return properties;
    }
}
