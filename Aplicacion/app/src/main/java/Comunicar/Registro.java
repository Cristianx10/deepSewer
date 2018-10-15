package Comunicar;

import java.io.Serializable;

public class Registro implements Serializable{
    public String name;
    public String ip;
    public int puerto;

    public Registro(String name, String ip, int puerto){
        this.name = name;
        this.ip = ip;
        this.puerto = puerto;
    }
}
