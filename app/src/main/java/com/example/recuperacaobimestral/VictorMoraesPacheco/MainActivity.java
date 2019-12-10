package com.example.recuperacaobimestral.VictorMoraesPacheco;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final int registro = 0;
        final int[] escolhaUsu = {0};
        final String[] perguntaCerta;
        if (registro == 0) {
            perguntaCerta = getResources().getStringArray(R.array.sp_primeira_pergunta);
        } else {
            perguntaCerta = getResources().getStringArray(R.array.sp_segunda_pergunta);
        }
        Spinner alternativa = (Spinner)findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, perguntaCerta);
        alternativa.setAdapter(adapter);
        alternativa.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                int escolha = arg0.getSelectedItemPosition();
                escolhaUsu[0] = escolha;
                if (verificarResposta(registro, escolhaUsu)) {
                    ativarAlerta(registro);
                } else {
                    Toast.makeText(getBaseContext(), "Que Pena....Resposta errada.", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }
    public boolean verificarResposta(int regis, int resposta) {
        if ((regis == 0) && (resposta == 1)) {
            return true;
        } else if ((regis != 0) && (resposta == 3)) {
            return true;
        }
        return false;
    }
    private AlertDialog alerta;

    private void ativarAlerta(int regis) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        if (regis == 0) {
            builder.setTitle("WOOOW");
            builder.setMessage("Você é bom!!! Clique em 'Prosseguir' para ir para a próxima pergunta");
            builder.setPositiveButton("Prosseguir", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface arg0, int arg1) {
                    Toast.makeText(MainActivity.this, "positivo=" + arg1, Toast.LENGTH_SHORT).show();
                }
            });
            alerta = builder.create();
            alerta.show();

        }
    }
}
