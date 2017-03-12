package com.example.kzy.assignment2;

import android.app.Activity;
import android.content.Context;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by kzy on 2017/3/12.
 */

public class DealTextFile extends Activity{

    public void deleteOriginalFile(){
        File surveyFile = new File("sdcard/" + "abc");
        if(surveyFile.exists()){
            surveyFile.delete();
        }
    }

    public void writeFile(String data) {
        File surveyFile = new File("sdcard/" + "abc");
        if(!surveyFile.exists()){
            try{
                surveyFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try{
            BufferedWriter buf = new BufferedWriter(new FileWriter(surveyFile,true));
            buf.write(data+"\n");
            buf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String readFile(){
        String aDataRow="";
        String aBuffer="";
        try{
            File myFile=new File("sdcard/" + "abc");
            FileInputStream fln=new FileInputStream(myFile);
            BufferedReader myReader=new BufferedReader(new InputStreamReader(fln));

            while((aDataRow=myReader.readLine())!=null){
                aBuffer+=aDataRow+"\n";
            }
            myReader.close();
        }catch (IOException e){
            e.printStackTrace();
        }
        return aBuffer;
    }
}
