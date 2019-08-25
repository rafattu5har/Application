package edu.northsouth.application;

/**
 * Customer type class
 * @author tushar
 * @version 1.0
 */
public class CustomerId
{
    private String name;
    private String email;
    private String gender;
    private String location;
    private String phn_no;

    /**
     * This is constructor for CustomerId class
     */
    public CustomerId() {

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPhn_no() {
        return phn_no;
    }

    public void setPhn_no(String phn_no) {
        this.phn_no = phn_no;
    }
}
