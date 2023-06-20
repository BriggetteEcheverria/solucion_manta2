package com.uniscan.solucion_manta;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.Calendar;
import java.util.Date;


public class Inventario extends AppCompatActivity {

    EditText inputSeriales ;
    TextView cantPescado;
    TextView cantCamaron;
    TextView cantPulpo;
    Button imprimir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inventario);

        inputSeriales = findViewById(R.id.serialesInventario);
        cantPescado = findViewById(R.id.pescado);
        cantCamaron = findViewById(R.id.camaron);
        cantPulpo = findViewById(R.id.pulpo);
        imprimir = findViewById(R.id.imprimir);

        inputSeriales.requestFocus();

        BluetoothConnection btc = BluetoothConnection.getInstance();

        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);

        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
        String formattedDate = df.format(c);



        //Se lee el codigo de barras de 22 caracteres y se lo divide en modelo y lote
        inputSeriales.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String serial = inputSeriales.getText().toString();
                Integer pescado = Integer.parseInt(cantPescado.getText().toString());
                Integer camaron = Integer.parseInt(cantCamaron.getText().toString());
                Integer pulpo = Integer.parseInt(cantPulpo.getText().toString());
                if(serial.equals("P935528")){
                    imprimir.setEnabled(true);
                    pescado = pescado + 1;
                    cantPescado.setText(pescado.toString());
                    inputSeriales.setText("");

                }
                if(serial.equals("C440204")){
                    imprimir.setEnabled(true);
                    camaron = camaron + 1;
                    cantCamaron.setText(camaron.toString());
                    inputSeriales.setText("");
                }
                if(serial.equals("P921900")){
                    imprimir.setEnabled(true);
                    pulpo = pulpo + 1;
                    cantPulpo.setText(pulpo.toString());
                    inputSeriales.setText("");
                }
                if(!serial.equals("P935528") && !serial.equals("C440204") && !serial.equals("P921900") && !serial.equals("") ){
                    Log.d("serial", "no es 1 2 o 3 ");
                    inputSeriales.setText("");
                }
                Integer finalPescado = pescado;
                Integer finalCamaron = camaron;
                Integer finalPulpo = pulpo;
                imprimir.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        btc.printMessage(
                                "^XA~TA000~JSN^LT0^MNW^MTD^PON^PMN^LH0,0^JMA^PR5,5~SD10^JUS^LRN^CI0^XZ\n\r" +
                                        "^XA\n\r" +
                                        "^MMT\n\r" +
                                        "^PW408\n\r" +
                                        "^LL0200\n\r" +
                                        "^LS0\n\r" +
                                        "^FO200,110^XGE:LOGO3.GRF^FS^\n\r" +
                                        "^FT200,138^A0I,40,38^FH\\^FDINVENTARIO^FS\n\r" +
                                        "^FT368,101^A0I,23,24^FH\\^FDBodega 01^FS\n\r" +
                                        "^FT200,100^A0I,20,19^FH\\^FDFecha:^FS\n\r" +
                                        "^FT140,100^A0I,17,16^FH\\^FD"+formattedDate +"^FS\n\r" +
                                        "^FT368,60^A0I,25,24^FH\\^FDPESCADO^FS\n\r" +
                                        "^FT242,60^A0I,25,24^FH\\^FDCAMAR\\E3N^FS\n\r" +
                                        "^FT108,60^A0I,25,24^FH\\^FDPULPO^FS\n\r" +
                                        "^FT330,18^A0I,36,36^FH\\^FD"+ finalPescado +"^FS\n\r" +
                                        "^FT200,18^A0I,36,36^FH\\^FD"+ finalCamaron +"^FS\n\r" +
                                        "^FT83,18^A0I,36,36^FH\\^FD"+ finalPulpo +"^FS\n\r" +
                                        "^PQ1,0,1,Y^XZ\n\r");
                        cantCamaron.setText("0");
                        cantPescado.setText("0");
                        cantPulpo.setText("0");
                        imprimir.setEnabled(false);
                    }
                });
            }
        });



    }
}