package first.app.e_gouvernance.model;

public class Critere {
    private String entitle;
    private  String description;

    public Critere() {
    }

    public Critere(String entitle, String description) {
        this.entitle = entitle;
        this.description = description;
    }

    public String getEntitle() {
        return entitle;
    }

    public void setEntitle(String entitle) {
        this.entitle = entitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
