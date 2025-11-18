package com.bryan.aplicacionweb.manejosesiones.services;

public class ServiceJdbcException extends RuntimeException {
    /*
    * Implementamos un constructor donde recibe como parametro un mensaje
    * Luego llamamos a la clase constructor de la clase padre para que
    * lance la excepcion
    * */
    public ServiceJdbcException(String mensaje){
        super(mensaje);
    }
    /*
    * Implementamos un constructoe deonde recibe dos parametros como
    * el mensaje y la causa de la excepcion
    *Luego se invoca at constructor de la clase padre donde devuelve
    *la causa de forma técnica*/
    public ServiceJdbcException(String mensaje, Throwable cause){
        super(cause);
    }
}
