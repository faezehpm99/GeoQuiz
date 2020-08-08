package com.example.geoquiz.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.geoquiz.R;
import com.example.geoquiz.model.Question;

public class QuizActivity extends AppCompatActivity {
    private static final String BUNDLE_KEY_CURRENT_INDEX = "currentIndex";
    public static final String EXTRA_QUESTION_ANSWER = "com.example.geoquiz.questionAnswer";
    private static final String TAG = "QuizActivity";
    private final String BUNDLE_KEY_CURRENT="currentIndex";
    private final String BUNDLE_KEY_SCORE="score";
    private final String BUNDLE_KEY_SELECTED="selected";
    private final String BUNDLE_REQUEST_CODE_CHEAT="";
    private final String BUNDLE_KEY_ARR="arr";
    private final String BUNDLE_KEY_ARR_CHEAT="arr";
    public static final int REQUEST_CODE_CHEAT = 0;
   /* private boolean mIsCheater = false;*/
    private TextView mTextViewQuestion;
    private Button mButtonTrue;
    private Button mButtonFalse;
    private Button mButtonNext;
    private Button mButtonPrev;
    private Button mButtonReset;
    private TextView result;
    private LinearLayout text_end;
    private LinearLayout true_false_buttom;
    private LinearLayout prev_next_buttom;
    private LinearLayout reset_layout;
    private Button mButtonCheat;
    private boolean[]cheatList={false,false,false,false,false,false};
    private int score = 0;
    private int selected = 0;
    protected int mCurrentIndex = 0;
    private int[] arr = {0, 0, 0, 0, 0, 0};
    private Question[] mQuestionBank = {
            new Question(R.string.question_australia, false, false),
            new Question(R.string.question_oceans, true, false),
            new Question(R.string.question_mideast, false, false),
            new Question(R.string.question_africa, true, false),
            new Question(R.string.question_americas, false, false),
            new Question(R.string.question_asia, false, false)
    };

    /**
     * This method is used to crete ui for activity.
     *
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: " + mCurrentIndex);

        //this method will create the layout
        //inflate: creating object of xml layout
        setContentView(R.layout.activity_quiz);

        if (savedInstanceState != null) {
            Log.d(TAG, "savedInstanceState: " + savedInstanceState);

            mCurrentIndex = savedInstanceState.getInt(BUNDLE_KEY_CURRENT_INDEX, 0);
            score=savedInstanceState.getInt(BUNDLE_KEY_SCORE,0);
            selected=savedInstanceState.getInt(BUNDLE_KEY_SELECTED,0);
            arr=savedInstanceState.getIntArray(BUNDLE_KEY_ARR);
            cheatList=savedInstanceState.getBooleanArray(BUNDLE_REQUEST_CODE_CHEAT);
        } else
            Log.d(TAG, "savedInstanceState is NULL!!");
        findViews();

        setListeners();


        updateQuestion();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(BUNDLE_KEY_CURRENT,mCurrentIndex);
        outState.putInt(BUNDLE_KEY_SCORE,score);
        outState.putInt(BUNDLE_KEY_SELECTED,selected);
        outState.putIntArray(BUNDLE_KEY_ARR,arr);
        outState.putBooleanArray(BUNDLE_REQUEST_CODE_CHEAT,cheatList);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode != Activity.RESULT_OK || data == null)
            return;

        //this means if the result if backed from CheatActivity
        if (requestCode == REQUEST_CODE_CHEAT) {
           /* mIsCheater = data.getBooleanExtra(CheatActivity.EXTRA_IS_CHEAT, false);*/
            boolean isCheat = data.getBooleanExtra(CheatActivity.EXTRA_IS_CHEAT, false);
            cheatList[mCurrentIndex]=true;
        }
    }

    private void findViews() {
        mTextViewQuestion = findViewById(R.id.txtview_question_text);
        mButtonTrue = findViewById(R.id.btn_true);
        mButtonFalse = findViewById(R.id.btn_false);
        mButtonNext = findViewById(R.id.btn_next);
        mButtonPrev = findViewById(R.id.btn_prev);
        mButtonReset=findViewById(R.id.button2);
        reset_layout=findViewById(R.id.reset_layout);
        mButtonCheat=findViewById(R.id.cheat_button);

        text_end = findViewById(R.id.text_end);
        true_false_buttom = findViewById(R.id.true_false_button);
        prev_next_buttom = findViewById(R.id.pre_next_buttom);
        result = findViewById(R.id.resault);

    }

    private void setListeners() {
       /* if (arr[mCurrentIndex]==1){
            mButtonTrue.setEnabled(false);
            mButtonFalse.setEnabled(false);
        }
*/

        mButtonTrue.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                arr[mCurrentIndex] = 1;
                checkAnswer(true);
                selected++;


                if (arr[mCurrentIndex] == 1) {
                    mButtonTrue.setEnabled(false);
                    mButtonFalse.setEnabled(false);
                } else {
                    mButtonTrue.setEnabled(true);
                    mButtonFalse.setEnabled(true);
                }



            }
        });


        mButtonFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arr[mCurrentIndex] = 1;
                checkAnswer(true);
                selected++;


                if (arr[mCurrentIndex] == 1) {
                    mButtonTrue.setEnabled(false);
                    mButtonFalse.setEnabled(false);
                } else {
                    mButtonTrue.setEnabled(true);
                    mButtonFalse.setEnabled(true);
                }

            }
        });

        mButtonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;

                updateQuestion();
               /* if (arr[mCurrentIndex] == 0) {
                    mButtonTrue.setEnabled(true);
                    mButtonFalse.setEnabled(true);
                } else {
                    mButtonTrue.setEnabled(false);
                    mButtonFalse.setEnabled(false);
                }*/

            }
        });


        mButtonPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrentIndex = (mCurrentIndex - 1 + mQuestionBank.length) % mQuestionBank.length;


                updateQuestion();
                if (arr[mCurrentIndex] == 1) {
                    mButtonTrue.setEnabled(false);
                    mButtonFalse.setEnabled(false);

                }


            }
        });

        mButtonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrentIndex = 0;
                score = 0;
                selected = 0;
                for (int i=0;i<6;i++){
                    arr[i]=0;
                }
                mButtonTrue.setEnabled(true);
                mButtonFalse.setEnabled(true);
                mTextViewQuestion.setVisibility(View.VISIBLE);
                true_false_buttom.setVisibility(View.VISIBLE);
                prev_next_buttom.setVisibility(View.VISIBLE);
                reset_layout.setVisibility(View.GONE
                );
                text_end.setVisibility(View.GONE);
                findViews();
                setListeners();
                updateQuestion();
            }
        });
        mButtonCheat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuizActivity.this, CheatActivity.class);
                intent.putExtra(EXTRA_QUESTION_ANSWER, mQuestionBank[mCurrentIndex].isAnswerTrue());

                startActivityForResult(intent, REQUEST_CODE_CHEAT);
            }
        });

    }

    private void updateQuestion() {
        if (selected == 6) {
            true_false_buttom.setVisibility(View.GONE);
            prev_next_buttom.setVisibility(View.GONE);
            text_end.setVisibility(View.VISIBLE);
            result.setText("GameOver" + "Your Score is" + score);
            mTextViewQuestion.setVisibility(View.GONE);
            reset_layout.setVisibility(View.VISIBLE);



        } else {
            mButtonTrue.setEnabled(true);
            mButtonFalse.setEnabled(true);
            int questionTextResId = mQuestionBank[mCurrentIndex].getQuestionTextResId();
            mTextViewQuestion.setText(questionTextResId);

        }
    }

    private void checkAnswer(boolean userPressed) {
        if (cheatList[mCurrentIndex]==true) {
            Toast.makeText(this, "تقلب کار خوبی نیست", Toast.LENGTH_LONG).show();
        } else {
            if (mQuestionBank[mCurrentIndex].isAnswerTrue() == userPressed)
                Toast.makeText(this, R.string.toast_correct, Toast.LENGTH_LONG).show();
            else
                Toast.makeText(this, R.string.toast_incorrect, Toast.LENGTH_SHORT).show();
        }
    }

}