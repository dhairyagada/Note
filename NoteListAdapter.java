package com.example.dhairya.note1;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class NoteListAdapter extends BaseAdapter{

    Context context;
    ArrayList<String> userID;
    ArrayList<String> UserTitle;
    ArrayList<String> UserNote;



    public NoteListAdapter(
            Context context2,
            ArrayList<String> id,
            ArrayList<String> title,
            ArrayList<String> note

    ) {

        this.context = context2;
        this.userID = id;
        this.UserTitle = title;
        this.UserNote= note;

    }

    public int getCount() {
        // TODO Auto-generated method stub
        return userID.size();
    }

    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
    }

    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    public View getView(int position, View child, ViewGroup parent) {

        Holder holder;

        LayoutInflater layoutInflater;

        if (child == null) {
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            child = layoutInflater.inflate(R.layout.activity_note_list_adapter, null);

            holder = new Holder();

           // holder.textviewid = (TextView) child.findViewById(R.id.textViewID);
            holder.textviewtitle = (TextView) child.findViewById(R.id.textViewTitle);
            holder.textviewnote=(TextView)child.findViewById(R.id.textViewNote);


            child.setTag(holder);

        } else {

            holder = (Holder) child.getTag();
        }
       // holder.textviewid.setText(userID.get(position));
        holder.textviewtitle.setText(UserTitle.get(position));
        holder.textviewnote.setText(UserNote.get(position));


        return child;
    }

    public class Holder {
       // TextView textviewid;
        TextView textviewtitle;
        TextView textviewnote;

    }
}
