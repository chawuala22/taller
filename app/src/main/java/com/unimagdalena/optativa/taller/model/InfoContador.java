package com.unimagdalena.optativa.taller.model;

import java.io.Serializable;
import java.util.Date;

public class InfoContador implements Serializable {
    int id;
    String barrio;
    String direccion;
    int valor;
    long idSpTipo;
    String nameSpTipo;
    long fecha_creacion;

    public InfoContador(int id, String barrio, String direccion, int valor, String nameSpTipo, long idSpTipo) {
        this.id = id;
        this.barrio = barrio;
        this.direccion = direccion;
        this.valor = valor;
        this.idSpTipo = idSpTipo;
        this.nameSpTipo = nameSpTipo;
    }

    public InfoContador(String barrio, String direccion, int valor, String nameSpTipo, long idSpTipo) {
        this.barrio = barrio;
        this.direccion = direccion;
        this.valor = valor;
        this.idSpTipo = idSpTipo;
        this.nameSpTipo = nameSpTipo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBarrio() {
        return barrio;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public long getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(long fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public long getIdSpTipo() {
        return idSpTipo;
    }

    public void setIdSpTipo(int idSpTipo) {
        this.idSpTipo = idSpTipo;
    }

    public String getNameSpTipo() {
        return nameSpTipo;
    }

    public void setNameSpTipo(String nameSpTipo) {
        this.nameSpTipo = nameSpTipo;
    }
}
