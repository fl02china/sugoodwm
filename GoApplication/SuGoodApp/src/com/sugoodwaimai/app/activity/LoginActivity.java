package com.sugoodwaimai.app.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.igexin.sdk.PushManager;
import com.litesuits.orm.LiteOrm;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.mob.tools.utils.UIHandler;
import com.sugoodwaimai.app.R;
import com.sugoodwaimai.app.application.SugoodApplication;
import com.sugoodwaimai.app.entity.Ctiy;
import com.sugoodwaimai.app.entity.User;
import com.sugoodwaimai.app.entity.UserIDAndPW;
import com.sugoodwaimai.app.global.Constant;
import com.sugoodwaimai.app.util.HttpUtil;
import com.sugoodwaimai.app.util.JsonUtil;
import com.sugoodwaimai.app.util.MD5Util;
import com.sugoodwaimai.app.util.ToastUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.tencent.qq.QQ;
import cz.msebera.android.httpclient.Header;

public class LoginActivity extends BaseActivity  implements Handler.Callback, PlatformActionListener {
    public static final String path = "http://192.168.1.66:8080/TuanShoppingServer/mypack/userAction_login";
    private Platform mPlatform;
    private EditText et_username;
    private EditText et_userpwd;
    private LiteOrm liteOrm;
    private static final int MSG_USERID_FOUND = 1;
    private static final int MSG_LOGIN = 2;
    private static final int MSG_AUTH_CANCEL = 3;
    private static final int MSG_AUTH_ERROR = 4;
    private static final int MSG_AUTH_COMPLETE = 5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_username = (EditText) findViewById(R.id.et_username);
        et_userpwd = (EditText) findViewById(R.id.et_userpwd);
//        this.changeBack();
    }
    public void qqLogin(View view){
        authorize(new QQ());


    }

    public void wxLogin(View view){

    }


    //执行授权,获取用户信息
    private void authorize(Platform plat) {
        if (plat.isAuthValid()) {
//            String userId = plat.getDb().getUserId();
//            if (!TextUtils.isEmpty(userId)) {
//                UIHandler.sendEmptyMessage(MSG_USERID_FOUND, this);
//                login(plat.getName(), userId, null);
//                return;

            plat.removeAccount(true);
            }

        plat.setPlatformActionListener(this);
        //true不使用SSO授权，false使用SSO授权
        plat.SSOSetting(false);
        plat.showUser(null);
    }

    // 判断登录是否成功
    public void onLogin(View view) {
        if (view.getId() == R.id.btn_login) {
            Log.d("geek", "登录");
            // 得到输入编辑框的数据
            final String name = et_username.getText().toString();
            final String pwd = et_userpwd.getText().toString();
//            et_username.setText("13077858411");
//            et_userpwd.setText("123456");
//            final String name = "13620906082";
//            final String pwd = "366325";
//            final String name = "13267889357";//13267889357   111111
//           final String pwd = "111111";
//            final String name = "13077858411";//13267889357   111111
//           final String pwd = "123456";
            if (TextUtils.isEmpty(name)) {
                Toast.makeText(LoginActivity.this, "用户名不能为空", Toast.LENGTH_LONG).show();
            } else if (TextUtils.isEmpty(pwd)) {
                Toast.makeText(LoginActivity.this, "密码不能为空", Toast.LENGTH_LONG).show();
            }else {
//            final String name = "18376543595";
//            final String pwd = "111111";


            Log.d("geek", "用户名：" + name + "密码：" + pwd);
            RequestParams data = new RequestParams();
            data.put("account", name);
            data.put("password", MD5Util.getMD5(pwd));

             String URL =  Constant.SUGOOGLOGIN;
            HttpUtil.post(URL, data, new JsonHttpResponseHandler() {

                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    super.onSuccess(statusCode, headers, response);
                    Log.e("ss", "onSuccess: " + response.toString());

                    try {
                        if (response.getBoolean("success")) {
                            SugoodApplication.isLogin = true;
                            User user = JsonUtil.toObject(response.getString("List"), User.class);

                            //   User user = JsonUtil.toObject(response.getString("content"), User.class);
                            SugoodApplication.user = user;

                            PushManager.getInstance().bindAlias(LoginActivity.this, user.getUserId());

                            UserIDAndPW userIDAndPW = new UserIDAndPW();


                            userIDAndPW.setLogin(true);
                            userIDAndPW.setPassword(pwd);
                            userIDAndPW.setUserID(name);
                            SugoodApplication.liteOrm.update(userIDAndPW);
                            //   String msg = response.getString("message");
                            Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_LONG).show();
                          //  initNetData();
                            Intent intent = new Intent();
                            intent.putExtra("nickname", SugoodApplication.user.getNickname());
                            intent.putExtra("face", SugoodApplication.user.getFace());
                            setResult(RESULT_OK, intent);

                            closeKeyboard(et_userpwd);

                            SharedPreferences preferences = getSharedPreferences("info", MODE_PRIVATE);
                            SharedPreferences.Editor sped = preferences.edit();
                            sped.putString("name", name);
                            sped.putString("pwd", pwd);
                            sped.putBoolean("istdlogin",false);
                            sped.commit();
                            finish();


                        } else {
                            SugoodApplication.isLogin = false;
                            ToastUtil.setToast(LoginActivity.this, response.getString("message"));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                    super.onFailure(statusCode, headers, responseString, throwable);
                }
            });


//            Log.d("geek", "data=" + data);
//            String result = "failure";
//            result = UserUtils.loginResult(path, data);
//            Log.d("geek", "返回结果：" + result);
//            if (name == null || (name == null && pwd == null)) {
//                // 弹出对话框（内容：“请输入Email或手机号”）
//                Toast.makeText(this, "请输入", Toast.LENGTH_LONG).show();
//            } else if (pwd == null) {
//                // 弹出对话框(内容："请输入密码")
//                Toast.makeText(this, "请输入", Toast.LENGTH_LONG).show();
//            } else {
//                if (result.equals("success")) {// 登录成功,跳转至
//                    Toast.makeText(this, result, Toast.LENGTH_LONG).show();
//                    Intent intent = new Intent(this, com.sugoodwaimai.app.activity.MineActivity.class);
//                    startActivity(intent);
//                } else {
//                    Toast.makeText(this, "登录失败", Toast.LENGTH_LONG).show();
//                }
//            }
        }
        }
    }

//    public void onReturnmine(View btn) {
//        if (btn.getId() == R.id.ib_return) {
//            Intent intent = new Intent(this, com.sugoodwaimai.app.activity.MineActivity.class);
//            startActivity(intent);
//        }
//    }

    public void register(View btn) {

        if (btn.getId() == R.id.btn_register) {
            Intent intent = new Intent();
            intent.setClass(this, RegisterActivity.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent();
            intent.setClass(this, ForgetPWActivity.class);
            startActivity(intent);
        }


    }


    //上传经纬度
    private void initNetData() {

        RequestParams params = new RequestParams();
        params.put("lat", "20.912420741522");
        params.put("lng", "110.08775659179");
        params.put("cityName", "湛江市");
        Log.e("ss", "initNetData: " + "jinru");
        HttpUtil.post(Constant.UPLOADLATLONG, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Log.e("", "onSuccess: " + response.toString());
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
                Log.e("", "onSuccess: " + response.toString());
                List<Ctiy> list = JsonUtil.toList(response.toString(), Ctiy.class);
                Log.e("", "onSuccess: " + list.get(0).toString());
                finish();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                Log.e("", "onFailure: " + responseString);
            }
        });
    }

    @Override
    public void onBackPressed() {

        finish();
        // super.onBackPressed();

    }

    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case MSG_USERID_FOUND: {
                //Toast.makeText(this, R.string.userid_found, Toast.LENGTH_SHORT).show();
            }
            break;
            case MSG_LOGIN: {

            }
            break;
            case MSG_AUTH_CANCEL: {
              //  Toast.makeText(this, R.string.auth_cancel, Toast.LENGTH_SHORT).show();
            }
            break;
            case MSG_AUTH_ERROR: {
              //  Toast.makeText(this, R.string.auth_error, Toast.LENGTH_SHORT).show();
            }
            break;
            case MSG_AUTH_COMPLETE: {
                Log.e("asd", "platform.getName():" + mPlatform.getName());
                Log.e("asd", "platform.getDb().getUserId()" + mPlatform.getDb().getUserId());
                final String openid = mPlatform.getDb().getUserId() + "";
                String gender = mPlatform.getDb().getUserGender();
                String head_url = mPlatform.getDb().getUserIcon();
                final String nickname = mPlatform.getDb().getUserName();

                Log.e("asd", "openid:" + openid);
                Log.e("asd", "gender:" + gender);
                Log.e("asd", "head_url:" + head_url);
                Log.e("asd", "nickname:" + nickname);
                RequestParams data = new RequestParams();
                data.put("openid", openid);
                data.put("headimgurl", head_url);
                data.put("nickname", nickname);
                String URL =  Constant.SUGOOGTHRLOGIN;
                HttpUtil.post(URL, data, new JsonHttpResponseHandler() {

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        super.onSuccess(statusCode, headers, response);
                        Log.e("ss", "onSuccess: " + response.toString());

                        try {
                            if (response.getBoolean("success")) {
                                SugoodApplication.isLogin = true;
                                User user = JsonUtil.toObject(response.getString("List"), User.class);

                                //   User user = JsonUtil.toObject(response.getString("content"), User.class);
                                SugoodApplication.user = user;
                                UserIDAndPW userIDAndPW = new UserIDAndPW();
                                userIDAndPW.setIstdLogin(true);
                                userIDAndPW.setLogin(false);
                                userIDAndPW.setPassword(user.getAccount());
                                userIDAndPW.setUserID(user.getAccount());
                                SugoodApplication.liteOrm.update(userIDAndPW);
                                //   String msg = response.getString("message");
                                Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_LONG).show();
                                //  initNetData();
                                Intent intent = new Intent();
                                intent.putExtra("nickname", SugoodApplication.user.getNickname());
                                intent.putExtra("face", SugoodApplication.user.getFace());
                                setResult(RESULT_OK, intent);


                                closeKeyboard(et_userpwd);


                                SharedPreferences preferences = getSharedPreferences("info", MODE_PRIVATE);
                                SharedPreferences.Editor sped = preferences.edit();
                                sped.putString("openid",openid);
                                sped.putString("nickname",nickname);
                                sped.putBoolean("istdlogin",true);
                                sped.commit();
                                finish();


                            } else {
                                SugoodApplication.isLogin = false;
                                ToastUtil.setToast(LoginActivity.this, response.getString("message"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                        super.onFailure(statusCode, headers, throwable, errorResponse);
                        Log.e("ss1", "onFailure: " +statusCode+ errorResponse);
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                        super.onFailure(statusCode, headers, throwable, errorResponse);
                        Log.e("ss2", "onFailure: " +statusCode+ errorResponse);
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                        super.onFailure(statusCode, headers, responseString, throwable);
                        Log.e("ss32", "onFailure: " +statusCode+ responseString);
                    }
                });

                //  Toast.makeText(this, R.string.auth_complete, Toast.LENGTH_SHORT).show();
            }
            break;
        }
        return false;
    }

    @Override
    public void onComplete(final Platform platform, int action, HashMap<String, Object> hashMap) {
        System.out.println("111111111111:"+action);
        mPlatform= platform;


        if (action == Platform.ACTION_USER_INFOR) {
            //登录成功,获取需要的信息
            UIHandler.sendEmptyMessage(MSG_AUTH_COMPLETE, this);

        }
    }

    @Override
    public void onError(Platform platform, int i, Throwable throwable) {

    }

    @Override
    public void onCancel(Platform platform, int i) {

    }


}
