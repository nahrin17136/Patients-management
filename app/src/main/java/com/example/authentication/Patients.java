package com.example.authentication;

public class Patients {
    private String Name;
    private String Age;
    private String Medicines;
    private String Serial_no;

    public Patients() {

    }
    public Patients(String name, String age, String medicines, String serial_no) {
        this.Name = name;
        this.Age = age;
        this.Medicines = medicines;
        this.Serial_no = serial_no;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        this.Age = age;
    }

    public String getMedicines() {
        return Medicines;
    }

    public void setMedicines(String medicines) {
        this.Medicines = medicines;
    }

    public String getSerial_no() {
        return Serial_no;
    }

    public void setSerial_no(String serial_no) {
        this.Serial_no = serial_no;
    }

}
