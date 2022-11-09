package com.hadesfranklyn.uber.helper;

import android.location.Location;

import com.google.android.gms.maps.model.LatLng;

public class Local {

    public static float calcularDistancia(LatLng latLngInicial, LatLng latLngFinal) {

        Location localInicial = new Location("Local inicial");
        localInicial.setLatitude(latLngInicial.latitude);
        localInicial.setLongitude(latLngInicial.longitude);

        Location localFinal = new Location("Local final");
        localFinal.setLatitude(latLngFinal.latitude);
        localFinal.setLongitude(latLngFinal.longitude);

        //Calcula distancia - resultado em metros
        //dividir por 1000 para converter em KM
        float distancia = localInicial.distanceTo(localFinal) / 1000;

        return distancia;
    }
}
