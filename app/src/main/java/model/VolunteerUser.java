package model;


public class  VolunteerUser extends User {
    private String cognome;
    private String nascita;
    private String cc;
    private String latitudine;
    private String longitudine;


    public VolunteerUser(int id,String email,String nome, String cognome,String tipo,String latitudine, String longitudine) {
        super(id,nome, email,"Volontario" );
        this.cognome = cognome;
        this.latitudine = latitudine;
        this.longitudine = longitudine;

    }

    public VolunteerUser( String nome, String cognome, String password, String indirizzo, String recapitoTel, String email, String nascita, String citta) {
        super(nome, password, indirizzo, recapitoTel, email,   citta, "Volontario");
        this.cognome = cognome;
        this.nascita = nascita;
        this.cc = "";
    }

    public String getNascita() {
        return nascita;
    }

    public void setNascita(String nascita) {
        this.nascita = nascita;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public String getCognome() {

        return this.cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;

    }

    public String getLongitudine() {
        return longitudine;
    }

    public void setLongitudine(String longitudine) {
        this.longitudine = longitudine;
    }

    public String getLatitudine() {
        return latitudine;
    }

    public void setLatitudine(String latitudine) {
        this.latitudine = latitudine;
    }
}

