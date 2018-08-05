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
//with 参数 Object:传入 Context、Fragment、Activity、Dialog 等，如果获取不到这些对象可以传 null
//permission 参数 String[]:需要获取的权限
//onGranted 参数 Action:成功回调
//onDenied 参数 Action:失败回调
 SmallPermission.with(this)
            .permission(permissions)
            .onGranted(new Action() {
              @Override
              public void onAction(List<String> Permissions) {
                Toast.makeText(SampleActivity.this, "成功", Toast.LENGTH_SHORT).show();
              }
            })
            .onDenied(new Action() {
              @Override
              public void onAction(List<String> Permissions) {
                Toast.makeText(SampleActivity.this, "失败", Toast.LENGTH_SHORT).show();
              }
            })
            .start();
```

### Todo List
1. 适配国内机型
