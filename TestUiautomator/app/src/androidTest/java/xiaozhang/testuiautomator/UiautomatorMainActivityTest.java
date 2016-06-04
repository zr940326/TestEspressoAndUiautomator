package xiaozhang.testuiautomator;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.RemoteException;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.Until;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * Created by zhang on 2016/6/4.
 * 使用Ui automator来测试 MainActivity
 *
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class UiautomatorMainActivityTest {
    private UiDevice uiDevice;
    private static final int LAUNCH_TIMEOUT = 5000;//运行的时间
    private static final String APPLICATION_PACKAGE
            = "xiaozhang.testuiautomator";
   // @Before代替setUp方法，有多个依次执行   @After 代替tearDown方法   //uiautomatorviewer  自动化界面分析工具
    @Before
    public void startUiautomatorMainActivityHomeScreen() {

        //初始化UiDevice实例
        uiDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());

        //从主屏幕
        uiDevice.pressHome();

        //等待运行
        final String launcherPackage = getLauncherPackageName();
        assertThat(launcherPackage, notNullValue());
        uiDevice.wait(Until.hasObject(By.pkg(launcherPackage).depth(0)), LAUNCH_TIMEOUT);

        //启动测试应用
        Context context = InstrumentationRegistry.getContext();//获取上下文
        final Intent intent = context.getPackageManager()
                .getLaunchIntentForPackage("xiaozhang.testuiautomator");
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);

        //等待应用程序出现
        uiDevice.wait(//等待5000秒
                Until.hasObject(By.pkg("xiaozhang.testuiautomator")//找到满足条件的包，取第一个
                .depth(0)), LAUNCH_TIMEOUT);


    }




    @Test
    public void testChangeText_sameActivity() {
        //点击button的操作,执行输入文本，
        uiDevice.findObject(By.res(APPLICATION_PACKAGE, "main_bt1"))
                .click();
        try {
            uiDevice.sleep();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        //uiDevice.waitForIdle(6000);//空虚时间6秒

        uiDevice.findObject(By.res(APPLICATION_PACKAGE, "main_edit"))
                .setText("代码输入的额");


        //检测文本的显示内容
        UiObject2 changedMain_EditText = uiDevice.wait  //uidevice.wait 设备等待500毫秒
                (Until.findObject(By.res(APPLICATION_PACKAGE, "main_edit"))//通过Until工具查找到,main_eidt
                        , 2000);
        assertThat(changedMain_EditText.getText(), is("代码输入的额"));


    }

    @Test //检测测试条件是不是为空
    public void checkPreconditions() {
        assertThat(uiDevice, notNullValue());
    }


    @After
    public void finishUiautomatorMainActivityHomeScreen() {
        uiDevice = null;

    }

    /**
     * 获取运行的包
     *
     * @return
     */
    private String getLauncherPackageName() {
        //创建启动intent
        final Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);

        //使用manage获取运行的包名
        PackageManager pm = InstrumentationRegistry.getContext().getPackageManager();
        ResolveInfo resolveInfo = pm.resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY);

        return resolveInfo.activityInfo.packageName;
    }

}
