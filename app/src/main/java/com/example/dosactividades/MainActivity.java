package com.example.dosactividades;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnSaludar;
    private Button btnNavegar;
    private EditText edtNombre;
    private EditText edtUrl;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnNavegar = (Button) findViewById(R.id.btnNavegar);
        btnSaludar = (Button) findViewById(R.id.btnSaludar);
        edtNombre = (EditText) findViewById(R.id.edtNombre);
        edtUrl = (EditText) findViewById(R.id.edtUrl);

        btnNavegar.setOnClickListener(MainActivity.this);
        btnSaludar.setOnClickListener(MainActivity.this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSaludar:
                intent = new Intent(this, Segunda.class);
                intent.putExtra("nombre", edtNombre.getText().toString());
                startActivity(intent);
                break;
            case R.id.btnNavegar:
                openWebPage(edtUrl.getText().toString());
                break;
        }
    }

    public void openWebPage(String url) {
        //Comprobamos que se trata de un https: o http:. Tambien lo podemos comprobar nosotros
        //con url.substring y extraemos del 0 al 7 (extrae uno menos del final) para http:// y
        //del 0 al 8 para https://
        if (URLUtil.isHttpsUrl(url) || URLUtil.isHttpUrl(url)) {
            //Debe ser del tipo https://... sino dice que no hay navegador.
            Uri uri = Uri.parse(url);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            //Comprobamos que hay navegador instalado tambien.
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            } else {
                Toast.makeText(this, "No hay un navegador instalado", Toast.LENGTH_SHORT).show();
            }
        }
        else {
            Toast.makeText(this, "No hay un navegador instalado", Toast.LENGTH_SHORT).show();
        }
    }
}
