package com.binit.buttar.englishmaster.activity;

import android.app.Activity;

import instamojo.library.InstapayListener;
import instamojo.library.InstamojoPay;

import android.app.AlertDialog;

import android.content.DialogInterface;
import android.content.IntentFilter;
import android.widget.Toast;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.binit.buttar.englishmaster.R;
import com.binit.buttar.englishmaster.utils.Logger;
import com.binit.buttar.englishmaster.utils.SessionManager;
import com.binit.buttar.englishmaster.utils.ShowToast;
import com.instamojo.android.helpers.Constants;
import com.instamojo.android.models.Order;

import org.json.JSONException;
import org.json.JSONObject;

import static com.binit.buttar.englishmaster.utils.SessionManager.KEY_IS_AD_FREE;


public class UserFormActivity extends AppCompatActivity {

    SessionManager sessionManager;

    private EditText nameEt, emailEt, phoneEt;
    Button pay;
    private String accessToken = null;
    private TextView amountTv, descriptionTv;

    private void callInstamojoPay(String email, String phone, String amount, String purpose, String buyername) {
        final Activity activity = this;
        sessionManager = new SessionManager(getApplicationContext());
        InstamojoPay instamojoPay = new InstamojoPay();
        IntentFilter filter = new IntentFilter("ai.devsupport.instamojo");
        registerReceiver(instamojoPay, filter);
        JSONObject pay = new JSONObject();
        try {
            pay.put("email", email);
            pay.put("phone", phone);
            pay.put("purpose", purpose);
            pay.put("amount", amount);
            pay.put("name", buyername);
            pay.put("send_sms", true);
            pay.put("send_email", true);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        initListener();
        instamojoPay.start(activity, pay, listener);
    }

    InstapayListener listener;

    private void initListener() {
        listener = new InstapayListener() {
            @Override
            public void onSuccess(String response) {
                Toast.makeText(getApplicationContext(), "response: " + response, Toast.LENGTH_LONG).show();
                Logger.showMessage("Response : " + response);
                AlertDialog.Builder builder = new AlertDialog.Builder(UserFormActivity.this);
                builder.setTitle("Payment Successful");
                builder.setMessage("Get Ready to Become English Champion!");
                sessionManager.createLoginSession(true, KEY_IS_AD_FREE);
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        finish();
                    }
                });
            }

            @Override
            public void onFailure(int code, String reason) {
                Toast.makeText(getApplicationContext(), " Payment Failed ", Toast.LENGTH_LONG).show();
            }
        };
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_form);
        // Call the function callInstamojo to start payment here

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("User Information");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        nameEt = findViewById(R.id.userName);
        emailEt = findViewById(R.id.email);
        phoneEt = findViewById(R.id.mobile);
        pay = findViewById(R.id.pay);
        amountTv = findViewById(R.id.amount);
        descriptionTv = findViewById(R.id.description);
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPayment();
            }
        });
    }

    private void startPayment() {

        String name = nameEt.getText().toString();
        final String email = emailEt.getText().toString();
        final String phone = phoneEt.getText().toString();
        String amount = "200";
        String description = descriptionTv.getText().toString();
        String transactionID = String.valueOf(System.currentTimeMillis());
        Order order = new Order(accessToken, transactionID, name, email, phone, amount, description);
        //set webhook
        //    order.setWebhook("http://becomeenglishchampion.com/webhook/");

        //Validate the Order
        if (!order.isValid()) {
            //oops order validation failed. Pinpoint the issue(s).
            if (!order.isValidName()) {
                nameEt.setError("Buyer name is invalid");
            }

            if (!order.isValidEmail()) {
                emailEt.setError("Buyer email is invalid");
            }

            if (!order.isValidPhone()) {
                phoneEt.setError("Buyer phone is invalid");
            }

            if (!order.isValidAmount()) {
                amountTv.setError("Amount is invalid or has more than two decimal places");
            }
            return;
        }
        callInstamojoPay(email, phone, amount, description, name);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            String orderID = data.getStringExtra(Constants.ORDER_ID);
            String transactionID = data.getStringExtra(Constants.TRANSACTION_ID);
            String paymentID = data.getStringExtra(Constants.PAYMENT_ID);
            // Check transactionID, orderID, and orderID for null before using them to check the Payment status.
            if (transactionID != null || paymentID != null) {
                Logger.showMessage("transaction Id: " + transactionID + ", paymentId: " + paymentID + ", orderId : " + orderID);
            } else {
                ShowToast.showToast("Oops!! Payment was cancelled", getApplicationContext());
            }
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }


}
