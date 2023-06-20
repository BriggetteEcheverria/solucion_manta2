package com.uniscan.solucion_manta;

import static android.content.ContentValues.TAG;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.util.Log;

import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

public class BluetoothConnection extends Thread {

    private static BluetoothConnection instance;
    private final BluetoothSocket socket;
    private final OutputStream stream;

    /**
     * Constructor de coneccion
     *
     * @param device El dispositivo al cual se va a conectar
     * @param bUuid  UUID para realizar la coneccion
     */

    public BluetoothConnection(BluetoothDevice device, String bUuid) {
        BluetoothSocket s = null;
        OutputStream sstream = null;
        try {
            s = device.createInsecureRfcommSocketToServiceRecord(UUID.fromString(bUuid));
            sstream = s.getOutputStream();
        } catch (IOException ioException) {
            Log.d(TAG, "BluetoothConnection: Fracasad");
        }
        stream = sstream;
        socket = s;
        instance = this;
    }

    /**
     * Singleton
     *
     * @return Instancia unica de coneccion
     */
    public static BluetoothConnection getInstance() {
        if (instance != null)
            return instance;
        return null;
    }

    /**
     * Se vuelve a crear una instancia de la clase para poder empezar el Thread
     *
     * @param device El dispositvo al cual se va a conectar
     * @param bUuid  UUID para realizar la coneccion
     */
    public void reconnect(BluetoothDevice device, String bUuid) {
        new BluetoothConnection(device, bUuid);
    }

    /**
     * Se verifica si el socket tiene una coneccion actualmente
     *
     * @return true si existe coneccion, false si no
     */
    public boolean isConnected() {
        return socket.isConnected();
    }

    @Override
    public void run() {
        try {
            socket.connect();
            calibrate();
        } catch (IOException ioException) {
            closeSocket();
        }
    }

    /**
     * Se cierra el socket en caso de ya no necesitar la coneccion
     */
    public void closeSocket() {
        try {
            socket.close();
        } catch (IOException e) {
            Log.e(TAG, "closeSocket: No se cerro", e);
        }
    }

    /**
     * Se calibra la impresora pra utilizar etiquetas con espacio blanco
     */
    public void calibrate() {
        try {
            String calibrationMsg = "! U1 setvar \"media.type\" \"label\"\n\r" +
                    "! U1 setvar \"media.sense_mode\" \"gap\"\n\r" +
                    "~JC\n\r^XA\n\r^JUS\n\r^XZ";
            stream.write(calibrationMsg.getBytes());
            stream.flush();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    /**
     * Se envia el mensaje para imprimir en el dispositivo conectado
     *
     * @param message El codigo zpl a imprimir
     */
    public void printMessage(String message) {
        try {
            stream.write(message.getBytes());
            stream.flush();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
