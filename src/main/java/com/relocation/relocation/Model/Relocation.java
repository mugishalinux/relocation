package com.relocation.relocation.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

@Entity
public class Relocation {

    @Id
    private String serialNumber;
    private String newAddress;
    private String telNo;
    @Lob
    @Column(columnDefinition = "BIGINT")
    private String picture;
    private String eligibility;
    private String txid;
    private String schedule;

    public Relocation() {
    }

    public Relocation(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Relocation(String serialNumber, String newAddress, String telNo, String picture, String eligibility, String txid, String schedule) {
        this.serialNumber = serialNumber;
        this.newAddress = newAddress;
        this.telNo = telNo;
        this.picture = picture;
        this.eligibility = eligibility;
        this.txid = txid;
        this.schedule = schedule;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getNewAddress() {
        return newAddress;
    }

    public void setNewAddress(String newAddress) {
        this.newAddress = newAddress;
    }

    public String getTelNo() {
        return telNo;
    }

    public void setTelNo(String telNo) {
        this.telNo = telNo;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getEligibility() {
        return eligibility;
    }

    public void setEligibility(String eligibility) {
        this.eligibility = eligibility;
    }

    public String getTxid() {
        return txid;
    }

    public void setTxid(String txid) {
        this.txid = txid;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }
}
