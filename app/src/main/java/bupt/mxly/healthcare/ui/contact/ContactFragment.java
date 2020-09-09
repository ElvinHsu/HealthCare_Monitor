package bupt.mxly.healthcare.ui.contact;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import bupt.mxly.healthmonitor.R;

public class ContactFragment extends Fragment implements View.OnClickListener {

    private ContactViewModel contactViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        contactViewModel = ViewModelProviders.of(this).get(ContactViewModel.class);
        View root = inflater.inflate(R.layout.fragment_contact, container, false);
        root.findViewById(R.id.btn_call_110).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent110 = new Intent(Intent.ACTION_DIAL);
                Uri data1 = Uri.parse("tel:110");
                intent110.setData(data1);
                startActivity(intent110);
            }
        });

        root.findViewById(R.id.btn_call_120).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent120 = new Intent(Intent.ACTION_DIAL);
                Uri data2 = Uri.parse("tel:120");
                intent120.setData(data2);
                startActivity(intent120);
            }
        });

        root.findViewById(R.id.btn_call_119).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent119 = new Intent(Intent.ACTION_DIAL);
                Uri data3 = Uri.parse("tel:119");
                intent119.setData(data3);
                startActivity(intent119);
            }
        });

        root.findViewById(R.id.btn_call_elder).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_elder = new Intent(Intent.ACTION_DIAL);
                Uri data4 = Uri.parse("tel:18810059259");
                intent_elder.setData(data4);
                startActivity(intent_elder);
            }
        });
        return root;
    }

    @Override
    public void onClick(View view) {

    }
}