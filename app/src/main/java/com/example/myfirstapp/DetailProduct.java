package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DetailProduct extends AppCompatActivity {
    ProductModel product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_product);
        getSupportActionBar().setTitle("Detail Product");
        Intent intent = getIntent();
        product = new ProductModel(intent.getStringExtra("id"), intent.getStringExtra("name"), intent.getStringExtra("category"), intent.getIntExtra("price", 0));

        LinearLayout bodyLayout = findViewById(R.id.bodyLayout);

        TextView idFieldText = new TextView(this);
        idFieldText.setText("ID");
        TextView nameFieldText = new TextView(this);
        nameFieldText.setText("Nama");
        TextView categoryFieldText = new TextView(this);
        categoryFieldText.setText("Kategori");
        TextView priceFieldText = new TextView(this);
        priceFieldText.setText("Harga");

        TextView idValueText = new TextView(this);
        idValueText.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
        idValueText.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
        idValueText.setText(product.getID());
        TextView nameValueText = new TextView(this);
        nameValueText.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
        nameValueText.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
        nameValueText.setText(product.getName());
        TextView categoryValueText = new TextView(this);
        categoryValueText.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
        categoryValueText.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
        categoryValueText.setText(product.getCategory());
        TextView priceValueText = new TextView(this);
        priceValueText.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
        priceValueText.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
        priceValueText.setText(String.valueOf(product.getPrice()));

        LinearLayout idLayout = new LinearLayout(this);
        idLayout.setOrientation(LinearLayout.HORIZONTAL);
        idLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
        idLayout.addView(idFieldText);
        idLayout.addView(idValueText);

        LinearLayout nameLayout = new LinearLayout(this);
        nameLayout.setOrientation(LinearLayout.HORIZONTAL);
        nameLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
        nameLayout.addView(nameFieldText);
        nameLayout.addView(nameValueText);

        LinearLayout categoryLayout = new LinearLayout(this);
        categoryLayout.setOrientation(LinearLayout.HORIZONTAL);
        categoryLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
        categoryLayout.addView(categoryFieldText);
        categoryLayout.addView(categoryValueText);

        LinearLayout priceLayout = new LinearLayout(this);
        priceLayout.setOrientation(LinearLayout.HORIZONTAL);
        priceLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
        priceLayout.addView(priceFieldText);
        priceLayout.addView(priceValueText);

        bodyLayout.addView(idLayout);
        bodyLayout.addView(nameLayout);
        bodyLayout.addView(categoryLayout);
        bodyLayout.addView(priceLayout);
    }

    public void editForm(View view) {
        Intent intent = new Intent(getApplicationContext(), FormProduct.class);
        Bundle extras = new Bundle();
        extras.putString("id", product.getID());
        extras.putString("name", product.getName());
        extras.putString("category", product.getCategory());
        extras.putInt("price", product.getPrice());
        intent.putExtras(extras);
        this.finish();
        startActivity(intent);
    }

    public void deleteForm(View view) {
        Intent intent = new Intent(getApplicationContext(), DeleteProduct.class);
        Bundle extras = new Bundle();
        extras.putString("id", product.getID());
        extras.putString("name", product.getName());
        extras.putString("category", product.getCategory());
        extras.putInt("price", product.getPrice());
        intent.putExtras(extras);
        this.finish();
        startActivity(intent);
    }
}