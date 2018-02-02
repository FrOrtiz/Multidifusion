package com.example.dam.multidifusion;


import java.util.Date;

public class Llamada {

//    private int state;
    private String state;
    private String phoneNum, tipo;
    private Date date;

    public Llamada() {
    }

    public Llamada(String state, String phoneNum, String tipo, Date date) {
        this.state = state;
        this.phoneNum = phoneNum;
        this.tipo = tipo;
        this.date = date;
    }

    public Llamada(String state, String phoneNum, String tipo) {
        this.state = state;
        this.phoneNum = phoneNum;
        this.tipo = tipo;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

//    @Override
//    public String toString() {
//        return "Llamada{" +
//                "state='" + state + '\'' +
//                ", phoneNum='" + phoneNum + '\'' +
//                ", tipo='" + tipo + '\'' +
//                ", date=" + date +
//                '}';
//    }

    @Override
    public String toString() {
        return "Llamada{" +
                "state='" + state + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", tipo='" + tipo + '\'' +
                '}';
    }
}
