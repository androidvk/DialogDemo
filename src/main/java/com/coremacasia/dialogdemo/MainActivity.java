package com.coremacasia.dialogdemo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class MainActivity extends AppCompatActivity {
    private AlertDialog.Builder alertDialog;
    private AlertDialog customDialog;
    private View dialogView;

    private ProgressDialog progressDialog;

    private Button b1, b2, b3, b4, b5, b6, b7, b8, b9, b0,
            bCE, bDot, bEquals, bMultiply, bMinus,
            bPlus;
    private TextView textView;
    private String operator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeAlertDialog();
        initializeCustomDialog();
        initializeProgressDialog();

    }

    private void initializeProgressDialog() {
        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("Please Wait!!");
    }

    private void initializeCustomDialog() {
        dialogView = LayoutInflater.from(MainActivity.this).inflate(R.layout.dialog_calculator, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setView(dialogView);
        customDialog = builder.create();

        textView = dialogView.findViewById(R.id.textView);
        b1 = dialogView.findViewById(R.id.b1);
        b2 = dialogView.findViewById(R.id.b2);
        b3 = dialogView.findViewById(R.id.b3);
        b4 = dialogView.findViewById(R.id.b4);
        b5 = dialogView.findViewById(R.id.b5);
        b6 = dialogView.findViewById(R.id.b6);
        b7 = dialogView.findViewById(R.id.b7);
        b8 = dialogView.findViewById(R.id.b8);
        b9 = dialogView.findViewById(R.id.b9);
        b0 = dialogView.findViewById(R.id.b0);
        bCE = dialogView.findViewById(R.id.b00);
        bDot = dialogView.findViewById(R.id.bDot);
        bPlus = dialogView.findViewById(R.id.bPlus);
        bMinus = dialogView.findViewById(R.id.bMinus);
        bMultiply = dialogView.findViewById(R.id.bMultiply);
        bEquals = dialogView.findViewById(R.id.bEquals);
        userClick();

    }

    private void initializeAlertDialog() {
        alertDialog = new AlertDialog.Builder(MainActivity.this);
        alertDialog.setTitle("Delete!!");
        alertDialog.setMessage("Do you want to delete this message??");
        alertDialog.setIcon(getResources().getDrawable(android.R.drawable.ic_delete));
        alertDialog.setCancelable(false);
        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this, "You have deleted this message! ", Toast.LENGTH_SHORT).show();
            }
        });
        alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
    }

    public void alertButton(View v) {
        alertDialog.show();
    }

    public void customDialogButton(View view) {
        customDialog.show();
    }

    public void progressDialogButton(View view) {
        progressDialog.show();
    }


    private void userClick() {

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getTextFromUser(1);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getTextFromUser(2);
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getTextFromUser(3);
            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getTextFromUser(4);
            }
        });

        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getTextFromUser(5);
            }
        });

        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getTextFromUser(6);
            }
        });

        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getTextFromUser(7);
            }
        });

        b8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getTextFromUser(8);
            }
        });

        b9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getTextFromUser(9);
            }
        });

        b0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getTextFromUser(0);
            }
        });

        bCE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView.setText("0");
            }
        });

        bEquals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                operator = "=";
                calculate();
            }
        });

        bPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                operator = "+";
                operate();
            }
        });

        bMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                operator = "*";
                operate();
            }
        });
        bMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                operator = "-";
                operate();
            }
        });

        bDot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                operator = ".";
                operate();
            }
        });

    }

    private void calculate() {
        String currentText = textView.getText().toString().trim();
        double result = 0;
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("rhino");
        try {
            result = (double) engine.eval(currentText);
        } catch (Exception e) {
            Toast.makeText(this, "Exception Raised", Toast.LENGTH_SHORT).show();
        }
        // currentText = currentText +"\n"+ "="+(result);
        textView.setText(String.valueOf(result));
    }

    private void operate() {
        String x = textView.getText().toString().trim();
        textView.setText(x + operator);
    }

    private void getTextFromUser(int userInput) {
        String x = textView.getText().toString().trim();
        textView.setText(x + String.valueOf(userInput));

    }

}
