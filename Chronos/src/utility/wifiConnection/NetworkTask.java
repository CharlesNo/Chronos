package utility.wifiConnection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

import view.ActivityChronometer;

import com.chronos.R;

import android.os.AsyncTask;
import android.widget.Button;
import android.widget.TextView;

public class NetworkTask extends AsyncTask<Void, byte[], Boolean> {
	
    Socket nsocket; 
    InputStream nis;
    OutputStream nos; 
    BufferedReader inFromServer;
	private ActivityChronometer activity;
	 /** Connexion booleen */
	 static Boolean connected=false;

    public NetworkTask(ActivityChronometer activityChronometer) {
		this.activity=activityChronometer;
	}

	@Override
    protected void onPreExecute() {
        changeConnectionStatus(true);
    }

    @Override
    protected Boolean doInBackground(Void... params) { //This runs on a different thread
        boolean result = false;
        try {
            SocketAddress sockaddr = new InetSocketAddress("192.168.1.1", 8888);
            nsocket = new Socket();
            nsocket.connect(sockaddr, 5000);
            if (nsocket.isConnected()) {
                nis = nsocket.getInputStream();
                nos = nsocket.getOutputStream();
                inFromServer = new BufferedReader(new InputStreamReader(nis));
                while(true){
                    String msgFromServer = inFromServer.readLine();
                    //Traitement start or stop chronometer
            }
           }
      
        } catch (IOException e) {
            e.printStackTrace();
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
            result = true;
        } finally {
            closeSocket();
        }
        return result;
    }

    //Method closes the socket
    public void closeSocket(){
        try {
            nis.close();
            nos.close();
            nsocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Method is called when task is cancelled
    @Override
    protected void onCancelled() {
        changeConnectionStatus(false);
    }

    //Method is called after taskexecution
    @Override
    protected void onPostExecute(Boolean result) {
        if (result) {
            outputText("onPostExecute: Terminé avec une erreur.");
        } else {
            outputText("onPostExecute: Terminé.");
        }
        changeConnectionStatus(false);
    }

/**
 * Permet de changer le statu de la connexion.
 * @param isConnected boolean
 */
 public void changeConnectionStatus(Boolean isConnected) {
     connected=isConnected;
     Button buttonConnect = (Button) activity.findViewById(R.id.connect);
     if(isConnected){
         outputText("Connexion établie");
         buttonConnect.setText("Déconnexion");
     }else{
         outputText("Déconnecté du serveur.");
         buttonConnect.setText("Connexion");
     }
 }
 public static boolean getConnected()
 {
	 return connected;
 }


 /**
  * Methode qui permet d'ecrire les textlog
  * @param msg Le message.
  */
 public void outputText(String msg) {
	 TextView textlog = (TextView) activity.findViewById(R.id.textlog);
     textlog.append(msg+"\n");
 }
}
