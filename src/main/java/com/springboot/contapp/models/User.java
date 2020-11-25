package com.springboot.contapp.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "user_ID")
    private int id;
    private int firmId;
    private String username;
    private String password;
    private String email;
    private String role;

    public User() {
    }

    public User(int id, int firmId, String username, String password, String email, String role) {
        this.id = id;
        this.firmId = firmId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFirmId() {
        return firmId;
    }

    public void setFirmId(int firmId) {
        this.firmId = firmId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public static boolean isCNPValid(String CNP) {
        int CNPNumber;
        if(CNP.length() != 13)
        return false;

        try {
            CNPNumber = Integer.parseInt(CNP);
        }
        catch (NumberFormatException exception) {
            exception.printStackTrace();
            return false;
        }
        
        if (!isFirstNumberValid(CNP.charAt(0)))
            return false;
        return false;
    }

    public static boolean isFirstNumberValid (char numberChar) {
        int number = Integer.parseInt("" + numberChar);
        if (number < 1 || number > 13)
            return false;
        return true;
    }
//
//    public static boolean isDateValid (String date) {
//        int month = Integer.parseInt(date.substring(2,4));
//        int day = Integer.parseInt(date.substring(4,6));
//        if (month > 12)
//            return false;
//        String dateFormatted =
//        DateFormat dateFormat = new SimpleDateFormat("991120");
//        dateFormat.setLenient(false);
//        try {
//            dateFormat.parse(date);
//        } catch (ParseException e) {
//            return false;
//        }
//        return true;
//    }
}
