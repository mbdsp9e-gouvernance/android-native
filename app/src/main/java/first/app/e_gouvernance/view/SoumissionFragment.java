package first.app.e_gouvernance.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import first.app.e_gouvernance.R;
import first.app.e_gouvernance.controller.SoumissionController;
import first.app.e_gouvernance.model.Soumission;
import first.app.e_gouvernance.util.Authorization;
import first.app.e_gouvernance.util.ListCallBack;
import first.app.e_gouvernance.util.TableAdapter;

public class SoumissionFragment extends Fragment {

    private RecyclerView recyclerView;
    private TableAdapter tableAdapter;
    private SoumissionController soumissionController;
    private ProgressBar loadingProgressBar;
    private List<Soumission> dataList = new ArrayList<>();

    public SoumissionFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_soumission, container, false);
        initListe(view);
        this.soumissionController = soumissionController.getInstance();
        getListSoumission();

        return view;
    }

    private void initListe(View view) {
        Authorization auth = new Authorization();
        if (!auth.verifyUser(requireActivity())) {
            Intent intent = new Intent(requireActivity(), MainActivity.class);
            startActivity(intent);
        }
        recyclerView = view.findViewById(R.id.recyclerView);
        loadingProgressBar = view.findViewById(R.id.progressBar);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void getListSoumission() {
        //   loadingProgressBar.setVisibility(View.VISIBLE);
        this.soumissionController.getAllSoumission(new ListCallBack() {
            @Override
            public void onListeResult(List<Soumission> soumissionList) {
                tableAdapter = new TableAdapter(soumissionList);
                recyclerView.setAdapter(tableAdapter);
            }
        });
        // loadingProgressBar.setVisibility(View.GONE);
    }
}