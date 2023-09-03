package first.app.e_gouvernance.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.views.MapView;

import first.app.e_gouvernance.BuildConfig;
import first.app.e_gouvernance.R;

public class Maps extends Fragment {

    private MapView mapView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_maps, container, false);

        mapView = rootView.findViewById(R.id.osm_map);

        // Configuration OSMDroid (peut être omise si vous utilisez les paramètres par défaut)
        Configuration.getInstance().setUserAgentValue(BuildConfig.APPLICATION_ID);
        mapView.setTileSource(TileSourceFactory.MAPNIK);

        // Centrez la carte sur Madagascar
        mapView.getController().setCenter(new org.osmdroid.util.GeoPoint(-18.8792, 47.5079));
        mapView.getController().setZoom(6);

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }
}
