package com.uniscan.solucion_manta;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ReEtiquetado extends AppCompatActivity {

    EditText codigo;
    Button imprimir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.re_etiquetado);

        codigo = findViewById(R.id.codigoReImpresion);
        imprimir = findViewById(R.id.imprimirPicking);
        codigo.requestFocus();

        BluetoothConnection btc = BluetoothConnection.getInstance();

        codigo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String codReimprimir = codigo.getText().toString();

                if (codReimprimir.equals("0.101KG")) {
                    imprimir.setEnabled(true);
                }

                if (codReimprimir.equals("2.00KG")) {
                    imprimir.setEnabled(true);
                }

                if (!codReimprimir.equals("0.101KG") && !codReimprimir.equals("2.00KG") && !codReimprimir.equals("")) {
                    Toast.makeText(ReEtiquetado.this, "Código no encontrado", Toast.LENGTH_SHORT).show();
                    imprimir.setEnabled(false);
                    codigo.setText("");
                }
                imprimir.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (codReimprimir.equals("0.101KG")) {
                            btc.printMessage("^XA~TA000~JSN^LT0^MNW^MTD^PON^PMN^LH0,0^JMA^PR5,5~SD10^JUS^LRN^CI0^XZ\n" +
                                    "^XA\n" +
                                    "^MMT\n" +
                                    "^PW408\n" +
                                    "^LL0200\n" +
                                    "^LS0\n" +
                                    "^FO288,128^GFA,01024,01024,00016,:Z64:\n" +
                                    "eJzt0D0KwzAMBWCHDB51g/om8rkKoXHpkLFXcsnQa2jL6tGFIjX+CU6uEPq2DyR4klL/nDqoRsJe3vKUkHxR14DQbNQnoNk7xr1Bwhet9uB6qh4Qk7WvxmJwyVrIHvwiw3nf5D56JmAjszjM7mdvGZKHzSN3Oy9+QJ0cs7vFRYTJwz00G90s1Q8qDxD1RbPOT36zcLpvEqoeufyj+KYs64Nh7dN8rvwA4PeCaw==:78C9\n" +
                                    "^FO256,96^GFA,01280,01280,00020,:Z64:\n" +
                                    "eJxjYBgFIxAwP3z8/3DDD/4d5Qe/PzCAij3+/O8w4x/7GemP4WIM6RYy/xn7DOc/KRZIgImlSVj8Z2ZLnP/AmCHxA1ysIA0o1nPA+PhjhFgCRMzg+TOE3gd1QLH+AwYGaXAxyQM2zD2F7S1JSGLS/2yAbmlvf/z9+QeEmAXDD/l29ubncDtGwSigBAAAQq0+fw==:CC9A\n" +
                                    "^FO224,96^GFA,00768,00768,00024,:Z64:\n" +
                                    "eJztzzEOgjAUBuDn1I1e4MWOXuElanqlEhIhgQCJA5tX8BoMDnVi5AqNDqwYFxapDaGRG+jgv/1f/uE9gK+FRKB4xdtHbwGyJOb97PKkRCPb5+A8z4qz91TblyGILwyAYVSr2UNtB0OrDtcAFgv18bA24lghAXSoFh6RERVDB2O+3B/IUMDQAOxy6X2b6mzvfHO5uYZAs0tZJkLL9j6Mk0t/pwDFS95c+2zq/q9/fjBvOdFHog==:595A\n" +
                                    "^FO224,64^GFA,00768,00768,00024,:Z64:\n" +
                                    "eJxjYBgFVAZ/sAsz4hBnPoBDfO9zhgI7dnZ5CPfDj4oCiPg/oHg9O7t9A5j74w9MvK+coeB7O0M1xDwLvhqYuD1DweN2hkKIuOW/Opi4OYr4uTKo+J90ZPG/f4qh4jueI4v/qSj+AHUpO0PZ9/YEmL0MhQjxunr2B1B3WjAUIMSB/mKQh3kULj4QAABGFzov:7794\n" +
                                    "^BY2,3,52^FT389,22^BCI,,N,N\n" +
                                    "^FD>:0.101kg^FS\n" +
                                    "^PQ1,0,1,Y^XZ\n");

                            imprimir.setEnabled(false);
                            codigo.setText("");
                        }
                        if (codReimprimir.equals("2.00KG")) {
                            btc.printMessage("^XA~TA000~JSN^LT0^MNW^MTD^PON^PMN^LH0,0^JMA^PR5,5~SD10^JUS^LRN^CI0^XZ\n" +
                                    "^XA\n" +
                                    "^MMT\n" +
                                    "^PW408\n" +
                                    "^LL0200\n" +
                                    "^LS0\n" +
                                    "^FO288,128^GFA,01024,01024,00016,:Z64:\n" +
                                    "eJzt0D0KwzAMBWCHDB51g/om8rkKoXHpkLFXcsnQa2jL6tGFIjX+CU6uEPq2DyR4klL/nDqoRsJe3vKUkHxR14DQbNQnoNk7xr1Bwhet9uB6qh4Qk7WvxmJwyVrIHvwiw3nf5D56JmAjszjM7mdvGZKHzSN3Oy9+QJ0cs7vFRYTJwz00G90s1Q8qDxD1RbPOT36zcLpvEqoeufyj+KYs64Nh7dN8rvwA4PeCaw==:78C9\n" +
                                    "^FO256,96^GFA,01280,01280,00020,:Z64:\n" +
                                    "eJxjYBgFIw8wP7B7+OHwwzkH7BjOtzcYgMXYP/xL3HH845wD9Qzn+w9AxNgsjtXPOWY550Byw8E5DyBiPJJt9T1nJLcdONh+cEbiB5iYMTtYrP/gjGSEGP8ZyWUHDs44OCMNrtde4ozkY2QxPsNjchLnDB8feCwBFwO6RU7++MfDB+rlD85I/wB1c50M/+GHBw/Y8cPtGAWjgDIAAHPaTK0=:56E4\n" +
                                    "^FO224,96^GFA,00768,00768,00024,:Z64:\n" +
                                    "eJztzzEOgkAQBdBPLLZjLzCR0itsoe6VNrHAxM1CYsExvAYFJlrRwRWIFJRgbKjADbh6BC383X+ZTGaAr0XLYssTXt670ZZ9yLvZB1lEQS7LR2/d6Og0u9d6l/pcCYQZAxjtUjV7zuI6rYTX0BIYKVLOfYxpFRwTEkBD6u2Eg/WEkYXBfOYFFnaPz6gC1kY6b0XMNtZX2c1WglDuztgEF1nW/TC5fN2vA2ge8/za6am7v/75wTwBoCdJiQ==:C8B6\n" +
                                    "^FO224,64^GFA,00768,00768,00024,:Z64:\n" +
                                    "eJxjYBgFVAZ/sAsz4hBnPoBDfO9zhh92Mv+h3A8/Kgog4v+eM/6ph4v/+AMT7ytnvve9gR0qbsFXAxO3Z+573CAHFbf8VwcTNweJW8DEz5VBxf+kg8QLoOJ//xRDxXc8B4k/gPmyovgDlMkOsvcRzF6GQrg40J0M/+DiBXBxoL8YauAehYsPBAAA9pc5YA==:1E04\n" +
                                    "^BY2,3,52^FT389,22^BCI,,N,N\n" +
                                    "^FD>:2.00kg^FS\n" +
                                    "^PQ1,0,1,Y^XZ\n");

                            imprimir.setEnabled(false);
                            codigo.setText("");
                        }

                    }
                });
            }
        });
    }
}