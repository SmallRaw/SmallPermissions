package com.smallraw.library.smallpermissions.check.actualTest;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.provider.VoicemailContract;
import android.text.TextUtils;

public class VoicemailAddTest extends BaseTest {
  private Context mContext;
  private ContentResolver mResolver;

  public VoicemailAddTest(Context context) {
    mContext = context;
    mResolver = mContext.getContentResolver();
  }

  @Override
  public boolean test() throws Throwable {
    try {
      Uri mBaseUri = VoicemailContract.Voicemails.CONTENT_URI;
      ContentValues contentValues = new ContentValues();
      contentValues.put(VoicemailContract.Voicemails.DATE, System.currentTimeMillis());
      contentValues.put(VoicemailContract.Voicemails.NUMBER, "1");
      contentValues.put(VoicemailContract.Voicemails.DURATION, 1);
      contentValues.put(VoicemailContract.Voicemails.SOURCE_PACKAGE, "permission");
      contentValues.put(VoicemailContract.Voicemails.SOURCE_DATA, "permission");
      contentValues.put(VoicemailContract.Voicemails.IS_READ, 0);
      Uri newVoicemailUri = mResolver.insert(mBaseUri, contentValues);
      long id = ContentUris.parseId(newVoicemailUri);
      int count = mResolver.delete(mBaseUri, VoicemailContract.Voicemails._ID + "=?", new String[]{Long.toString(id)});
      return count > 0;
    } catch (Exception e) {
      String message = e.getMessage();
      if (!TextUtils.isEmpty(message)) {
        message = message.toLowerCase();
        return !message.contains("add_voicemail");
      }
      return false;
    }
  }
}
