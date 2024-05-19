package com.umadev.transactions.model;

/** Entity of the project. */
public class Dentist {

  private Integer id;
  private String surname;
  private String name;
  // we use strings when we do not want to operate with a number
  private String registrationNumber;

  /**
   * Contructor of the entity with id from DB.
   *
   * @param id the id of the dentist in the db.
   * @param surname the surname of the dentist.
   * @param name the name of the dentist.
   * @param registrationNumber the registration number of the dentist.
   */
  public Dentist(Integer id, String surname, String name, String registrationNumber) {
    this.id = id;
    this.surname = surname;
    this.name = name;
    this.registrationNumber = registrationNumber;
  }

  /**
   * Contructor of the entity without id, intended to insert to db.
   *
   * @param surname the surname of the dentist.
   * @param name the name of the dentist.
   * @param registrationNumber the registration number of the dentist.
   */
  public Dentist(String surname, String name, String registrationNumber) {
    this.surname = surname;
    this.name = name;
    this.registrationNumber = registrationNumber;
  }

  public Integer getId() {
    return id;
  }

  public String getSurname() {
    return surname;
  }

  public String getName() {
    return name;
  }

  public String getRegistrationNumber() {
    return registrationNumber;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public void setRegistrationNumber(String registrationNumber) {
    this.registrationNumber = registrationNumber;
  }

  @Override
  public String toString() {
    return "Dentist #" + id + ", " + name + " " + surname + ", " + registrationNumber;
  }
}
