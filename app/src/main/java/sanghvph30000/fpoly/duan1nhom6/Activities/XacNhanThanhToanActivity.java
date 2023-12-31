package sanghvph30000.fpoly.duan1nhom6.Activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import sanghvph30000.fpoly.duan1nhom6.Adapters.SanPhamThanhToanAdapter;
import sanghvph30000.fpoly.duan1nhom6.DAO.GioHangDAO;
import sanghvph30000.fpoly.duan1nhom6.DAO.HoaDonChiTietDAO;
import sanghvph30000.fpoly.duan1nhom6.DAO.HoaDonDAO;
import sanghvph30000.fpoly.duan1nhom6.DAO.SanPhamDAO;
import sanghvph30000.fpoly.duan1nhom6.Models.GioHang;
import sanghvph30000.fpoly.duan1nhom6.Models.HoaDon;
import sanghvph30000.fpoly.duan1nhom6.Models.HoaDonChiTiet;
import sanghvph30000.fpoly.duan1nhom6.Models.SanPham;
import sanghvph30000.fpoly.duan1nhom6.R;


public class XacNhanThanhToanActivity extends AppCompatActivity {
    ImageView imgBack;
    TextView tvNguoi_nhan_hang, tvSdt_nguoi_nhan, tvGia_tam_tinh, tvThanh_tien, tvDia_chi_nhan_hang;
    Button btnXac_nhan_dat_hang;
    HoaDonDAO hoaDonDAO;
    SanPhamDAO sanPhamDAO;
    HoaDon hoaDon;
    SharedPreferences sharedPreferences;
    int tongTien, nguoiDung_id;
    String diaChi, tenNguoiNhan, sdt;
    List<GioHang> listGioHang;
    RecyclerView recycleView_thanh_toan;
    HoaDonChiTietDAO hoaDonChiTietDAO;
    SanPhamThanhToanAdapter sanPhamThanhToanAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xac_nhan_thanh_toan);
        imgBack = findViewById(R.id.imgBack);
        tvNguoi_nhan_hang = findViewById(R.id.tvNguoi_nhan_hang);
        tvSdt_nguoi_nhan = findViewById(R.id.tvSdt_nguoi_nhan);
        tvGia_tam_tinh = findViewById(R.id.tvGia_tam_tinh);
        tvThanh_tien = findViewById(R.id.tvThanh_tien);
        recycleView_thanh_toan = findViewById(R.id.recycler_view_don_hang_thanh_toan);
        tvDia_chi_nhan_hang = findViewById(R.id.tvDia_chi_nhan_hang);
        btnXac_nhan_dat_hang = findViewById(R.id.btnXac_nhan_dat_hang);
        sharedPreferences = getSharedPreferences("NGUOIDUNG", MODE_PRIVATE);
        nguoiDung_id = sharedPreferences.getInt("nguoiDung_id",-1);
        sanPhamDAO = new SanPhamDAO(this);
        hoaDonDAO = new HoaDonDAO(this);
        hoaDonChiTietDAO = new HoaDonChiTietDAO(this);

        Intent intent = getIntent();
        tongTien = intent.getIntExtra("tongTien", 0);
        diaChi = intent.getStringExtra("diaChi");
        tenNguoiNhan = intent.getStringExtra("nguoiNhan");
        sdt = intent.getStringExtra("sdt");
        listGioHang = (List<GioHang>) intent.getSerializableExtra("listGioHang");

        sanPhamThanhToanAdapter = new SanPhamThanhToanAdapter(listGioHang, XacNhanThanhToanActivity.this);
        recycleView_thanh_toan.setAdapter(sanPhamThanhToanAdapter);

        tvNguoi_nhan_hang.setText(tenNguoiNhan);
        tvSdt_nguoi_nhan.setText(sdt);
        tvThanh_tien.setText(""+tongTien);
        tvGia_tam_tinh.setText(""+tongTien);
        tvDia_chi_nhan_hang.setText(diaChi);


        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        btnXac_nhan_dat_hang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xacNhanDatHang();
                }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    public void xacNhanDatHang(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Xác nhận")
                .setPositiveButton("Đặt hàng", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Calendar calendar = Calendar.getInstance();
                        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
                        String currentTime = format.format(calendar.getTime());

                        hoaDon = new HoaDon();
                        hoaDon.setNguoiDung_id(nguoiDung_id);
                        hoaDon.setThoiGian(currentTime);
                        hoaDon.setTongTien(tongTien);
                        hoaDon.setDiaChi(diaChi);
                        if (hoaDonDAO.themHoaDon(hoaDon) > 0) {
                            GioHangDAO gioHangDAO = new GioHangDAO(XacNhanThanhToanActivity.this);
                            for (GioHang gioHang : listGioHang) {
                                if(gioHang.getTrangThaiMua()==1){
                                    gioHangDAO.xoaKhoiGioHang(gioHang.getSanPham_id(), nguoiDung_id);
                                    HoaDonChiTiet hdct = new HoaDonChiTiet();
                                    HoaDon hoaDon1 = hoaDonDAO.getHoaDonCuoiCung(nguoiDung_id);
                                    hdct.setHoaDon_id(hoaDon1.getHoaDon_id());
                                    hdct.setSanPham_id(gioHang.getSanPham_id());
                                    hdct.setSoLuong(gioHang.getSoLuong());
                                    hdct.setTrangThaiThanhToan(0);
                                    hdct.setTrangThaiDonHang(0);
                                    if(hoaDonChiTietDAO.themHoaDonChiTiet(hdct) > 0){
                                        Toast.makeText(XacNhanThanhToanActivity.this, "Ok", Toast.LENGTH_SHORT).show();
                                        int decreaseAmount = gioHang.getSoLuong();;

                                        // Lấy thông tin sản phẩm từ cơ sở dữ liệu bằng ID sản phẩm
                                        SanPham sanPham = sanPhamDAO.getSanPhamById(gioHang.getSanPham_id());
                                        if (sanPham != null) {
                                            int currentQuantity = sanPham.getSoLuongConLai();
                                            sanPham.setSoLuongConLai(currentQuantity - decreaseAmount);

                                            // Cập nhật số lượng sản phẩm vào cơ sở dữ liệu hoặc nguồn dữ liệu tương ứng
                                            // Ví dụ: sanPhamDAO.capNhatSanPham(sanPham);
                                            sanPhamDAO.SuaSanPham(sanPham);
                                        }
                                    }
                                }
                                startActivity(new Intent(XacNhanThanhToanActivity.this, MainActivity.class));
                                finish();
                            }
                            Toast.makeText(XacNhanThanhToanActivity.this, "Đã thêm đơn hàng", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(XacNhanThanhToanActivity.this, "Thêm đơn hàng thất bại", Toast.LENGTH_SHORT).show();
                        }
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).setCancelable(false);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
