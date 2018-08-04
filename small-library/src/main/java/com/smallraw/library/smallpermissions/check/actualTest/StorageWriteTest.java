package com.smallraw.library.smallpermissions.check.actualTest;

import android.content.Context;
import android.os.Environment;

import java.io.File;

public class StorageWriteTest extends BaseTest {
  private Context mContext;

  public StorageWriteTest(Context context) {
    mContext = context;
  }

  @Override
  public boolean test() throws Throwable {
    File directory = Environment.getExternalStorageDirectory();
    if (!directory.exists() || !directory.canWrite()) return false;
    File parent = new File(directory, "Android");
    if (parent.exists() && parent.isFile()) if (!parent.delete()) return false;
    if (!parent.exists()) if (!parent.mkdirs()) return false;
    File file = new File(parent, "ANDROID.PERMISSION.TEST");
    if (file.exists()) return file.delete();
    else return file.createNewFile();
  }
}
