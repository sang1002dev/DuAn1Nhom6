package sanghvph30000.fpoly.duan1nhom6.Databases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {


    public DBHelper(Context context) {
        super(context, "DuAn1_Database", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String createNguoiDung = "create table NGUOIDUNG(" +
                "nguoiDung_id integer primary key autoincrement," +
                "imgSrc text not null," +
                "hoTen text not null," +
                "soDienThoai text not null," +
                "email text not null," +
                "taiKhoan text not null," +
                "matKhau text not null," +
                "loaiTaiKhoan integer not null," +
                "isXoaMem integer not null)";
        db.execSQL(createNguoiDung);


        String createGioHang = "create table GIOHANG(nguoiDung_id integer ," +
                "sanPham_id integer ," +
                "soLuong integer not null, " +
                "trangThaiMua integer not null," +
                "foreign key (nguoiDung_id) references NGUOIDUNG(nguoiDung_id)," +
                "foreign key (sanPham_id)  references SANPHAM(sanPham_id)," +
                "primary key(nguoiDung_id, sanPham_id))";
        db.execSQL(createGioHang);

        String createLoaiSanPham = "create table LOAISANPHAM(" +
                "loaiSanPham_id integer primary key autoincrement," +
                "tenLoai text not null)";
        db.execSQL(createLoaiSanPham);

        String createSanPham = "create table SANPHAM(" +
                "sanPham_id integer primary key autoincrement," +
                "loaiSanPham_id integer references LOAISANPHAM(loaiSanPham_id)," +
                "tenSanPham text not null," +
                "anhSanPham text not null," +
                "giaSanPham integer not null," +
                "moTa text not null," +
                "soLuongConLai integer not null," +
                "isYeuThich integer not null," +
                "isXoaMem integer not null)";
        db.execSQL(createSanPham);

        String createHoaDon = "create table HOADON(" +
                "hoaDon_id integer primary key autoincrement," +
                "nguoiDung_id integer references NGUOIDUNG(nguoiDung_id)," +
                "ngayMua text not null," +
                "tongTien integer not null," +
                "diaChi text not null)";
        db.execSQL(createHoaDon);

        String createHoaDonChiTiet = "create table HOADONCHITIET(" +
                "hoaDon_id integer not null, " +
                "sanPham_id integer not null," +
                "soLuong integer not null," +
                "trangThaiDonHang integer not null," +
                "trangThaiThanhToan integer not null," +
                "foreign key (hoaDon_id) references HOADON(hoaDon_id)," +
                "foreign key (sanPham_id) references SANPHAM(sanPham_id))";
        db.execSQL(createHoaDonChiTiet);

        //DATA ẢO

        /*1: Bang 'NGUOIDUNG'
         * Cấu trúc cột: nguoiDung_id, hoTen, soDienThoai, email, taiKhoan, matKhau, loaiTaiKhoan
         * Ghi chú:
         * - Giá trị tại cột 'loaiTaiKhoan': 1 --> admin, 0 --> người dùng */

        db.execSQL("insert into NGUOIDUNG values" +
                "(1,'sang', 'Hoang Van Sang', '0968786843', 'sanghvph30000@mail.com','sang','admin', 1,0)," +
                "(2,'avatar_mac_dinh', 'Trinh Dinh Nam', '0375816024', 'namdtph12345@gmail.com','nam','123', 0,0)");

        /*2: Bang 'BINHLUAN'
         * Cấu trúc cột: binhLuan_id, nguoiDung_id, sanPham_id, noiDung, thoiGian */



        /*2: Bang 'GIOHANG'
         * Cấu trúc cột:nguoiDung_id, sanPham_id, soLuong, trangThai
         * 0- chua mua
         * 1- da mua */



        /*3: Bảng 'LOAISANPHAM'
        * Cấu trúc cột: loaiSanPham_id, tenLoai */

        db.execSQL("insert into LOAISANPHAM values" +
                "(1, 'Iphone')," +
                "(2, 'SamSung')," +
                "(3, 'Oppo')," +
                "(4, 'Ipad')," +
                "(5, 'Vivo')");

        /*4: Bảng 'SANPHAM'
        * Cấu trúc cột: sanPham_id, loaiSanPham_id, tenSanPham, anhSanPham, giaSanPham, moTa, soLuongConLai, isYeuThich*/

        db.execSQL("insert into SANPHAM values" +
                // vo co thap
                "(1, 1, 'Iphone 14 ProMax', 'img_8', 15000000, 'Iphone mới ra chất lượng vô cùng tốt',51, 0,0)," +
                "(2, 1, 'Iphone 14 Pro', 'img_9', 25000000, 'Iphone mới ra chất lượng vô cùng tốt',50, 1,0)," +
                "(3, 1, 'Iphone 13 ProMax', 'img_10', 5000000, 'Iphone mới ra chất lượng vô cùng tốt',40, 0,0)," +
                "(4, 1, 'Iphone 13 Pro', 'img_11', 6500000, 'Iphone mới ra chất lượng vô cùng tốt',45, 0,0)," +
                "(5, 1, 'Iphone 12', 'img_12', 1000000, 'Iphone mới ra chất lượng vô cùng tốt',56, 0,0)," +


                // vo co cao
                "(6, 2, 'SamSung Galaxy 8', 'img_13', 18000000, 'SamSung đẳng cấp, siêu bền và giá cả phù hợp',30, 1,0)," +
                "(7, 2, 'SamSung Note 8', 'img_14', 28000000, 'SamSung đẳng cấp, siêu bền và giá cả phù hợp',34, 0,0)," +
                "(8, 2, 'SamSung Note 7', 'img_15', 13000000, 'SamSung đẳng cấp, siêu bền và giá cả phù hợp',31, 1,0)," +
                "(9, 2, 'SamSung X8', 'img_16', 16000000, 'SamSung đẳng cấp, siêu bền và giá cả phù hợp',36, 0,0)," +
                "(10, 2, 'SamSung Galaxy X', 'img_17', 19000000, 'SamSung đẳng cấp, siêu bền và giá cả phù hợp',39, 0,0)," +

                // vo co trung
                "(11, 3, 'Oppo Neo 3', 'img_18', 17000000, 'Vớ nữ cổ trung, êm chân thoát mát',40, 0,0)," +
                "(12, 3, 'Oppo Note 8', 'img_19', 16000000, 'Vớ thể thao nữ cổ trung, êm chân thoát mát',41, 0,0)," +
                "(13, 3, 'Oppo Reno Z', 'img_20', 15000000, 'Vớ thể thao nữ cổ trung, êm chân thoát mát',42, 1,0)," +
                "(14, 3, 'Oppo ZX', 'img_21', 14000000, 'Vớ thể thao nữ cổ trung, êm chân thoát mát',45, 0,0)," +

                // vo luoi
                //Không dùng ảnh có độ phân giải cao Quân nhé. Vì nó sẽ ko load được và bị chết app - Anh Sơn gửi đến Quân.
                "(15, 4, 'Ipad Pro 11', 'img_22', 20000000, 'Vớ lười nam/nũ chất liệu cao cấp',60, 0,0)," +
                "(16, 4, 'Ipax 10X', 'img_23', 20000000, 'Vớ lười nam/nũ chất liệu cao cấp',60, 0,0)," +
                "(17, 4, 'Ipax Air', 'img_24', 2000000, 'Vớ lười nam/nũ chất liệu cao cấp',60, 0,0)," +
                "(18, 4, 'Ipax Gen 10', 'img_25', 20000000, 'Vớ lười nam/nũ chất liệu cao cấp',60, 0,0)," +

                //vo basic
                "(19, 5, 'ViVo Y12S', 'img_26', 20000000, 'Vớ basic nam/nũ chất liệu cao cấp',60, 0,0)," +
                "(20, 5, 'ViVo Y11', 'img_27', 20000000, 'Vớ basic nam/nũ chất liệu cao cấp',60, 0,0)," +
                "(21, 5, 'ViVio Y13', 'img_28', 20000000, 'Vớ basic nam/nũ chất liệu cao cấp',60, 0,0)," +
                "(22, 5, 'ViVo V25S', 'img_29', 20000000, 'Vớ basic nam/nũ chất liệu cao cấp',60, 0,0)"


        );

        /*5: Bảng 'HOADON'
        *Cấu trúc cột: hoaDon_id, nguoiDung_id, ngayMua, tongTien, diaChi */

        db.execSQL("insert into HOADON values" +
                "(1, 1, '03/07/2023', 15000,'diachi1 - thi tran - Da Bac - Hoa Binh')," +
                "(2, 2, '04/07/2023', 25000,'diachi2 - thi tran - Da Bac - Hoa Binh')," +
                "(3, 3, '05/07/2023', 20000,'tk Tay mang - thi tran Da Bac - Da Bac - Hoa Binh')," +
                "(4, 4, '06/07/2023', 30000,'diachi4 - thi tran - Da Bac - Hoa Binh')," +
                "(5, 5, '07/07/2023', 35000,'diachi5 - thi tran - Da Bac - Hoa Binh')");

        /*6: Bảng 'HOADONCHITIET':
         * Ghi chú:
         * Trang thai don hang:
         * - 0: Order thanh con
         * - 1: Dang giao hang
         * - 2: da nhan hang
         *
         * Trang thai thanh toan:
         * - 0: Chua thanh toan
         * - 1: Da thanh toan
         * Cấu trúc bảng: hoaDon_id, sanPham_id, soLuong, trangThaiDonHang, trangThaiThanhToan */

        db.execSQL("insert into HOADONCHITIET values" +
                "(1, 2, 2, 0, 0)," +
                "(1, 1, 3, 1, 1)," +
                "(2, 3, 5, 2, 1)," +
                "(3, 4, 4, 3, 0)," +
                "(4, 20, 1, 0, 0)," +
                "(5, 15, 4, 1, 1)," +
                "(5, 16, 2, 4, 0)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists NGUOIDUNG");
        db.execSQL("drop table if exists HOADON");
        db.execSQL("drop table if exists LOAISANPHAM");
        db.execSQL("drop table if exists SANPHAM");
        db.execSQL("drop table if exists HOADONCHITIET");
        onCreate(db);
    }
}
