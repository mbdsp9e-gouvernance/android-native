package first.app.e_gouvernance.model;

public class DetailItem {
    private String type;
    private String detail;

    public DetailItem(String type, String detail) {
        this.type = type;
        this.detail = detail;
    }

    public String getType() {
        return type;
    }

    public String getDetail() {
        return detail;
    }
}
