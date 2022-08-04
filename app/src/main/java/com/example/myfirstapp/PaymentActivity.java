package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class PaymentActivity extends AppCompatActivity {
    ProductModel product;
    String phoneNumber;
    String userID;
    int accountBalance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        Intent intent = getIntent();
        product = new ProductModel(intent.getStringExtra("id"), intent.getStringExtra("name"), intent.getStringExtra("category"), intent.getIntExtra("price", 0));
        phoneNumber = intent.getStringExtra("phoneNumber");
        accountBalance = intent.getIntExtra("accountBalance", 0);
        userID = intent.getStringExtra("userID");

        LinearLayout bodyLayout = findViewById(R.id.body_layout);

        TextView dateFieldText = new TextView(this);
        dateFieldText.setText("Tanggal");

        TextView dateText = new TextView(this);
        dateText.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
        dateText.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
        dateText.setText(new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date()));

        LinearLayout dateFieldLayout = new LinearLayout(this);
        dateFieldLayout.setOrientation(LinearLayout.HORIZONTAL);
        dateFieldLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
        dateFieldLayout.addView(dateFieldText);
        dateFieldLayout.addView(dateText);

        bodyLayout.addView(dateFieldLayout);

        TextView phoneLabelText = new TextView(this);
        phoneLabelText.setText("Phone Number");

        TextView phoneText = new TextView(this);
        phoneText.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
        phoneText.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
        phoneText.setText(phoneNumber);

        LinearLayout phoneNumberLayout = new LinearLayout(this);
        phoneNumberLayout.setOrientation(LinearLayout.HORIZONTAL);
        phoneNumberLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
        phoneNumberLayout.addView(phoneLabelText);
        phoneNumberLayout.addView(phoneText);

        bodyLayout.addView(phoneNumberLayout);

        TextView providerLabelText = new TextView(this);
        providerLabelText.setText("Provider");

        TextView providerText = new TextView(this);
        providerText.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
        providerText.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
        providerText.setText(product.getName());

        LinearLayout providerLayout = new LinearLayout(this);
        providerLayout.setOrientation(LinearLayout.HORIZONTAL);
        providerLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
        providerLayout.addView(providerLabelText);
        providerLayout.addView(providerText);

        bodyLayout.addView(providerLayout);

        Space space = new Space(this);
        space.setLayoutParams(new ViewGroup.LayoutParams(0,100));

        bodyLayout.addView(space);

        TextView productLabelText = new TextView(this);
        productLabelText.setText("Product");

        TextView productText = new TextView(this);
        productText.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
        productText.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
        productText.setText(product.getCategory());

        LinearLayout productLayout = new LinearLayout(this);
        productLayout.setOrientation(LinearLayout.VERTICAL);
        productLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
        productLayout.addView(productLabelText);
        productLayout.addView(productText);

        bodyLayout.addView(productLayout);

        Space space1 = new Space(this);
        space1.setLayoutParams(new ViewGroup.LayoutParams(0,100));

        bodyLayout.addView(space1);

        TextView balanceLabelText = new TextView(this);
        balanceLabelText.setText("Deposit");

        TextView balanceText = new TextView(this);
        balanceText.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
        balanceText.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
        balanceText.setText(String.valueOf(accountBalance));

        LinearLayout balanceLayout = new LinearLayout(this);
        balanceLayout.setOrientation(LinearLayout.HORIZONTAL);
        balanceLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
        balanceLayout.addView(balanceLabelText);
        balanceLayout.addView(balanceText);

        bodyLayout.addView(balanceLayout);

        TextView priceLabelText = new TextView(this);
        priceLabelText.setText("Price");

        TextView priceText = new TextView(this);
        priceText.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
        priceText.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
        priceText.setText(String.valueOf(product.getPrice()));

        LinearLayout priceLayout = new LinearLayout(this);
        priceLayout.setOrientation(LinearLayout.HORIZONTAL);
        priceLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
        priceLayout.addView(priceLabelText);
        priceLayout.addView(priceText);

        bodyLayout.addView(priceLayout);

        Space space2 = new Space(this);
        space2.setLayoutParams(new ViewGroup.LayoutParams(0,20));

        bodyLayout.addView(space2);

        TextView changeLabelText = new TextView(this);
        changeLabelText.setText("Sisa");

        TextView changeText = new TextView(this);
        changeText.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
        changeText.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
        changeText.setText(String.valueOf(accountBalance-product.getPrice()));

        LinearLayout changeLayout = new LinearLayout(this);
        changeLayout.setOrientation(LinearLayout.HORIZONTAL);
        changeLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
        changeLayout.addView(changeLabelText);
        changeLayout.addView(changeText);

        bodyLayout.addView(changeLayout);
    }

    public void pay(View view) {
        Map<String, Object> reqRequest = new HashMap<>();
        reqRequest.put("customerID", phoneNumber);
        reqRequest.put("productCode", product.getID());
        reqRequest.put("userID", userID);

        FirebaseFirestore.getInstance().collection("rawRequests").add(reqRequest);
        Toast toast = Toast.makeText(getApplicationContext(), "Terimakasih, permintaanmu akan segera di proses", Toast.LENGTH_SHORT);
        toast.show();
        finish();
    }

    public void back(View view) {
        finish();
    }
}