package sanghvph30000.fpoly.duan1nhom6.Fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import sanghvph30000.fpoly.duan1nhom6.Adapters.LoaiSanPhamAdapter;
import sanghvph30000.fpoly.duan1nhom6.DAO.LoaiSanPhamDAO;
import sanghvph30000.fpoly.duan1nhom6.Models.LoaiSanPham;
import sanghvph30000.fpoly.duan1nhom6.R;


public class Loai_San_Pham_Fragment extends Fragment {


    private RecyclerView recyclerView;
    private LoaiSanPhamDAO sanPhamDAO;
    private FloatingActionButton floatbtnLoaiSanPham;

    public Loai_San_Pham_Fragment() {
        // Required empty public constructor
    }


    public static Loai_San_Pham_Fragment newInstance() {
        Loai_San_Pham_Fragment fragment = new Loai_San_Pham_Fragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_loai__san__pham_, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recycler_view_LoaiSanPham);
        floatbtnLoaiSanPham = view.findViewById(R.id.floatbtnLoaiSanPham);

        floatbtnLoaiSanPham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogFloatButton();
            }
        });


        loatDate(recyclerView);
    }

    public void loatDate(RecyclerView recyclerView) {
        sanPhamDAO = new LoaiSanPhamDAO(getContext());
        ArrayList<LoaiSanPham> list = sanPhamDAO.getDsLoaiSanPham();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        LoaiSanPhamAdapter loaiSanPhamAdapter = new LoaiSanPhamAdapter(getContext(), list);
        recyclerView.setAdapter(loaiSanPhamAdapter);
    }


    public void showDialogFloatButton(){
        AlertDialog.Builder builder  =new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view  =inflater.inflate(R.layout.dialog_floatbutton_loaisanpham, null);
        builder.setView(view);
        EditText edTenLoaiSanPham  =view.findViewById(R.id.edThemLoaiSanPham);
        builder.setNegativeButton("Huỷ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).setPositiveButton("Thêm Mới", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        Dialog dialog  =builder.create();
        dialog.show();
    }
}