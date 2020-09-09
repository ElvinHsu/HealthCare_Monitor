package bupt.mxly.healthcare;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import bupt.mxly.healthcare.db.DBAdapter;
import bupt.mxly.healthcare.db.UserInfo;
import bupt.mxly.healthmonitor.R;

public class UpdateActivity extends AppCompatActivity {
    private Button insertbutton;
    private EditText phonetoinsert;
    private EditText pwdtoinsert;
    private EditText pwdtoverify;
    private EditText nametoinsert;
    private EditText agetoinsert;
    private EditText heightroinsert;
    private EditText weighttoinsert;
    private EditText sextoinsert;
    private EditText bloodtoinsert;
    private EditText historytoinsert;
    private EditText addresstoinsert;
    String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update);
        insertbutton = (Button)findViewById(R.id.bt_insert);
        //phonetoinsert = (EditText) findViewById(R.id.phonetoinsert);
//        pwdtoinsert = (EditText) findViewById(R.id.pwdtoinsert);
//        pwdtoverify=(EditText)findViewById(R.id.pwdtoverify);
//        nametoinsert = (EditText) findViewById(R.id.nametoinsert);
//        agetoinsert = (EditText) findViewById(R.id.agetoinsert);
//        heightroinsert = (EditText) findViewById(R.id.heighttoinsert);
//        weighttoinsert = (EditText) findViewById(R.id.weighttoinsert);
//        sextoinsert = (EditText) findViewById(R.id.sextoinsert);
//        bloodtoinsert = (EditText) findViewById(R.id.bloodtoinsert);
//        historytoinsert = (EditText) findViewById(R.id.historytoinsert);
//        addresstoinsert = (EditText) findViewById(R.id.addresstoinsert);

        setStatusBarFullTransparent();
        setFitSystemWindow(true);
        setStatusBarLightMode(this, true);
        phone = getIntent().getStringExtra("data");

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

//                if(pwdtoinsert.getText().toString().equals(pwdtoverify.getText().toString())){
//                    DBAdapter db = new DBAdapter();
//                    UserInfo info = new UserInfo();
//                    info.setPhone(phone);
//                    System.out.println(phone);
//                    info.setName(nametoinsert.getText().toString());
//                    info.setPwd(rc4.crypt(pwdtoinsert.getText().toString()));
//                    info.setAge(Integer.parseInt(agetoinsert.getText().toString()));
//                info.setHeight(Double.parseDouble(heightroinsert.getText().toString()));
//                info.setWeight(Double.parseDouble(weighttoinsert.getText().toString()));
//                info.setSex(sextoinsert.getText().toString());
//                info.setBlood(bloodtoinsert.getText().toString());
//                info.setHistory(historytoinsert.getText().toString());
//                info.setAddress(addresstoinsert.getText().toString());
//
//                    db.updateUserInfo(info);
//
//                    Intent intent=new Intent(UpdateActivity.this, MainActivity.class);
//                    intent.putExtra("userinfo",info);
//                    setResult(1,intent);
//                    finish();
//                }
//                else{
//                    Toast toast=Toast.makeText(UpdateActivity.this,"两次输入密码不一致！请您重新输入",Toast.LENGTH_SHORT    );
//                    toast.setGravity(Gravity.CENTER, 0, 0);
//                    toast.show();
//                    pwdtoinsert.setText("");
//                    pwdtoverify.setText("");
//                }

            finish();
            }
        });
    }
    /**
     * 全透状态栏
     */
    protected void setStatusBarFullTransparent() {
        if (Build.VERSION.SDK_INT >= 21) {//21表示5.0
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= 19) {//19表示4.4
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //虚拟键盘也透明
            //getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }

    /**
     * 如果需要内容紧贴着StatusBar
     * 应该在对应的xml布局文件中，设置根布局fitsSystemWindows=true。
     */
    private View contentViewGroup;

    protected void setFitSystemWindow(boolean fitSystemWindow) {
        if (contentViewGroup == null) {
            contentViewGroup = ((ViewGroup) findViewById(android.R.id.content)).getChildAt(0);
        }
        contentViewGroup.setFitsSystemWindows(fitSystemWindow);
    }

    /**
     * 让状态栏字体颜色变深
     * @param activity
     * @param isLightMode
     */
    public static void setStatusBarLightMode(Activity activity, boolean isLightMode) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Window window = activity.getWindow();
            int option = window.getDecorView().getSystemUiVisibility();
            if (isLightMode) {
                option |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            } else {
                option &= ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            }
            window.getDecorView().setSystemUiVisibility(option);
        }
    }
}
