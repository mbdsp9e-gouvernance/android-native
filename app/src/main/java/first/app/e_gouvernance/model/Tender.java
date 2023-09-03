package first.app.e_gouvernance.model;

import java.util.ArrayList;
import java.util.List;

public class Tender {
    private String ref;
    private String title;
    private String description;
    private List<Critere> critere;
    private String dateEmission;
    private String dateLimit;
    private Integer tenderStatus;

    public Tender() {
    }

    public Tender(String ref, String title, String description, List<Critere> critere, String dateEmission, String dateLimit, Integer tenderStatus) {
        this.ref = ref;
        this.title = title;
        this.description = description;
        this.critere = critere;
        this.dateEmission = dateEmission;
        this.dateLimit = dateLimit;
        this.tenderStatus = tenderStatus;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Critere> getCritere() {
        return critere;
    }

    public void setCritere(List<Critere> critere) {
        this.critere = critere;
    }

    public String getDateEmission() {
        return dateEmission;
    }

    public void setDateEmission(String dateEmission) {
        this.dateEmission = dateEmission;
    }

    public String getDateLimit() {
        return dateLimit;
    }

    public void setDateLimit(String dateLimit) {
        this.dateLimit = dateLimit;
    }

    public Integer getTenderStatus() {
        return tenderStatus;
    }

    public void setTenderStatus(Integer tenderStatus) {
        this.tenderStatus = tenderStatus;
    }
}
