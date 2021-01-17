package com.smallraw.library.smallpermissions.retriever;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import android.util.Log;

import com.smallraw.library.smallpermissions.permisson.ActivityPermission;
import com.smallraw.library.smallpermissions.permisson.IPermission;
import com.smallraw.library.smallpermissions.permisson.RequestPermissionsFragment;
import com.smallraw.library.smallpermissions.permisson.RequestPermissionsSupportFragment;

import java.util.HashMap;
import java.util.Map;

public class PermissionsClientRetriever implements Handler.Callback {
    public static final String FRAGMENT_TAG = "com.smallraw.library.smallpermissions.fragment";
    private static final String TAG = "PermissionsRetriever";

    private static final PermissionsClientRetriever INSTANCE = new PermissionsClientRetriever();

    private static final int ID_REMOVE_FRAGMENT_MANAGER = 1;
    private static final int ID_REMOVE_SUPPORT_FRAGMENT_MANAGER = 2;

    private final Handler mHandler;

    final Map<FragmentManager, RequestPermissionsSupportFragment> mPendingSupportPhotoManagerFragments =
            new HashMap();
    final Map<android.app.FragmentManager, RequestPermissionsFragment> mPendingPhotoManagerFragments =
            new HashMap();

    public PermissionsClientRetriever() {
        mHandler = new Handler(Looper.getMainLooper(), this /* Callback */);
    }

    public static PermissionsClientRetriever getInstance() {
        return INSTANCE;
    }

    public IPermission get(Context context) {
        return new ActivityPermission(context);
    }

    public IPermission get(Activity activity) {
        assertNotDestroyed(activity);
        android.app.FragmentManager fm = activity.getFragmentManager();
        return fragmentGet(activity.getApplicationContext(), fm);
    }

    public IPermission get(FragmentActivity activity) {
        assertNotDestroyed(activity);
        FragmentManager fm = activity.getSupportFragmentManager();
        return supportFragmentGet(activity.getApplicationContext(), fm);
    }

    public IPermission get(Fragment fragment) {
        if (fragment.getActivity() == null) {
            throw new IllegalArgumentException("You cannot start a load on a fragment before it is attached");
        }
        FragmentManager fm = fragment.getChildFragmentManager();
        return supportFragmentGet(fragment.getContext().getApplicationContext(), fm);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public IPermission get(android.app.Fragment fragment) {
        if (fragment.getActivity() == null) {
            throw new IllegalArgumentException("You cannot start a load on a fragment before it is attached");
        }
        android.app.FragmentManager fm = fragment.getChildFragmentManager();
        return fragmentGet(fragment.getActivity().getApplicationContext(), fm);
    }

    private IPermission fragmentGet(Context applicationContext, android.app.FragmentManager fm) {
        RequestPermissionsFragment current = getRequestManagerFragment(fm);
        return current;
    }

    private IPermission supportFragmentGet(Context applicationContext, FragmentManager fm) {
        RequestPermissionsSupportFragment current = getSupportRequestManagerFragment(fm);
        return current;
    }

    private RequestPermissionsSupportFragment getSupportRequestManagerFragment(final FragmentManager fm) {
        RequestPermissionsSupportFragment current = (RequestPermissionsSupportFragment) fm.findFragmentByTag(FRAGMENT_TAG);
        if (current == null) {
            current = mPendingSupportPhotoManagerFragments.get(fm);
            if (current == null) {
                current = new RequestPermissionsSupportFragment();
                mPendingSupportPhotoManagerFragments.put(fm, current);
                fm.beginTransaction().add(current, FRAGMENT_TAG).commitAllowingStateLoss();
                mHandler.obtainMessage(ID_REMOVE_SUPPORT_FRAGMENT_MANAGER, fm).sendToTarget();
            }
        }
        return current;
    }

    private RequestPermissionsFragment getRequestManagerFragment(final android.app.FragmentManager fm) {
        RequestPermissionsFragment current = (RequestPermissionsFragment) fm.findFragmentByTag(FRAGMENT_TAG);
        if (current == null) {
            current = mPendingPhotoManagerFragments.get(fm);
            if (current == null) {
                current = new RequestPermissionsFragment();
                mPendingPhotoManagerFragments.put(fm, current);
                fm.beginTransaction().add(current, FRAGMENT_TAG).commitAllowingStateLoss();
                mHandler.obtainMessage(ID_REMOVE_FRAGMENT_MANAGER, fm).sendToTarget();
            }
        }
        return current;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private static void assertNotDestroyed(Activity activity) {
        if (activity.isDestroyed()) {
            throw new IllegalArgumentException("You cannot start a load for a destroyed activity");
        }
    }

    @Override
    public boolean handleMessage(Message message) {
        boolean handled = true;
        Object removed = null;
        Object key = null;
        switch (message.what) {
            case ID_REMOVE_SUPPORT_FRAGMENT_MANAGER:
                FragmentManager supportFm = (FragmentManager) message.obj;
                key = supportFm;
                removed = mPendingSupportPhotoManagerFragments.remove(supportFm);
                break;
            case ID_REMOVE_FRAGMENT_MANAGER:
                android.app.FragmentManager fm = (android.app.FragmentManager) message.obj;
                key = fm;
                removed = mPendingPhotoManagerFragments.remove(fm);
                break;
            default:
                handled = false;
        }
        if (handled && removed == null && Log.isLoggable(TAG, Log.WARN)) {
            Log.w(TAG, "Failed to remove expected request manager fragment, manager: " + key);
        }
        return handled;
    }
}
