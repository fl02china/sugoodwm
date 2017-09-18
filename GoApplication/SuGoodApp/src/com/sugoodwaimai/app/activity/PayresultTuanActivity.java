package com.sugoodwaimai.app.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.sugoodwaimai.app.R;
import com.sugoodwaimai.app.application.SugoodApplication;
import com.sugoodwaimai.app.global.Constant;
import com.sugoodwaimai.app.util.HttpUtil;
import com.sugoodwaimai.app.util.ToastUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import cz.msebera.android.httpclient.Header;

/**
 * Created by Administrator on 2017/9/15 0015.
 */

public class PayresultTuanActivity extends BaseActivity {
    String type;
    String orderId;
    String code;
    ImageView imageCode;
    TextView back;
    TextView tx_code;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tuan_payresult);
        Intent intentfinish = new Intent("FinishActivity");
        sendBroadcast(intentfinish);//发送对应的广播
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        type = bundle.getString("type");
        //code= bundle.getString("price");
        orderId=bundle.getString("orderId");
        requestData();
        RelativeLayout orderButton = (RelativeLayout) findViewById(R.id.order);
        tx_code= (TextView) findViewById(R.id.code);
        back = (TextView) findViewById(R.id.back);
        imageCode  = (ImageView) findViewById(R.id.imageCode);

        //getresult(type,tradeno);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });
        orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SugoodApplication.isLogin) {
                    //Toast.makeText(mContext, "订单", Toast.LENGTH_SHORT).show();
                    Intent intent2 = new Intent();

                    Bundle bundle=new Bundle();
                    bundle.putInt("type",1);

                    intent2.putExtras(bundle);
                    //    intent.setClass(MineActivity.this, UserOrderActivity.class);
                    intent2.setClass(PayresultTuanActivity.this, OrderManagerActivity.class);
                    startActivity(intent2);
                    finish();
                } else {
//                    Intent intent = new Intent();
//                    intent.setClass(MineActivity.this, LoginActivity.class);
//                    startActivity(intent);
                    ToastUtil.setToast(PayresultTuanActivity.this, "请先登录");
                }


            }
        });

    }

    private void requestData() {
        RequestParams params = new RequestParams();
        params.put("orderId", orderId);
        HttpUtil.post(Constant.TUAN_CODE_URL, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {


                String response =  new String(responseBody)  ;
                System.out.println("111aaaaresponse:"+response);
                try {
                    JSONObject myJsonObject = new JSONObject( response);
                  //  String List =  myJsonObject.getString("List") ;
                    JSONArray array = myJsonObject.getJSONArray("List");
                    String code =array.getJSONObject(0).getString("code");
                    System.out.println("111aaaacode:"+code);
                    Bitmap qrBitmap = generateBitmap(code, 500, 500);
                    imageCode.setImageBitmap(qrBitmap);
                    tx_code.setText("团购劵号："+code);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });

    }

    private Bitmap generateBitmap(String content, int width, int height) {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        Map<EncodeHintType, String> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        try {
            BitMatrix encode = qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, width, height, hints);
            int[] pixels = new int[width * height];
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (encode.get(j, i)) {
                        pixels[i * width + j] = 0x00000000;
                    } else {
                        pixels[i * width + j] = 0xffffffff;
                    }
                }
            }
            return Bitmap.createBitmap(pixels, 0, width, width, height, Bitmap.Config.RGB_565);
        } catch (WriterException e) {
            e.printStackTrace();
        }
        return null;
    }
}
