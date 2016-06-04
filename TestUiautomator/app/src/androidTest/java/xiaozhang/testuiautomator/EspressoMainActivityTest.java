package xiaozhang.testuiautomator;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by zhang on 2016/6/4.
 * 使用Espresson测试 MainActivity
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class EspressoMainActivityTest {
    //为MainActivity提供依赖
    @Rule
    public ActivityTestRule<UIautomatorMainActivity> mMainActivityRule = new ActivityTestRule<UIautomatorMainActivity>(
            UIautomatorMainActivity.class);

    //进行测试
    @Test
    public void changeText_sameActvity(){
        //@点击button的操作,%执行输入文本，
        //@
        onView(withId(R.id.main_bt1)).
            //1.onView（）找元素 { withId（）按id查询   WithText（）按显示内容查询   }
        perform(click());
            //2.perform() 操作元素 {click() 点击, replaceText(text)替换文本, closeSoftKeyboard()关闭软键盘}
        //%
        onView(withId(R.id.main_edit)).
                perform(replaceText("代码输入的"),closeSoftKeyboard()); //typeText()输入文本


        //!检测文本的显示内容
        //!
        onView(withId(R.id.main_edit)).//check()检测结果
                check(matches(withText("代码输入的")));

    }


    //多个@Test *******



}
