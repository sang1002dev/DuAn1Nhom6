package sanghvph30000.fpoly.duan1nhom6.Activities;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import sanghvph30000.fpoly.duan1nhom6.DAO.GioHangDAO;
import sanghvph30000.fpoly.duan1nhom6.DAO.SanPhamDAO;
import sanghvph30000.fpoly.duan1nhom6.Models.GioHang;
import sanghvph30000.fpoly.duan1nhom6.Models.SanPham;
import sanghvph30000.fpoly.duan1nhom6.R;


public class ChiTietSanPhamActivity extends AppCompatActivity {
    ImageView imgAnh_sanpham_chitiet, imgBack, imgGio_hang, imgYeu_thich, imgHome, imgThong_bao;
    TextView tvTen_sanpham_chitiet, tvGia_sanpham_chitiet, tvSo_luong,tvMota;
    Button btnChon_mua;
    SanPham sanPham;
    ImageView imgYeuThich_frameSPChiTiet2;

    SharedPreferences sharedPreferences;
    int getNguoiDung_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_san_pham);
        imgAnh_sanpham_chitiet = findViewById(R.id.imgAnh_sanpham_chitiet);
        imgBack = findViewById(R.id.imgBack);
        imgYeuThich_frameSPChiTiet2 = findViewById(R.id.imgYeuThich_frameSPChiTiet2);
        imgGio_hang = findViewById(R.id.imgGio_hang);
        imgYeu_thich = findViewById(R.id.imgYeu_thich);
        imgHome = findViewById(R.id.imgHome);

        tvTen_sanpham_chitiet = findViewById(R.id.tvTen_sanpham_chitiet);
        tvGia_sanpham_chitiet = findViewById(R.id.tvGia_sanpham_chitiet);
        tvSo_luong = findViewById(R.id.tvSo_luong);
        tvMota = findViewById(R.id.tvMota);
        btnChon_mua = findViewById(R.id.btnChon_mua);

        sharedPreferences = getSharedPreferences("NGUOIDUNG", MODE_PRIVATE);
        getNguoiDung_id = sharedPreferences.getInt("nguoiDung_id", 0);

        //lay san pham tu ben adapter san pham 2, khi click vao san pham se lay san pham do va truyen qua chi tiet san pham
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        sanPham = (SanPham) bundle.getSerializable("sanPham");

        //set anh cho san pham
        String srcImg = sanPham.getAnhSanPham();
        int resourceId = getResources().getIdentifier(srcImg, "drawable", getPackageName());
        Picasso.get().load(resourceId).into(imgAnh_sanpham_chitiet);
        tvTen_sanpham_chitiet.setText(sanPham.getTenSanPham());
        tvGia_sanpham_chitiet.setText("" + sanPham.getGiaSanPham() + " VND");
        tvSo_luong.setText("" + sanPham.getSoLuongConLai());
        tvMota.setText("" +sanPham.getMoTa());
        if (sanPham.getIsYeuThich() == 0) {
            imgYeuThich_frameSPChiTiet2.setImageResource(R.drawable.frame4_trai_tim2);
        } else {
            imgYeuThich_frameSPChiTiet2.setImageResource(R.drawable.frame4_trai_tim);
        }


        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        //Chức năng bình luận


        // Chức năng yêu thích
        int sanPham_id = sanPham.getSanPham_id();
        int isYeuThich = sanPham.getIsYeuThich();
        sanPhamYeuThich(sanPham_id, isYeuThich, imgYeuThich_frameSPChiTiet2);

        imgGio_hang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChiTietSanPhamActivity.this, GioHangActivity.class);
                startActivity(intent);
            }
        });

        imgHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ChiTietSanPhamActivity.this, MainActivity.class));
            }
        });

        imgYeu_thich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(ChiTietSanPhamActivity.this, YeuThichAtivivty.class);
                Bundle bundle1 = new Bundle();
                bundle1.putSerializable("sanPham", sanPham);
                intent1.putExtras(bundle1);
                startActivity(intent1);
                finish();
            }
        });

        btnChon_mua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chonMua(getNguoiDung_id, sanPham_id);
            }
        });

    }

    public void sanPhamYeuThich(int sanPham_id, int isYeuThich, ImageView imgIsYeuThich) {
        if (isYeuThich == 1) {
            imgIsYeuThich.setImageResource(R.drawable.frame4_trai_tim);
        }
        imgYeuThich_frameSPChiTiet2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SanPhamDAO sanPhamDAO = new SanPhamDAO(getApplicationContext());
                if (isYeuThich == 1) {
                    imgIsYeuThich.setImageResource(R.drawable.frame4_trai_tim2);
                    sanPhamDAO.changeIsYeuThich(sanPham_id, 0);
                } else {
                    imgIsYeuThich.setImageResource(R.drawable.frame4_trai_tim);
                    sanPhamDAO.changeIsYeuThich(sanPham_id, 1);
                    Toast.makeText(ChiTietSanPhamActivity.this, "Đã thêm vào yêu thích", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void chonMua(int nguoiDung_id, int sanPham_id) {
        GioHangDAO gioHangDAO = new GioHangDAO(ChiTietSanPhamActivity.this);
        GioHang gioHang = new GioHang();
        gioHang.setNguoiDung_id(nguoiDung_id);
        gioHang.setSanPham_id(sanPham_id);
        gioHang.setSoLuong(1);
        gioHang.setTrangThaiMua(0);
        if (gioHangDAO.themVaoGioHang(gioHang) > 0) {
            Toast.makeText(this, "Đã thêm vào giỏ hàng", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Mặt hàng này đã tồn tại trong giỏ hàng", Toast.LENGTH_SHORT).show();
        }
    }
}