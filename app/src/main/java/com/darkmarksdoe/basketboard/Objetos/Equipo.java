package com.darkmarksdoe.basketboard.Objetos;


public class Equipo {
    private long Conferencia;
    private String Sede;
    private String Nombre;
    private String ID;

    public Equipo() {
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public long getConferencia() {
        return Conferencia;
    }

    public void setConferencia(long conferencia) {
        Conferencia = conferencia;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String  getSede() {
        return Sede;
    }

    public void setSede(String sede) {
        Sede = sede;
    }

}
