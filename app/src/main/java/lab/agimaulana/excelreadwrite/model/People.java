package lab.agimaulana.excelreadwrite.model;

/**
 * Created by root on 8/13/16.
 */
public class People {
    private String name;
    private String job;
    private String phone;
    private String email;
    private String address;

    public People() {
    }

    public People( String name, String job, String phone, String email, String address) {
        this.name = name;
        this.job = job;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
