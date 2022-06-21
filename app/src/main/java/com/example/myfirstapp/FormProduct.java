package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class FormProduct extends AppCompatActivity {
    boolean isFormAdd = true;
    EditText nameEditText, categoryEditText, priceEditText;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_product);
        Intent intent = getIntent();
        LinearLayout bodyLayout = findViewById(R.id.bodyLayout);

        nameEditText = new EditText(this);
        categoryEditText = new EditText(this);
        priceEditText = new EditText(this);
        priceEditText.setInputType(InputType.TYPE_CLASS_NUMBER);

        if(intent.getStringExtra("id") != null){
            isFormAdd = false;
            id = intent.getStringExtra("id");
            getSupportActionBar().setTitle("Edit Form");
            nameEditText.setText(intent.getStringExtra("id"));
            nameEditText.setText(intent.getStringExtra("name"));
            categoryEditText.setText(intent.getStringExtra("category"));
            priceEditText.setText(String.valueOf(intent.getIntExtra("price",0)));
        }else{
            getSupportActionBar().setTitle("Add Form");
        }

//        Form
        TextView nameText = new TextView(this);
        nameText.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
        nameText.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
        nameText.setText("Product Name");

        bodyLayout.addView(nameText);
        bodyLayout.addView(nameEditText);

        TextView categoryText = new TextView(this);
        categoryText.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
        categoryText.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
        categoryText.setText("Product Category");

        bodyLayout.addView(categoryText);
        bodyLayout.addView(categoryEditText);

        TextView priceText = new TextView(this);
        priceText.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
        priceText.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
        priceText.setText("Product Price");

        bodyLayout.addView(priceText);
        bodyLayout.addView(priceEditText);
    }

    public void submitForm(View view) {
        try{
            Map<String, Object> product = new HashMap<>();
            product.put("name", String.valueOf(nameEditText.getText()));
            product.put("category", String.valueOf(categoryEditText.getText()));
            product.put("price", Integer.parseInt(String.valueOf(priceEditText.getText())));
            if(isFormAdd){
                FirebaseFirestore.getInstance().collection("products").add(product);
            }else{
                FirebaseFirestore.getInstance().document("products/"+id).update(product);
            }
            Toast toast = Toast.makeText(getApplicationContext(), "Berhasil menyimpan data", Toast.LENGTH_SHORT);
            toast.show();
            this.finish();
        }catch (Exception e){
            Toast toast = Toast.makeText(getApplicationContext(), "Gagal menyimpan data", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}