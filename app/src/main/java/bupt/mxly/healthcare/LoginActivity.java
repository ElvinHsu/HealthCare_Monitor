package bupt.mxly.healthcare;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import bupt.mxly.healthcare.db.DBAdapter;
import bupt.mxly.healthcare.db.UserInfo;
import bupt.mxly.healthmonitor.R;

public class LoginActivity extends AppCompatActivity {
    EditText phone_login;
    EditText pwd_login;
    Button bt_login;
    TextView login_result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        phone_login = (EditText)findViewById(R.id.phone_login);
        pwd_login = (EditText)findViewById(R.id.pwd_login);
        bt_login = (Button)findViewById(R.id.bt_login);
        login_result=(TextView)findViewById(R.id.login_result);

        setStatusBarFullTransparent();
        setFitSystemWindow(true);
        setStatusBarLightMode(this, true);

    }
    @Override
    protected void onStart() {
        final RC4 rc4 = new RC4();
        //rc4算法初始化
        rc4.key="WhQpVccuUyCblNuSmk6NXE3INAICmKdJpTFQUd5jYUSTg0thV58Kqrjk1KaLq0xZbJqAjhmr5lFzgCrbh4U6j2p5NarTW02YDv4QxkqhjbbH5SdzXuNt5xU4pEYHnM9Wkg34Sa1OU9zCNZk1tefeDrfBNR6419n3QdBPcESkJcXcsUzHws0gHDpHRzqPI0KRJd5s58Zc8vgQYFuT6GpWLbdrgOoV74Yj5mroMyGFW6DhMT8anvWRZLiFtWrbfR8d".toCharArray();
        rc4.initSbox();
        super.onStart();

        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DBAdapter db = new DBAdapter();

                UserInfo userinf = db.queryUserInfo(phone_login.getText().toString());
                String correctpwd = rc4.crypt(userinf.getPwd());
                String pwd = pwd_login.getText().toString();
                if(correctpwd.equals(pwd)){
//                    login_result.setText("登录成功");

                    Intent intent=new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra("userinfo",userinf);
                    //startActivity(intent);
                    setResult(1,intent);
                    finish();
                }
                else{
                    login_result.setText("登录失败");
                }
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
