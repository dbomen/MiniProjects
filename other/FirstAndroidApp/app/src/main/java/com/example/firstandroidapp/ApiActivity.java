package com.example.firstandroidapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

// IDEA:
// - the user types the name of the pokemon
// - we send a HTTP GET request to the pokeapi.co api
// - we display the sprite (.png file) of the pokemon
public class ApiActivity extends AppCompatActivity {

    EditText apiActivity_TextInput;
    TextView apiActivity_PokemonName;
    ImageView apiActivity_PokemonImage;
    TextView apiActivity_HelpText;

    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_api);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        apiActivity_TextInput = findViewById(R.id.apiActivity_TextInput);
        apiActivity_PokemonName = findViewById(R.id.apiActivity_PokemonName);
        apiActivity_PokemonImage = findViewById(R.id.apiActivity_PokemonImage);
        apiActivity_HelpText = findViewById(R.id.apiActivity_HelpText);

        Intent i = getIntent();
        user = (User) i.getSerializableExtra("user");
    }

    public void showPokemon(View v) {

        // VOLLEY
        // -----
        // pre send
        String pokemonName = apiActivity_TextInput.getText().toString().toLowerCase();
        String url = String.format("https://pokeapi.co/api/v2/pokemon/%s", pokemonName);
        apiActivity_TextInput.setText("");

        // we setup the request
        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) { // on success we update activity components

                        Log.i("JSON REQUEST", response.toString());

                        // UPDATING IMAGE
                        // ---
                        // we get the url for the pokemon image
                        String url = "";
                        try {
                            url = response.getJSONObject("sprites").getString("front_default");
                        } catch (JSONException e) {

                            Log.i("ERROR", "ERROR GETTING URL FROM IMAGE");
                            return;
                        }

                        // we setup another GET request to get the pokemon image
                        ImageRequest imageRequest = new ImageRequest(url,
                                new Response.Listener<Bitmap>() {
                                    @Override
                                    public void onResponse(Bitmap response) {

                                        // on success we update components

                                        // we update the pokemon image
                                        apiActivity_PokemonImage.setImageBitmap(response);

                                        // we update the pokemon name
                                        apiActivity_PokemonName.setText(pokemonName);
                                    }
                                }, 0, 0, ImageView.ScaleType.CENTER_CROP, null,
                                new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {

                                        // on error we toast an error message
                                        Toast.makeText(v.getContext(), "ERROR WITH IMAGE", Toast.LENGTH_SHORT).show();
                                    }
                                }
                        );

                        // we add the request to be sent
                        NetworkSingleton.getInstance(v.getContext()).addToRequestQueue(imageRequest);
                        // ---
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) { // on error we toast an error

                        Toast.makeText(v.getContext(), "ERROR", Toast.LENGTH_SHORT).show();
                    }
                }
        );

        // we add the request to be sent
        NetworkSingleton.getInstance(this).addToRequestQueue(jsonRequest);
        // -----
    }

    // left swipe goes back
    private float initialX;
    @Override
    public boolean onTouchEvent(MotionEvent e) {

        if (e.getAction() == MotionEvent.ACTION_MOVE)  return true;

        switch (e.getAction()) {

            case MotionEvent.ACTION_DOWN: // starting action

                this.initialX = e.getX();

                break;

            case MotionEvent.ACTION_UP: // ending action

                float x = e.getX();

                if (Math.abs(initialX - x) >= 200) {

                    if (initialX < x) { // right swipe action

                        // move activities
                        Intent i = new Intent(ApiActivity.this, MainActivity.class);
                        i.putExtra("user", this.user);
                        ApiActivity.this.startActivity(i);
                        ApiActivity.this.finish();
                        overridePendingTransition(R.anim.left_in, R.anim.left_out); // za animation
                    }
                }

                break;
        }

        return true;
    }
}