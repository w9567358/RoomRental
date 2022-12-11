package uk.ac.tees.w9567358.aad.roomrental.ui;

import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import uk.ac.tees.w9567358.aad.roomrental.R;
import uk.ac.tees.w9567358.aad.roomrental.util.map.DrawDistance;

public class MapsFragment extends Fragment {
    private MapsFragmentArgs args;
    private OnMapReadyCallback callback = new OnMapReadyCallback() {
    private DrawDistance drawMap = new DrawDistance();
    public  Location mLocation;
    Button pathBtn;


        @Override
        public void onMapReady(GoogleMap googleMap) {
            LatLng start= new LatLng(54.5683717, -1.2393065);
            LatLng end = new LatLng(54.9814188, -1.6370033);
            googleMap.addMarker(new MarkerOptions().position(start).title("Teeside").snippet("University"));
            googleMap.addMarker(new MarkerOptions().position(end).title("Newcastle").draggable(true).snippet(""));
            CameraPosition a = new CameraPosition.Builder().target(start).tilt(12).zoom(10f).bearing(10f).build();
            googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(a));
            googleMap.getUiSettings().isZoomControlsEnabled();
            googleMap.getUiSettings().isCompassEnabled();
            googleMap.getUiSettings().setMyLocationButtonEnabled(true);
            drawMap.addPolyLine(googleMap,start,end);

        }
    };

    public  void displayMyLocation(Location mLocation,GoogleMap googleMap) {
        CameraPosition cameraPosition = new CameraPosition.Builder().
                target(new LatLng(mLocation.getLatitude(), mLocation.getLongitude())).
                zoom(2).
                bearing(0).
                build();
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }
    public void showMap(){
        if(args.getCoordinate() == null){
            Toast.makeText(requireContext(),"This Empty",Toast.LENGTH_SHORT).show();
        }
        else{
            args= MapsFragmentArgs.fromBundle(getArguments());

        }

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_maps, container, false);
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }

    }
}