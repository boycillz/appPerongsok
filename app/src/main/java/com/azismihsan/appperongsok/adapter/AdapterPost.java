package com.azismihsan.appperongsok.adapter;

import android.content.Context;
import android.text.Layout;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.azismihsan.appperongsok.R;
import com.azismihsan.appperongsok.model.ModelPost;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.squareup.picasso.Picasso;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class AdapterPost extends RecyclerView.Adapter<AdapterPost.MyHolder> {

    Context context;
    List<ModelPost> postList;

    public AdapterPost(Context context, List<ModelPost> postList) {
        this.context = context;
        this.postList = postList;
    }


    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflate layout row_post.xml
        View view = LayoutInflater.from(context).inflate(R.layout.row_post, parent, false);

        return new  MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        //get data
        String uid = postList.get(position).getUid();
        String uEmail = postList.get(position).getuEmail();
        String uName = postList.get(position).getuName();
        String uDp = postList.get(position).getuDp();
        final String pImage = postList.get(position).getpImage();
        final String pId = postList.get(position).getpId();
        String pTitle = postList.get(position).getpTitle();
        String pDescription = postList.get(position).getpDescr();
        String pAddress = postList.get(position).getpAddress();
        String pTimeStamp = postList.get(position).getpTime();

        //convert timestamp to dd/mm/yyyy hh:mm am/pm
        Calendar calendar = Calendar.getInstance(Locale.getDefault());
        calendar.setTimeInMillis(Long.parseLong(pTimeStamp));
        String pTime = DateFormat.format("dd/MM/yyyy hh:mm aa", calendar).toString();

        //set data
        holder.uNameTv.setText(uName);
        holder.pTimeTv.setText(pTime);
        holder.pTitleTv.setText(pTitle);
        holder.pDescriptionTv.setText(pDescription);
        holder.pAddressTv.setText(pAddress);

        //set user dp
        try {
            Picasso.get().load(uDp).placeholder(R.drawable.ic_face_custom).into(holder.uPictureIv);
        }
        catch (Exception e){

        }

        //set post image
        //if there is no image i.e. pImage.equals("noImage") then hide ImageView
        if (pImage.equals("noImage")){
            //hide imageview
            holder.pImageIv.setVisibility(View.GONE);
        }
        else {
            //show imageview
            holder.pImageIv.setVisibility(View.VISIBLE);//make sure to correct this
            try {
                Picasso.get().load(pImage).into(holder.pImageIv);
            }
            catch (Exception e){

            }
        }


    }

    @Override
    public int getItemCount() {
        return postList.size();
    }


//    public AdapterPost(@NonNull FirebaseRecyclerOptions<ModelPost> options) {
//        super(options);
//    }
//
//    @Override
//    protected void onBindViewHolder(@NonNull MyHolder holder, int position, @NonNull ModelPost model) {
//        holder.pAddressTv.setText(model.getpAddress());
//        holder.pDescriptionTv.setText(model.getpDescr());
//        holder.pTitleTv.setText(model.getpTitle());
//        holder.pTimeTv.setText(model.getpTime());
//
//
//        //convert timestamp to dd/mm/yyyy hh:mm am/pm
//        Calendar calendar = Calendar.getInstance(Locale.getDefault());
//        calendar.setTimeInMillis(Long.parseLong(pTimeStamp));
//        String pTime = DateFormat.format("dd/MM/yyyy hh:mm aa", calendar).toString();
//    }
//
//    @NonNull
//    @Override
//    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_post, parent, false);
//        return new MyHolder(view);
//    }

    //viewholder class
    class MyHolder extends RecyclerView.ViewHolder {

        //view from row_post.xml
        ImageView uPictureIv, pImageIv;
        TextView uNameTv, pTimeTv, pTitleTv, pDescriptionTv, pAddressTv;
        LinearLayout profileLayout;

        public MyHolder(@NonNull View itemView) {
            super(itemView);

            //init views
            uPictureIv = itemView.findViewById(R.id.uPictureIv);
            pImageIv = itemView.findViewById(R.id.pImageIvPost);
            uNameTv = itemView.findViewById(R.id.uNameTv);
            pTimeTv = itemView.findViewById(R.id.pTimeTv);
            pTitleTv = itemView.findViewById(R.id.pTitleTv);
            pDescriptionTv = itemView.findViewById(R.id.pDescriptionTv);
            pAddressTv = itemView.findViewById(R.id.pAddressTv);
            profileLayout = itemView.findViewById(R.id.profileLayout);
        }
    }
}
