package sanghvph30000.fpoly.duan1nhom6.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import sanghvph30000.fpoly.duan1nhom6.Adapters.QLDonHangAdapter;
import sanghvph30000.fpoly.duan1nhom6.DAO.HoaDonChiTietDAO;
import sanghvph30000.fpoly.duan1nhom6.Models.HoaDonChiTiet;
import sanghvph30000.fpoly.duan1nhom6.R;


public class QL_dangGiao_Fragment extends Fragment {
    private RecyclerView ryc_ql_dangGiao;
    private HoaDonChiTietDAO hoaDonChiTietDAO;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ql_dang_giao, container, false);
        ryc_ql_dangGiao = view.findViewById(R.id.ryc_ql_dangGiao);
        loadData(ryc_ql_dangGiao);
        return view;
    }
    public void loadData(RecyclerView recyclerView){
        hoaDonChiTietDAO = new HoaDonChiTietDAO(getContext());
        ArrayList<HoaDonChiTiet> list = hoaDonChiTietDAO.getDonHangByHDCTForAdmin(2);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        QLDonHangAdapter adapter = new QLDonHangAdapter(getContext(), list);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        loadData(ryc_ql_dangGiao);
    }
}
