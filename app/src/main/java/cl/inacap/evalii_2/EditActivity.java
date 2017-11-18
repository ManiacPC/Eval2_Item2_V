package cl.inacap.evalii_2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import cl.inacap.evalii_2.models.Recordatorio;

public class EditActivity extends AppCompatActivity {
    private TextView textCodRecordatorio;
    private EditText editTitulo;
    private EditText editContenido;
    private Button btnGuardar;
    private Recordatorio recordatorioEditar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        this.textCodRecordatorio = (TextView) findViewById(R.id.txtCod);
        this.editTitulo = (EditText) findViewById(R.id.editTitulo);
        this.editContenido = (EditText) findViewById(R.id.editContenido);
        this.btnGuardar = (Button) findViewById(R.id.btnGuardar);

        this.recordatorioEditar = (Recordatorio) getIntent().getSerializableExtra("recordatorioEditar");

        this.editTitulo.setText(recordatorioEditar.getTitulo());
        this.editContenido.setText(recordatorioEditar.getContenido());
        this.textCodRecordatorio.setText(String.valueOf(recordatorioEditar.getCodRecordatorio()));

        this.btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Código que debe escribir el estudiante para guardar los cambios
                Recordatorio r = new Recordatorio(getBaseContext());
                r.setTitulo(editTitulo.getText().toString());
                r.setContenido(editContenido.getText().toString());
                r.setCodRecordatorio(recordatorioEditar.getCodRecordatorio());
                if(r.actualizar()) {
                    Toast.makeText(getBaseContext(), "El registro ha sido actualizado", Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    Toast.makeText(getBaseContext(), "No se ha podido actualizar el registro", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
