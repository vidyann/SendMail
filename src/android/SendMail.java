package co.merce.doorvoeringen;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.text.Html;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.LOG;
import org.apache.cordova.PluginResult;
import org.apache.cordova.PluginResult.Status;
import org.json.JSONArray;
import org.json.JSONException;


@SuppressLint("ParserError")
public class SendMail extends CordovaPlugin {
    public final String ACTION_SEND_EMAIL = "sendEmail";

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException { 
        PluginResult result = null;
        if (!action.equals(ACTION_SEND_EMAIL)) {
            result = new PluginResult(Status.INVALID_ACTION);
            callbackContext.sendPluginResult(result);
            callbackContext.error("INVALID_ACTION:" + action);
            return false;
        }
        try {
            String message = args.getString(0);
            this.sendEmailViaGmail(message);
            result = new PluginResult(Status.OK);
            callbackContext.sendPluginResult(result);
            callbackContext.success();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            callbackContext.error(e.getMessage());
            result = new PluginResult(PluginResult.Status.ERROR);
            callbackContext.sendPluginResult(result);
            return false;
        } 
    }

    private void sendEmailViaGmail(String body) throws Exception {
        Mail m = new Mail("2vidya.narasimhan@gmail.com", "a401eden");
        String[] toArr = {"2vidya.narasimhan@gmail.com"};
        m.set_to(toArr);
        m.set_from("2vidya.narasimhan@gmail.com");
        m.set_body(body);
        m.set_subject("TEST SUBJECT");
        boolean sendFlag = m.send();
    }

}