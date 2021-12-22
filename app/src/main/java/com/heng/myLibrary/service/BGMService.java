package com.heng.myLibrary.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.heng.myLibrary.R;

/**
 * @author : HengZhang
 * @date : 2021/12/19 16:28
 * <p>
 * BGM服务
 */

public class BGMService extends Service {
    //创建播放器对象
    private MediaPlayer player;

    public BGMService() {
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        //获取MainActivity中 按钮的点击类型：根据不同类型处理不同事件
        String action = intent.getStringExtra("action");

        if ("play".equals(action)) {
            //播放
            playMusic();
        } else if ("stop".equals(action)) {
            //停止
            stopMusic();
        } else if ("pause".equals(action)) {
            //暂停
            pauseMusic();
        }
        return super.onStartCommand(intent, flags, startId);
    }

    /**
     * 播放音乐
     */
    public void playMusic() {
        if (player == null) {
            player = MediaPlayer.create(this, R.raw.music1);
        }
        player.start();
    }

    /**
     * 暂停播放
     */
    public void pauseMusic() {
        if (player != null && player.isPlaying()) {
            player.pause();
        }
    }

    /**
     * 停止播放
     */
    public void stopMusic() {
        if (player != null) {
            player.stop();
            player.reset();//重置
            player.release();//释放
            player = null;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //在服务死亡之前停止音乐
        stopMusic();
    }
}
