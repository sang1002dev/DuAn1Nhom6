package sanghvph30000.fpoly.duan1nhom6.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import sanghvph30000.fpoly.duan1nhom6.Adapters.SanPhamAdapter2;
import sanghvph30000.fpoly.duan1nhom6.DAO.SanPhamDAO;
import sanghvph30000.fpoly.duan1nhom6.Models.SanPham;
import sanghvph30000.fpoly.duan1nhom6.R;


public class Ipad_Fragment extends Fragment {
    private RecyclerView recyclerView;
    private SanPhamDAO sanPhamDAO;
    public Ipad_Fragment() {
        // Required empty public constructor
    }



    public static Ipad_Fragment newInstance() {
        Ipad_Fragment fragment = new Ipad_Fragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ipad, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        recyclerView = view.findViewById(R.id.recyclerView_voluoi);
        sanPhamDAO = new SanPhamDAO(getContext());
        loadDataGridLayout(recyclerView);
        super.onViewCreated(view, savedInstanceState);
    }

    public void loadDataGridLayout(RecyclerView recyclerView) {
        int numColumns = 2;
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), numColumns));
        ArrayList<SanPham> list = sanPhamDAO.getDsVoCoTrung();
        SanPhamAdapter2 adapter = new SanPhamAdapter2(getContext(), list);
        recyclerView.setAdapter(adapter);
    }
}