package com.trabajo.carlos.memorycardgamematerial.vistas;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
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
import android.widget.Toast;

import com.trabajo.carlos.memorycardgamematerial.R;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Button btnPlay;

    private String nombreLogin;

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

        //Creamos la musica de fondo y la ponemos en bucle
        musica = MediaPlayer.create(this, R.raw.musica);
        musica.start();
        musica.setLooping(true);

        btnPlay = (Button)findViewById(R.id.btnPlay);
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mostrarDialogoPersonalizado();

            }
        });

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

    /**
     * Método que crea un cuadro de diálogo personalizado
     */
    public void mostrarDialogoPersonalizado() {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.dialogo_login, null);
        dialogBuilder.setView(dialogView);

        final EditText edtLogin = (EditText) dialogView.findViewById(R.id.edtLogin);

        dialogBuilder.setTitle("Login");
        dialogBuilder.setMessage("Introduzca su nombre");
        dialogBuilder.setPositiveButton("Continuar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

                //Comprobamos que no se inserte el campo vacio
                if (edtLogin.getText().toString().equals("")){

                    Toast.makeText(getApplicationContext(), "Debe insertar un nombre", Toast.LENGTH_SHORT).show();

                }else{

                    //Recogemos el nombre introducido para enviarlo a la otra actividad
                    nombreLogin = edtLogin.getText().toString();

                    Intent intent = new Intent(MainActivity.this, GameActivity.class);

                    intent.putExtra("nombre", nombreLogin);

                    startActivity(intent);

                }

            }
        });
        dialogBuilder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

                dialog.cancel();

            }
        });

        AlertDialog dialogo = dialogBuilder.create();
        dialogo.show();

    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_ranking) {

            Intent intent = new Intent(MainActivity.this, RankingActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_contacto) {

            Intent intent = new Intent(MainActivity.this, ContactoActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_about) {

            Intent intent = new Intent(MainActivity.this, AboutActivity.class);
            startActivity(intent);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //Cuando se cierra la app se para la musica
    @Override
    protected void onPause() {
        super.onPause();
        musica.release();
    }
}