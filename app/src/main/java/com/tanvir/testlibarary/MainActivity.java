package com.tanvir.testlibarary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tanvir.test_library.BasicFunction;
import com.tanvir.test_library.BasicFunctionListener;

public class MainActivity extends AppCompatActivity implements BasicFunctionListener {

    BasicFunction basicFunction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        basicFunction = new BasicFunction(this,this);
        basicFunction.getResponceData("https://www.faruqeahammad.com/homejob/index.php/ApiView/install_view","Tanvir",101,"POST");


    }

    @Override
    public void OnServerResponce(String jsonObject, int RequestCode) {

    }

    @Override
    public void OnConnetivityError() {

    }

}
