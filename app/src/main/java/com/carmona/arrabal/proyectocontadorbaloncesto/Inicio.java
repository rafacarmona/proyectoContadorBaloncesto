package com.carmona.arrabal.proyectocontadorbaloncesto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Inicio extends AppCompatActivity {

    //vars
    Button boton;
    EditText equipoLocal;
    EditText equipoVisitante;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        boton = (Button) findViewById(R.id.botonPrincipal);
        equipoLocal = (EditText) findViewById(R.id.nombreEquipoLocal);
        equipoVisitante = (EditText) findViewById(R.id.nombreEquipoVisitante);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //llamada a la funcion
                if(!equipoLocal.getText().toString().equals("") && !equipoVisitante.getText().toString().equals("")){
                    Intent inten = new Intent(Inicio.this , MainActivity.class);
                    Bundle b = new Bundle();
                    b.putString("nombreLocal", equipoLocal.getText().toString());
                    b.putString("nombreVisitante", equipoVisitante.getText().toString());
                    inten.putExtras(b);

                    startActivity(inten);

                }
            }
        });
    }
}
