package com.example.dam.multidifusion;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

//import java.util.Date;

public class ReceptorEstadoTelefono extends BroadcastReceiver {

    private static int lastState = -1;
    private static boolean esEntrante = false;

    public void onReceive(final Context context, Intent intent) {
        final TelephonyManager tm = (TelephonyManager)
                context.getSystemService(Context.TELEPHONY_SERVICE);
        tm.listen(new PhoneStateListener() {
            public void onCallStateChanged(int state, String phoneNumber) {
                Intent intent = new Intent(context, ServicioClienteRest.class);
                //Hace que guarde el registro 1 sola vez
                if(lastState == state){
                    return;
                }

                switch (state) {
                    case TelephonyManager.CALL_STATE_RINGING:
                        //sonando
                        esEntrante = true;
                        llamada(intent,state,phoneNumber,esEntrante);
                        break;
                    case TelephonyManager.CALL_STATE_OFFHOOK:
                        //descolgado
//                        TelephonyManager.CALL_STATE_RINGING
                        if (lastState != 1) {
                            esEntrante = false;
                            llamada(intent,state,phoneNumber,esEntrante);
                        }else{
                            esEntrante = true;
                            llamada(intent,state,phoneNumber,esEntrante);
                        }
                        break;
                    case TelephonyManager.CALL_STATE_IDLE:
                        //sin actividad
//                        TelephonyManager.CALL_STATE_RINGING
                        if (lastState == 1) {
                            esEntrante = true;
                            intent.putExtra("state", state);
                            intent.putExtra("phone", phoneNumber);
                            intent.putExtra("tipo", "perdida");
                        }else{
                            if(esEntrante == true){
                                llamada(intent,state,phoneNumber,esEntrante);
                            }else{
                                llamada(intent,state,phoneNumber,esEntrante);
                            }
                        }
                        break;
                }
                context.startService(intent);
                lastState = state;
            }
        }, PhoneStateListener.LISTEN_CALL_STATE);
    }

    private void llamada(Intent intent, int state, String phoneNumber, boolean esEntrante){
        if(esEntrante == true){
            intent.putExtra("state", state);
            intent.putExtra("phone", phoneNumber);
            intent.putExtra("tipo", "entrante");
        }else{
            intent.putExtra("state", state);
            intent.putExtra("phone", phoneNumber);
            intent.putExtra("tipo", "saliente");
        }
    }
}