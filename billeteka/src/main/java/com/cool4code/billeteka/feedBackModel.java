package com.cool4code.billeteka;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by marcosantonioaguilerely on 5/25/14.
 */
@ParseClassName("feedback")
public class feedBackModel extends ParseObject{
    String nombre, correo, tipo, comentario;

    public feedBackModel(){
        /*this.setComentario("xxxx");
        this.setNombre("xxxxx");
        this.setTipo("xxxx");
        this.setCorreo("@@@@@");*/
    }

    public String getNombre(String nombre){
        return getNombre("nombre");
    }

    public void setNombre(String _setNombre){
        put("nombre", _setNombre);
    }

    public String getCorreo(String correo){
        return getCorreo("correo");
    }

    public void setCorreo(String _setCorreo){
        put("correo", _setCorreo);
    }

    public String getTipo(String tipo){
        return getTipo("tipofeedback");
    }

    public void setTipo(String _setTipo){
        put("tipofeedback", _setTipo);
    }

    public String getComentario(String comentario){
        return getComentario("comentario");
    }

    public void setComentario(String _setComentario){
        put("comentario", _setComentario);
    }
}
