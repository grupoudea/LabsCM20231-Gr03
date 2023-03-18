package co.edu.udea.compumovil.gr03_20231.lab1.dto;

import java.util.Date;

public class InformacionContactoDto {
    private int telefono;
    private String correo;
    private String pais;
    private String ciudad;
    private String direccion;

    public int gettelefono() {
        return telefono;
    }

    public void settelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {

        this.correo = correo;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {

        this.pais = pais;
    }


    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
