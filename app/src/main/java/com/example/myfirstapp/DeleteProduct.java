package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class DeleteProduct extends AppCompatActivity {
    ProductModel product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_product);
        getSupportActionBar().setTitle("Hapus Product");

        Intent intent = getIntent();
        product = new ProductModel(intent.getStringExtra("id"), intent.getStringExtra("name"), intent.getStringExtra("category"), intent.getIntExtra("price", 0));

        LinearLayout bodyLayout = findViewById(R.id.bodyLayout);

        TextView warningText = new TextView(this);
        warningText.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
        warningText.setText("Yakin hapus "+product.getName()+". Setelah di hapus data tidak dapat dikembalikan?");

        bodyLayout.addView(warningText);
    }

    public void deleteProduct(View view) {
        try{
            FirebaseFirestore.getInstance().document("products/"+product.getID()).delete();
            Toast toast = Toast.makeText(getApplicationContext(), "Data berhasil di hapus", Toast.LENGTH_SHORT);
            toast.show();
            this.finish();
        }catch (Exception e){
            Toast toast = Toast.makeText(getApplicationContext(), "Gagal menghapus data", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public void cancelDelete(View view) {
        Intent intent = new Intent(getApplicationContext(), DetailProduct.class);
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