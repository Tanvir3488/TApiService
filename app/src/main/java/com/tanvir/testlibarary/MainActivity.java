package com.tanvir.testlibarary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.tanvir.BasicFun.BasicFunction;
import com.tanvir.BasicFun.BasicFunctionListener;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements BasicFunctionListener {
    BasicFunction basicFunction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        basicFunction= new BasicFunction(this,this);
    basicFunction.getResponceData("https://www.faruqeahammad.com/homejob/index.php/ApiView/install_view","{" +
            "  user_id: 3," +
            "  flag: 0," +
            "  Security_Code: 1a01e4787dd033fdaf5bdf8031137cc3," +
            "  APP_NAME:JOB Desk" +
            "}",101);


    }

    @Override
    public void OnServerResponce(JSONObject jsonObject, int RequestCode) {
        Log.e("null",jsonObject.toString()+"");
    }

    @Override
    public void OnConnetivityError() {

    }
}
