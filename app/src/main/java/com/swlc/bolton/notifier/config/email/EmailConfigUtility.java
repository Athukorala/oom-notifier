package com.swlc.bolton.notifier.config.email;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * A utility class that reads/saves SMTP settings from/to a properties file.
 *
 * @author athukorala
 *
 */
public class EmailConfigUtility {

    private final File configFile = new File("smtp.properties");

    public Properties loadProperties() throws IOException {
        Properties defaultProps = new Properties();
        // sets default properties
        defaultProps.setProperty("mail.smtp.host", "smtp.gmail.com");
        defaultProps.setProperty("mail.transport.protocol", "smtp");
        defaultProps.setProperty("mail.smtp.starttls.enable", "true");
        defaultProps.setProperty("mail.smtp.auth", "true");
        defaultProps.setProperty("mail.smtp.port", "465");
        defaultProps.setProperty("mail.smtp.socketFactory.port", "465");
        defaultProps.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        defaultProps.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");

        Properties configProps = new Properties(defaultProps);

        // loads properties from file
        if (configFile.exists()) {
            InputStream inputStream = new FileInputStream(configFile);
            configProps.load(inputStream);
            inputStream.close();
        }
        return configProps;
    }
}
