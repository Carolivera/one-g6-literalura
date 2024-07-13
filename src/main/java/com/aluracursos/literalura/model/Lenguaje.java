package com.aluracursos.literalura.model;

public enum Lenguaje {
    EN("en","Inglés"),
    ES("es", "Español");


    private String lenguajeApi;
    private String lenguajeEspañol;
    Lenguaje(String lenguajeApi, String lenguajeEspañol){
        this.lenguajeApi = lenguajeApi;
        this.lenguajeEspañol = lenguajeEspañol;
    }
    public static Lenguaje fromString(String text){
        for(Lenguaje lenguaje : Lenguaje.values()){
            if(lenguaje.lenguajeApi.equalsIgnoreCase(text)){
                return lenguaje;
            }
        }
        throw new IllegalArgumentException("No se encontró un lenguaje llamado: " + text +
                ". Intente nuevamente");
    }

}
