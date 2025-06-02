package tn.hr.compteur.technicien.enitite;

public class LoginRequest {
    private String nom;
    private String password;

    public LoginRequest() {}
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}

