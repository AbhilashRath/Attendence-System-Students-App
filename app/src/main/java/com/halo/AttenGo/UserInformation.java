package com.halo.AttenGo;

public class UserInformation {

    public String Name;
    public String RollNo;
    public Integer Chemistry;
    public Integer Physics;
    public Integer Petroleum;
    public Integer Maths;


    public UserInformation(){

    }

    public UserInformation(String name, String rollNo, Integer chemistry, Integer physics, Integer petroleum, Integer maths) {
        Name = name;
        RollNo = rollNo;
        Chemistry = chemistry;
        Physics = physics;
        Petroleum = petroleum;
        Maths = maths;
    }
}
