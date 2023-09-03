package first.app.e_gouvernance.model;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import first.app.e_gouvernance.util.CallWS;
import first.app.e_gouvernance.util.FormatDate;
import first.app.e_gouvernance.util.ListCallBack;
import first.app.e_gouvernance.util.SingleCallBack;
import first.app.e_gouvernance.util.WSCallBack;

public class Soumission {

    private String id;
    private Society society;
    private String dateSoumission;
    private Tender tender;

    private Integer status;// Reject : 0 , Valid : 1

    public Soumission() {
    }

    public Soumission(String id, Society society, String dateSoumission, Tender tender, Integer status) {
        this.id = id;
        this.society = society;
        this.dateSoumission = dateSoumission;
        this.tender = tender;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Society getSociety() {
        return society;
    }

    public void setSociety(Society society) {
        this.society = society;
    }

    public String getDateSoumission() {
        return dateSoumission;
    }

    public void setDateSoumission(String dateSoumission) {
        this.dateSoumission = dateSoumission;
    }

    public Tender getTender() {
        return tender;
    }

    public void setTender(Tender tender) {
        this.tender = tender;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void getListSoumission(ListCallBack callback) {
        CallWS webServiceCall = new CallWS();
        String url = "/soumissions";
        FormatDate formatDate = new FormatDate();

        webServiceCall.responseGet(url, null, new WSCallBack() {
            @Override
            public void onSuccess(String response) {

                Gson gson = new Gson();
                JsonElement responseElement = gson.fromJson(response, JsonElement.class);
                JsonObject responseObject = responseElement.getAsJsonObject();

                JsonArray soumissionsArray = responseObject.getAsJsonArray("soumissions");
                List<Soumission> listSoumission = new ArrayList<>();

                for (JsonElement jsonElement : soumissionsArray) {
                    if (jsonElement.isJsonObject()) {
                        JsonObject jsonObject = jsonElement.getAsJsonObject();
                        String id = jsonObject.get("_id").getAsString();
                        String dateSoumission = jsonObject.get("dateSoumission").getAsString();
                        Integer status = jsonObject.get("status").getAsInt();
                        JsonObject society = jsonObject.getAsJsonObject("society");
                        JsonObject tender = jsonObject.getAsJsonObject("tender");


                        String societyName = society.get("name").getAsString();
                        String societyNif = society.get("nif").getAsString();
                        String societyStat = society.get("stat").getAsString();
                        String societyPass = society.get("password").getAsString();
                        Society newSociety = new Society(societyName, societyNif, societyStat, societyPass);


                        String tenderRef = tender.get("reference").getAsString();
                        String tenderTitle = tender.get("title").getAsString();
                        String tenderDesc = tender.get("description").getAsString();
                        String tenderDateEmission = tender.get("dateEmission").getAsString();
                        String tenderDateLimit = tender.get("dateLimit").getAsString();
                        Integer tenderStatus = tender.get("tenderStatus").getAsInt();
                        JsonArray critere = tender.get("critere").getAsJsonArray();
                        List<Critere> critereList = new ArrayList<>();
                        for (JsonElement critereElement : critere) {
                            JsonObject critereObject = critereElement.getAsJsonObject();
                            String entitle = critereObject.get("entitle").getAsString();
                            String description = critereObject.get("description").getAsString();
                            Critere critereItem = new Critere(entitle, description);
                            critereList.add(critereItem);
                        }
                        Tender newTender = new Tender(tenderRef, tenderTitle, tenderDesc, critereList, formatDate.getDateFormatted(tenderDateEmission), formatDate.getDateFormatted(tenderDateLimit), tenderStatus);


                        Soumission newSoumission = new Soumission(id, newSociety, formatDate.getDateFormatted(dateSoumission), newTender, status);
                        listSoumission.add(newSoumission);

                    }
                }

                callback.onListeResult(listSoumission);
            }

            @Override
            public void onFailure(int statusCode) {
                callback.onListeResult(null);
            }
        });
    }


    public void getSoumission(SingleCallBack callback, String id) {
        CallWS webServiceCall = new CallWS();
        String url = "/soumissions/" + id;
        FormatDate formatDate = new FormatDate();


        webServiceCall.responseGet(url, null, new WSCallBack() {
            @Override
            public void onSuccess(String response) {

                Gson gson = new Gson();
                JsonElement responseElement = gson.fromJson(response, JsonElement.class);
                JsonObject responseObject = responseElement.getAsJsonObject();


                JsonObject jsonObject = responseObject.getAsJsonObject("soumission");
                String id = jsonObject.get("_id").getAsString();
                String dateSoumission = jsonObject.get("dateSoumission").getAsString();
                Integer status = jsonObject.get("status").getAsInt();
                JsonObject society = jsonObject.getAsJsonObject("society");
                JsonObject tender = jsonObject.getAsJsonObject("tender");


                String societyName = society.get("name").getAsString();
                String societyNif = society.get("nif").getAsString();
                String societyStat = society.get("stat").getAsString();
                String societyPass = society.get("password").getAsString();
                Society newSociety = new Society(societyName, societyNif, societyStat, societyPass);


                String tenderRef = tender.get("reference").getAsString();
                String tenderTitle = tender.get("title").getAsString();
                String tenderDesc = tender.get("description").getAsString();
                String tenderDateEmission = tender.get("dateEmission").getAsString();
                String tenderDateLimit = tender.get("dateLimit").getAsString();
                Integer tenderStatus = tender.get("tenderStatus").getAsInt();
                JsonArray critere = tender.get("critere").getAsJsonArray();
                List<Critere> critereList = new ArrayList<>();
                for (JsonElement critereElement : critere) {
                    JsonObject critereObject = critereElement.getAsJsonObject();
                    String entitle = critereObject.get("entitle").getAsString();
                    String description = critereObject.get("description").getAsString();
                    Critere critereItem = new Critere(entitle, description);
                    critereList.add(critereItem);
                }
                Tender newTender = new Tender(tenderRef, tenderTitle, tenderDesc, critereList, formatDate.getDateFormatted(tenderDateEmission), formatDate.getDateFormatted(tenderDateLimit), tenderStatus);


                Soumission newSoumission = new Soumission(id, newSociety, formatDate.getDateFormatted(dateSoumission), newTender, status);

                callback.onSingleResult(newSoumission);
            }

            @Override
            public void onFailure(int statusCode) {
                callback.onSingleResult(null);
            }
        });
    }
}
