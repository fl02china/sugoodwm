package com.sugoodwaimai.app.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;

import com.igexin.sdk.GTIntentService;
import com.igexin.sdk.PushConsts;
import com.igexin.sdk.PushManager;
import com.igexin.sdk.message.FeedbackCmdMessage;
import com.igexin.sdk.message.GTCmdMessage;
import com.igexin.sdk.message.GTTransmitMessage;
import com.igexin.sdk.message.SetTagCmdMessage;
import com.sugoodwaimai.app.R;
import com.sugoodwaimai.app.activity.MainActivity;
import com.sugoodwaimai.app.activity.OrderManagerActivity;
import com.sugoodwaimai.app.application.SugoodApplication;

/**
 * 继承 GTIntentService 接收来自个推的消息, 所有消息在线程中回调, 如果注册了该服务, 则务必要在 AndroidManifest中声明, 否则无法接受消息<br>
 * onReceiveMessageData 处理透传消息<br>
 * onReceiveClientId 接收 cid <br>
 * onReceiveOnlineState cid 离线上线通知 <br>
 * onReceiveCommandResult 各种事件处理回执 <br>
 */
public class DemoIntentService extends GTIntentService {

    private static final String TAG = "GetuiSdkDemo";

//    private AudioManager am =null;
//
//    //最大音量
//    float audioMaxVolumn;
//    //当前音量
//    float audioCurrentVolumn;
//    float volumnRatio;
//    //音效播放池
//    private SoundPool soundPool = new SoundPool(2,AudioManager.STREAM_MUSIC,0);
//    //存放音效的HashMap
//    private Map<Integer,Integer> map = new HashMap<Integer,Integer>()
    /**
     * 为了观察透传数据变化.
     */
    private static int cnt;
    int  soundCount =0;
    private MediaPlayer mPlayer = null;
    public DemoIntentService() {


    }

    @Override
    public void onReceiveServicePid(Context context, int pid) {
        Log.d(TAG, "onReceiveServicePid -> " + pid);
    }

    @Override
    public void onReceiveMessageData(Context context, GTTransmitMessage msg) {
        String appid = msg.getAppid();
        String taskid = msg.getTaskId();
        String messageid = msg.getMessageId();
        byte[] payload = msg.getPayload();
        String pkg = msg.getPkgName();
        String cid = msg.getClientId();

        // 第三方回执调用接口，actionid范围为90000-90999，可根据业务场景执行
        boolean result = PushManager.getInstance().sendFeedbackMessage(context, taskid, messageid, 90001);
        Log.d(TAG, "call sendFeedbackMessage = " + (result ? "success" : "failed"));

        Log.d(TAG, "onReceiveMessageData -> " + "appid = " + appid + "\ntaskid = " + taskid + "\nmessageid = " + messageid + "\npkg = " + pkg
                + "\ncid = " + cid);

        if (payload == null) {
            Log.e(TAG, "receiver payload = null");
        } else {
            String data = new String(payload);
            Log.d(TAG, "receiver payload = " + data);

           // Toast.makeText(getApplicationContext(),data, Toast.LENGTH_LONG).show();
            // 测试消息为了观察数据变化
            if (data.equals(getResources().getString(R.string.push_transmission_data))) {
                data = data + "-" + cnt;
                cnt++;
            }
            if (data.equals("order")){

                showNotification(context);
                startAlarm(context);
                //playMusic();

                  sendMessage(data, 0);


        }

        Log.d(TAG, "----------------------------------------------------------------------------------------------");
    }



    }

    private void playMusic() {
        mPlayer = MediaPlayer.create(this, R.raw.you_have_new_order);
        mPlayer.setLooping(false);

        mPlayer.setOnCompletionListener(
                new MediaPlayer.OnCompletionListener()
                {
                    @Override
                    public void onCompletion(MediaPlayer arg0)
                    {
                        System.out.println("11111soundCount:"+soundCount);
                        try
                        {
                            if(0==soundCount)
                            {
                                mPlayer.start();
                                soundCount++;
                            }
                            else if (1==soundCount){
                                soundCount=0;
                            }
                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();
                        }
                    }
                });
        mPlayer.start();
    }

    private void showNotification(Context context) {
        NotificationManager barmanager=(NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notice;
        Notification.Builder builder = new Notification.Builder(context).setTicker("商家已经接单")
                .setSmallIcon(R.drawable.push).setWhen(System.currentTimeMillis());
        Intent[] appIntent=null;
        appIntent=makeIntentStack(context);//上面有改方法
        appIntent[1].setAction(Intent.ACTION_MAIN);
        appIntent[1].addCategory(Intent.CATEGORY_LAUNCHER);
        appIntent[1].setFlags(Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);//关键的一步，设置启动模式
        PendingIntent contentIntent =PendingIntent.getActivities(context, 0,appIntent,PendingIntent.FLAG_UPDATE_CURRENT);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            notice = builder.setContentIntent(contentIntent).setContentTitle("速购得客户端").setContentText("商家已经接单").build();
            notice.flags=Notification.FLAG_AUTO_CANCEL;
            barmanager.notify(10,notice);

        }
    }
    //提示音
    private static void startAlarm(Context context) {
        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        if (notification == null) return;
        Ringtone r = RingtoneManager.getRingtone(context, notification);
        r.play();
    }
    Intent[] makeIntentStack(Context context){
            Intent[] intents = new Intent[2];
            intents[0] = Intent.makeRestartActivityTask(new ComponentName(context,MainActivity.class));


                //    intent.setClass(MineActivity.this, UserOrderActivity.class);

            intents[1] = new Intent(context,  OrderManagerActivity.class);
                Bundle bundle=new Bundle();

                bundle.putInt("type",1);

                intents[1].putExtras(bundle);
              //  intents[1].setClass(context, OrderManagerActivity.class);
            return intents;
        }
    @Override
    public void onReceiveClientId(Context context, String clientid) {
        Log.e(TAG, "onReceiveClientId -> " + "clientid = " + clientid);

        sendMessage(clientid, 1);
    }

    @Override
    public void onReceiveOnlineState(Context context, boolean online) {
        Log.d(TAG, "onReceiveOnlineState -> " + (online ? "online" : "offline"));
    }

    @Override
    public void onReceiveCommandResult(Context context, GTCmdMessage cmdMessage) {
        Log.d(TAG, "onReceiveCommandResult -> " + cmdMessage);

        int action = cmdMessage.getAction();

        if (action == PushConsts.SET_TAG_RESULT) {
            setTagResult((SetTagCmdMessage) cmdMessage);
        } else if ((action == PushConsts.THIRDPART_FEEDBACK)) {
            feedbackResult((FeedbackCmdMessage) cmdMessage);
        }
    }

    private void setTagResult(SetTagCmdMessage setTagCmdMsg) {
        String sn = setTagCmdMsg.getSn();
        String code = setTagCmdMsg.getCode();

        int text = R.string.add_tag_unknown_exception;
        switch (Integer.valueOf(code)) {
            case PushConsts.SETTAG_SUCCESS:
                text = R.string.add_tag_success;
                break;

            case PushConsts.SETTAG_ERROR_COUNT:
                text = R.string.add_tag_error_count;
                break;

            case PushConsts.SETTAG_ERROR_FREQUENCY:
                text = R.string.add_tag_error_frequency;
                break;

            case PushConsts.SETTAG_ERROR_REPEAT:
                text = R.string.add_tag_error_repeat;
                break;

            case PushConsts.SETTAG_ERROR_UNBIND:
                text = R.string.add_tag_error_unbind;
                break;

            case PushConsts.SETTAG_ERROR_EXCEPTION:
                text = R.string.add_tag_unknown_exception;
                break;

            case PushConsts.SETTAG_ERROR_NULL:
                text = R.string.add_tag_error_null;
                break;

            case PushConsts.SETTAG_NOTONLINE:
                text = R.string.add_tag_error_not_online;
                break;

            case PushConsts.SETTAG_IN_BLACKLIST:
                text = R.string.add_tag_error_black_list;
                break;

            case PushConsts.SETTAG_NUM_EXCEED:
                text = R.string.add_tag_error_exceed;
                break;

            default:
                break;
        }

        Log.d(TAG, "settag result sn = " + sn + ", code = " + code + ", text = " + getResources().getString(text));
    }

    private void feedbackResult(FeedbackCmdMessage feedbackCmdMsg) {
        String appid = feedbackCmdMsg.getAppid();
        String taskid = feedbackCmdMsg.getTaskId();
        String actionid = feedbackCmdMsg.getActionId();
        String result = feedbackCmdMsg.getResult();
        long timestamp = feedbackCmdMsg.getTimeStamp();
        String cid = feedbackCmdMsg.getClientId();

        Log.d(TAG, "onReceiveCommandResult -> " + "appid = " + appid + "\ntaskid = " + taskid + "\nactionid = " + actionid + "\nresult = " + result
                + "\ncid = " + cid + "\ntimestamp = " + timestamp);
    }

    private void sendMessage(String data, int what) {
        Message msg = Message.obtain();
        msg.what = what;
        msg.obj = data;
        SugoodApplication.sendMessage(msg);
    }
}
