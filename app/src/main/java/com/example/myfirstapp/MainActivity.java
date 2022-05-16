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
import android.widget.Space;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private DatePickerDialog.OnDateSetListener dateSetListener;
    List<MenuModel> menus = new ArrayList<MenuModel>();
    EditText cashEditText;

    int total(){
        int result = 0;
        for(int i=0;i<menus.size();i++){
            result+=menus.get(i).getPrice()*menus.get(i).getQty();
        }
        return result;
    }

    int ppn(){
        int result =0;
        int total = total();
        if(total>0)
            result+=total*10/100;
        return result;
    }

    int subTotal(){
        int result = 0;
        result +=total()+ppn();
        return result;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        menus.add(new MenuModel("Honey Garlic Chicken Rice","Makanan",35000,0));
        menus.add(new MenuModel("Beef Burger","Makanan",30000,0));
        menus.add(new MenuModel("Regular Fries","Makanan",25000,0));
        menus.add(new MenuModel("Ice Cream Cone","Minuman",10000,0));
        menus.add(new MenuModel("Flurry Oreo","Minuman",18000,0));
        menus.add(new MenuModel("Fanta Float","Minuman",15000, 0));
        LinearLayout menuLayout = findViewById(R.id.menu_layout);

        TextView cashFieldText = new TextView(this);
        cashFieldText.setText("Uang");

        TextView totalFieldText = new TextView(this);
        totalFieldText.setText("Total");

        TextView totalText = new TextView(this);
        totalText.setId(49999+1);
        totalText.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
        totalText.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
        totalText.setText("Rp."+String.valueOf(total()));

        LinearLayout totalFieldLayout = new LinearLayout(this);
        totalFieldLayout.setOrientation(LinearLayout.HORIZONTAL);
        totalFieldLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
        totalFieldLayout.addView(totalFieldText);
        totalFieldLayout.addView(totalText);

        menuLayout.addView(totalFieldLayout);

        TextView ppnFieldText = new TextView(this);
        ppnFieldText.setText("PPN 10%");

        TextView ppnText = new TextView(this);
        ppnText.setId(59999+1);
        ppnText.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
        ppnText.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
        ppnText.setText("Rp."+String.valueOf(ppn()));

        LinearLayout ppnFieldLayout = new LinearLayout(this);
        ppnFieldLayout.setOrientation(LinearLayout.HORIZONTAL);
        ppnFieldLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
        ppnFieldLayout.addView(ppnFieldText);
        ppnFieldLayout.addView(ppnText);

        menuLayout.addView(ppnFieldLayout);

        Space space3 = new Space(this);
        space3.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,2));
        space3.setBackgroundColor(getResources().getColor(R.color.black));

        menuLayout.addView(space3);

        TextView subTotalFieldText = new TextView(this);
        subTotalFieldText.setText("Sub Total");

        TextView subTotalText = new TextView(this);
        subTotalText.setId(69999+1);
        subTotalText.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
        subTotalText.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
        subTotalText.setText("Rp."+String.valueOf(subTotal()));

        LinearLayout subTotalFieldLayout = new LinearLayout(this);
        subTotalFieldLayout.setOrientation(LinearLayout.HORIZONTAL);
        subTotalFieldLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
        subTotalFieldLayout.addView(subTotalFieldText);
        subTotalFieldLayout.addView(subTotalText);

        menuLayout.addView(subTotalFieldLayout);

        menuLayout.addView(cashFieldText);

        cashEditText = new EditText(this);
        cashEditText.setInputType(InputType.TYPE_CLASS_NUMBER);

        menuLayout.addView(cashEditText);

        for(int i = 0; i<menus.size(); i++){
            LinearLayout addMenu = new LinearLayout(this);
            LinearLayout detailMenu = new LinearLayout(this);
            LinearLayout qtyMenu = new LinearLayout(this);
            Button incButton = new Button(this);
            Button decButton = new Button(this);
            TextView qtyText = new TextView(this);
            TextView nameText = new TextView(this);
            TextView priceText = new TextView(this);

            nameText.setText(menus.get(i).getName());
            nameText.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT));


            priceText.setText("Rp."+String.valueOf(menus.get(i).getPrice()));
            priceText.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
            priceText.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));

            detailMenu.setOrientation(LinearLayout.HORIZONTAL);
            detailMenu.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 80));
            detailMenu.addView(nameText);
            detailMenu.addView(priceText);

            qtyText.setId(30000+i);
            qtyText.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            qtyText.setLayoutParams(new ViewGroup.LayoutParams(120, 100));
            qtyText.setText("0");

            incButton.setId(90000+i);
            incButton.setText("+");
//            decButton.setWidth(40);
//            decButton.setHeight(40);
            incButton.setBackgroundColor(getResources().getColor(R.color.purple_700));
            incButton.setTextColor(getResources().getColor(R.color.white));
            incButton.setLayoutParams(new ViewGroup.LayoutParams(120,120));
            incButton.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    int index = v.getId();
                    index-=90000;
                    menus.get(index).inc();
                    TextView qty = findViewById(30000+index);
                    qty.setText(String.valueOf(menus.get(index).getQty()));

                    TextView totalText = findViewById(49999+1);
                    totalText.setText("Rp."+String.valueOf(total()));

                    TextView ppnText = findViewById(59999+1);
                    ppnText.setText("Rp."+String.valueOf(ppn()));

                    TextView subTotalText = findViewById(69999+1);
                    subTotalText.setText("Rp."+String.valueOf(subTotal()));
                }
            });


            decButton.setId(91000+i);
            decButton.setText("-");
//            decButton.setWidth(40);
//            decButton.setHeight(40);
            decButton.setBackgroundColor(getResources().getColor(R.color.purple_700));
            decButton.setTextColor(getResources().getColor(R.color.white));
            decButton.setLayoutParams(new ViewGroup.LayoutParams(120,120));
            decButton.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    int index = v.getId();
                    index-=91000;
                    if(menus.get(index).getQty()>0){
                        menus.get(index).dec();
                        TextView qty = findViewById(30000+index);
                        qty.setText(String.valueOf(menus.get(index).getQty()));

                        TextView totalText = findViewById(49999+1);
                        totalText.setText("Rp."+String.valueOf(total()));

                        TextView ppnText = findViewById(59999+1);
                        ppnText.setText("Rp."+String.valueOf(ppn()));

                        TextView subTotalText = findViewById(69999+1);
                        subTotalText.setText("Rp."+String.valueOf(subTotal()));
                    }
                }
            });

            qtyMenu.setOrientation(LinearLayout.HORIZONTAL);
            qtyMenu.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            qtyMenu.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 120));
            qtyMenu.addView(incButton);
            qtyMenu.addView(qtyText);
            qtyMenu.addView(decButton);

            addMenu.setOrientation(LinearLayout.VERTICAL);
            addMenu.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 260));
            addMenu.addView(detailMenu);
            addMenu.addView(qtyMenu);
            menuLayout.addView(addMenu);

        }
    }

    public void submitForm(View view){
        if(total()>0){
            try {
                int cash = Integer.parseInt(cashEditText.getText().toString());
                if(cash >= subTotal()){
                    Intent intent = new Intent(this, PaymentActivity.class);
                    Bundle extras = new Bundle();
                    ArrayList<String> menuList = new ArrayList<String>();
                    for(int i=0;i<menus.size();i++){
                        if(menus.get(i).getQty()>0){
                            MenuModel menu = menus.get(i);
                            JSONObject obj = new JSONObject();
                            try {
                                obj.put("name", menu.getName());
                                obj.put("price", menu.getPrice());
                                obj.put("type", menu.getType());
                                obj.put("qty", menu.getQty());

                                menuList.add(obj.toString());
                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                        }
                    }
                    extras.putStringArrayList("MENULIST", menuList);
                    extras.putInt("CASH", cash);
                    intent.putExtras(extras);
                    someActivityResultLauncher.launch(intent);
                }else{
                    Toast.makeText(this, "Uang tidak mencukupi",
                            Toast.LENGTH_LONG).show();
                }
            } catch(NumberFormatException nfe) {
                Toast.makeText(this, "Mohon masukkan uang dengan benar",
                        Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(this, "Keranjang masih kosong",
                    Toast.LENGTH_LONG).show();
        }

    }

    ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        for(int i=0;i<menus.size();i++){
                            menus.get(i).setQty(0);
                            TextView qtyText = findViewById(30000+i);
                            qtyText.setText(String.valueOf(menus.get(i).getQty()));
                        }
                        cashEditText.setText("");
                    }
                }
            });

}