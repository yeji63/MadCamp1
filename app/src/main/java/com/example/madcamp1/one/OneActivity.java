package com.example.madcamp1.one;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Vector;

import com.example.madcamp1.R;
import com.example.madcamp1.condition.CheckAvailable;
import com.example.madcamp1.databinding.ActivityOneBinding;

public class OneActivity extends AppCompatActivity {

    ActivityOneBinding binding;
    OneAdapter adapter;
    CheckAvailable checkAvailable;
    Vector<One> mOne;

    private static int N;
    int imgres;
    Bitmap imgbtm;

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_one);

        // get intent data
        Intent i = getIntent();
        // Selected image id
        N = i.getExtras().getInt("n_num");
        //img int로 받기
        imgres = i.getExtras().getInt("imgres");
        //카메라 찍은거 받기
        imgbtm = i.getParcelableExtra("imgbtm");


        // span과 이미지 갯수만 변경되면 모든 M x N 적용가능
        GridLayoutManager layoutManager = new GridLayoutManager(this, N);
        binding.oneViews.setLayoutManager(layoutManager);
        adapter = new OneAdapter(this, N);
        binding.oneViews.setAdapter(adapter);

        binding.oneViews.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int length = binding.oneViews.getWidth() / N;

                SliceImageView siv = new SliceImageView(OneActivity.this);
                siv.setWH(length, length);
                Bitmap[] bitmaps = siv.getbitNXN();
                mOne = new Vector<>();
                for (int i = 0; i < bitmaps.length; i++) {
                    if (i == Math.pow(N, 2) - 1) {
                        mOne.add(new One(bitmaps[i], i, true));
                    } else {
                        mOne.add(new One(bitmaps[i], i, false));
                    }
                }
                adapter.update(mOne);
                checkAvailable = new CheckAvailable(mOne, N);
                int shufflePos = N*N - 1;
                int[] ways = {-1, 1, -N, N};
                int shuffleCount = (int) (Math.random() * 500) + 500;
                for (int i = 0; i < shuffleCount; i++) {
                    int wayIdx = (int) (Math.random() * 4);
                    shufflePos += ways[wayIdx];
                    if (shufflePos < N*N && shufflePos > -1) {
                        int[] result = checkAvailable.check(shufflePos);
                        if (result[0] != 100) {
                            adapter.change(shufflePos, result[0]);
                            shufflePos = result[0];
                        }
                    } else {
                        shufflePos -= ways[wayIdx];
                    }
                }
                adapter.update(mOne);
                adapter.setLength(length);
                adapter.notifyDataSetChanged();
                binding.oneViews.addOnItemTouchListener(itemTouchListener);
                binding.oneViews.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });


        binding.restartBtn.setOnClickListener(view -> recreate());
    }

    private int getAnim(int anim) {
        int animation = 0;
        switch (anim) {
            case 1:
                animation = R.anim.down_to_up;
                break;
            case 2:
                animation = R.anim.up_to_down;
                break;
            case 3:
                animation = R.anim.right_to_left;
                break;
            case 4:
                animation = R.anim.left_to_right;
                break;
        }
        return animation;
    }

    RecyclerView.OnItemTouchListener itemTouchListener = new RecyclerView.OnItemTouchListener() {
        @Override
        public boolean onInterceptTouchEvent(@NonNull RecyclerView parent, @NonNull MotionEvent evt) {
            int action = evt.getAction();
            if (action == MotionEvent.ACTION_UP) {
                View child = parent.findChildViewUnder(evt.getX(), evt.getY());
                if(child != null) {
                    int pos = parent.getChildAdapterPosition(child);
                    Log.e("pos", String.valueOf(pos));
                    if (pos != -1) {
                        int[] result = checkAvailable.check(pos);
                        int newPos = result[0];
                        int anim = getAnim(result[1]);
                        Log.e("new pos = " + newPos, "anim = " + result[1]);
                        if (newPos != 100) {
                            Animation animation = AnimationUtils.loadAnimation(OneActivity.this, anim);
                            animation.setAnimationListener(new Animation.AnimationListener() {
                                @Override
                                public void onAnimationStart(Animation animation) {
                                }

                                @Override
                                public void onAnimationEnd(Animation animation) {
                                    Vector<One> one = adapter.change(pos, newPos);
                                    int good_job = 0;
                                    for (int i = 0; i < mOne.size(); i++) {
                                        if (i == mOne.get(i).getTag()) {
                                            good_job++;
                                        }
                                    }
                                    Log.e("current good job", String.valueOf(good_job));
                                    if (good_job == mOne.size()) {
                                        binding.showCorrectImageView.setVisibility(View.VISIBLE);
                                        if(imgbtm==null){
                                            binding.showCorrectImageView.setImageResource(imgres);
                                        } else if(imgres==0){
                                           binding.showCorrectImageView.setImageBitmap(imgbtm);
                                        }

                                        for (int i = 0; i < one.size(); i++) {
                                            boolean empty = one.get(i).isEmpty();
                                            if (empty) {
                                                adapter.finish(i);
                                                binding.oneViews.removeOnItemTouchListener(itemTouchListener);
                                                break;
                                            }
                                        }
                                        Toast.makeText(OneActivity.this, "퍼즐 완성!.", Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onAnimationRepeat(Animation animation) {
                                }
                            });
                            child.startAnimation(animation);
                        }
                    }
                }
            }
            return false;
        }

        @Override
        public void onTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {

        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean b) {

        }
    };

    class SliceImageView extends View {
        Bitmap img;
        int imgW, imgH;
        Bitmap[] bitNXN = new Bitmap[N*N];

        int width = 0;
        int height = 0;

        public void setWH(int width, int height) {
            this.width = width;
            this.height = height;
            if(imgbtm==null){
                binding.correctImageView.setImageResource(imgres);
                img = BitmapFactory.decodeResource(getResources(), imgres);
            } else if(imgres==0){
                binding.correctImageView.setImageBitmap(imgbtm);
                img = imgbtm;
            }
            img = Bitmap.createScaledBitmap(img, width, height, true);
            imgW = img.getWidth() / N;
            imgH = img.getHeight() / N;

            for (int i = 0; i < Math.pow(N, 2); i++) {
                bitNXN[i] = Bitmap.createBitmap(img, (i % N) * imgW, (i / N) * imgH, imgW, imgH);
            }
            //bitNXN[N*N-1] = Bitmap.createBitmap(img, 0, 0, 1, 1);
            bitNXN[N*N-1]=BitmapFactory.decodeResource(getResources(), R.drawable.white);
        }

        public SliceImageView(Context context) {
            super(context);
        }

        public Bitmap[] getbitNXN() {
            return bitNXN;
        }
    }
}
