package com.babybuy;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

import com.github.dhaval2404.imagepicker.ImagePicker;


public class EditItem extends AppCompatActivity {
    private Button buttonAddItem;
    private EditText name, price, description;
    private ImageView imageView;
    private Uri imageUri;

    public static Intent getIntent(Context context) {
        return new Intent(context, EditItem.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_items);

        findId();

        imageUri = Uri.EMPTY;
        imageView.setOnClickListener(this::pickImage);
        buttonAddItem.setOnClickListener(this::saveItem);
    }

    private void saveItem(View view) {
        String itemName = name.getText().toString();
        if (itemName.isEmpty()) {
            name.setError("Name field is empty");
            name.requestFocus();
        }
        double itemPrice = 0;
        try {
            itemPrice = Double.parseDouble(price.getText().toString());
        } catch (NullPointerException e) {
            Toast.makeText(
                    getApplicationContext(),
                    "Something wrong with price.",
                    Toast.LENGTH_SHORT
            ).show();
        } catch (NumberFormatException e) {
            Toast.makeText(
                    getApplicationContext(),
                    "Price should be a number",
                    Toast.LENGTH_SHORT
            ).show();
        }
        if (itemPrice <= 0) {
            price.setError("Price should be greater than 0.");
            price.requestFocus();
        }
        String itemDescription = description.getText().toString();
        if(itemDescription.isEmpty()) {
            description.setError("Description is empty.");
            description.requestFocus();
        }

        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;
        currentFirebaseUser.getUid().toString();

        Map<String, Object> map = new HashMap<>();
        map.put("name", name.getText().toString());
        map.put("price", price.getText().toString());
        map.put("userID", currentFirebaseUser.getUid());
        map.put("description", description.getText().toString());
        map.put("imageURL", imageUri.toString());

        FirebaseDatabase.getInstance().getReference().child("items").push()
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        name.setText("");
                        description.setText("");
                        price.setText("");
                        Toast.makeText(getApplicationContext(), "Inserted Successfully",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(EditItem.this, MainActivity.class);
                        startActivity(intent);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "Failed to insert",Toast.LENGTH_LONG).show();
                    }
                });
    }


    private void findId() {
        buttonAddItem = findViewById(R.id.btn_updateItem);
        name = (EditText)findViewById(R.id.updateName);
        price =(EditText)findViewById(R.id.updatePrice);
        description =(EditText) findViewById(R.id.updateDescription);
        imageView = findViewById(R.id.updateImage);
    }

    private void pickImage(View view) {
        ImagePicker.with(EditItem.this)
                .crop()                    //Crop image(Optional), Check Customization for more option
                .compress(1024)            //Final image size will be less than 1 MB(Optional)
                .maxResultSize(1080, 1080)    //Final image resolution will be less than 1080 x 1080(Optional)
                .start();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (data != null) {
            imageUri = data.getData();
            imageView.setImageURI(imageUri);
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}
