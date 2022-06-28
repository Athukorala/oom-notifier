package com.swlc.bolton.notifier.repository.db;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author athukorala
 */
public class ConnectionFactory {
    private Connection conn;
    private static ConnectionFactory dbConnection;

    private ConnectionFactory() throws ClassNotFoundException, SQLException, IOException {
        FileReader dbFileReader = null;
        try {
            Properties dpProperties = new Properties();
            File dbFile = new File("settings/application.properties");
            dbFileReader = new FileReader(dbFile);
            dpProperties.load(dbFileReader);

            conn = (Connection) DriverManager.getConnection("jdbc:mysql://" + dpProperties.getProperty("db.ip") + "/" + dpProperties.getProperty("db.name"), (String) dpProperties.getProperty("db..user"), (String) dpProperties.getProperty("db.password"));

        } catch (FileNotFoundException ex) {
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                dbFileReader.close();
            } catch (IOException ex) {
                Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public Connection getConnection() {
        return conn;
    }

    public static ConnectionFactory getInstance() throws ClassNotFoundException, SQLException, IOException {
        if (dbConnection == null) {
            dbConnection = new ConnectionFactory();
        }
        return dbConnection;
    }
}
