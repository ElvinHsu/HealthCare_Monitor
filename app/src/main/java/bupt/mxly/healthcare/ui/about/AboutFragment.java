package bupt.mxly.healthcare.ui.about;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

import bupt.mxly.healthcare.LoginActivity;
import bupt.mxly.healthmonitor.R;
import bupt.mxly.healthcare.RegisterActivity;
import bupt.mxly.healthcare.db.UserInfo;
import tech.linjiang.suitlines.SuitLines;
import tech.linjiang.suitlines.Unit;

public class AboutFragment extends Fragment {

    private AboutViewModel aboutViewModel;
    //Button toinformation;
    Button tologin;
    Button toregister;
    Button logout;
    LinearLayout inforlayout;
    TextView username;
    TextView showage;
    TextView showheight;
    TextView showweight;
    TextView showsex;
    TextView showblood;
    TextView showhistory;
    TextView showaddress;
    UserInfo userInfo;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        aboutViewModel =
                ViewModelProviders.of(this).get(AboutViewModel.class);
        View root = inflater.inflate(R.layout.fragment_about, container, false);
//        inforlayout=(LinearLayout)root.findViewById(R.id.infolayout);
//        username = (TextView)root.findViewById(R.id.username);
//        showage=(TextView)root.findViewById(R.id.showage);
//        showheight=(TextView)root.findViewById(R.id.showheight);
//        showweight=(TextView)root.findViewById(R.id.showweight);
//        showsex=(TextView)root.findViewById(R.id.showsex);
//        showblood=(TextView)root.findViewById(R.id.showblood);
//        showhistory=(TextView)root.findViewById(R.id.showhisrory);
//        showaddress=(TextView)root.findViewById(R.id.showaddress);
        //toinformation=(Button)root.findViewById(R.id.bt_toinformation);
        toregister=(Button)root.findViewById(R.id.bt_toregister);
        tologin=(Button)root.findViewById(R.id.bt_tologin);
        logout=(Button)root.findViewById(R.id.bt_logout);
        tologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(getActivity(), LoginActivity.class);
                //startActivity(intent);
                startActivityForResult(intent,1);
            }
        });
        toregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(getActivity(), RegisterActivity.class);
                //startActivity(intent);
                startActivityForResult(intent,2);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                userInfo = new UserInfo();
                logout.setVisibility(getView().GONE);
                tologin.setVisibility(getView().VISIBLE);
                username.setVisibility(getView().GONE);
                inforlayout.setVisibility(getView().GONE);
                toregister.setVisibility(getView().VISIBLE);
            }
        });
//        toinformation.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                showage.setText(String.valueOf(userInfo.getAge()));
//                showheight.setText(String.valueOf(userInfo.getHeight()));
//                showweight.setText(String.valueOf(userInfo.getWeight()));
//                showsex.setText(userInfo.getSex());
//                showblood.setText(userInfo.getBlood());
//                showhistory.setText(userInfo.getHistory());
//                showaddress.setText(userInfo.getAddress());
//            }
//        });

        return root;
    }
    @Override
    public void onStart() {

        super.onStart();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        userInfo= (UserInfo)data.getSerializableExtra("userinfo");
        System.out.println(userInfo.getName());
        username.setVisibility(getView().VISIBLE);
        username.setText("欢迎您: "+userInfo.getName());
        tologin.setVisibility(getView().GONE);
        toregister.setVisibility(getView().GONE);
        logout.setVisibility(getView().VISIBLE);
        inforlayout.setVisibility(getView().VISIBLE);
        showage.setText(String.valueOf(userInfo.getAge()));
        showheight.setText(String.valueOf(userInfo.getHeight()));
        showweight.setText(String.valueOf(userInfo.getWeight()));
        showsex.setText(userInfo.getSex());
        showblood.setText(userInfo.getBlood());
        showhistory.setText(userInfo.getHistory());
        showaddress.setText(userInfo.getAddress());
    }
}