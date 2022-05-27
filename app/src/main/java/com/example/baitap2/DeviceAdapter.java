package com.example.baitap2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SwitchCompat;

import java.util.List;

public class DeviceAdapter extends ArrayAdapter<Device> {
    private IOnItemClickListener listener;

    public DeviceAdapter(@NonNull Context context, int resource, @NonNull List<Device> objects, IOnItemClickListener listener) {
        super(context, resource, objects);
        this.listener = listener;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View currentItemView = convertView;

        if (currentItemView == null) {
            currentItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_view_items, parent, false);
        }

        Device device = getItem(position);

        TextView textViewName = currentItemView.findViewById(R.id.textViewName);
        TextView textViewPhoneNumber = currentItemView.findViewById(R.id.textViewDescription);
        SwitchCompat switchStatus = currentItemView.findViewById(R.id.switchStatus);
        switchStatus.setChecked(device.getStatus());
        textViewName.setText(device.getName());
        textViewPhoneNumber.setText(device.getDescription());

        switchStatus.setOnClickListener(view -> listener.onClick(device, position));

        return currentItemView;
    }
}
