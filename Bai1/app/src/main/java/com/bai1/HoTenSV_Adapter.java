package com.bai1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class HoTenSV_Adapter extends ArrayAdapter<Contact_TenSV> {
    private final IOnItemClickListener listener;

    public HoTenSV_Adapter(@NonNull Context context, int resource, @NonNull List<Contact_TenSV> objects, IOnItemClickListener listener) {
        super(context, resource, objects);
        this.listener = listener;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View currentItemView = convertView;

        if (currentItemView == null) {
            currentItemView = LayoutInflater.from(getContext()).inflate(R.layout.item_contact, parent, false);
        }

        Contact_TenSV contact = getItem(position);

        TextView tvId = currentItemView.findViewById(R.id.tvId);
        TextView tvName = currentItemView.findViewById(R.id.tvName);
        TextView tvPhoneNumber = currentItemView.findViewById(R.id.tvPhoneNumber);

        //todo: mở đoạn này khi cần sử dụng vì khi dùng sẽ không hiện được context menu nữa
        //Sự kiện khi nhấn giữ một phần tử listview
//        currentItemView.setOnLongClickListener(view -> {
//            listener.onClick(contact, position);
//            return true;
//        });

        tvId.setText(String.valueOf(contact.getId()));
        tvName.setText(contact.getName());
        tvPhoneNumber.setText(contact.getPhoneNumber());

        return currentItemView;
    }
}
