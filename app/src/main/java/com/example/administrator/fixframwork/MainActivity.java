package com.example.administrator.fixframwork;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    TextView result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        result= (TextView) findViewById(R.id.result);
        findViewById(R.id.caculator).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Caclutor calutor=new   Caclutor();
                result.setText(""+calutor.caculator());
            }
        });
        findViewById(R.id.fix).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fix();
            }
        });
    }

    private void fix()
    {
        File file=new File(Environment.getExternalStorageDirectory(),"out.dex");
        DexManager dexManager=new DexManager(this);
        dexManager.loadDex(file);
    }
}
