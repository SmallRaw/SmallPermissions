package com.smallraw.library.smallpermissions.check.actualTest;

import android.content.Context;
import android.os.Environment;

import java.io.File;

public class StorageReadTest extends BaseTest {
  private Context mContext;

  public StorageReadTest(Context context) {
    mContext = context;
  }

  @Override
  public boolean test() throws Throwable {
    File directory = Environment.getExternalStorageDirectory();
    if (directory.exists() && directory.canRead()) {
      long modified = directory.lastModified();
      String[] pathList = directory.list();
      return modified > 0 && pathList != null;
    }
    return false;
  }
}
