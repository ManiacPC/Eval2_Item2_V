package cl.inacap.evalii_2;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import cl.inacap.evalii_2.helpers.DatabaseHelper;
import cl.inacap.evalii_2.models.Recordatorio;

public class MainActivity extends AppCompatActivity {
    private ListView lstRecordatorios;
    private int codRecordatorioEliminar;
    private ArrayAdapter<Recordatorio> adapter;
    private Button btnInsertar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.btnInsertar = (Button) findViewById(R.id.btnInsertar);

        // Objeto helper instanciado sólo para crear la BD durante la
        // primera ejecución de la app una vez descargado
        // y abierto el proyecto
        DatabaseHelper helper = new DatabaseHelper(getBaseContext());

        // Indicar y completar ListView
        // i.- indicar recurso GUI
        this.lstRecordatorios = (ListView) findViewById(R.id.lstRecordatorios);

        // ii.- crear ArrayAdapter
        this.adapter = new ArrayAdapter<Recordatorio>(
                this,
                android.R.layout.simple_list_item_1,
                new Recordatorio(getBaseContext()).obtenerRecordatorios()
        );

        // iii.- fijar adapter
        this.lstRecordatorios.setAdapter(adapter);

        // iv. establecer listener cuando se pinche en alguno
        this.lstRecordatorios.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Código que debe escribir el estudiante para llamar a la actividad para editar
                ArrayList<Recordatorio> recordatorios = new Recordatorio(getBaseContext()).obtenerRecordatorios();
                Recordatorio r = (Recordatorio) recordatorios.get(position);

                Intent intento = new Intent(MainActivity.this, EditActivity.class);
                intento.putExtra("recordatorioEditar", r);
                startActivityForResult(intento,100);
            }
        });

        // v.- definición de diálogo para eliminar
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirmación");
        builder.setMessage("¿Desea borrar el elemento?");
        builder.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Código que debe escribir el estudiante para eliminar
                int codRecordatorio = new Recordatorio(getBaseContext()).obtenerRecordatorios().get(codRecordatorioEliminar).getCodRecordatorio();
                Recordatorio r = new Recordatorio(getBaseContext());
                if(r.eliminar(codRecordatorio)) {
                    Toast.makeText(getBaseContext(), "El recordatorio ha sido eliminado", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getBaseContext(), "No se ha podido eliminar el recordatorio", Toast.LENGTH_LONG).show();
                }
                actualizarLista();
                Toast.makeText(getBaseContext(), "Ha sido eliminado", Toast.LENGTH_LONG).show();
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        final AlertDialog dialog = builder.create();


        // vi.- establecer listener para cuando se mantenga presionado para buscar eliminar un elemento
        this.lstRecordatorios.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                codRecordatorioEliminar = position;
                dialog.show();
                return true;
            }
        });

        this.btnInsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intento = new Intent(MainActivity.this, InsertActivity.class);
                startActivityForResult(intento,200);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 200 && resultCode == 7) {
            Toast.makeText(getBaseContext(), "No se olvide que puede programar una alarma para este recordatorio",Toast.LENGTH_LONG).show();
        }
        this.actualizarLista();
    }

    private void actualizarLista() {
        adapter.clear();
        adapter.addAll(new Recordatorio(getBaseContext()).obtenerRecordatorios());
        adapter.notifyDataSetChanged();
    }

}
