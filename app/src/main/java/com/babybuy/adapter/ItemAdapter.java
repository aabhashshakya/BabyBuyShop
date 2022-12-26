package com.babybuy.adapter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.babybuy.EditItem;
import com.babybuy.Models.Item;
import com.babybuy.R;
import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import de.hdodenhof.circleimageview.CircleImageView;

public class ItemAdapter extends FirebaseRecyclerAdapter<Item, ItemAdapter.myviewholder>
{
    public ItemAdapter(@NonNull FirebaseRecyclerOptions<Item> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull Item item) {
        holder.name.setText(item.getName());
        holder.price.setText(item.getPrice().toString());
        holder.description.setText(item.getDescription());
         Glide.with(holder.image.getContext()).load(item.getImageURL()).into(holder.image);
         holder.checkBox.setChecked(item.getIsPurchased());

        //edit button click
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (view.getContext(), EditItem.class);
                intent.putExtra("itemName", item.getName());
                intent.putExtra("itemDescription", item.getDescription());
                intent.putExtra("itemPrice", item.getPrice());
                intent.putExtra("itemURL", item.getImageURL());
                view.getContext().startActivity(intent);

            }
        });


        holder.sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent smsIntent = new Intent(Intent.ACTION_VIEW);
                smsIntent.setData(Uri.parse("smsto:"));
                smsIntent.putExtra("address"  , new String("0123456789"));
                smsIntent.putExtra("sms_body"  , "Item Name:" + item.getName()
                + "\r\nItem Description:" + item.getDescription()
                        + "\r\nItem Price:" + item.getPrice()
                        + "\r\nItem Purchase:" + item.getIsPurchased());
                view.getContext().startActivity(smsIntent);
            }
        });


        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(item.getIsPurchased() == null || item.getIsPurchased() == false) {
                    FirebaseDatabase.getInstance().getReference().child("items")
                            .child(getRef(position).getKey()).child("isPurchased").setValue(true);
                }else{
                    FirebaseDatabase.getInstance().getReference().child("items")
                            .child(getRef(position).getKey()).child("isPurchased").setValue(false);                }
            }
        });
    }

    private void startActivity() {
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
       View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,parent,false);
       return new myviewholder(view);
    }

    class myviewholder extends RecyclerView.ViewHolder
    {
        CircleImageView image;
        ImageView edit,delete,sms;
        TextView name,price, description;
        CheckBox checkBox;
        public myviewholder(@NonNull View itemView)
        {
            super(itemView);
            image=(CircleImageView)itemView.findViewById(R.id.img1);
            name=(TextView)itemView.findViewById(R.id.name);
            price=(TextView)itemView.findViewById(R.id.price);
            description=(TextView)itemView.findViewById(R.id.description);

            edit=(ImageView)itemView.findViewById(R.id.editicon);
            delete=(ImageView)itemView.findViewById(R.id.deleteicon);
            sms=(ImageView)itemView.findViewById(R.id.sms);
            checkBox=(CheckBox)itemView.findViewById(R.id.item_checkbox);
        }
    }
}
