package kr.co.woobi.imyeon.animatorwithconstraintview;

import android.app.ActivityOptions;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.TransitionManager;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ConstraintSet mConstaintSet1 = new ConstraintSet();
    ConstraintSet mConstaintSet2 = new ConstraintSet();
    ConstraintLayout mConstraintLayout;
    boolean mOld = true;

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mConstaintSet2.clone(this, R.layout.activity_main2);
        setContentView(R.layout.activity_main);
        mConstraintLayout = findViewById(R.id.constraintView);
        mConstaintSet1.clone(mConstraintLayout);

        Button btn = findViewById(R.id.button);
        imageView = findViewById(R.id.imageView);
        TextView textView = findViewById(R.id.textView);

        Animation out = AnimationUtils.loadAnimation(this, R.anim.out);
        textView.startAnimation(out);

        btn.setOnClickListener(this);
        imageView.setOnClickListener(this);
        textView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                TransitionManager.beginDelayedTransition(mConstraintLayout);
                if (mOld = !mOld) {
                    mConstaintSet1.applyTo(mConstraintLayout);
                } else {
                    mConstaintSet2.applyTo(mConstraintLayout);
                }
                break;
            case R.id.imageView:
                Intent intent = new Intent(this, TransitionTargetActivity.class);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this, v, "ani");
                ActivityCompat.startActivity(this, intent, options.toBundle());
                break;
            case R.id.textView:
                Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
                v.startAnimation(shake);
                break;
        }
    }
}
