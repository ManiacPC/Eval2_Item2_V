package cl.inacap.evalii_2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cl.inacap.evalii_2.models.Recordatorio;

public class InsertActivity extends AppCompatActivity {
    private Button btnInsertarRecordatorio;
    private EditText editTitulo;
    private EditText editContenido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        this.btnInsertarRecordatorio = (Button) findViewById(R.id.btnInsertarRecordatorio);
        this.editContenido = (EditText) findViewById(R.id.editContenidoInsertar);
        this.editTitulo = (EditText) findViewById(R.id.editTituloInsertar);

        this.btnInsertarRecordatorio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Recordatorio recordatorioInsertar = new Recordatorio(getBaseContext());
                recordatorioInsertar.setTitulo(editTitulo.getText().toString());
                recordatorioInsertar.setContenido(editContenido.getText().toString());
                if(recordatorioInsertar.insertar(recordatorioInsertar)) {
                    Toast.makeText(getBaseContext(), "Ha sido insertado un nuevo recordatorio",Toast.LENGTH_LONG).show();
                    setResult(7);
                    finish();
                } else {
                    Toast.makeText(getBaseContext(), "No ha sido posible insertar un nuevo recordatorio",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
