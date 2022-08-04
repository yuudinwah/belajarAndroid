package com.example.myfirstapp;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.MediaStore;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Space;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;

public class MainActivity extends AppCompatActivity {
    UserModel user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayout menuLayout = findViewById(R.id.menu_layout);
        FirebaseFirestore.getInstance().collection("users").whereEqualTo("id","7OOKRqXDO8TDAbDPkn2cFHajjdp1").addSnapshotListener((EventListener<QuerySnapshot>) (snapshot, e) -> {
            if (snapshot != null && !snapshot.getDocuments().isEmpty()) {
                DocumentSnapshot doc = snapshot.getDocuments().get(0);
                user = new UserModel(doc.getId(), String.valueOf(doc.get("userID")), String.valueOf(doc.get("status")), String.valueOf(doc.get("name")), String.valueOf(doc.get("email")), String.valueOf(doc.get("photo")), Integer.parseInt(String.valueOf(doc.get("balance"))), Integer.parseInt(String.valueOf(doc.get("point"))));

                ImageView userPhoto = findViewById(R.id.userPhoto);
                Glide.with(this).load(user.getPhoto()).into(userPhoto);

                TextView userNameText = findViewById(R.id.userName);
                userNameText.setText("Name : "+user.getName());

                TextView userEmailText = findViewById(R.id.userEmail);
                userEmailText.setText("Email : "+user.getEmail());

                TextView accountBalanceText = findViewById(R.id.accountBalance);
                accountBalanceText.setText("Sisa Deposit : "+user.getBalance());

            }else{
                TextView userNameText = findViewById(R.id.userName);
                userNameText.setText("User tidak ditemukan");
            }
            return;
        });
    }

    public void phoneSubmit(View view) {
        LinearLayout menuLayout = findViewById(R.id.menu_layout);
        EditText phoneController = findViewById(R.id.phoneController);
        if(phoneController.getText().length()>7){
            String phoneNumber = String.valueOf(phoneController.getText());
            if(phoneNumber.contains("+62")){
                phoneNumber = phoneNumber.replace("+62", "0");
            }
            String prefix = phoneNumber.substring(0, 4);
            String operator = "";
            if( prefix.equals("0831") || prefix.equals("0832") || prefix.equals("0832") || prefix.equals("0833") || prefix.equals("0838")){
                operator = "Axis";
            }else if(prefix.equals("0811") || prefix.equals("0812") || prefix.equals("0813")|| prefix.equals("0821")|| prefix.equals("0822")|| prefix.equals("0823") || prefix.equals("0851") || prefix.equals("0852") || prefix.equals("0853")){
                operator = "Telkomsel";
            }else if (prefix.equals("0814") || prefix.equals("0815") || prefix.equals("0816") || prefix.equals("0855") || prefix.equals("0856")||prefix.equals("0857") || prefix.equals("0858")){
                operator = "Indosat";
            }else if (prefix.equals("0817")|| prefix.equals("0818")|| prefix.equals("0819")||prefix.equals("0859") || prefix.equals("0877")||prefix.equals("0878")){
                operator = "XL";
            }else if (prefix.equals("0895") || prefix.equals("0896")|| prefix.equals("0897") || prefix.equals("0898") || prefix.equals("0899")){
                operator = "Tri";
            }else if (prefix.equals("0881") || prefix.equals("0882") || prefix.equals("0883") || prefix.equals("0884")||prefix.equals("0885")||prefix.equals("0886")||prefix.equals("0887")||prefix.equals("0888")||prefix.equals("0889")){
                operator = "Smartfren";
            }else{
                operator = "Profider tidak dikenal";
            }
            Toast toast = Toast.makeText(getApplicationContext(), operator, Toast.LENGTH_SHORT);
            toast.show();
            FirebaseFirestore.getInstance().collection("products").whereEqualTo("operator", operator).addSnapshotListener((EventListener<QuerySnapshot>) (snapshot, e) -> {
                menuLayout.removeAllViews();
                if (snapshot != null && !snapshot.getDocuments().isEmpty()) {
                    List<ProductModel> products = new ArrayList<ProductModel>();
                    for(int i = 0; i<snapshot.getDocuments().size();i++){
                        DocumentSnapshot doc = snapshot.getDocuments().get(i);
                        products.add(new ProductModel(doc.getId(), String.valueOf(doc.get("operator")), String.valueOf(doc.get("denom")), Integer.parseInt(String.valueOf(doc.get("price")))));
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
                                EditText phoneController = findViewById(R.id.phoneController);

                                Intent intent = new Intent(getApplicationContext(), PaymentActivity.class);
                                Bundle extras = new Bundle();
                                extras.putString("phoneNumber", String.valueOf(phoneController.getText()).replace("+62", "0"));
                                extras.putInt("accountBalance", user.getBalance());
                                extras.putString("userID", user.getUserID());

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
        }else{
            Toast toast = Toast.makeText(getApplicationContext(), "Nomor Handphone tidak boleh kosong", Toast.LENGTH_SHORT);
            toast.show();
        }

    }

    public void updateImage(View view) {
        final CharSequence[] items = {"Ambil Gambar", "Ambil dari Galeri", "Batalkan"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Ubah Gambar");
        builder.setItems(items, (dialog, index)->{
            if(index == 0){
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 10);
            }else if (index == 1){
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "Pilih Gambar"), 20);
            }else if (index == 2){
                dialog.dismiss();
            }
        });
        builder.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 20 && resultCode == RESULT_OK && data != null){
            final Uri path = data.getData();
            Thread thread = new Thread(()->{
               try{
                   InputStream inputStream = getContentResolver().openInputStream(path);
                   Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                   ImageView userPhoto = findViewById(R.id.userPhoto);
                   userPhoto.post(()->{
                       userPhoto.setImageBitmap(bitmap);
                   });
                   updatePhoto(bitmap);
               }catch(IOException e){
                   e.printStackTrace();
               }
            });
            thread.start();
        }else if(requestCode == 10 && resultCode == RESULT_OK){
            final Bundle extras = data.getExtras();
            Thread thread   = new Thread(()->{
                Bitmap bitmap = (Bitmap) extras.get("data");
                ImageView userPhoto = findViewById(R.id.userPhoto);
                userPhoto.post(()->{
                    userPhoto.setImageBitmap(bitmap);
                });
                updatePhoto(bitmap);
            });
            thread.start();
        }
    }

    private void updatePhoto(Bitmap bitmap){
//        ImageView userPhoto = findViewById(R.id.userPhoto);
//        userPhoto.setDrawingCacheEnabled(true);
//        userPhoto.buildDrawingCache();
//        Bitmap bitmap = ((BitmapDrawable) userPhoto.getDrawable()).getBitmap();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[]data = baos.toByteArray();

        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference reference = storage.getReference("users/"+user.getID());
        UploadTask uploadTask = reference.putBytes(data);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast toast = Toast.makeText(getApplicationContext(), "Gambar gagal diubah", Toast.LENGTH_SHORT);
                toast.show();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                if(taskSnapshot.getMetadata()!=null){
                    if(taskSnapshot.getMetadata().getReference() != null){
                        taskSnapshot.getMetadata().getReference().getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                            @Override
                            public void onComplete(@NonNull Task<Uri> task) {
                                if(task.getResult() != null){
                                    Map<String, Object> update = new HashMap<>();
                                    update.put("photo", task.getResult().toString());
                                    FirebaseFirestore.getInstance().document("users/"+user.getID()).update(update);
                                    Toast toast = Toast.makeText(getApplicationContext(), "Gambar berhasil diubah", Toast.LENGTH_SHORT);
                                    toast.show();
                                }else{
                                    Toast toast = Toast.makeText(getApplicationContext(), "Gambar gagal diubah", Toast.LENGTH_SHORT);
                                    toast.show();
                                }
                            }
                        });
                    }else{
                        Toast toast = Toast.makeText(getApplicationContext(), "Gambar gagal diubah", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }else{
                    Toast toast = Toast.makeText(getApplicationContext(), "Gambar gagal diubah", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
    }
}