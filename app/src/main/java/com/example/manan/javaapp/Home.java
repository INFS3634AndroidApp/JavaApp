package com.example.manan.javaapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class Home extends AppCompatActivity {
    private static final FirebaseAuth mAuth = FirebaseAuth.getInstance();
    ;

    private static final String KEY = mAuth.getCurrentUser().getUid();
    private static final DatabaseReference mRef = FirebaseDatabase.getInstance().getReference("user");

    private static int week;
    private static ArrayList<CheckBox> checkBoxes = new ArrayList<>();

    private Button lecture1contentBtn;
    private Button lecture2contentBtn;
    private Button lecture3contentBtn;
    private Button lecture4contentBtn;
    private Button lecture5contentBtn;
    private Button lecture6contentBtn;
    private Button lecture7contentBtn;
    private Button lecture8contentBtn;
    private Button lecture9contentBtn;

    private Button lecture1QuizBtn;
    private Button lecture2QuizBtn;
    private Button lecture3QuizBtn;
    private Button lecture4QuizBtn;
    private Button lecture5QuizBtn;
    private Button lecture6QuizBtn;
    private Button lecture7QuizBtn;
    private Button lecture8QuizBtn;
    private Button lecture9QuizBtn;

    private TextView L1TV;
    private TextView L2TV;
    private TextView L3TV;
    private TextView L4TV;
    private TextView L5TV;
    private TextView L6TV;
    private TextView L7TV;
    private TextView L8TV;
    private TextView L9TV;

    private CheckBox L1CB;
    private CheckBox L2CB;
    private CheckBox L3CB;
    private CheckBox L4CB;
    private CheckBox L5CB;
    private CheckBox L6CB;
    private CheckBox L7CB;
    private CheckBox L8CB;
    private CheckBox L9CB;

    private LinearLayout L1LL;
    private LinearLayout L2LL;
    private LinearLayout L3LL;
    private LinearLayout L4LL;
    private LinearLayout L5LL;
    private LinearLayout L6LL;
    private LinearLayout L7LL;
    private LinearLayout L8LL;
    private LinearLayout L9LL;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        getCurrentUserWeek();

        lecture1contentBtn = findViewById(R.id.L1CBtn);
        lecture2contentBtn = findViewById(R.id.L2CBtn);
        lecture3contentBtn = findViewById(R.id.L3CBtn);
        lecture4contentBtn = findViewById(R.id.L4CBtn);
        lecture5contentBtn = findViewById(R.id.L5CBtn);
        lecture6contentBtn = findViewById(R.id.L6CBtn);
        lecture7contentBtn = findViewById(R.id.L7CBtn);
        lecture8contentBtn = findViewById(R.id.L8CBtn);
        lecture9contentBtn = findViewById(R.id.L9CBtn);

        lecture1QuizBtn = findViewById(R.id.L1QBtn);
        lecture2QuizBtn = findViewById(R.id.L2QBtn);
        lecture3QuizBtn = findViewById(R.id.L3QBtn);
        lecture4QuizBtn = findViewById(R.id.L4QBtn);
        lecture5QuizBtn = findViewById(R.id.L5QBtn);
        lecture6QuizBtn = findViewById(R.id.L6QBtn);
        lecture7QuizBtn = findViewById(R.id.L7QBtn);
        lecture8QuizBtn = findViewById(R.id.L8QBtn);
        lecture9QuizBtn = findViewById(R.id.L9QBtn);

        L1LL = findViewById(R.id.L1LL);
        L2LL = findViewById(R.id.L2LL);
        L3LL = findViewById(R.id.L3LL);
        L4LL = findViewById(R.id.L4LL);
        L5LL = findViewById(R.id.L5LL);
        L6LL = findViewById(R.id.L6LL);
        L7LL = findViewById(R.id.L7LL);
        L8LL = findViewById(R.id.L8LL);
        L9LL = findViewById(R.id.L9LL);

        L1LL.setVisibility(View.GONE);
        L2LL.setVisibility(View.GONE);
        L3LL.setVisibility(View.GONE);
        L4LL.setVisibility(View.GONE);
        L5LL.setVisibility(View.GONE);
        L6LL.setVisibility(View.GONE);
        L7LL.setVisibility(View.GONE);
        L8LL.setVisibility(View.GONE);
        L9LL.setVisibility(View.GONE);

        L1CB = findViewById(R.id.L1CB);
        L2CB = findViewById(R.id.L2CB);
        L3CB = findViewById(R.id.L3CB);
        L4CB = findViewById(R.id.L4CB);
        L5CB = findViewById(R.id.L5CB);
        L6CB = findViewById(R.id.L6CB);
        L7CB = findViewById(R.id.L7CB);
        L8CB = findViewById(R.id.L8CB);
        L9CB = findViewById(R.id.L9CB);

        L1TV = findViewById(R.id.L1TV);
        L2TV = findViewById(R.id.L2TV);
        L3TV = findViewById(R.id.L3TV);
        L4TV = findViewById(R.id.L4TV);
        L5TV = findViewById(R.id.L5TV);
        L6TV = findViewById(R.id.L6TV);
        L7TV = findViewById(R.id.L7TV);
        L8TV = findViewById(R.id.L8TV);
        L9TV = findViewById(R.id.L9TV);

        checkBoxes.add(L1CB);
        checkBoxes.add(L2CB);
        checkBoxes.add(L3CB);
        checkBoxes.add(L4CB);
        checkBoxes.add(L5CB);
        checkBoxes.add(L6CB);
        checkBoxes.add(L7CB);
        checkBoxes.add(L8CB);
        checkBoxes.add(L9CB);


        L1TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (L1LL.getVisibility() == View.GONE) {
                    L1LL.setVisibility(View.VISIBLE);
                } else {
                    L1LL.setVisibility(View.GONE);
                }
            }
        });

        L2TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (L2LL.getVisibility() == View.GONE) {
                    L2LL.setVisibility(View.VISIBLE);
                } else {
                    L2LL.setVisibility(View.GONE);
                }
            }
        });

        L3TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (L3LL.getVisibility() == View.GONE) {
                    L3LL.setVisibility(View.VISIBLE);
                } else {
                    L3LL.setVisibility(View.GONE);
                }
            }
        });

        L4TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (L4LL.getVisibility() == View.GONE) {
                    L4LL.setVisibility(View.VISIBLE);
                } else {
                    L4LL.setVisibility(View.GONE);
                }
            }
        });

        L5TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (L5LL.getVisibility() == View.GONE) {
                    L5LL.setVisibility(View.VISIBLE);
                } else {
                    L5LL.setVisibility(View.GONE);
                }
            }
        });

        L6TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (L6LL.getVisibility() == View.GONE) {
                    L6LL.setVisibility(View.VISIBLE);
                } else {
                    L6LL.setVisibility(View.GONE);
                }
            }
        });

        L7TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (L7LL.getVisibility() == View.GONE) {
                    L7LL.setVisibility(View.VISIBLE);
                } else {
                    L7LL.setVisibility(View.GONE);
                }
            }
        });

        L8TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (L8LL.getVisibility() == View.GONE) {
                    L8LL.setVisibility(View.VISIBLE);
                } else {
                    L8LL.setVisibility(View.GONE);
                }
            }
        });

        L9TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (L9LL.getVisibility() == View.GONE) {
                    L9LL.setVisibility(View.VISIBLE);
                } else {
                    L9LL.setVisibility(View.GONE);
                }
            }
        });

        lecture1contentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Content.class);
                intent.putExtra("lecture", "Lecture1.pdf");
                startActivity(intent);
            }
        });

        lecture2contentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Content.class);
                intent.putExtra("lecture", "Lecture2.pdf");
                startActivity(intent);
            }
        });

        lecture3contentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Content.class);
                intent.putExtra("lecture", "Lecture3.pdf");
                startActivity(intent);
            }
        });

        lecture4contentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Content.class);
                intent.putExtra("lecture", "Lecture4.pdf");
                startActivity(intent);
            }
        });

        lecture5contentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Content.class);
                intent.putExtra("lecture", "Lecture5.pdf");
                startActivity(intent);
            }
        });

        lecture6contentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Content.class);
                intent.putExtra("lecture", "Lecture6.pdf");
                startActivity(intent);
            }
        });

        lecture7contentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Content.class);
                intent.putExtra("lecture", "Lecture7.pdf");
                startActivity(intent);
            }
        });

        lecture8contentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Content.class);
                intent.putExtra("lecture", "Lecture8.pdf");
                startActivity(intent);
            }
        });

        lecture9contentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Content.class);
                intent.putExtra("lecture", "Lecture9.pdf");
                startActivity(intent);
            }
        });

        lecture1QuizBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Quiz.class);
                intent.putExtra("lecture", "lecture1");
                startActivity(intent);
            }
        });

        lecture2QuizBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Quiz.class);
                intent.putExtra("lecture", "lecture2");
                startActivity(intent);
            }
        });

        lecture3QuizBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Quiz.class);
                intent.putExtra("lecture", "lecture3");
                startActivity(intent);
            }
        });

        lecture4QuizBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Quiz.class);
                intent.putExtra("lecture", "lecture4");
                startActivity(intent);
            }
        });

        lecture5QuizBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Quiz.class);
                intent.putExtra("lecture", "lecture5");
                startActivity(intent);
            }
        });

        lecture6QuizBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Quiz.class);
                intent.putExtra("lecture", "lecture6");
                startActivity(intent);
            }
        });

        lecture7QuizBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Quiz.class);
                intent.putExtra("lecture", "lecture7");
                startActivity(intent);
            }
        });

        lecture8QuizBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Quiz.class);
                intent.putExtra("lecture", "lecture8");
                startActivity(intent);
            }
        });

        lecture9QuizBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Quiz.class);
                intent.putExtra("lecture", "lecture9");
                startActivity(intent);
            }
        });

    }

    // function to get the saved week for the use from firebase. This function also uses the
    // retrieved week number to check the checboxes.
    // Cannot call method in DatabaseHelpers as the it runs on a separate thread.
    // Known bug: this function does not check the checkboxes if the user navigates back to the
    // home activity from the content activity or the quiz activity.
    public static void getCurrentUserWeek() {

        mRef.child(KEY).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {
                    System.out.println(dataSnapshot);
                    User currUser = dataSnapshot.getValue(User.class);
                    week = currUser.getWeek();

                    for (int i = 0; i < week; i++) {
                        CheckBox cb = checkBoxes.get(i);
                        cb.setChecked(true);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println(databaseError.getMessage());
            }
        });
    }

}
