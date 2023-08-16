package com.librarysystem.models;

public class MemberRecord {
    private String memberID;
    private MemberType type;
    private String dateOfMembership;
    private int noBooksIssued = 0;
    private int maxBookLimit = 5;
    private String name;
    private String address;
    private String phoneNo;

    public MemberRecord(String memberID, MemberType type, String dateOfMembership, int noBooksIssued, int maxBookLimit, String name, String address, String phoneNo) {
        this.memberID = memberID;
        this.type = type;
        this.dateOfMembership = dateOfMembership;
        this.noBooksIssued = noBooksIssued;
        this.maxBookLimit = maxBookLimit;
        this.name = name;
        this.address = address;
        this.phoneNo = phoneNo;
    }

    public MemberRecord(String memberID, String type, String name) {
        this.memberID = memberID;
        this.type = MemberType.valueOf(type);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getMemberID() {
        return memberID;
    }

    public int getNoBooksIssued() {
        return noBooksIssued;
    }

    public int getMaxBookLimit() {
        return maxBookLimit;
    }

    public void borrowBook() {
        noBooksIssued++;

    }

    public void incBookIssued() {
        noBooksIssued++;
    }

    public void setMembershipType(String updatedMembershipType) {
        type = MemberType.valueOf(updatedMembershipType);

    }

    public void decBookIssued() {
        noBooksIssued--;
    }


    public String getMemberType() {
        return type.toString();
    }

}


