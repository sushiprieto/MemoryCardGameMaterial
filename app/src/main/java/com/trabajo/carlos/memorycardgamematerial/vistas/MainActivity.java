package com.trabajo.carlos.memorycardgamematerial.vistas;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.trabajo.carlos.memorycardgamematerial.R;
import com.trabajo.carlos.memorycardgamematerial.fragments.AboutFragment;
import com.trabajo.carlos.memorycardgamematerial.fragments.ContactFragment;
import com.trabajo.carlos.memorycardgamematerial.fragments.MainFragment;
import com.trabajo.carlos.memorycardgamematerial.fragments.RankingFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    //Creamos los fragments
    private MainFragment fmain = new MainFragment();
    private RankingFragment franking = new RankingFragment();
    private AboutFragment fabout = new AboutFragment();
    private ContactFragment fcontact = new ContactFragment();

    //Creamos los tags para los fragments
    private final String tagfmain = "fmain";
    private final String tagfranking = "franking";
    private final String tagfabout = "fabout";
    private final String tagfcontact = "fcontact";

    private MediaPlayer musica;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Instanciamos el MainFragment para que aparezca en esta activity por defecto
        //Inicializamos
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.contenedor_fragment, fmain, tagfmain);
        fragmentTransaction.commit();

        musica = MediaPlayer.create(this, R.raw.musica);
        musica.start();
        musica.setLooping(true);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        //Inicializamos
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        if (id == R.id.nav_play) {

            fragmentTransaction.replace(R.id.contenedor_fragment, fmain, tagfmain);
            fragmentTransaction.commit();

        } else if (id == R.id.nav_ranking) {

            fragmentTransaction.replace(R.id.contenedor_fragment, franking, tagfranking);
            fragmentTransaction.commit();

        } else if (id == R.id.nav_contacto) {

            fragmentTransaction.replace(R.id.contenedor_fragment, fcontact, tagfcontact);
            fragmentTransaction.commit();

        } else if (id == R.id.nav_about) {

            fragmentTransaction.replace(R.id.contenedor_fragment, fabout, tagfabout);
            fragmentTransaction.commit();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}