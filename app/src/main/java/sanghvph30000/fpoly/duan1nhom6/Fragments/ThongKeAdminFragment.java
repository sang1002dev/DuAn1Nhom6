package sanghvph30000.fpoly.duan1nhom6.Fragments;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import sanghvph30000.fpoly.duan1nhom6.DAO.ThongKeDAO;
import sanghvph30000.fpoly.duan1nhom6.R;

public class ThongKeAdminFragment extends Fragment {
    private EditText edtTKDTStart, edtTKDTEnd;
    private Button btnThongKe;
    private TextView tvDonHangThanhCong, tvDonHangDaHuy, tvTongTien;
    String dateStart, dateEnd;
    boolean ipDateStart, ipDateEnd;
    private ThongKeDAO thongKeDAO;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thong_ke_admin, container, false);

        edtTKDTStart = view.findViewById(R.id.edtTKDTStart);
        edtTKDTEnd = view.findViewById(R.id.edtTKDTEnd);
        btnThongKe = view.findViewById(R.id.btnThongKe);
        tvDonHangThanhCong = view.findViewById(R.id.tvDonHang_thanhcong);
        tvDonHangDaHuy = view.findViewById(R.id.tvDonHang_dahuy);
        tvTongTien = view.findViewById(R.id.tvTong_tien);

        // Khởi tạo đối tượng ThongKeDAO
        thongKeDAO = new ThongKeDAO(getContext());
        edtTKDTStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int ngay = calendar.get(Calendar.DAY_OF_MONTH);
                int thang = calendar.get(Calendar.MONTH);
                int nam = calendar.get(Calendar.YEAR);
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int mYear, int mMonth, int mDay) {
                        calendar.set(mYear, mMonth, mDay);
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                        String subDate = simpleDateFormat.format(calendar.getTime());
                        edtTKDTStart.setText(subDate);
                        dateStart = subDate;
                        ipDateStart = true;
                    }
                }, nam, thang, ngay);
                datePickerDialog.show();
            }
        });


//        Get ngày kết thúc
        edtTKDTEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int ngay = calendar.get(Calendar.DAY_OF_MONTH);
                int thang = calendar.get(Calendar.MONTH);
                int nam = calendar.get(Calendar.YEAR);
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int mYear, int mMonth, int mDay) {
                        calendar.set(mYear, mMonth, mDay);
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                        String subDate = simpleDateFormat.format(calendar.getTime());
                        edtTKDTEnd.setText(subDate);
                        dateEnd = subDate;
                        ipDateEnd = true;
                    }
                }, nam, thang, ngay);
                datePickerDialog.show();
            }
        });

        // Đặt sự kiện click cho nút Thống kê
        btnThongKe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lấy ngày bắt đầu và ngày kết thúc từ EditText
                String ngayBatDau = edtTKDTStart.getText().toString().trim();
                String ngayKetThuc = edtTKDTEnd.getText().toString().trim();

                // Kiểm tra xem ngày bắt đầu và ngày kết thúc có hợp lệ không
                if (!ngayBatDau.isEmpty() && !ngayKetThuc.isEmpty()) {
                    // Gọi các hàm để thống kê và hiển thị kết quả
                    int donHangThanhCong = thongKeDAO.donHangThanhCong(ngayBatDau, ngayKetThuc);
                    int donHangDaHuy = thongKeDAO.donHangDaHuy(ngayBatDau, ngayKetThuc);
                    int tongTien = thongKeDAO.tongTien(ngayBatDau, ngayKetThuc);

                    // Hiển thị kết quả lên TextViews
                    tvDonHangThanhCong.setText(String.valueOf(donHangThanhCong));
                    tvDonHangDaHuy.setText(String.valueOf(donHangDaHuy));
                    tvTongTien.setText(String.valueOf(tongTien));
                }
            }
        });

        return view;
    }
}
