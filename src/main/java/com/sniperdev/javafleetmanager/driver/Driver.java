package com.sniperdev.javafleetmanager.driver;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "drivers")
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Pattern(regexp = "[a-zA-Z]+", message = "It must consist of letters")
    @NotBlank(message = "First name is mandatory")
    private String firstName;

    @Pattern(regexp = "[a-zA-Z]+", message = "It must consist of letters")
    @NotBlank(message = "Last name is mandatory")
    private String lastName;

    @Pattern(regexp = "^[0-9]{9}$", message = "It must be 9 digits long")
    @NotBlank(message = "Phone number is mandatory")
    private String phoneNumber;

    @Email(message = "It must be an email")
    @NotBlank(message = "Email is mandatory")
    private String email;

    @Pattern(regexp = "^\\d{4}\\-(0[1-9]|1[012])\\-(0[1-9]|[12][0-9]|3[01])$", message = "It must be a date in format YYYY-MM-DD")
    @NotBlank(message="Birth date is mandatory")
    private String birthDate;

    @Pattern(regexp = "^[0-9A-Z]{9}$", message = "It must be 9 characters long")
    @NotBlank(message="License number is mandatory")
    private String drivingLicenseNumber;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthDate() {
        return birthDate;
    }
    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getDrivingLicenseNumber() {
        return drivingLicenseNumber;
    }
    public void setDrivingLicenseNumber(String drivingLicenseNumber) {
        this.drivingLicenseNumber = drivingLicenseNumber;
    }
}
