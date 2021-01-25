package com.example.proyectomodular.model;

public class Contacto {

    private String nombre,numero,email;
    private boolean enviar;

    public Contacto(String nombre, String numero, String email, boolean enviar) {
        this.nombre = nombre;
        this.numero = numero;
        this.email = email;
        this.enviar = enviar;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isEnviar() {
        return enviar;
    }

    public void setEnviar(boolean enviar) {
        this.enviar = enviar;
    }

    @Override
    public String toString() {
        return "Contacto{" +
                "nombre='" + nombre + '\'' +
                ", numero='" + numero + '\'' +
                ", email='" + email + '\'' +
                ", enviar=" + enviar +
                '}';
    }
}
