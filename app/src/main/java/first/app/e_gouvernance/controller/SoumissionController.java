package first.app.e_gouvernance.controller;

import first.app.e_gouvernance.model.Soumission;
import first.app.e_gouvernance.util.ListCallBack;
import first.app.e_gouvernance.util.SingleCallBack;

public final class SoumissionController {

    private static SoumissionController instance = null;

    private SoumissionController() {
        super();
    }

    public static final SoumissionController getInstance() {
        if (SoumissionController.instance == null) {
            SoumissionController.instance = new SoumissionController();
        }
        return SoumissionController.instance;
    }

    public void getAllSoumission(ListCallBack callBack) {
        Soumission soumission = new Soumission();
        soumission.getListSoumission(callBack);
    }

    public void getSoumission(SingleCallBack callBack, String id) {
        Soumission soumission = new Soumission();

        soumission.getSoumission(callBack, id);
    }


}


