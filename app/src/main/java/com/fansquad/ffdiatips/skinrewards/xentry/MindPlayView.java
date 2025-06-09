package com.fansquad.ffdiatips.skinrewards.xentry;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.fansquad.ffdiatips.skinrewards.R;
import com.fansquad.ffdiatips.skinrewards.databinding.ScreenBraingridMainBinding;
import com.fansquad.ffdiatips.skinrewards.datazone.QMatrixCore;
import com.fansquad.ffdiatips.skinrewards.datazone.QUnitNode;
import com.fansquad.ffdiatips.skinrewards.zstream.NetRouteHelper;

import java.util.List;
import java.util.Locale;

public class MindPlayView extends CoreHostFx {

    private ScreenBraingridMainBinding screenBraingridMainBinding;
    private int questionIndex = 0;
    private int correctAnswers = 0;
    private List<QUnitNode> qUnitNodes;
    private Button[] buttons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        screenBraingridMainBinding = ScreenBraingridMainBinding.inflate(getLayoutInflater());
        setContentView(screenBraingridMainBinding.getRoot());

        String category = getIntent().getStringExtra("SectionType");
        screenBraingridMainBinding.toolbarLayout.headerTitle.setText(category);
        screenBraingridMainBinding.toolbarLayout.btnBack.setOnClickListener(v -> exitWithBridgeAd());

        qUnitNodes = QMatrixCore.getQuestions(category);
        buttons = new Button[]{
                screenBraingridMainBinding.btnOption1,
                screenBraingridMainBinding.btnOption2,
                screenBraingridMainBinding.btnOption3,
                screenBraingridMainBinding.btnOption4
        };

        loadQuestion();

        for (int i = 0; i < buttons.length; i++) {
            final int index = i;
            buttons[i].setOnClickListener(view -> handleOptionClick(index));
        }
    }

    private void loadQuestion() {
        if (questionIndex >= qUnitNodes.size()) {
            youWinnner();

            return;
        }

        QUnitNode q = qUnitNodes.get(questionIndex);

        screenBraingridMainBinding.txtCircleNumber.setText(String.format(Locale.getDefault(), "%02d", questionIndex + 1));
        screenBraingridMainBinding.txtQuestionCounter.setText("Question " + (questionIndex + 1) + "/" + qUnitNodes.size());
        screenBraingridMainBinding.txtCorrectCounter.setText("Correct " + correctAnswers + "/" + qUnitNodes.size());

        if (getIntent().getStringExtra("SectionType").equals("Color Puzzle")) {
            screenBraingridMainBinding.viewColorCircle.setVisibility(View.VISIBLE);
            screenBraingridMainBinding.txtCircleNumber.setVisibility(View.VISIBLE);

            switch (q.getOptions()[q.getAnswerIndex()].toLowerCase()) {
                case "red":
                    screenBraingridMainBinding.viewColorCircle.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FF0C0C")));
                    break;
                case "yellow":
                    screenBraingridMainBinding.viewColorCircle.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FFBE0C")));
                    break;
                case "green":
                    screenBraingridMainBinding.viewColorCircle.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#00982E")));
                    break;
                case "white":
                case "pink":
                    screenBraingridMainBinding.viewColorCircle.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FF1D7F")));
                    break;
                default:
                    screenBraingridMainBinding.viewColorCircle.setBackgroundColor(Color.GRAY);
            }

            screenBraingridMainBinding.txtQuestion.setText("Select Right Answer");
        } else {
            screenBraingridMainBinding.viewColorCircle.setVisibility(View.GONE);
            screenBraingridMainBinding.txtCircleNumber.setVisibility(View.VISIBLE);
            screenBraingridMainBinding.txtQuestion.setText(q.getQuestion());
        }

        for (int i = 0; i < buttons.length; i++) {
            buttons[i].setText(q.getOptions()[i]);
            buttons[i].setBackgroundResource(R.drawable.drx_moziqon1);
            buttons[i].setEnabled(true);
        }
    }

    private void handleOptionClick(int selectedIndex) {
        QUnitNode current = qUnitNodes.get(questionIndex);
        int correctIndex = current.getAnswerIndex();

        for (int i = 0; i < buttons.length; i++) {
            buttons[i].setEnabled(false);
            buttons[i].setBackgroundResource(i == selectedIndex ? R.drawable.drx_moziqon2 : R.drawable.drx_moziqon1);
            buttons[i].setTextColor(i == selectedIndex
                    ? getResources().getColor(R.color.drx_moziqon)
                    : getResources().getColor(R.color.drx_moziqonwhite));
        }

        if (selectedIndex == correctIndex) {
            correctAnswers++;

            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
            screenBraingridMainBinding.btnOption1.setTextColor(getResources().getColor(R.color.drx_moziqonwhite));
            screenBraingridMainBinding.btnOption2.setTextColor(getResources().getColor(R.color.drx_moziqonwhite));
            screenBraingridMainBinding.btnOption3.setTextColor(getResources().getColor(R.color.drx_moziqonwhite));
            screenBraingridMainBinding.btnOption4.setTextColor(getResources().getColor(R.color.drx_moziqonwhite));

        } else {
            Toast.makeText(this, "Wrong!", Toast.LENGTH_SHORT).show();
            screenBraingridMainBinding.btnOption1.setTextColor(getResources().getColor(R.color.drx_moziqonwhite));
            screenBraingridMainBinding.btnOption2.setTextColor(getResources().getColor(R.color.drx_moziqonwhite));
            screenBraingridMainBinding.btnOption3.setTextColor(getResources().getColor(R.color.drx_moziqonwhite));
            screenBraingridMainBinding.btnOption4.setTextColor(getResources().getColor(R.color.drx_moziqonwhite));
        }

        questionIndex++;

        new Handler().postDelayed(this::loadQuestion, 1000);
    }

    @Override
    public void onBackPressed() {
        exitWithBridgeAd();
    }

    private void youWinnner() {


        View dialogView = LayoutInflater.from(MindPlayView.this).inflate(R.layout.modal_spinfetch, null);
        AlertDialog dialog = new AlertDialog.Builder(MindPlayView.this)
                .setView(dialogView)
                .create();

        if (dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }


        dialogView.findViewById(R.id.btnCollect).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("MyAppPrefs", MODE_PRIVATE);
                int currentTotal = sharedPreferences.getInt("reward_value", 0); // default is 0

                int my_new = Integer.parseInt("50");
                int updatedTotal = currentTotal + my_new;

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("reward_value", updatedTotal);
                editor.apply();


                NetRouteHelper.triggerSparkFlow(MindPlayView.this);
                dialog.dismiss();
                finish();
            }
        });


        dialogView.findViewById(R.id.txtHome).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
                Intent intent = new Intent(MindPlayView.this, MainCoreNav.class);
                startActivity(intent);
                finish();
            }
        });
        dialogView.findViewById(R.id.txAD2X).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("MyAppPrefs", MODE_PRIVATE);
                int currentTotal = sharedPreferences.getInt("reward_value", 0);

                int my_new = Integer.parseInt("100");
                int updatedTotal = currentTotal + my_new;

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("reward_value", updatedTotal);
                editor.apply();


                NetRouteHelper.triggerSparkFlow(MindPlayView.this);
                dialog.dismiss();
                finish();
            }
        });

        dialog.show();
    }

}