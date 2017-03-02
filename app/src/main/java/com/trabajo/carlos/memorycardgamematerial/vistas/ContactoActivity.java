package com.trabajo.carlos.memorycardgamematerial.vistas;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.trabajo.carlos.memorycardgamematerial.R;

public class ContactoActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnTwitter, btnFacebook, btnInstagram;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);

        btnTwitter = (Button)findViewById(R.id.btnTwitter);
        btnFacebook = (Button)findViewById(R.id.btnFacebook);
        btnInstagram = (Button)findViewById(R.id.btnInstagram);

        btnTwitter.setOnClickListener(this);
        btnFacebook.setOnClickListener(this);
        btnInstagram.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.btnTwitter:

                String perfilTwitter = "https://twitter.com/SushiPrieto";
                Intent intentTwitter = new Intent(Intent.ACTION_VIEW);
                intentTwitter.setData(Uri.parse(perfilTwitter));
                startActivity(intentTwitter);

                break;

            case R.id.btnFacebook:

                String perfilFacebook = "https://www.facebook.com/carlos.prietoninjassassin";
                Intent intentFacebook = new Intent(Intent.ACTION_VIEW);
                intentFacebook.setData(Uri.parse(perfilFacebook));
                startActivity(intentFacebook);

                break;

            case R.id.btnInstagram:

                String perfilInstagram = "https://www.instagram.com/sushi_prieto/?hl=es";
                Intent instentInstagram = new Intent(Intent.ACTION_VIEW);
                instentInstagram.setData(Uri.parse(perfilInstagram));
                startActivity(instentInstagram);

                break;

        }

    }
}
