allprojects {
repositories {
... maven { url 'https://jitpack.io' }
}

}

dependencies {
implementation 'com.github.Tanvir3488:TApiService:7'
}

public class MainActivity implements BasicFunctionListener {

@Override
protected void onCreate(Bundle savedInstanceState) {
super.onCreate(savedInstanceState); setContentView(R.layout.activity_main);
basicFunction = new BasicFunction(this, this); // creating object

basicFunction.savePreference("USER_NAME", USER_NAME); /one of basic method you can use

basicFunction.getResponceData(URL, DATA, REQUEST_CODE,REQUEST_METHOD); // Api Calling
}

@Override
public void OnServerResponce(JSONObject jsonObject, int RequestCode) {
Log.e("Json", jsonObject.toString());

switch (RequestCode) {
case 101:
//do what you want to do
break;
case 102:
//do what you want to do if you use multiple API in one activity break;

}
