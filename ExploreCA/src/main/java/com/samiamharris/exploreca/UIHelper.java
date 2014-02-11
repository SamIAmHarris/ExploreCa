package com.samiamharris.exploreca;

import android.app.Activity;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by samharris on 2/11/14.
 */
public class UIHelper {

    public static void displayText(Activity activity, int id, String text) {
        TextView tv = (TextView) activity.findViewById(id);
        tv.setText(text);
    }

    public static String getText(Activity activity, int id) {
        EditText et = (EditText) activity.findViewById(id);
        return et.getText().toString();
    }

    public static boolean getCDChecked(Activity activity, int id) {
        CheckBox cb = (CheckBox) activity.findViewById(id);
        return cb.isChecked();
    }

    public static void setCDChecked(Activity activity, int id, boolean b) {
        CheckBox cb = (CheckBox) activity.findViewById(id);
        cb.setChecked(b);
    }


}
