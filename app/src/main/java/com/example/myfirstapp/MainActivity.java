package com.example.myfirstapp;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.InputType;
import android.text.format.DateFormat;
import android.util.ArrayMap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Space;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayout menuLayout = findViewById(R.id.menu_layout);

        FirebaseFirestore.getInstance().collection("products").addSnapshotListener((EventListener<QuerySnapshot>) (snapshot, e) -> {
            menuLayout.removeAllViews();
            if (snapshot != null && !snapshot.getDocuments().isEmpty()) {
                List<ProductModel> products = new ArrayList<ProductModel>();
                for(int i = 0; i<snapshot.getDocuments().size();i++){
                    DocumentSnapshot doc = snapshot.getDocuments().get(i);
                    products.add(new ProductModel(doc.getId(), String.valueOf(doc.get("name")), String.valueOf(doc.get("category")), Integer.parseInt(String.valueOf(doc.get("price")))));
                }
//                TextView lengthText = new TextView(this);
//                lengthText.setText(String.valueOf(products.size()));
//
//                menuLayout.addView(lengthText);
                for(int i = 0; i<products.size(); i++){
                    ProductModel product = products.get(i);

                    RelativeLayout productLayout = new RelativeLayout(this);
                    LinearLayout titleLayout = new LinearLayout(this);

                    TextView nameText = new TextView(this);
                    TextView categoryText = new TextView(this);
                    TextView priceText = new TextView(this);

                    nameText.setText(product.getName());
                    nameText.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT));

                    categoryText.setText(product.getCategory());
                    categoryText.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT));

                    priceText.setText("Rp."+String.valueOf(product.getPrice()));
                    priceText.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
                    priceText.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));

                    titleLayout.setOrientation(LinearLayout.VERTICAL);
                    titleLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 260));
                    titleLayout.addView(nameText);
                    titleLayout.addView(categoryText);

                    productLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 260));
                    productLayout.addView(priceText);
                    productLayout.addView(titleLayout);

                    productLayout.setOnClickListener(new View.OnClickListener(){
                        @Override
                        public void onClick(View v){
                            Intent intent = new Intent(getApplicationContext(), DetailProduct.class);
                            Bundle extras = new Bundle();
                            extras.putString("id", product.getID());
                            extras.putString("name", product.getName());
                            extras.putString("category", product.getCategory());
                            extras.putInt("price", product.getPrice());
                            intent.putExtras(extras);
                            startActivity(intent);
                        }
                    });

                    menuLayout.addView(productLayout);
                }

            }else{
                TextView noDataText = new TextView(this);
                noDataText.setText("No Data");

                menuLayout.addView(noDataText);
            }
            return;
        });

    }

    public void insertForm(View view){
        Intent intent = new Intent(this, FormProduct.class);
        startActivity(intent);

    }
}