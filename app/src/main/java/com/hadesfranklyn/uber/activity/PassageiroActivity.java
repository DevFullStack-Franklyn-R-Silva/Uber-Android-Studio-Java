package com.hadesfranklyn.uber.activity;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.hadesfranklyn.uber.R;
import com.hadesfranklyn.uber.config.ConfiguracaoFirebase;
import com.hadesfranklyn.uber.databinding.ActivityPassageiroBinding;

public class PassageiroActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private FirebaseAuth autenticacao;
    private ActivityPassageiroBinding binding;
    private LocationManager locationManager;
    private LocationListener locationListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPassageiroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //Troca o titulo da toolbar
        setTitle("Iniciar uma viagem");
        setSupportActionBar(binding.toolbar);

        //Configuracoes iniciais
        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuSair:
                autenticacao.signOut();
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //Recuperar localizacao do usuario
        recuperarLocalizacaoUsuario();

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-9.75164, -36.6604);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Brasil"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    private void recuperarLocalizacaoUsuario() {
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {

                // Recuperar latitude e longitude
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();

                LatLng meuLocal = new LatLng(latitude, longitude);

                mMap.clear();
                mMap.addMarker(new MarkerOptions()
                        .position(meuLocal)
                        .title("Meu Local")
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.usuario)));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(meuLocal, 20));
            }
        };

        //Solicitar atualizacoes de localizacao
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED ) {
            locationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER,
                    10000,
                    10,
                    locationListener
            );
        }

    }

}