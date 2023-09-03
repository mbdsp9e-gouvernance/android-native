package first.app.e_gouvernance.model;

public class Society {
    private  String name;
    private  String nif;
    private String stat;
    private String password;

    public Society() {
    }

    public Society(String name, String nif, String stat, String password) {
        this.name = name;
        this.nif = nif;
        this.stat = stat;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
