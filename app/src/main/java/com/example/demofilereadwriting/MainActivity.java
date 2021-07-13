package com.example.demofilereadwriting;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class MainActivity extends AppCompatActivity {

    String folderLocation;
    Button btnWrite, btnRead;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //UI handlers to be defined
        btnRead = findViewById(R.id.btnRead);
        btnWrite = findViewById(R.id.btnWrite);

        //===================FolderCreation=================
        //=============--------InternalFolderCreation-------===================
        //folderLocation = getFilesDir().getAbsolutePath() + "/MyFolder";
        //=============--------InternalFolderCreation-------===================

        //=============--------ExternalFolderCreation-------===================
        folderLocation = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Folder";
        //=============--------ExternalFolderCreation-------===================

        File folder = new File(folderLocation);
        if (folder.exists() == false){
            boolean result = folder.mkdir();
            if (result == true){
                Log.d("File Read/Write", "Folder created");
            }
        }
        //===================FolderCreation=================

        btnWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    //=============--------InternalFolderCreation-------===================
                    //String folderLocation= getFilesDir().getAbsolutePath() + "/Folder";
                    //=============--------InternalFolderCreation-------===================

                    //=============--------ExternalFolderCreation-------===================
                    String folderLocation= Environment.getExternalStorageDirectory().getAbsolutePath() + "/Folder";
                    //=============--------ExternalFolderCreation-------===================

                    File targetFile_I= new File(folderLocation, "data.txt");
                    FileWriter writer_I= new FileWriter(targetFile_I, true); //-> True(Append existing date) False(Overwriting existing data)
                    writer_I.write("test data" + "\n");
                    writer_I.flush();
                    writer_I.close();
                }
                catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Failed to write!", Toast.LENGTH_LONG).show();
                e.printStackTrace();
                }
            }
        });



        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //=============--------InternalFolderCreation-------===================
                //String folderLocation = getFilesDir().getAbsolutePath() + "/Folder";
                //=============--------InternalFolderCreation-------===================

                //=============--------ExternalFolderCreation-------===================
                String folderLocation = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Folder";
                //=============--------ExternalFolderCreation-------===================

                File targetFile= new File(folderLocation, "data.txt");

                if (targetFile.exists() == true){
                    String data ="";
                    try {
                    FileReader reader = new FileReader(targetFile);
                    BufferedReader br= new BufferedReader(reader);

                    String line = br.readLine();
                    while (line != null){
                        data += line + "\n";
                        line = br.readLine();
                    }

                    br.close();
                    reader.close();
                    } catch (Exception e) {
                        Toast.makeText(MainActivity.this, "Failed to read!", Toast.LENGTH_LONG).show();
                        e.printStackTrace();
                    }
                    Log.d("Content", data);
                }
            }
        });
    }
}
