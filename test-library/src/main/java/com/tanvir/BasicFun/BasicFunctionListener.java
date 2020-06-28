package com.tanvir.BasicFun;

import org.json.JSONObject;

public interface BasicFunctionListener {

     void OnServerResponce(JSONObject jsonObject, int RequestCode);
     void OnConnetivityError();


}
