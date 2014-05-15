package com.cool4code.billeteka;

/**
 * Created by COOL4CODE dev team on 5/3/14.
 * David Almeciga Ayala
 * Paola Vanegas
 * Marcos Aguilera Ely
 * Alejandro Zarate
 **/
public class BillsBean {
    private String idParse ,dia ,mes ,ano ,denominacion ,serie ,descripcion ,tiempo ,f1_4 ,f5_7 ,f8_10 ,p1_4 ,p5_7 ,p8_10 ,bernardom13 ,createdAt ,updatedAt;

    public String getDenominacion(){
        return denominacion;
    }

    public void setDenominacion(String denominacion){
        this.denominacion= denominacion;
    }

    public String getAno(){
        return ano;
    }

    public void setAno(String ano){
        this.ano= ano;
    }

    public String getIdParse(){
        return idParse;
    }

    public void setIdParse(String idParse){
        this.idParse= idParse;
    }

    public BillsBean(String idParse, String denominacion, String ano){
        super();
        this.idParse= idParse;
        this.denominacion= denominacion;
        this.ano= ano;
    }
    @Override
    public String toString(){
        return this.denominacion;
    }

}
