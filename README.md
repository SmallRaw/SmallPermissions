# SmallPermissions

### 简介
Android 权限框架

### 用法
#### 用法一
使用注解，现支持普通方法以及静态方法，如果没有权限方法体里面的代码不会执行
```
//参数一 String[]:需要获取的权限
//参数二 int:如果没有权限时的提示
//参数三 boolean:是否打开设置页面(可不填)
@PermissionsApply(
            permissions = {Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION},
            hint = R.string.app_hint, openSetting = false)
public void test(){

}
```

#### 用法二
调用方法,
```
//参数一 Object:传入 Context、Fragment、Activity、Dialog 等，如果获取不到这些对象可以传 null
//参数二 String[]:需要获取的权限
//参数三 int:请求码
//参数四 callback:回调接口
SmallPermission.requestPermission(this,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                123,
                new PermissionsCallback() {
                    @Override
                    public void onPermissionGranted() {
                        Toast.makeText(SampleActivity.this, "成功", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onPermissionDenied(List<String> Permissions) {
                        Toast.makeText(SampleActivity.this, "失败", Toast.LENGTH_SHORT).show();
                    }
                });
```

### Todo List
1. 适配国内机型
