package com.carmona.arrabal.proyectocontadorbaloncesto;

import android.content.DialogInterface;
import android.graphics.Typeface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    /**
     * @author Rafael Carmona Arrabal
     * @param savedInstanceState
     */
    //vars
    //botones que suman +1 o restan +1 en los contadores.
    ImageButton botonSumarLocal;
    ImageButton botonRestarLocal;
    ImageButton botonSumarVisitor;
    ImageButton botonRestarVisitor;
    //botones que suman de +3,+2,+1 en local y visitante.
    Button botonMasUnoLocal;
    Button botonMasDosLocal;
    Button botonMasTresLocal;
    Button botonMasUnoVisitante;
    Button botonMasDosVisitante;
    Button botonMasTresVisitante;
    //botones de refresco y volver, variable que guarda el ultimo punto anotado.
    Button botonReset;
    Button botonUndo;
    int ultimoPunto;
    //guarda si el ultimo valor ha sido de local(0) o visitante(1)
    boolean lastPointLoc;
    //marcadores
    TextView tvLocal;
    TextView tvVisitante;
    TextView tvMarcadorLocal;
    TextView tvMarcadorVisitante;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Asignación de las variables
        //image Button
        //conseguimos
        Bundle bundle = this.getIntent().getExtras();

        botonSumarLocal = (ImageButton) findViewById(R.id.annadirLocal);
        botonSumarVisitor = (ImageButton) findViewById(R.id.annadirVisitante);
        botonRestarLocal = (ImageButton) findViewById(R.id.quitarLocal);
        botonRestarVisitor = (ImageButton) findViewById(R.id.quitarVisitante);
        //Buttons +3+2+1
        botonMasDosLocal = (Button) findViewById(R.id.botonMasDosLocal);
        botonMasUnoLocal = (Button) findViewById(R.id.botonMasUnoLocal);
        botonMasTresLocal = (Button) findViewById(R.id.botonMasTresLocal);
        botonMasDosVisitante = (Button) findViewById(R.id.botonMasDosVisitante);
        botonMasUnoVisitante = (Button) findViewById(R.id.botonMasUnoVisitante);
        botonMasTresVisitante = (Button) findViewById(R.id.botonMasTresVisitante);
        //Botones refresh y undo
        botonReset = (Button) findViewById(R.id.botonReset);
        botonUndo = (Button) findViewById(R.id.botonUndo);
        ultimoPunto = 0;

        tvLocal = (TextView) findViewById(R.id.localTV);
        tvVisitante = (TextView) findViewById(R.id.visitanteTV);
        tvMarcadorLocal = (TextView) findViewById(R.id.marcadorLocal);
        tvMarcadorVisitante = (TextView) findViewById(R.id.marcadorVisitante);
        //le asignamos la fuente.
        tvLocal.setText(bundle.getString("nombreLocal"));
        tvVisitante.setText( bundle.getString("nombreVisitante"));
        Typeface face=Typeface.createFromAsset(getAssets(),"fonts/erbos_draco_1st_nbp.ttf");
        tvLocal.setTypeface(face);
        tvVisitante.setTypeface(face);
        tvMarcadorVisitante.setTypeface(face);
        tvMarcadorLocal.setTypeface(face);
        //ponemos disabled el boton de undo
        botonUndo.setEnabled(false);
        //funciones. boton suma y resta 1 local.
        botonSumarLocal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //llamada a la funcion
                cambiaLocal(view, 1);
            }
        });
        botonRestarLocal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //llamada a la funcion
                if(Integer.parseInt(tvMarcadorLocal.getText()+"")!= 0){
                    cambiaLocal(view, -1);
                }
            }
        });
        //boton suma y resta visitor
        botonSumarVisitor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //llamada a la funcion
                cambiaVisitante(view, 1);
            }
        });
        botonRestarVisitor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //llamada a la funcion
                if(Integer.parseInt(tvMarcadorVisitante.getText()+"") != 0){
                    cambiaVisitante(view, -1);
                }
            }
        });
        //botones +1+2+3 de local
        botonMasUnoLocal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //llamada a la funcion
                cambiaLocal(view, 1);
            }
        });
        botonMasDosLocal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //llamada a la funcion
                cambiaLocal(view, 2);
            }
        });
        botonMasTresLocal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //llamada a la funcion
                cambiaLocal(view, 3);
            }
        });
        //botones +1+2+3 de visitante
        botonMasUnoVisitante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //llamada a la funcion
                cambiaVisitante(view, 1);
            }
        });
        botonMasDosVisitante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //llamada a la funcion
                cambiaVisitante(view, 2);
            }
        });
        botonMasTresVisitante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //llamada a la funcion
                cambiaVisitante(view, 3);
            }
        });
        //boton de reset y undo
        botonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //llamada a la funcion
                reset(view);
            }
        });
        botonUndo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //llamada a la funcion
                undo(view, ultimoPunto);
                ultimoPunto = 0;
                botonUndo.setEnabled(false);
            }
        });
    }
    //cambiar marcador.
    public void cambiaLocal(View view, int puntuacion){
        try{
            tvMarcadorLocal.setText((Integer.parseInt(tvMarcadorLocal.getText()+"")+puntuacion)+"");
            //guardamos que el local ha sido el ultimo, y el ultimo valor introducido.
            lastPointLoc = false;
            ultimoPunto = puntuacion;
            botonUndo.setEnabled(true);
        }catch(Exception e){
            return;
        }
    }
    public void cambiaVisitante(View view, int puntuacion){
        try{
            tvMarcadorVisitante.setText((Integer.parseInt(tvMarcadorVisitante.getText()+"")+puntuacion)+"");
            //guardamos que el visitante ha sido el ultimo, y el ultimo valor introducido.
            lastPointLoc = true;
            ultimoPunto = puntuacion;
            botonUndo.setEnabled(true);
        }catch(Exception e){
            return;
        }
    }
    //Undo
    public void undo(View view, int ultimoValor){
       try{
           if(ultimoValor>0){
               if(lastPointLoc){
                   tvMarcadorVisitante.setText((Integer.parseInt(tvMarcadorVisitante.getText()+"")-ultimoValor)+"");
               }else{
                   tvMarcadorLocal.setText((Integer.parseInt(tvMarcadorLocal.getText()+"")-ultimoValor)+"");
               }
           }else{
               botonUndo.setEnabled(false);
           }
       }catch(Exception e){
           return;
       }
    }

    public void reset(View view){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        // set title
        alertDialogBuilder.setTitle("Reset");

        // set dialog message
        alertDialogBuilder
                .setMessage("¿Estas seguro de que quieres resetear?")
                .setCancelable(false)
                .setPositiveButton("Si",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        tvMarcadorVisitante.setText( "0");
                        tvMarcadorLocal.setText("0");
                        ultimoPunto = 0;
                        botonUndo.setEnabled(false);
                    }
                })
                .setNegativeButton("No",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        // if this button is clicked, just close
                        // the dialog box and do nothing
                        dialog.cancel();
                    }
                });

        AlertDialog alerta = alertDialogBuilder.create();

        alerta.show();
    }

}


