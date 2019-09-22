package com.test.ezsponsor;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.view.Gravity;
import android.widget.*;
import net.minidev.json.parser.JSONParser;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import com.microsoft.appcenter.AppCenter;
import com.microsoft.appcenter.analytics.Analytics;
import com.microsoft.appcenter.auth.SignInResult;
import com.microsoft.appcenter.crashes.Crashes;
import com.microsoft.appcenter.auth.Auth;
import com.microsoft.appcenter.utils.async.AppCenterConsumer;
import okhttp3.*;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Button button;

    private TextView mTextViewResult;
    private String redirect;
    private String myResponse;
    private JSONObject obj;
    private String sender = "";
    private EditText editTextName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.donateButton);
        editTextName = (EditText) findViewById(R.id.amount);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // length is bounded by 256 Character

                String amount = editTextName.getText().toString();
                byte[] array = new byte[256];
                new Random().nextBytes(array);
                int n = 7;
                String randomString
                        = new String(array, Charset.forName("UTF-8"));

                // Create a StringBuffer to store the result
                StringBuffer r = new StringBuffer();

                // remove all spacial char
                String  AlphaNumericString
                        = randomString
                        .replaceAll("[^A-Za-z0-9]", "");

                // Append first 20 alphanumeric characters
                // from the generated random String into the result
                for (int k = 0; k < AlphaNumericString.length(); k++) {

                    if (Character.isLetter(AlphaNumericString.charAt(k))
                            && (n > 0)
                            || Character.isDigit(AlphaNumericString.charAt(k))
                            && (n > 0)) {

                        r.append(AlphaNumericString.charAt(k));
                        n--;
                    }
                }
                String result = r.toString();
                OkHttpClient client = new OkHttpClient();

                MediaType mediaType = MediaType.parse("application/json");
                RequestBody body = RequestBody.create(mediaType, String.format("{\n    \t\"referenceNumber\" : \"referenceNumber\",\n    \t\"sourceMoneyRequestId\" : \"%s\",\n    \t\"requestedFrom\" : {\t\n    \t\t\"contactName\" : \"requestFromContactName\",\n    \t\t\"language\" : \"en\",\n    \t\t\"notificationPreferences\": [\n    \t\t\t{\n        \t\t\"handle\": \"maunikchaudhry@gmail.com\",\n        \t\t\"handleType\": \"email\",\n        \t\t\"active\": \"True\"\n      \t\t\t}\n    \t\t]\n  \t\t},\n\n  \t\t\"amount\": %s,\n  \t\t\"currency\": \"CAD\",\n \t\t\"editableFulfillAmount\": \"False\",\n  \t\t\"requesterMessage\": \"Thanks you for your donation!\",\n  \t\t\"invoice\": {\n   \t\t\t\"invoiceNumber\": \"string\",\n    \t\t\"dueDate\": \"2019-09-30T16:12:12.000Z\"\n  \t\t},\n\n  \t\t\"expiryDate\": \"2019-09-30T16:12:12.000Z\",\n  \t\t\"supressResponderNotifications\": \"False\",\n  \t\t\"returnURL\": \"string\",\n  \t\t\"creationDate\": \"2019-09-21T18:12:12.000Z\",\n  \t\t\"status\": 0,\n  \t\t\"fulfillAmount\": 0,\n  \t\t\"responderMessage\": \"generateRandomString\",\n  \t\t\"notificationStatus\": 0\n\t\n}", result, amount));
                Request request = new Request.Builder()
                        .url("https://gateway-web.beta.interac.ca/publicapi/api/v2/money-requests/send")
                        .post(body)
                        .addHeader("Accept", "application/json")
                        .addHeader("Content-Type", "application/json")
                        .addHeader("accessToken", "Bearer 9aa4d294-f635-498d-b4c2-cfc606e14334")
                        .addHeader("thirdPartyAccessId", "CA1TAJjNNVYGWdxt")
                        .addHeader("requestId", "requestID")
                        .addHeader("deviceId", "deviceID")
                        .addHeader("apiRegistrationId", "CA1ARMVEkCNxhnvk")
                        .addHeader("cache-control", "no-cache")
                        .addHeader("Postman-Token", "a3c687bc-6eea-40db-96e3-17c18adf6ee2")
                        .build();

                //Response response = client.newCall(request).execute();

                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        if (response.isSuccessful()) {
                            myResponse = response.body().string();
                            sender = myResponse.substring(55, myResponse.length() - 2);
                            // Log.i("bb", myResponse);
                            MainActivity.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Log.i("tt", sender);
                                    Intent intent = new Intent(MainActivity.this, Donation2.class);
                                    intent.putExtra("sender", sender);
                                    startActivity(intent);
                                }
                            });
                        }

                    }


                });
                // System.out.println(response);

                ProgressDialog progressDialog = ProgressDialog.show(MainActivity.this, "",
                        "Processing. Please wait...", true);
            }
        });

    }


}