package hu.petrik.etlap;

public class Etlap {
    private int id;
    private int ar;
    private String nev;
    private String kategoria;


    public Etlap(int id,int ar, String nev, String kategoria) {
        this.id = id;
        this.ar = ar;
        this.nev = nev;
        this.kategoria = kategoria;

    }
    public int getId() {
        return ar;
    }

    public int getAr() {
        return ar;
    }

    public String getNev() {
        return nev;
    }

    public String getKategoria() {
        return kategoria;
    }

    public void setNev(String nev) {
        this.nev = nev;
    }

    public void setKategoria(String kategoria) {
        this.kategoria = kategoria;
    }



    public void setAr(int ar) {
        this.ar = ar;
    }
    public void setId(int id) {
        this.id = id;
    }



}

