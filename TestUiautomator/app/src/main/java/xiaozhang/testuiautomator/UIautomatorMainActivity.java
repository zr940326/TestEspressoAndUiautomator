package xiaozhang.testuiautomator;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by zhang on 2016/6/4.
 */
public class UIautomatorMainActivity extends Activity implements View.OnClickListener {
    private Button main_bt1;
    private EditText main_edit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_uiautomator_main_layout);
        main_bt1= (Button) findViewById(R.id.main_bt1);
        main_bt1.setOnClickListener(this);
        main_edit= (EditText) findViewById(R.id.main_edit);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_bt1:
                main_edit.setText("这是点击按钮，输入的文字！");

                break;

        }
    }
}
