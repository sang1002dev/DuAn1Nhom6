package sanghvph30000.fpoly.duan1nhom6.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;


import sanghvph30000.fpoly.duan1nhom6.R;

public class ManHinhChaoActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView imgSanPham_frame1, imgIcon1_frame1, imgIcon2_frame1, imgIcon3_frame1, imgIcon4_frame1;
    private Button btnGetStarted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_chao);

        imgSanPham_frame1 = findViewById(R.id.imgSanPham_frame1);
        imgIcon1_frame1 = findViewById(R.id.imgIcon1_frame1);
        imgIcon2_frame1 = findViewById(R.id.imgIcon2_frame1);
        imgIcon3_frame1 = findViewById(R.id.imgIcon3_frame1);

        btnGetStarted = findViewById(R.id.btnGetStarted);

        imgIcon1_frame1.setOnClickListener(this);
        imgIcon2_frame1.setOnClickListener(this);
        imgIcon3_frame1.setOnClickListener(this);

        btnGetStarted.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.imgIcon1_frame1) {
            //Icon1
            imgSanPham_frame1.setImageResource(R.drawable.img_1);
            imgIcon1_frame1.setImageResource(R.drawable.frame1_cham_sang);
            imgIcon2_frame1.setImageResource(R.drawable.frame1_cham_toi);
            imgIcon3_frame1.setImageResource(R.drawable.frame1_cham_toi);
        } else if (id == R.id.imgIcon2_frame1) {
            //Icon2
            imgSanPham_frame1.setImageResource(R.drawable.img_6);
            imgIcon1_frame1.setImageResource(R.drawable.frame1_cham_toi);
            imgIcon2_frame1.setImageResource(R.drawable.frame1_cham_sang);
            imgIcon3_frame1.setImageResource(R.drawable.frame1_cham_toi);
        } else if (id == R.id.imgIcon3_frame1) {
            //Icon3
            imgSanPham_frame1.setImageResource(R.drawable.img_7);
            imgIcon1_frame1.setImageResource(R.drawable.frame1_cham_toi);
            imgIcon2_frame1.setImageResource(R.drawable.frame1_cham_toi);
            imgIcon3_frame1.setImageResource(R.drawable.frame1_cham_sang);
        } else if (id == R.id.btnGetStarted) {
            //btnGetStarted
            Intent intent = new Intent(this, DangNhapActivity.class);
            startActivity(intent);
        }
    }
}