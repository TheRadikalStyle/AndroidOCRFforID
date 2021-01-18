package mx.brandonvargas.androidocr;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import mx.brandonvargas.ocrforid.OcrIdActivity;

public class MainActivity extends AppCompatActivity {

    private final int OCR_REQUEST = 1;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button)findViewById(R.id.button);
        textView = (TextView)findViewById(R.id.tv_result);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(MainActivity.this, OcrIdActivity.class),OCR_REQUEST);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Bundle extras = data.getExtras();
        if(resultCode == Activity.RESULT_OK && requestCode == OCR_REQUEST){
            if(data != null){
                Boolean is_ine = data.getBooleanExtra("IS_INE", false);
                String name = data.getStringExtra("NAME");
                String lastName = data.getStringExtra("LAST_NAME");
                String motherLastName = data.getStringExtra("M_LAST_NAME");
                String curp = data.getStringExtra("CURP");
                String address = data.getStringExtra("ADDRESS");
                String elector = data.getStringExtra("ELECTOR");
                String state = data.getStringExtra("STATE");
                String town = data.getStringExtra("TOWN");
                String section = data.getStringExtra("SECTION");
                String id = data.getStringExtra("ID");
                Uri uri1 = Uri.parse(data.getStringExtra("URI1"));
                Uri uri2 = Uri.parse(data.getStringExtra("URI2"));
                String showResult = "Nombre: "+name+
                        "\nApellido Paterno: "+lastName+
                        "\nApellido Materno: "+motherLastName+
                        "\nCurp: "+curp+
                        "\nDirección: "+address+
                        "\nClave de elector: "+elector+
                        "\nEstado: "+state+
                        "\nMunicipio: "+town+
                        "\nSección: "+section+
                        "\nOCR: "+id;
                showResult(showResult);
            }else{
                Toast.makeText(this, "Data de retorno es null", Toast.LENGTH_SHORT).show();
            }

        }
    }

    private void showResult(String showResult) {
        textView.setText(showResult);
    }
}
