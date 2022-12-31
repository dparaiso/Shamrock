package com.example.shamrock;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
//Adapter for our list
public class ListAdapter extends ArrayAdapter<Patient> {


    public ListAdapter(Context context, ArrayList<Patient> patientArrayList){

        super(context, R.layout.patients_list_item,patientArrayList);

    }
    //get the current item id
    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Patient patient = getItem(position);

        if (convertView == null){

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.patients_list_item,parent,false);

        }
        // all attributes for our item and subitem design, and set the source
        ImageView imageView = convertView.findViewById(R.id.profile_pic);
        TextView userName = convertView.findViewById(R.id.personName);
        TextView id = convertView.findViewById(R.id.list_patient_id);

        imageView.setImageResource(patient.getImageId());
        userName.setText(patient.getUsername());
        id.setText(patient.getList_patient_id());


        return convertView;
    }
}