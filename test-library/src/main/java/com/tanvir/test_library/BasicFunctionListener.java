package com.tanvir.test_library;

import org.json.JSONObject;

public interface BasicFunctionListener {

     void OnServerResponce(JSONObject jsonObject, int RequestCode);
     void OnConnetivityError();


}
