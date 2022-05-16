package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class PaymentActivity extends AppCompatActivity {

    EditText cashEditText;
    int total = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        Intent intent = getIntent();
        ArrayList<String> menuList = intent.getStringArrayListExtra("MENULIST");
        int cash = intent.getIntExtra("CASH", 0);
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

        Space space = new Space(this);
        space.setLayoutParams(new ViewGroup.LayoutParams(0,100));

        bodyLayout.addView(space);

        for(int i =0; i<menuList.size();i++){
            try {
                JSONObject obj = new JSONObject(menuList.get(i));
                MenuModel menu = new MenuModel(obj.getString("name"), obj.getString("type"), obj.getInt("price"), obj.getInt("qty"));
                total += menu.getPrice()*menu.getQty();

                LinearLayout addMenu = new LinearLayout(this);
                LinearLayout detailMenu = new LinearLayout(this);
                LinearLayout qtyMenu = new LinearLayout(this);
                TextView qtyText = new TextView(this);
                TextView subTotalMenuText = new TextView(this);
                TextView nameText = new TextView(this);
                TextView priceText = new TextView(this);

                nameText.setText(menu.getName());
                nameText.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT));


                priceText.setText("Rp."+String.valueOf(menu.getPrice()));
                priceText.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                priceText.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));

                detailMenu.setOrientation(LinearLayout.HORIZONTAL);
                detailMenu.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 80));
                detailMenu.addView(nameText);
                detailMenu.addView(priceText);

                qtyText.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                qtyText.setLayoutParams(new ViewGroup.LayoutParams(250, 100));
                qtyText.setText(String.valueOf(menu.getQty())+" X ");

                subTotalMenuText.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
                subTotalMenuText.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 100));
                subTotalMenuText.setText("Rp."+String.valueOf(menu.getQty() * menu.getPrice()));

                qtyMenu.setOrientation(LinearLayout.HORIZONTAL);
                qtyMenu.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 120));
                qtyMenu.addView(qtyText);
                qtyMenu.addView(subTotalMenuText);

                addMenu.setOrientation(LinearLayout.VERTICAL);
                addMenu.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 260));
                addMenu.addView(detailMenu);
                addMenu.addView(qtyMenu);
                bodyLayout.addView(addMenu);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        Space space2 = new Space(this);
        space2.setLayoutParams(new ViewGroup.LayoutParams(0,100));

        bodyLayout.addView(space2);

        TextView totalFieldText = new TextView(this);
        totalFieldText.setText("Total");

        TextView totalText = new TextView(this);
        totalText.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
        totalText.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
        totalText.setText("Rp."+String.valueOf(total));

        LinearLayout totalFieldLayout = new LinearLayout(this);
        totalFieldLayout.setOrientation(LinearLayout.HORIZONTAL);
        totalFieldLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
        totalFieldLayout.addView(totalFieldText);
        totalFieldLayout.addView(totalText);

        bodyLayout.addView(totalFieldLayout);

        TextView ppnFieldText = new TextView(this);
        ppnFieldText.setText("PPN 10%");

        TextView ppnText = new TextView(this);
        ppnText.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
        ppnText.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
        ppnText.setText("Rp."+String.valueOf(total*10/100));

        LinearLayout ppnFieldLayout = new LinearLayout(this);
        ppnFieldLayout.setOrientation(LinearLayout.HORIZONTAL);
        ppnFieldLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
        ppnFieldLayout.addView(ppnFieldText);
        ppnFieldLayout.addView(ppnText);

        bodyLayout.addView(ppnFieldLayout);

        Space space3 = new Space(this);
        space3.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,2));
        space3.setBackgroundColor(getResources().getColor(R.color.black));

        bodyLayout.addView(space3);

        TextView subTotalFieldText = new TextView(this);
        subTotalFieldText.setText("Sub Total");

        TextView subTotalText = new TextView(this);
        subTotalText.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
        subTotalText.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
        subTotalText.setText("Rp."+String.valueOf(total+(total*10/100)));

        LinearLayout subTotalFieldLayout = new LinearLayout(this);
        subTotalFieldLayout.setOrientation(LinearLayout.HORIZONTAL);
        subTotalFieldLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
        subTotalFieldLayout.addView(subTotalFieldText);
        subTotalFieldLayout.addView(subTotalText);

        bodyLayout.addView(subTotalFieldLayout);

        Space space4 = new Space(this);
        space4.setLayoutParams(new ViewGroup.LayoutParams(0,20));

        bodyLayout.addView(space4);

        TextView cashFieldText = new TextView(this);
        cashFieldText.setText("Uang");

        TextView cashText = new TextView(this);
        cashText.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
        cashText.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
        cashText.setText("Rp."+String.valueOf(cash));

        LinearLayout cashFieldLayout = new LinearLayout(this);
        cashFieldLayout.setOrientation(LinearLayout.HORIZONTAL);
        cashFieldLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
        cashFieldLayout.addView(cashFieldText);
        cashFieldLayout.addView(cashText);

        bodyLayout.addView(cashFieldLayout);

        TextView changeFieldText = new TextView(this);
        changeFieldText.setText("Kembali");

        TextView changeText = new TextView(this);
        changeText.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
        changeText.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
        changeText.setText("Rp."+String.valueOf(cash-(total+total*10/100)));

        LinearLayout changeFieldLayout = new LinearLayout(this);
        changeFieldLayout.setOrientation(LinearLayout.HORIZONTAL);
        changeFieldLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
        changeFieldLayout.addView(changeFieldText);
        changeFieldLayout.addView(changeText);

        bodyLayout.addView(changeFieldLayout);

    }

    public void back(View view) {
        Intent intent = new Intent();
        intent.putExtra("data", true);
        setResult(RESULT_OK, intent);
        finish();
    }

    public void submitPayment(View view) {
        try {
            int cash = Integer.parseInt(cashEditText.getText().toString());
            if(cash < total+total*11/100){

            }
        } catch(NumberFormatException nfe) {
            System.out.println("Could not parse " + nfe);
        }
    }
}