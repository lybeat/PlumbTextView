package cc.widget.sample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button normalBtn = (Button) findViewById(R.id.normal_btn);
        normalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, NormalActivity.class);
                startActivity(intent);
            }
        });
        Button paddingBtn = (Button) findViewById(R.id.padding_btn);
        paddingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, PaddingActivity.class);
                startActivity(intent);
            }
        });

        Button formatBtn = (Button) findViewById(R.id.format_btn);
        formatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, FormatActivity.class);
                startActivity(intent);
            }
        });
        Button leftLineBtn = (Button) findViewById(R.id.left_light_btn);
        leftLineBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, LeftLineActivity.class);
                startActivity(intent);
            }
        });
        Button fontStyleBtn = (Button) findViewById(R.id.font_style_btn);
        fontStyleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, FontStyleActivity.class);
                startActivity(intent);
            }
        });
        Button familyBtn = (Button) findViewById(R.id.family_btn);
        familyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, FamilyActivity.class);
                startActivity(intent);
            }
        });
    }
}
