package com.trabajo.carlos.memorycardgamematerial.fragments;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.trabajo.carlos.memorycardgamematerial.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactFragment extends Fragment implements View.OnClickListener{

    private Button btnTwitter, btnFacebook, btnInstagram;

    public ContactFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_contact, container, false);

        btnTwitter = (Button)v.findViewById(R.id.btnTwitter);
        btnFacebook = (Button)v.findViewById(R.id.btnFacebook);
        btnInstagram = (Button)v.findViewById(R.id.btnInstagram);

        btnTwitter.setOnClickListener(this);
        btnFacebook.setOnClickListener(this);
        btnInstagram.setOnClickListener(this);

        return v;
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
