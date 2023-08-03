package sanghvph30000.fpoly.duan1nhom6.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.regex.Pattern;

import sanghvph30000.fpoly.duan1nhom6.Activities.GioHangActivity;
import sanghvph30000.fpoly.duan1nhom6.Adapters.SanPhamAdapter2;
import sanghvph30000.fpoly.duan1nhom6.DAO.SanPhamDAO;
import sanghvph30000.fpoly.duan1nhom6.Models.SanPham;
import sanghvph30000.fpoly.duan1nhom6.R;


public class HomeFragment extends Fragment {
    private RecyclerView recyclerView_frame4;
    private ImageView imgHome, imgYeu_thich, imgGio_hang, imgThong_bao;
    private SanPhamDAO sanPhamDAO;
    ArrayList<SanPham> listtemporary;
    ArrayList<SanPham> oriList;
    private TextInputEditText edTimkiem;
    private SanPhamAdapter2 searchResultsAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView_frame4 = view.findViewById(R.id.recyclerView_frame4);
        sanPhamDAO = new SanPhamDAO(getContext());
        imgHome = view.findViewById(R.id.imgHome);
        imgYeu_thich = view.findViewById(R.id.imgYeu_thich);
        imgGio_hang = view.findViewById(R.id.imgGio_hang);
        edTimkiem = view.findViewById(R.id.edTimKiem);

        oriList = sanPhamDAO.getDsSanPham();

        // Set up your RecyclerView and adapter

        ImageView searchButton = view.findViewById(R.id.imgTimKiem);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSearchButtonClick(v);
            }
        });


        //load Data
//        loadData(recyclerView_frame4);
        loadDataGridLayout(recyclerView_frame4);

        imgHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        imgGio_hang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), GioHangActivity.class));
            }
        });


        imgYeu_thich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                YeuThich_Fragment fragment = new YeuThich_Fragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frameLayout_frame4, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });
        return view;
    }

    public void loadDataGridLayout(RecyclerView recyclerView) {
        int numColumns = 2;
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), numColumns));
        ArrayList<SanPham> list = sanPhamDAO.getDsSanPham();
        SanPhamAdapter2 adapter = new SanPhamAdapter2(getContext(), list);
        recyclerView.setAdapter(adapter);
    }

    // tìm kiếm nhé

    public void onSearchButtonClick(View view) {
        String searchText = edTimkiem.getText().toString().toLowerCase();

        ArrayList<SanPham> searchResults = new ArrayList<>();
        oriList = sanPhamDAO.getDsSanPham();
        for (SanPham sanPham : oriList) {
            int giaSanPham = sanPham.getGiaSanPham();
            String tenSanPham = sanPham.getTenSanPham().toLowerCase();
            String normalizedMaSach = normalizeString(tenSanPham);
            if (isNumeric(searchText)) {
                // Tìm kiếm theo giá sản phẩm
                if (giaSanPham <= Integer.parseInt(searchText)) {
                    searchResults.add(sanPham);
                }
            } else {
                // Tìm kiếm theo tên sản phẩm
                if (normalizedMaSach.toLowerCase().replace("đ", "d").contains(searchText.toLowerCase())) {
                    searchResults.add(sanPham);
                }
            }

        }

        SanPhamAdapter2 phieuMuonAdapter = new SanPhamAdapter2(getContext(), searchResults);
        // Gán adapter mới cho RecyclerView hoặc ListView của bạn
        recyclerView_frame4.setAdapter(phieuMuonAdapter);
    }

    private boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }



    // loại bỏ dấu và chữ hoa
        private String normalizeString(String input) {
        //ó ý nghĩa là chuẩn hóa chuỗi input và loại bỏ các dấu diacritic trong chuỗi đó.
        String normalizedString = Normalizer.normalize(input, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(normalizedString).replaceAll("").toLowerCase();
    }

}



