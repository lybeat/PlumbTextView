package cc.widget.sample;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import cc.sayaki.widget.PlumbTextView;

/**
 * Author: lybeat
 * Date: 2016/11/21
 */

public class FamilyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_family);

        PlumbTextView familyVTxt = (PlumbTextView) findViewById(R.id.family_vtxt);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/FZLTCXHJW.TTF");
        familyVTxt.setTypeface(typeface);
    }
}
