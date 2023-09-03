package first.app.e_gouvernance.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import first.app.e_gouvernance.R;
import first.app.e_gouvernance.controller.SoumissionController;
import first.app.e_gouvernance.model.DetailItem;
import first.app.e_gouvernance.model.Soumission;
import first.app.e_gouvernance.util.Authorization;
import first.app.e_gouvernance.util.SingleCallBack;

public class SoumissionDetails extends AppCompatActivity {
    private SoumissionController soumissionController;
    private String id;

    private TableLayout tableLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soumission_details);

        initDetail();
        this.soumissionController = soumissionController.getInstance();
        getSoumissionDetail();
    }

    private void initDetail() {
        Authorization auth = new Authorization();
        if (!auth.verifyUser(SoumissionDetails.this)) {
            Intent intent = new Intent(SoumissionDetails.this, MainActivity.class);
            startActivity(intent);
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Detail");
        // Récupérer la valeur transmise depuis l'Intent
        id = getIntent().getStringExtra("id");
        tableLayout = findViewById(R.id.tableLayout);
    }

    private void getSoumissionDetail() {

        this.soumissionController.getSoumission(new SingleCallBack() {
            @Override
            public void onSingleResult(Soumission soumission) {
                List<DetailItem> detailItemList = new ArrayList<>();

                detailItemList.add(new DetailItem("Numero de la soumission", soumission.getId()));
                detailItemList.add(new DetailItem("Titre de l'appel d'offre", soumission.getTender().getTitle()));
                detailItemList.add(new DetailItem("Date de soumission", soumission.getDateSoumission()));
                String statusAsString = "none";
                if (soumission.getStatus() == 0) {
                    statusAsString = "Rejeté";
                } else if (soumission.getStatus() == 1) {
                    statusAsString = "Validé";
                }
                detailItemList.add(new DetailItem("Statut", statusAsString));
                detailItemList.add(new DetailItem("Document fournis", ""));
                detailItemList.add(new DetailItem("Reference", soumission.getTender().getRef()));
                detailItemList.add(new DetailItem("Description", soumission.getTender().getDescription()));
                detailItemList.add(new DetailItem("Date d'émission", soumission.getTender().getDateEmission()));
                detailItemList.add(new DetailItem("Date limite", soumission.getTender().getDateLimit()));
                // detailItemList.add(new DetailItem("Critères associés", soumission.getTender().getCritere()));

                for (DetailItem detailItem : detailItemList) {
                    TableRow newRow = (TableRow) LayoutInflater.from(SoumissionDetails.this).inflate(R.layout.row_detail_item, null);
                    TextView textViewType = newRow.findViewById(R.id.textViewType);
                    TextView textViewDetail = newRow.findViewById(R.id.textViewDetail);

                    textViewType.setText(detailItem.getType());
                    textViewDetail.setText(detailItem.getDetail());

                    tableLayout.addView(newRow);
                }
            }
        }, id);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}