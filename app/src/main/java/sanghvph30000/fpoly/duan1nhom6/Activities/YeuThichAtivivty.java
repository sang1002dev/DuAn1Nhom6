package sanghvph30000.fpoly.duan1nhom6.Activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import sanghvph30000.fpoly.duan1nhom6.Fragments.YeuThich_Fragment;
import sanghvph30000.fpoly.duan1nhom6.Models.SanPham;
import sanghvph30000.fpoly.duan1nhom6.R;


public class YeuThichAtivivty extends AppCompatActivity {

    SanPham sanPham;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yeu_thich_ativivty);
        YeuThich_Fragment fragment = new YeuThich_Fragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        sanPham = (SanPham) bundle.getSerializable("sanPham");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent1 = new Intent(YeuThichAtivivty.this, ChiTietSanPhamActivity.class);
        Bundle bundle1 = new Bundle();
        bundle1.putSerializable("sanPham", sanPham);
        intent1.putExtras(bundle1);
        startActivity(intent1);
        finish();
    }
}