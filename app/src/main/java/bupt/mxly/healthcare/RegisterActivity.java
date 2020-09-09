package bupt.mxly.healthcare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import bupt.mxly.healthcare.db.DBAdapter;
import bupt.mxly.healthcare.db.UserInfo;
import bupt.mxly.healthmonitor.R;

public class RegisterActivity extends AppCompatActivity {
    private Button insertbutton;
    private EditText phonetoinsert;
    private EditText pwdtoinsert;
    private EditText nametoinsert;
    private EditText agetoinsert;
    private EditText heightroinsert;
    private EditText weighttoinsert;
    private EditText sextoinsert;
    private EditText bloodtoinsert;
    private EditText historytoinsert;
    private EditText addresstoinsert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        insertbutton = (Button)findViewById(R.id.bt_insert);
        phonetoinsert = (EditText) findViewById(R.id.phonetoinsert);
        pwdtoinsert = (EditText) findViewById(R.id.pwdtoinsert);
        nametoinsert = (EditText) findViewById(R.id.nametoinsert);
        agetoinsert = (EditText) findViewById(R.id.agetoinsert);
//        heightroinsert = (EditText) findViewById(R.id.heighttoinsert);
//        weighttoinsert = (EditText) findViewById(R.id.weighttoinsert);
//        sextoinsert = (EditText) findViewById(R.id.sextoinsert);
//        bloodtoinsert = (EditText) findViewById(R.id.bloodtoinsert);
//        historytoinsert = (EditText) findViewById(R.id.historytoinsert);
//        addresstoinsert = (EditText) findViewById(R.id.addresstoinsert);
    }

    @Override
    protected void onStart() {
        super.onStart();
        final RC4 rc4 = new RC4();
        //rc4算法初始化
        rc4.key = "WhQpVccuUyCblNuSmk6NXE3INAICmKdJpTFQUd5jYUSTg0thV58Kqrjk1KaLq0xZbJqAjhmr5lFzgCrbh4U6j2p5NarTW02YDv4QxkqhjbbH5SdzXuNt5xU4pEYHnM9Wkg34Sa1OU9zCNZk1tefeDrfBNR6419n3QdBPcESkJcXcsUzHws0gHDpHRzqPI0KRJd5s58Zc8vgQYFuT6GpWLbdrgOoV74Yj5mroMyGFW6DhMT8anvWRZLiFtWrbfR8d".toCharArray();
        rc4.initSbox();
        insertbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                DBAdapter db = new DBAdapter();
                UserInfo info = new UserInfo();
                info.setPhone(phonetoinsert.getText().toString());
                info.setName(nametoinsert.getText().toString());
                info.setPwd(rc4.crypt(pwdtoinsert.getText().toString()));
                info.setAge(Integer.parseInt(agetoinsert.getText().toString()));
//                info.setHeight(Double.parseDouble(heightroinsert.getText().toString()));
//                info.setWeight(Double.parseDouble(weighttoinsert.getText().toString()));
//                info.setSex(sextoinsert.getText().toString());
//                info.setBlood(bloodtoinsert.getText().toString());
//                info.setHistory(historytoinsert.getText().toString());
//                info.setAddress(addresstoinsert.getText().toString());
                db.insertUserInfo(info);

                Intent intent=new Intent(RegisterActivity.this, MainActivity.class);
                intent.putExtra("userinfo",info);
                setResult(1,intent);
                finish();
            }
        });
    }
}
