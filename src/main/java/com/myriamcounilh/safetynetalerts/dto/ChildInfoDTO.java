package com.myriamcounilh.safetynetalerts.dto;

import java.util.List;

public class ChildInfoDTO {

    private String firstname;
    private String lastname;
    private int age;
    private List<PersonInfoLiteDTO> memberOfFamily;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<PersonInfoLiteDTO> getMemberOfFamily() {
        return memberOfFamily;
    }

    public void setMemberOfFamily(List<PersonInfoLiteDTO> memberOfFamily) {
        this.memberOfFamily = memberOfFamily;
    }
}
