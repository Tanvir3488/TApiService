package com.tanvir.test_library;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BasicFunction {


    public BasicFunctionListener basicFunctionListener;
    public Context context;
    ProgressDialog pDialog;
    Activity activity;

    public BasicFunction(BasicFunctionListener otherVIew,Context context) {
        this.basicFunctionListener = otherVIew;
        this.context = context;
        this.activity = (Activity) context;
    }







    public  void savePreference(String key, String value)
    {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public  String getPreference(String key)
    {
        String value="";
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        value = prefs.getString(key, "null");

        return value;

    }


    public final boolean isInternetOn() {
        ConnectivityManager connec =  (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if ((connec.getNetworkInfo(0).getState() == NetworkInfo.State.CONNECTED) ||
                (connec.getNetworkInfo(0).getState() == NetworkInfo.State.CONNECTING) ||
                (connec.getNetworkInfo(1).getState() == NetworkInfo.State.CONNECTING) ||
                (connec.getNetworkInfo(1).getState() == NetworkInfo.State.CONNECTED)) {

            return true;
        } else if ( connec.getNetworkInfo(0).getState() == NetworkInfo.State.DISCONNECTED ||  connec.getNetworkInfo(1).getState() == NetworkInfo.State.DISCONNECTED  ) {

            return false;
        }
        return false;
    }
    public static String getCurrentDate()
    {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String CurrentDate = ""+dateFormat.format(date);

        return CurrentDate;

    }

    public String getCurrentTime()
    {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        String CurrentDate =dateFormat.format(date);

        return CurrentDate;

    }

    public String getCurrentDateTime()
    {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String CurrentDate =dateFormat.format(date);

        return CurrentDate;

    }

    public String getCurrentTimeAMPM()
    {
        DateFormat dateFormat = new SimpleDateFormat("hh:mm a");
        Date date = new Date();
        String CurrentDate =dateFormat.format(date);

        return CurrentDate;

    }
    public void getResponceData(String URL, String request_json,int Request_Code,String RType){

        try {
            if (isInternetOn()) {
                new AsyncTaskc(request_json, URL, Request_Code, RType).execute();
                Log.e("ISInternet","true");

            }
            else {
                basicFunctionListener.OnConnetivityError();
                Log.e("ISInternet","false");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public void getResponceData(String URL,int Request_Code,String RType){

        try {
            if (isInternetOn()) {
                new AsyncTaskc(URL, Request_Code,RType).execute();
                Log.e("ISInternet","true");
            }
            else {
                basicFunctionListener.OnConnetivityError();
                Log.e("ISInternet","false");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }




    class AsyncTaskc extends AsyncTask<String, String, String> {
        String json;
        // ProgressDialog pDialog;
        String url;
        String RType;
        int Rc;
        //  ProgressDialog pDialog;
        String Responce;
        public AsyncTaskc(String json, String URL,int RC,String RType) throws JSONException {
            JSONObject finaljsonObject =new JSONObject(json);

            finaljsonObject.put("office_id", getPreference("office_id"));
            finaljsonObject.put("distributor_id", getPreference("db_id"));
            finaljsonObject.put("store_id", getPreference("store_id"));
            finaljsonObject.put("sales_representative_id", getPreference("sr_id"));
            finaljsonObject.put("sales_representative_code", getPreference("sr_code"));
            finaljsonObject.put("territory_id", getPreference("territory_id"));
            finaljsonObject.put("tso_id", getPreference("tso_id"));
            finaljsonObject.put("ae_id", getPreference("ae_id"));
            this.json = finaljsonObject.toString();
            this.url = URL;
            this.Rc = RC;
            this.RType = RType;
            Responce="";

        }


        public AsyncTaskc(String URL,int RC,String RType) throws JSONException {

            JSONObject jsonObject =new JSONObject();

            this.json = jsonObject.toString();
            this.url = URL;
            this.Rc = RC;
            this.RType = RType;
            Responce="";

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();


            pDialog = createProgressDialog(context);
            pDialog.setCancelable(false);
            pDialog.getWindow()
                    .setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            pDialog.setContentView(R.layout.progress);
            pDialog.getWindow().setGravity(Gravity.CENTER);
            //pDialog.setContentView(R.layout.progressdialog);
            pDialog.show();

        }

        @Override
        protected String doInBackground(String... args) {


            try {
                // String url= Url.Registration;
                Responce="";
                // Log.e("Service_Call_Details","Url= "+url+" request_json = " +json+" responce = "+Responce);
                Responce =  makeServiceCall(url,json,RType);


               // Log.e("Service_Call_Details","Url= "+url+" request_json = " +json+" responce = "+Responce);

                // Toast.makeText(context,Responce,Toast.LENGTH_LONG).show();



                pDialog.dismiss();




            } catch (Exception e) {
                Log.e("Exception",e.getMessage());
            }

            return null;
        }

        @Override
        protected void onPostExecute(String file_url) {

            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {


                        Log.e("resposncformother",Responce+" Tanvir");
                        if (TextUtils.isEmpty(Responce)){
                            Responce="nothing";
                        }
                            pDialog.dismiss();
                            basicFunctionListener.OnServerResponce(Responce, Rc);
                            cancel(true);






                }
            });

        }



    }
    public static ProgressDialog createProgressDialog(Context context) {
        ProgressDialog dialog = new ProgressDialog(context);
        try {
            dialog.show();
        } catch (WindowManager.BadTokenException e) {

        }
        dialog.setCancelable(false);
        dialog.getWindow()
                .setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.progress);
        // dialog.setMessage(Message);
        return dialog;
    }

    public static String makeServiceCall(String url1, String MyJson,String RType) {

        try {

            URL url = new URL(url1);

            byte[] postDataBytes = MyJson.getBytes("UTF-8");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(1000000);
            conn.setConnectTimeout(1000000);
            conn.setUseCaches(false);
            conn.setAllowUserInteraction(false);
            conn.setRequestMethod(RType);
            conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            conn.setDoOutput(true);
            conn.getOutputStream().write(postDataBytes);

            Log.e("staus",conn.getResponseCode()+"");
            if (conn.getResponseCode()!=200){
                Reader in = new BufferedReader(new InputStreamReader(conn.getErrorStream(), "UTF-8"));
                StringBuilder sb = new StringBuilder();
                for (int c; (c = in.read()) >= 0;)
                    sb.append((char)c);
                return sb.toString();
            }

            Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            StringBuilder sb = new StringBuilder();
            for (int c; (c = in.read()) >= 0;)
                sb.append((char)c);
            return sb.toString();
        } catch (UnsupportedEncodingException e) {
            Log.e("error1", e.getMessage());
            e.printStackTrace();
        } catch (ProtocolException e) {
            Log.e("error1", e.getMessage());
            e.printStackTrace();
        } catch (MalformedURLException e) {
            Log.e("error1", e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            Log.e("error1", e.getMessage());
            e.printStackTrace();
        }

        return "";
    }
}
