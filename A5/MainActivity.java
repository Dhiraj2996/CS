package com.example.ankur.calculator.feature;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.transform.Result;

public class MainActivity extends AppCompatActivity
{
    double answer=0;
    double history = 0;
    TextView result;
    TextView info;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        result = (TextView)findViewById(R.id.display);
        info   = (TextView)findViewById(R.id.result);
        answer = 0;
    }

    String[] arr = {"/","*","-","+"};
    ArrayList<String> op = new ArrayList<>(Arrays.asList(arr));
    ArrayList<String> A = new ArrayList<>();
    ArrayList<String> B = new ArrayList<>();
    ArrayList<String> hist = new ArrayList<>();
    String str = "";
    String str1 = "";


    public ArrayList<String> cloneList(ArrayList<String> list)
    {
        ArrayList<String> clone = new ArrayList<String>(list.size());
        for (String item : list)
            clone.add(item);
        return clone;
    }

    public void click1(View v)
    {
        Button btn = (Button)v;
        str = btn.getText().toString();

        if(op.contains(str))
        {
            if(A.size()==0)

                str1 = str;
            else
            {
                A.add(str);
                str1 = "";
            }

        }
        else
        {
            int len = A.size();
            if(len>2)
            {
                if(op.contains(A.get(len-1)) && op.contains(A.get(len-2)))
                {
                        str1 = A.get(len- 1);
                        A.remove(len- 1);
                        str1 = str1+ str;
                        A.add(str1);
                }
                else
                {
                    str1 = str1 + str;
                    if(len>0 && !op.contains(A.get(len-1)))
                        A.remove(len-1);
                    A.add(str1);
                }
            }
            else
            {
                str1 = str1 + str;
                if(len>0 && !op.contains(A.get(len-1)))
                    A.remove(len-1);
                A.add(str1);
            }

        }

        if(A.size()==1)
            answer = Double.parseDouble(A.get(0));

        info.setText(info.getText().toString() + str);
//        result.setText(A.toString());
        B.clear();
        B = cloneList(A);
        if(B.size()>2 && B.size()%2 != 0 && !op.contains(B.get(B.size()-1)))
        {
            click(v);
        }

    }

    public void click(View v)
    {
        int len = B.size();

        while(len>1)
        {
            if(len>3)
            {
                if(B.get(3).equals("*") || B.get(3).equals("/"))
                {
                    if(B.get(3).equals("*"))
                        answer = Double.parseDouble(B.get(2)) * Double.parseDouble(B.get(4));
                    else
                        answer = Double.parseDouble(B.get(2)) / Double.parseDouble(B.get(4));

                    B.remove(2);
                    B.remove(2);
                    B.remove(2);
                    B.add(2, Double.toString(answer));
                    len = B.size();
                }

                else
                {
                    if(B.get(1).equals("+"))
                        answer = Double.parseDouble(B.get(0)) + Double.parseDouble(B.get(2));
                    else if(B.get(1).equals("-"))
                        answer = Double.parseDouble(B.get(0)) - Double.parseDouble(B.get(2));
                    else if(B.get(1).equals("*"))
                        answer = Double.parseDouble(B.get(0)) * Double.parseDouble(B.get(2));
                    else
                        answer = Double.parseDouble(A.get(0)) / Double.parseDouble(A.get(2));

                    B.remove(0);
                    B.remove(0);
                    B.remove(0);
                    B.add(0,Double.toString(answer));
                    len = B.size();
                }
            }

            else
            {
                if(B.get(1).equals("+"))
                    answer = Double.parseDouble(B.get(0)) + Double.parseDouble(B.get(2));
                else if(B.get(1).equals("-"))
                    answer = Double.parseDouble(B.get(0)) - Double.parseDouble(B.get(2));
                else if(B.get(1).equals("*"))
                    answer = Double.parseDouble(B.get(0)) * Double.parseDouble(B.get(2));
                else
                    answer = Double.parseDouble(B.get(0)) / Double.parseDouble(B.get(2));

                B.remove(0);
                B.remove(0);
                B.remove(0);
                B.add(0,Double.toString(answer));
                len = B.size();
            }

//            if(B.size()==1)
//            {
//                answer = Double.parseDouble(B.get(0));
//            }
//            hist.add(String.valueOf(answer));
            result.setText(String.valueOf(answer));
        }


    }

    public void sin(View v)
    {
        result.setText(String.valueOf(Math.sin(answer*Math.PI/180)));
    }
    public void cos(View v)
    {
        result.setText(String.valueOf(Math.cos(answer*Math.PI/180)));
    }
    public void tan(View v)
    {
        result.setText(String.valueOf(Math.tan(answer*Math.PI/180)));
    }
    public void cosec(View v)
    {
        result.setText(String.valueOf(1/Math.sin(answer*Math.PI/180)));
    }
    public void sec(View v)
    {
        result.setText(String.valueOf(1/Math.cos(answer*Math.PI/180)));
    }
    public void cot(View v)
    {
        result.setText(String.valueOf(1/Math.tan(answer*Math.PI/180)));
    }


    public void store(View v)
    {
        try
        {
//            FileWriter f = new FileWriter("/home/ankur/result.txt");
//            f.write(String.valueOf(answer));
//            result.setText(null);

//            hist.add(String.valueOf(answer));
            history = answer;
            result.setText(null);
            info.setText(null);

        }
        catch (Exception e) {}

    }

    public void recall(View v)
    {
//        if(hist.size()>=0)
        {
            result.setText(null);
            info.setText(null);
            info.setText(String.valueOf(history));
            A.clear();
            A.add(String.valueOf(history));
            answer = 0;
//            hist.remove(hist.size()-1);
        }
//        else
//            result.setText("No History");
    }
    public void clear(View v)
    {

        {
            str1 = "";
            str = "";
            A.clear();
            answer = 0;
            result.setText(null);
            info.setText(null);
        }
//        click1(v);
    }
}
