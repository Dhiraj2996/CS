package com.example.root.calculator2;

import android.content.Context;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.Normalizer;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public TextView textView;
    public TextView textView2;
    public FileWriter fileName;
    double calc;
    String multiplySign = Normalizer.normalize("\u00D7", Normalizer.Form.NFD);
    String divideSign = Normalizer.normalize("\u00F7", Normalizer.Form.NFD);
    String additionSign = Normalizer.normalize("\u002B", Normalizer.Form.NFD);
    String subtractionSign = Normalizer.normalize("\u2212", Normalizer.Form.NFD);
    String squareRootSign = Normalizer.normalize("\u221A", Normalizer.Form.NFD);
    public Button button4;
    FileOutputStream fileOutputStream;
    OutputStreamWriter writer;
    File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        calc=0;
//        try {
//            fileName = new FileWriter("history.txt");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        if (!file.exists()) {
//            try {
//                file.createNewFile();
//            } catch (IOException ioe) {
//                ioe.printStackTrace();
//            }
//        }
//        String path = Environment.getExternalStorageDirectory()
//                .getAbsolutePath() + "history.txt";
//        file = new File(path);
//        try {
//            fileOutputStream = new FileOutputStream(file,true);
//            writer = new OutputStreamWriter(fileOutputStream);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        File homeDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS),"Calculator2");
//        if (!homeDir.exists())
//            homeDir.mkdirs();
//        file = new File(homeDir, "text.txt");

        textView2=(TextView) findViewById(R.id.textView2);
        textView=(TextView) findViewById(R.id.textView);
        button4=(Button) findViewById(R.id.button4);
        button4.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                textView.setText("");
                textView2.setText("");
                str="";
                str1="";
                arrayList.clear();
                return true;
            }
        });
    }

    public void writetoFile(String result)
    {
        try
        {
            //File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
            String path = Environment.getExternalStorageDirectory().getAbsoluteFile()+"/"+getPackageName();
            File dir = new File(path);
            if (!dir.exists())
                dir.mkdirs();
            File myFile = new File(dir, "mytextfile.txt");
            FileOutputStream fOut = new FileOutputStream(myFile,true);
            OutputStreamWriter myOutWriter = new OutputStreamWriter(fOut);
            myOutWriter.append(result);
            myOutWriter.close();
            fOut.close();
//
//            OutputStreamWriter s=new OutputStreamWriter(context.openFileOutput(file.toString(), Context.MODE_PRIVATE));
//            s.write(result);
//            s.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }
    ArrayList<String> arrayList=new ArrayList<String>();
    String str="";
    String str1="";
    public void onClickClear(View v)
    {
        String num="";
        arrayList.clear();
        String str=textView.getText().toString();
        Log.d("Extracted string : ",""+str);
        if(str.length()!=0) {
            for (int i = 0; i < str.length() - 1; i++) {
                if (str.charAt(i) == multiplySign.charAt(0)) {
                    Log.d("Inside : ", "Multiplication");
                    arrayList.add(num);
                    arrayList.add(multiplySign);
                    num = "";
                }
                if (str.charAt(i) == divideSign.charAt(0)) {
                    arrayList.add(num);
                    arrayList.add(divideSign);
                    num = "";
                }
                if (str.charAt(i) == additionSign.charAt(0)) {
                    Log.d("CLEARRRRRRRRR ", "Inside add");
                    arrayList.add(num);
                    arrayList.add(additionSign);
                    num = "";
                }
                if (str.charAt(i) == subtractionSign.charAt(0)) {
                    arrayList.add(num);
                    arrayList.add(subtractionSign);
                    num = "";
                }
                if (str.charAt(i) == squareRootSign.charAt(0)) {
                    arrayList.add(num);
                    arrayList.add(squareRootSign);
                    num = "";
                }
                if (!(str.charAt(i) == multiplySign.charAt(0)) && !(str.charAt(i) == divideSign.charAt(0)) && !(str.charAt(i) == additionSign.charAt(0)) && !(str.charAt(i) == subtractionSign.charAt(0)) && !(str.charAt(i) == squareRootSign.charAt(0))) {
                    Log.d("CLEARRRRRRRRR ", "Inside NOT! " + num);
                    num = num + str.charAt(i);
                    if (i == str.length() - 2) {
                        arrayList.add(num);
                    }
                }
            }
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < arrayList.size(); i++) {
                builder.append(arrayList.get(i));
            }
            Log.d("arraylist", "" + builder.toString());
            //if(arrayList.size()>0)
            //arrayList.remove(arrayList.size()-1);
            Log.d("STR 1 : ", "" + str1);
            if (str1.length() != 0)
                str1 = str1.substring(0, str1.length() - 1);
//                //str.charAt(str.length()-1);
            textView.setText(str.substring(0, str.length() - 1));
        }

    }
    public void onClick1(View v)
    {
        //TextView textView2=(TextView) findViewById(R.id.textView);
        Button button= (Button)v;
        str = (String) button.getText().toString();
       // Log.d("SSSS",""+str);
        if(arrayList.size()==0)
        {
            if(str.equals(subtractionSign))
            {
                arrayList.add("0");
            }
        }
        if( !str.contains(additionSign) && !str.contains(subtractionSign) && !str.contains(multiplySign) && !str.contains(divideSign) && !str.contains(squareRootSign))
        {
            StringBuilder builder = new StringBuilder();
            for (int i=0;i<arrayList.size();i++) {
                builder.append(arrayList.get(i));
            }
            Log.d("arraylist" , ""+builder.toString()+" str "+str);
            String st="";
            str1= str1+str;
            if(arrayList.size()>0)
            {
                Log.d("SIZE : ", "Gela atta");
                st=arrayList.get(arrayList.size()-1);
            }

            if(!st.equals(additionSign) && !st.equals(subtractionSign) && !st.equals(multiplySign) && !st.equals(divideSign) && !st.equals(squareRootSign) && arrayList.size()>0)
            {
                Log.d("Test","Inside if : " + "\u00D7");
                arrayList.remove(arrayList.size()-1);
            }
            arrayList.add(str1);
            StringBuilder builder2 = new StringBuilder();
            for (int i=0;i<arrayList.size();i++) {
                builder.append(arrayList.get(i));
            }
            Log.d("arraylist2" , ""+builder2.toString());

        }
        else
        {
            //arrayList.add(str);

            arrayList.add(str);
//            Log.d("size",""+arrayList.size());
//            String n2 = Normalizer.normalize("\u00D7", Normalizer.Form.NFD);
//            if((arrayList.get((arrayList.size())-1).equals(n2)))
//                Log.d("X","Found x");
            str1="";
        }
        textView.setText(textView.getText().toString()+str);
    }
    public void onClickTrigno(View v)
    {
            if (arrayList.size() > 0 && !".".equals(arrayList.get(0).toString())) {
                double res = 0.0;
                //TextView textView2=(TextView) findViewById(R.id.textView);
                Button button = (Button) v;
                String st = arrayList.get(0);
                double val = Double.parseDouble(st);
                str = (String) button.getText().toString();
                if (str.equals("SIN")) {
                    textView.setText("sin(" + st + ")");
                    res = Math.sin(val);
                }
                if (str.equals("COS")) {
                    res = Math.cos(val);
                    textView.setText("cos(" + st + ")");
                }
                if (str.equals("TAN")) {
                    res = Math.tan(val);
                    textView.setText("tan(" + st + ")");
                }
                if (str.equals("SEC")) {
                    res = 1 / Math.cos(val);
                    textView.setText("sec(" + st + ")");
                }
                if (str.equals("COSEC")) {
                    res = 1 / Math.sin(val);
                    textView.setText("cosec(" + st + ")");
                }
                if (str.equals("COT")) {
                    res = 1 / Math.tan(val);
                    textView.setText("cot(" + st + ")");
                }
                textView2.setText("" + res);
            }
    }
//    public void onClickcheck(View v)
//    {
//        StringBuilder builder = new StringBuilder();
////        for (int i=0;i<arrayList.size();i++) {
//            builder.append(arrayList.get(2));
////        }
//        TextView textView3=(TextView) findViewById(R.id.textView3);
//        textView3.setText(builder.toString());
//    }
//    public void onClickcheck2(View v)
//    {
//        StringBuilder builder = new StringBuilder();
////        for (int i=0;i<arrayList.size();i++) {
//            builder.append(arrayList.get(1));
////        }
//        TextView textView4=(TextView) findViewById(R.id.textView4);
//        textView4.setText(builder.toString());
//    }
    public void onClick2(View v) throws IOException {
        if (arrayList.size() != 0) {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < arrayList.size(); i++) {
                builder.append(arrayList.get(i));
            }
            Log.d("ARRAY LIST IN EQUAL SIGN", "" + builder.toString());
            writetoFile(builder.toString());
            //writer.append(builder.toString());
            //writer.append('A');
            if (arrayList.contains(multiplySign) || arrayList.contains(divideSign) || arrayList.contains(additionSign) || arrayList.contains(subtractionSign) || arrayList.contains(squareRootSign)) {
                while (arrayList.contains(squareRootSign)) {
                    int index = arrayList.indexOf(squareRootSign);
                    Log.d("index : ", "" + index);
                    double res = Math.sqrt(Double.parseDouble(arrayList.get(index + 1)));
                    arrayList.set(index, Double.toString(res));
                    arrayList.remove(index + 1);
                    if (index - 1 >= 0) {
                        if (!arrayList.get(index - 1).equals(multiplySign) && !arrayList.get(index - 1).equals(divideSign) && !arrayList.get(index - 1).equals(additionSign) && !arrayList.get(index - 1).equals(subtractionSign) && !arrayList.get(index - 1).equals(squareRootSign) && arrayList.size() > 0) {
                            Log.d("Inside index-1", "dsgdsg");
                            if (index - 1 == 0) {
                                Log.d("Inside index-1==0", "sfd");
                                res = res * Double.parseDouble(arrayList.get(index - 1));
                                arrayList.set(index - 1, Double.toString(res));
                                arrayList.remove(index);
                            }
                            if (index - 2 >= 0) {
                                Log.d("Inside index-1>=0", "dgd");
                                if (!arrayList.get(index - 2).equals(squareRootSign)) {
                                    Log.d("INSIDE ", "dsgdg");
                                    res = res * Double.parseDouble(arrayList.get(index - 1));
                                    arrayList.set(index - 1, Double.toString(res));
                                    arrayList.remove(index);
                                }
                            }
                        }
                    }
                    if ((index + 1) <= arrayList.size() - 1) {
                        if (!arrayList.get(index + 1).equals(multiplySign) && !arrayList.get(index + 1).equals(divideSign) && !arrayList.get(index + 1).equals(additionSign) && !arrayList.get(index + 1).equals(subtractionSign) && !arrayList.get(index + 1).equals(squareRootSign) && arrayList.size() > 0) {
                            res = res * Double.parseDouble(arrayList.get(index + 1));
                            arrayList.set(index, Double.toString(res));
                            arrayList.remove(index + 1);
                        }
                    }
                }
                while (arrayList.contains(multiplySign) || arrayList.contains(divideSign)) {
                    if (arrayList.contains(multiplySign) && arrayList.contains(divideSign)) {
                        if (arrayList.indexOf(divideSign) < arrayList.indexOf(multiplySign)) {
                            int index = arrayList.indexOf(divideSign);
                            Log.d("Division numbers ", "" + arrayList.get(index - 1) + " " + arrayList.get(index + 1));
                            double res = Double.parseDouble(arrayList.get(index - 1)) / Double.parseDouble(arrayList.get(index + 1));
                            arrayList.set(index - 1, Double.toString(res));
                            arrayList.remove(index);
                            arrayList.remove(index);

                            Log.d("Division ", "" + res);
                        } else {
                            int index = arrayList.indexOf(multiplySign);
                            Log.d("Multiplication numbers ", "" + arrayList.get(index - 1) + " " + arrayList.get(index + 1));
                            double res = Double.parseDouble(arrayList.get(index - 1)) * Double.parseDouble(arrayList.get(index + 1));
                            arrayList.set(index - 1, Double.toString(res));
                            arrayList.remove(index);
                            arrayList.remove(index);
                            Log.d("Multiplication ", "" + res);
                        }
                    } else {
                        //if(arrayList.indexOf(divideSign) < arrayList.indexOf(multiplySign) && arrayList.indexOf(divideSign)!=-1) {
                        if (arrayList.contains(divideSign)) {
                            int index = arrayList.indexOf(divideSign);
                            Log.d("Division numbers ", "" + arrayList.get(index - 1) + " " + arrayList.get(index + 1));
                            double res = Double.parseDouble(arrayList.get(index - 1)) / Double.parseDouble(arrayList.get(index + 1));
                            arrayList.set(index - 1, Double.toString(res));
                            arrayList.remove(index);
                            arrayList.remove(index);

                            Log.d("Division ", "" + res);
                        }
                        //}
                        //if(arrayList.indexOf(divideSign) > arrayList.indexOf(multiplySign) && arrayList.indexOf(multiplySign)!=-1) {
                        if (arrayList.contains(multiplySign)) {
                            int index = arrayList.indexOf(multiplySign);
                            Log.d("Multiplication numbers ", "" + arrayList.get(index - 1) + " " + arrayList.get(index + 1));
                            double res = Double.parseDouble(arrayList.get(index - 1)) * Double.parseDouble(arrayList.get(index + 1));
                            arrayList.set(index - 1, Double.toString(res));
                            arrayList.remove(index);
                            arrayList.remove(index);
                            Log.d("Multiplication ", "" + res);
//                for(int i=(arrayList.indexOf("\u00D7"))+2;i<arrayList.size();i++)
//                {
//
//                }
                        }
                    }
                    //}
                }
                while (arrayList.contains(additionSign) || arrayList.contains(subtractionSign)) {
                    Log.d("Inside while of plus-minus ", "");
                    if (arrayList.contains(additionSign) && arrayList.contains(subtractionSign)) {
                        if (arrayList.indexOf(additionSign) < arrayList.indexOf(subtractionSign)) {
                            int index = arrayList.indexOf(additionSign);
                            Log.d("Addition numbers ", "" + arrayList.get(index - 1) + " " + arrayList.get(index + 1));
                            double res = Double.parseDouble(arrayList.get(index - 1)) + Double.parseDouble(arrayList.get(index + 1));
                            arrayList.set(index - 1, Double.toString(res));
                            arrayList.remove(index);
                            arrayList.remove(index);
                            Log.d("Addition ", "" + res);
                        } else {
                            int index = arrayList.indexOf(subtractionSign);
                            Log.d("Subtraction numbers ", "" + arrayList.get(index - 1) + " " + arrayList.get(index + 1));
                            double res = Double.parseDouble(arrayList.get(index - 1)) - Double.parseDouble(arrayList.get(index + 1));
                            arrayList.set(index - 1, Double.toString(res));
                            arrayList.remove(index);
                            arrayList.remove(index);
                            Log.d("Subtraction ", "" + res);
                        }
                    } else {
                        //if(arrayList.indexOf(additionSign) < arrayList.indexOf(subtractionSign) && arrayList.indexOf(additionSign)!=-1) {
                        if (arrayList.contains(additionSign)) {
                            int index = arrayList.indexOf(additionSign);
                            Log.d("Addition numbers ", "" + arrayList.get(index - 1) + " " + arrayList.get(index + 1));
                            double res = Double.parseDouble(arrayList.get(index - 1)) + Double.parseDouble(arrayList.get(index + 1));
                            arrayList.set(index - 1, Double.toString(res));
                            arrayList.remove(index);
                            arrayList.remove(index);
                            Log.d("Addition ", "" + res);
                        }
                        //}
                        //if(arrayList.indexOf(subtractionSign) < arrayList.indexOf(additionSign) && arrayList.indexOf(subtractionSign)!=-1) {
                        if (arrayList.contains(subtractionSign)) {
                            int index = arrayList.indexOf(subtractionSign);
                            Log.d("Subtraction numbers ", "" + arrayList.get(index - 1) + " " + arrayList.get(index + 1));
                            double res = Double.parseDouble(arrayList.get(index - 1)) - Double.parseDouble(arrayList.get(index + 1));
                            arrayList.set(index - 1, Double.toString(res));
                            arrayList.remove(index);
                            arrayList.remove(index);
                            Log.d("Subtraction ", "" + res);
                        }
                        //}
                    }
                }
                StringBuilder builder2 = new StringBuilder();
                builder2.append(arrayList.get(0));
                //writer.append(" = "+builder2.toString());
                textView2.setText("" + arrayList.get(0));
                writetoFile(" = " + builder2.toString() + "\n");
                //writer.close();
                //fileOutputStream.close();
            } else {
                textView2.setText(arrayList.get(0));
            }

        }
    }
}

