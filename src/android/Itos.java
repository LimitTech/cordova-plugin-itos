package es.limit.cordova.itos;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.content.pm.PackageManager;
import android.os.Looper;
import android.util.Log;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Itos extends CordovaPlugin {
  private static final String ACCESS_COARSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
  private static final String LOG_TAG = "ITOS";
  private static final int SEARCH_REQ_CODE = 0;

  private CallbackContext callbackContext;
  private JSONArray args;
  private CordovaWebView webView;

  @Override
  public void initialize(CordovaInterface cordova, CordovaWebView webView) {
    super.initialize(cordova, webView);
    Log.d(LOG_TAG, "Plugin created");
    this.webView = webView;
  }

  @Override
  public boolean execute(String action, JSONArray args, CallbackContext callbackContext) {
    this.args = args;
    this.callbackContext = callbackContext;

    return false;
  }


  private void logAndCallCallbackError(String message) {
    Log.e(LOG_TAG, message);
    callbackContext.error(message);
  }

  private void logAndCallCallbackError(String message, Throwable exception) {
    Log.e(LOG_TAG, message, exception);
    callbackContext.error(message);
  }
}
