package sanghvph30000.fpoly.duan1nhom6.DAO;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import sanghvph30000.fpoly.duan1nhom6.Databases.DBHelper;

public class ThongKeDAO {
    private DBHelper dbHelper;

    public ThongKeDAO(Context context) {
        dbHelper = new DBHelper(context);
    }
    @SuppressLint("Range")
    public int tongTien(String ngayBatDau, String ngayKetThuc) {
        int tongTien = 0;
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT SUM(HOADONCHITIET.soLuong * SANPHAM.giaSanPham) AS tongTien " +
                "FROM HOADONCHITIET INNER JOIN SANPHAM ON HOADONCHITIET.sanPham_id = SANPHAM.sanPham_id " +
                "INNER JOIN HOADON ON HOADONCHITIET.hoaDon_id = HOADON.hoaDon_id " +  // Thêm phần INNER JOIN với bảng HOADON
                "WHERE HOADONCHITIET.trangThaiDonHang = 3 " +
                "AND HOADON.ngayMua BETWEEN ? AND ?", new String[]{ngayBatDau, ngayKetThuc});
        if (cursor.moveToFirst()) {
            tongTien = cursor.getInt(cursor.getColumnIndex("tongTien"));  // Sửa cách lấy giá trị từ Cursor
        }
        cursor.close();
        return tongTien;
    }




    @SuppressLint("Range")
    public int donHangThanhCong(String ngayBatDau, String ngayKetThuc) {
        int donHangThanhCong = 0;
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT COUNT(HOADON.hoaDon_id) AS donHangThanhCong " +
                "FROM HOADON INNER JOIN HOADONCHITIET ON HOADON.hoaDon_id = HOADONCHITIET.hoaDon_id " +
                "INNER JOIN SANPHAM ON HOADONCHITIET.sanPham_id = SANPHAM.sanPham_id " +
                "WHERE HOADONCHITIET.trangThaiDonHang = 3 " +
                "AND HOADON.ngayMua BETWEEN ? AND ?", new String[]{ngayBatDau, ngayKetThuc});
        if (cursor.moveToFirst()) {
            donHangThanhCong = cursor.getInt(cursor.getColumnIndex("donHangThanhCong"));
        }
        cursor.close();
        return donHangThanhCong;
    }


    @SuppressLint("Range")
    public int donHangDaHuy(String ngayBatDau, String ngayKetThuc) {
        int donHangDaHuy = 0;
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT COUNT(HOADON.hoaDon_id) AS donHangDaHuy " +
                "FROM HOADON INNER JOIN HOADONCHITIET ON HOADON.hoaDon_id = HOADONCHITIET.hoaDon_id " +
                "INNER JOIN SANPHAM ON HOADONCHITIET.sanPham_id = SANPHAM.sanPham_id " +
                "WHERE HOADONCHITIET.trangThaiDonHang = 4 " +
                "AND HOADON.ngayMua BETWEEN ? AND ?", new String[]{ngayBatDau, ngayKetThuc});
        if (cursor.moveToFirst()) {
            donHangDaHuy = cursor.getInt(cursor.getColumnIndex("donHangDaHuy"));
        }
        cursor.close();
        return donHangDaHuy;
    }

}
