package com.cool4code.billeteka;

/**
 * Created by marcosantonioaguilerely on 5/3/14.
 **/
public class BillsBean {
    private String objectId ,dia ,mes ,ano ,denominacion ,serie ,descripcion ,tiempo ,f1_4 ,f5_7 ,f8_10 ,p1_4 ,p5_7 ,p8_10 ,bernardom13 ,createdAt ,updatedAt;

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

    public BillsBean(String denominacion, String ano){
        super();
        this.denominacion= denominacion;
        this.ano= ano;
    }
    @Override
    public String toString(){
        return this.denominacion;
    }

}
