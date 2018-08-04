package com.smallraw.library.smallpermissions.check.actualTest;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;

public class ContactsReadTest extends BaseTest {
  private Context mContext;

  public ContactsReadTest(Context context) {
    mContext = context;
  }

  @Override
  public boolean test() throws Throwable {
    ContentResolver contentResolver = mContext.getContentResolver();
    String[] projection = new String[]{ContactsContract.Data._ID, ContactsContract.Data.DATA1};
    Cursor cursor = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, projection, null, null, null);
    if (cursor != null) {
      try {
        BaseTest.read(cursor);
      } finally {
        cursor.close();
      }
      return true;
    } else {
      return false;
    }
  }


}
