package com.smallraw.library.smallpermissions.check.actualTest;

import android.content.Context;
import android.content.pm.PackageManager;
import android.media.MediaRecorder;

import java.io.File;

public class RecordAudioTest extends BaseTest {
  private Context mContext;

  public RecordAudioTest(Context context) {
    mContext = context;
  }

  @Override
  public boolean test() throws Throwable {
    File mTempFile = null;
    MediaRecorder mediaRecorder = new MediaRecorder();

    try {
      mTempFile = File.createTempFile("permission", "test");

      mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
      mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.AMR_NB);
      mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
      mediaRecorder.setOutputFile(mTempFile.getAbsolutePath());
      mediaRecorder.prepare();
      mediaRecorder.start();
      return true;
    } catch (Throwable e) {
      PackageManager packageManager = mContext.getPackageManager();
      return !packageManager.hasSystemFeature(PackageManager.FEATURE_MICROPHONE);
    } finally {
      try {
        mediaRecorder.stop();
      } catch (Exception ignored) {
      }
      try {
        mediaRecorder.release();
      } catch (Exception ignored) {
      }

      if (mTempFile != null && mTempFile.exists()) {
        mTempFile.delete();
      }
    }
  }
}
