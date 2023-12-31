package sanghvph30000.fpoly.duan1nhom6.Activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Array;
import java.util.ArrayList;

import sanghvph30000.fpoly.duan1nhom6.DAO.HoaDonChiTietDAO;
import sanghvph30000.fpoly.duan1nhom6.Fragments.DonHang_Fragment;
import sanghvph30000.fpoly.duan1nhom6.Fragments.HomeFragment;
import sanghvph30000.fpoly.duan1nhom6.Fragments.Loai_San_Pham_Fragment;
import sanghvph30000.fpoly.duan1nhom6.Fragments.QL_DonHang_Fragment;
import sanghvph30000.fpoly.duan1nhom6.Fragments.Ql_NguoiDung_Fragment;
import sanghvph30000.fpoly.duan1nhom6.Fragments.SanPham_Fragment;
import sanghvph30000.fpoly.duan1nhom6.Fragments.Iphone_Fragment;
import sanghvph30000.fpoly.duan1nhom6.Fragments.SamSung_Fragment;
import sanghvph30000.fpoly.duan1nhom6.Fragments.Oppo_Fragment;
import sanghvph30000.fpoly.duan1nhom6.Fragments.Ipad_Fragment;
import sanghvph30000.fpoly.duan1nhom6.Fragments.ThongKeAdminFragment;
import sanghvph30000.fpoly.duan1nhom6.Fragments.ViVo_Fragment;
import sanghvph30000.fpoly.duan1nhom6.Fragments.YeuThich_Fragment;
import sanghvph30000.fpoly.duan1nhom6.Models.HoaDonChiTiet;
import sanghvph30000.fpoly.duan1nhom6.R;


public class MainActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout_frame4;
    private Toolbar toolbar_frame4;
    private FrameLayout frameLayout_frame4;
    private NavigationView navigationView_frame4;
    private FragmentManager fragmentManager;
    private Fragment fragment;
    boolean isSelected;
    private ImageView imgAvatar_header, imgMuiTen_header;
    private TextView tvName_header, tvPhoneNumber_header, tvEmail_header;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Check data quan ly binh luan
//        BinhLuanDAO binhLuanDAO = new BinhLuanDAO(MainActivity.this);
//        ArrayList<BinhLuan> list1 = binhLuanDAO.getDSBinhLuanCach2();
//        Toast.makeText(this, list1.size() + "", Toast.LENGTH_SHORT).show();
//        ArrayList<BinhLuan> listTheoBinhLuan_id = binhLuanDAO.getDsBinhLuanTheoSanPham_id(2);
//        Toast.makeText(this, listTheoBinhLuan_id.size() + "", Toast.LENGTH_SHORT).show();
//        boolean check = binhLuanDAO.xoaBinhLuanTheoBinhLuan_id(5);
//        if (check){
//            Toast.makeText(this, "Thanh cong", Toast.LENGTH_SHORT).show();
//        }
//        ArrayList<BinhLuan> list = binhLuanDAO.getDSBinhLuanCach1();
//        Toast.makeText(this, list.size() + "", Toast.LENGTH_SHORT).show();

        //check data HOADONCHITIET
//        HoaDonChiTietDAO hoaDonChiTietDAO = new HoaDonChiTietDAO(MainActivity.this);
//        SharedPreferences sharedPreferences = getContext().getSharedPreferences("NGUOIDUNG", Context.MODE_PRIVATE);
//        int nguoiDung_id = sharedPreferences.getInt("nguoiDung_id",-1);
//        ArrayList<HoaDonChiTiet> listDonHangByHDCT = hoaDonChiTietDAO.getDonHangByHDCT(0,nguoiDung_id);
//        Toast.makeText(this, listDonHangByHDCT.size() + "", Toast.LENGTH_SHORT).show();


        //Ánh xạ
        drawerLayout_frame4 = findViewById(R.id.drawerLayout_frame4);
        toolbar_frame4 = findViewById(R.id.toolbar_frame4);
        frameLayout_frame4 = findViewById(R.id.frameLayout_frame4);
        navigationView_frame4 = findViewById(R.id.navigationView_frame4);

        //Ánh xạ các widget headerLayout
        View headerLayout = navigationView_frame4.getHeaderView(0);
        imgAvatar_header = headerLayout.findViewById(R.id.imgAvatar_header);
        imgMuiTen_header = headerLayout.findViewById(R.id.imgMuiTen_header);
        tvName_header = headerLayout.findViewById(R.id.tvName_header);
        tvPhoneNumber_header = headerLayout.findViewById(R.id.tvPhoneNumber_header);
        tvEmail_header = headerLayout.findViewById(R.id.tvEmail_header);

        //Xử lý cho toolbar
        setSupportActionBar(toolbar_frame4);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.header_menu);

        //set fragmentHome mặc định
        fragmentManager = getSupportFragmentManager();
        fragment = new HomeFragment();
        fragmentManager.beginTransaction().replace(R.id.frameLayout_frame4, fragment).commit();

        //Action của navigationView
        setActionForNavigationView(navigationView_frame4);
        //CHuyển qua chi tiết người dùng
        headerLayout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ChiTietNguoiDung.class);
                startActivity(intent);
                drawerLayout_frame4.closeDrawer(GravityCompat.START);
            }
        });
    }


    public void setActionForNavigationView(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int menuId = item.getItemId();
                fragmentManager = getSupportFragmentManager();
                if (menuId == R.id.menuTrangChu) {
                    fragment = new HomeFragment();
                    fragmentManager.beginTransaction().replace(R.id.frameLayout_frame4, fragment).commit();
                } else if (menuId == R.id.menuVoCoTrung) {
                    fragment = new Ipad_Fragment();
                    fragmentManager.beginTransaction().replace(R.id.frameLayout_frame4, fragment).commit();
                } else if (menuId == R.id.menuVoCoCao) {
                    fragment = new SamSung_Fragment();
                    fragmentManager.beginTransaction().replace(R.id.frameLayout_frame4, fragment).commit();
                } else if (menuId == R.id.menuVoCoNgan) {
                    fragment = new Oppo_Fragment();
                    fragmentManager.beginTransaction().replace(R.id.frameLayout_frame4, fragment).commit();
                } else if (menuId == R.id.menuYeuThich) {
                    fragment = new YeuThich_Fragment();
                    fragmentManager.beginTransaction().replace(R.id.frameLayout_frame4, fragment).commit();
                } else if (menuId == R.id.menuLoaiSanPham) {
                    fragment = new Loai_San_Pham_Fragment();
                    fragmentManager.beginTransaction().replace(R.id.frameLayout_frame4, fragment).commit();
                } else if (menuId == R.id.menuSanPham) {
                    fragment = new SanPham_Fragment();
                    fragmentManager.beginTransaction().replace(R.id.frameLayout_frame4, fragment).commit();
                } else if (menuId == R.id.menuVoLuoi) {
                    fragment = new SamSung_Fragment();
                    fragmentManager.beginTransaction().replace(R.id.frameLayout_frame4, fragment).commit();
                } else if (menuId == R.id.menuVoBasic) {
                    fragment = new Iphone_Fragment();
                    fragmentManager.beginTransaction().replace(R.id.frameLayout_frame4, fragment).commit();
                } else if (menuId == R.id.menuQLNguoiDung) {
                    fragment = new Ql_NguoiDung_Fragment();
                    fragmentManager.beginTransaction().replace(R.id.frameLayout_frame4, fragment).commit();
                } else if (menuId == R.id.menuDonHang) {
                    fragment = new DonHang_Fragment();
                    fragmentManager.beginTransaction().replace(R.id.frameLayout_frame4, fragment).commit();
                } else if (menuId == R.id.menu_ThongKeAdmin) {
                    fragment = new ThongKeAdminFragment();
                    fragmentManager.beginTransaction().replace(R.id.frameLayout_frame4, fragment).commit();
                }else if (menuId == R.id.menuQlDonHang){
                    fragment = new QL_DonHang_Fragment();
                    fragmentManager.beginTransaction().replace(R.id.frameLayout_frame4, fragment).commit();
                }

                drawerLayout_frame4.closeDrawer(GravityCompat.START);
                toolbar_frame4.setTitle(item.getTitle());
                return false;
            }
        });

        //Hiển thị thông tin sharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("NGUOIDUNG", MODE_PRIVATE);
        String imgSrc = sharedPreferences.getString("imgSrc", "");
        String hoTen = sharedPreferences.getString("hoTen", "");
        String soDienThoai = sharedPreferences.getString("soDienThoai", "");
        String email = sharedPreferences.getString("email", "");

        //Set thong tin nguoi dung cho header layout
        boolean isUri = imgSrc.startsWith("content://");
        if (isUri) {
            Picasso.get().load(Uri.parse(imgSrc)).into(imgAvatar_header);
        } else {
            int idResource = this.getResources().getIdentifier(imgSrc, "drawable", this.getPackageName());
            imgAvatar_header.setImageResource(idResource);
        }
        tvName_header.setText(hoTen);
        tvPhoneNumber_header.setText(soDienThoai);
        tvEmail_header.setText(email);
        int loaiTaiKhoan = sharedPreferences.getInt("loaiTaiKhoan", -1);
        if (loaiTaiKhoan == 1){
            Menu menu = navigationView.getMenu();
            menu.findItem(R.id.menuTrangChu).setVisible(false);
            menu.findItem(R.id.menuYeuThich).setVisible(false);
            menu.findItem(R.id.menuDonHang).setVisible(false);

        }else {
            Menu menu = navigationView.getMenu();
            menu.findItem(R.id.menuQlForAdmin).setVisible(false);
        }
        //Chuyen man hinh chi tiet
        imgMuiTen_header.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ChiTietNguoiDung.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            drawerLayout_frame4.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout_frame4.isDrawerOpen(GravityCompat.START)) {
            drawerLayout_frame4.closeDrawer(GravityCompat.START);
        } else {
            if (isSelected) {
                drawerLayout_frame4.openDrawer(GravityCompat.START); // nếu isSelected = true, hiển thị NavigationView khi ấn back
                isSelected = false; // reset lại giá trị của biến isSelected
            } else {
                fragmentManager = getSupportFragmentManager();
                fragment = new HomeFragment();
                fragmentManager.beginTransaction().replace(R.id.frameLayout_frame4, fragment).commit();
            }
        }
    }

    public void exit() {
        new AlertDialog.Builder(this)
                .setTitle("Confirm")
                .setMessage("Do you want to exit the application?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Kết thúc ứng dụng khi người dùng xác nhận thoát
                        finish();
                        System.exit(1);
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }
}