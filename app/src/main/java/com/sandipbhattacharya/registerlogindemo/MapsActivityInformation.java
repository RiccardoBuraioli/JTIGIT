package com.sandipbhattacharya.registerlogindemo;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.sandipbhattacharya.registerlogindemo.databinding.ActivityMapsInformationBinding;

import java.util.HashMap;
import java.util.Map;

public class MapsActivityInformation extends FragmentActivity implements OnMapReadyCallback {
    private EditText ind;
    private TextView lati, longi;
    private String name,  email, password, cognome,tipo;
    private GoogleMap mMap;
    double lat;
    double lon;
    private ActivityMapsInformationBinding binding;
    private String URL = "http://192.168.1.10/api/register.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsInformationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ind = findViewById(R.id.indirizzoText);
        lati = findViewById(R.id.latitudine);
        longi = findViewById(R.id.longitudine);
        name = getIntent().getStringExtra("name");
        email =getIntent().getStringExtra("email");
        password =getIntent().getStringExtra("password");
        cognome = getIntent().getStringExtra("cognome");
        tipo = getIntent().getStringExtra("tipo");

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener()
        {
            @Override
            public void onMapClick(LatLng coord)
            {

                mMap.clear();
                Toast.makeText(MapsActivityInformation.this,coord.latitude + "  "+ coord.longitude ,Toast.LENGTH_SHORT).show();
                mMap.addMarker(new MarkerOptions().position(coord).title("la tua posizione"));
                lati.setText(String.valueOf(coord.latitude));
                longi.setText(String.valueOf(coord.longitude));
                lat=coord.latitude;
                lon= coord.longitude;
                mMap.moveCamera(CameraUpdateFactory.newLatLng(coord));

                android.util.Log.i("onMapClick", "Horray!");
            }
        });
    }
    public void save(View view) {

        //String indir = indirizzo.getText().toString().trim();

         if(!name.equals("") && !email.equals("") && !password.equals("")){

             String indiriz = ind.getText().toString().trim();




             StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if (response.equals("SUCCESS")) {
                       Toast.makeText(getApplicationContext(),"è andata bene", Toast.LENGTH_SHORT).show();
                    } else if (response.equals("NONE")) {
                        Toast.makeText(getApplicationContext(),"è andata male", Toast.LENGTH_SHORT).show();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(), error.toString().trim(), Toast.LENGTH_SHORT).show();
                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> data = new HashMap<>();
                    data.put("email", email);
                    data.put("password", password);
                    data.put("nome", name);
                    data.put("cognome", cognome);
                    data.put("tipo", tipo);
                    data.put("latitudine", String.valueOf(lat));
                    data.put("longitudine", String.valueOf(lon));
                    data.put("indirizzo", indiriz);

                    return data;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);
        }
    }

}