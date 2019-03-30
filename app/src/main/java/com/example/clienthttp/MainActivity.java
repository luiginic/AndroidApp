package com.example.clienthttp;
import ApiManager.ApiManager;
import de.hdodenhof.circleimageview.CircleImageView;


import android.graphics.Bitmap;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;



//import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;



public class MainActivity extends AppCompatActivity {

//    Button viewTreatmentBtn = findViewById(R.id.viewTreatmentBtn);
//    Button reactionBtn = findViewById(R.id.reportChangesBtn);
    CircleImageView avatar;
//    TextView name = findViewById(R.id.name);
//    final View statsView = findViewById(R.id.statsView);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String url = "https://cdn.vox-cdn.com/thumbor/1V9LV_FVRHj_EwzA_FCIyoi7_Ek=/0x0:800x450/1200x800/filters:focal(336x161:464x289)/cdn.vox-cdn.com/uploads/chorus_image/image/59883773/pikachu_wide.0.0.jpg";
        avatar = findViewById(R.id.avatar);
        ImageRequest request = new ImageRequest(url,
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap bitmap) {
                        avatar.setImageBitmap(bitmap);
                    }
                }, 0, 0, null,
                new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError error) {
                        avatar.setImageResource(R.drawable.no_image_user);
                    }
                });

        ApiManager.getInstance(this.getApplicationContext()).addToRequestQueue(request);


    }
}
