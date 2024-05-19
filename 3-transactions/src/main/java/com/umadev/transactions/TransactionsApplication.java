package com.umadev.transactions;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.apache.log4j.Logger;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TransactionsApplication {

  private static final Logger LOGGER = Logger.getLogger(TransactionsApplication.class);

  public static void main(String[] args) {
    Connection connection = null;
    try {
      connection = getConnection();
    } catch (Exception e) {
      e.printStackTrace();
      LOGGER.error(e.getMessage());
    } finally {
      try {
        connection.close();
      } catch (SQLException sqlE) {
        sqlE.printStackTrace();
        LOGGER.error(sqlE.getMessage());
      }
    }
  }

  private static Connection getConnection() throws ClassNotFoundException, SQLException {
    Class.forName("org.h2.Driver");
    return DriverManager.getConnection("jdbc:h2:~/transactions", "", "");
  }
}
