package com.umadev.transactions;

import com.umadev.transactions.db.DataBaseConnection;
import com.umadev.transactions.model.Dentist;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.log4j.Logger;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/** Shows the boilerplate code to create transacions with barefoot JDBC and H2. */
@SpringBootApplication
public class TransactionsApplication {

  private static final Logger LOGGER = Logger.getLogger(TransactionsApplication.class);
  private static final String SQL_CREATE =
      "DROP TABLE IF EXISTS DENTIST;"
          + "CREATE TABLE DENTIST (ID INT AUTO_INCREMENT PRIMARY KEY,"
          + "SURNAME VARCHAR(50) NOT NULL, NAME VARCHAR(50) NOT NULL, "
          + "REGNUMBER VARCHAR(50) NOT NULL)";
  public static final String SQL_INSERT = "INSERT INTO DENTIST VALUES (DEFAULT, ?,?,?)";
  public static final String SQL_UPDATE = "UPDATE DENTIST SET REGNUMBER = ? WHERE ID=?";
  public static final String SQL_SELECT = "SELECT * FROM DENTIST";

  public static void main(String[] args) {
    Connection connection = null;
    Dentist dentistFromDb = null;
    Dentist dentist = new Dentist("perez", "sr. dentist", "12345");

    try {
      connection = DataBaseConnection.getConnection();

      // CREATE DB TABLE
      Statement statement = connection.createStatement();
      statement.execute(SQL_CREATE);

      // INSERT A NEW ROW
      // parameterized query
      PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT);
      preparedStatement.setString(1, dentist.getSurname());
      preparedStatement.setString(2, dentist.getName());
      preparedStatement.setString(3, dentist.getRegistrationNumber());
      preparedStatement.execute();

      ResultSet resultSet = statement.executeQuery(SQL_SELECT);
      while (resultSet.next()) {
        Integer id = resultSet.getInt(1);
        String surname = resultSet.getString(2);
        String name = resultSet.getString(3);
        String registrationNumber = resultSet.getString(4);
        dentistFromDb = new Dentist(id, surname, name, registrationNumber);
      }
      dentist.setId(dentistFromDb.getId());
      LOGGER.info("The inserted dentist is:" + dentist.toString());

      // UPDATE A RECORD (USING TRANSACTION)
      // disable auto commit to make the transaciton or rollback
      connection.setAutoCommit(false);
      String newRegistrationNumber = "54321";
      try {
        preparedStatement = connection.prepareStatement(SQL_UPDATE);
        preparedStatement.setString(1, newRegistrationNumber);
        preparedStatement.setInt(2, dentist.getId());
        preparedStatement.execute();
        connection.commit();
      } catch (Exception e) {
        connection.rollback();
        LOGGER.error("TRANSACTION ERROR: " + e.getMessage());
      }

      // set autocomit to default (recommended) state.
      connection.setAutoCommit(true);

      // expected dentist:
      dentist.setRegistrationNumber(newRegistrationNumber);

      // actual dentist in the DB
      resultSet = statement.executeQuery(SQL_SELECT);
      while (resultSet.next()) {
        Integer id = resultSet.getInt(1);
        String surname = resultSet.getString(2);
        String name = resultSet.getString(3);
        String registrationNumber = resultSet.getString(4);
        dentistFromDb = new Dentist(id, surname, name, registrationNumber);
      }
      LOGGER.info("Expected dentist: " + dentist.toString());
      LOGGER.info("Dentist in DB: " + dentistFromDb.toString());

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
}
