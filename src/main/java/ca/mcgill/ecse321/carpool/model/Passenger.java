package ca.mcgill.ecse321.carpool.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Passenger{
   private String username;

public void setUsername(String value) {
    this.username = value;
}
public String getUsername() {
    return this.username;
}
private String password;

public void setPassword(String value) {
    this.password = value;
}
public String getPassword() {
    return this.password;
}
private String displayName;

public void setDisplayName(String value) {
    this.displayName = value;
}
public String getDisplayName() {
    return this.displayName;
}
private Integer phoneNumber;

public void setPhoneNumber(Integer value) {
    this.phoneNumber = value;
}
public Integer getPhoneNumber() {
    return this.phoneNumber;
}
   private Vehicle vehicle;
   
   @ManyToOne(optional=false)
   public Vehicle getVehicle() {
      return this.vehicle;
   }
   
   public void setVehicle(Vehicle vehicle) {
      this.vehicle = vehicle;
   }
   
   }