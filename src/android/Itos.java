package es.limit.cordova.itos;

import android.content.Context;
import android.Manifest;
import android.util.Log;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.json.JSONArray;
import org.json.JSONException;

import com.itos.sdk.cm5.deviceLibrary.Printer.Printer;
import com.itos.sdk.cm5.deviceLibrary.Printer.Align;
import com.itos.sdk.cm5.deviceLibrary.Printer.Printer;
import com.itos.sdk.cm5.deviceLibrary.Printer.PrinterCallbacks;

public class Itos extends CordovaPlugin {
  private static final String LOG_TAG = "ITOS";

  private final int FONT_SIZE_SMALL = 20;
  private final int FONT_SIZE_NORMAL = 24;
  private final int FONT_SIZE_BIG = 24;

  private Context context;
  private CallbackContext callbackContext;
  private JSONArray args;
  private CordovaWebView webView;

  @Override
  public void initialize(CordovaInterface cordova, CordovaWebView webView) {
    super.initialize(cordova, webView);
    Log.d(LOG_TAG, "Plugin created");
    this.webView = webView;
    this.context = this.cordova.getActivity().getApplicationContext();
  }

  @Override
  public boolean execute(String action, JSONArray args, CallbackContext callbackContext) {
    this.args = args;
    this.callbackContext = callbackContext;

    if (action.equals("print")) {
      this.print();

      return true;
    }

    return false;
  }

    private void print() {
        Log.e(LOG_TAG, "print");

        String textToPrint = this.args.optString(0, "");
        int fontSize = this.args.optInt(1, FONT_SIZE_NORMAL);
        int align = this.args.optInt(2, 0);
        boolean bold = this.args.optBoolean(3, false);

        Align textAlign;
        switch(align) {
            case 0:
                textAlign = Align.LEFT;
                break;
            case 1:
                textAlign = Align.RIGHT;
                break;
            case 2:
                textAlign = Align.CENTER;
                break;
            default:
                textAlign = Align.LEFT;
        }

//         cordova.getThreadPool().execute(new Runnable() {
            Printer mPrinter = new Printer(this.context);

            mPrinter.initPrinter();

            mPrinter.appendStr( textToPrint, fontSize, textAlign, bold );

            int retCode = mPrinter.startPrint( true, new PrinterCallbacks() {
                @Override
                public void onPrintResult( int retCode ) {
                    Log.d( LOG_TAG,  String.format( "PrintResult: %d", retCode ) );
                }
            });

            Log.d( LOG_TAG, "startPrint() retCode = " + retCode );
//         });

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
