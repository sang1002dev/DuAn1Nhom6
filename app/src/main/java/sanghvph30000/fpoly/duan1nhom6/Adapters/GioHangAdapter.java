package sanghvph30000.fpoly.duan1nhom6.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;


import sanghvph30000.fpoly.duan1nhom6.Activities.GioHangActivity;
import sanghvph30000.fpoly.duan1nhom6.DAO.GioHangDAO;
import sanghvph30000.fpoly.duan1nhom6.Models.GioHang;
import sanghvph30000.fpoly.duan1nhom6.R;

public class GioHangAdapter extends RecyclerView.Adapter<GioHangAdapter.ViewHolder> {
    List<GioHang> list;
    List<GioHang> listMuaHang;
    GioHangActivity context;
    GioHangDAO gioHangDAO;
    SharedPreferences sharedPreferences;
    int nguoiDung_id;
    int price;
    public GioHangAdapter(List<GioHang> list, GioHangActivity context) {
        this.list = list;
        this.context = context;
        gioHangDAO = new GioHangDAO(context);
        sharedPreferences = context.getSharedPreferences("NGUOIDUNG", Context.MODE_PRIVATE);
        nguoiDung_id = sharedPreferences.getInt("nguoiDung_id", -1);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_gio_hang, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GioHang gioHang = list.get(position);
        String srcImg = list.get(position).getAnhSanPham();
        int resourceId = context.getResources().getIdentifier(srcImg, "drawable", context.getPackageName());
        Picasso.get().load(resourceId).into(holder.imgAnh_san_pham);
        holder.tvTen_san_pham.setText(list.get(position).getTenSanPham());
        holder.tvSo_luong_mua.setText(""+list.get(position).getSoLuong());
        holder.tvGia_san_pham.setText(""+list.get(position).getGiaSanPham());
        if(list.get(holder.getAdapterPosition()).getTrangThaiMua() == 0){
            holder.ckbMua_hang.setChecked(false);
        }else{
            holder.ckbMua_hang.setChecked(true);
        }
        holder.imgCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xoaSanPhamKhoiGioHang(holder.getAdapterPosition(), holder.tvSo_luong_mua);
            }
        });

        holder.imgPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.tvSo_luong_mua.setText(""+(Integer.parseInt(holder.tvSo_luong_mua.getText().toString())+1));
                gioHang.setSoLuong(Integer.parseInt(holder.tvSo_luong_mua.getText().toString()));
                gioHangDAO.suaSoLuong(gioHang);
                    price = 0;
                    for(GioHang gioHang1: list){
                        if(gioHang1.getTrangThaiMua()==1){
                            price += (gioHang1.getGiaSanPham()) * (gioHang1.getSoLuong());
                        }
                }
                context.tvTotal.setText(""+price);
            }
        });

        holder.imgMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.tvSo_luong_mua.getText().toString().matches("1")){
                    return;
                }
                holder.tvSo_luong_mua.setText(""+(Integer.parseInt(holder.tvSo_luong_mua.getText().toString())-1));
                gioHang.setSoLuong(Integer.parseInt(holder.tvSo_luong_mua.getText().toString()));
                gioHangDAO.suaSoLuong(gioHang);
                price = 0;
                for(GioHang gioHang1: list){
                    if(gioHang1.getTrangThaiMua()==1){
                        price += (gioHang1.getGiaSanPham()) * (gioHang1.getSoLuong());
                    }
                }
                context.tvTotal.setText(""+price);
            }
        });

        holder.ckbMua_hang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(gioHang.getTrangThaiMua()==0){
                    gioHang.setTrangThaiMua(1);
                    gioHangDAO.suaTrangThaiMua(gioHang);
                    price = 0;
                    for(GioHang gioHang1: list){
                        if(gioHang1.getTrangThaiMua()==1){
                            price += (gioHang1.getGiaSanPham()) * (gioHang1.getSoLuong());
                        }
                    }
                    context.tvTotal.setText(""+price);
                }else{
                    gioHang.setTrangThaiMua(0);
                    gioHangDAO.suaTrangThaiMua(gioHang);
                    price = 0;
                    for(GioHang gioHang1: list){
                        if(gioHang1.getTrangThaiMua()==1){
                            price += (gioHang1.getGiaSanPham()) * (gioHang1.getSoLuong());
                        }
                    }
                    context.tvTotal.setText(""+price);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        if(list != null)
            return list.size();
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvSo_luong_mua, tvTen_san_pham, tvGia_san_pham;
        ImageView imgAnh_san_pham, imgMinus, imgPlus, imgCancel;
        CheckBox ckbMua_hang;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvSo_luong_mua = itemView.findViewById(R.id.tvSo_luong_mua);
            tvTen_san_pham = itemView.findViewById(R.id.tvTen_san_pham);
            tvGia_san_pham = itemView.findViewById(R.id.tvGia_san_pham);
            imgAnh_san_pham = itemView.findViewById(R.id.imgAnh_san_pham);
            imgMinus = itemView.findViewById(R.id.imgMinus);
            imgPlus = itemView.findViewById(R.id.imgPlus);
            imgCancel = itemView.findViewById(R.id.imgCancel);
            ckbMua_hang = itemView.findViewById(R.id.ckbMua_hang);
        }
    }

    public void xoaSanPhamKhoiGioHang(int viTri, TextView tvSoluong){
        if(gioHangDAO.xoaKhoiGioHang(list.get(viTri).getSanPham_id(), list.get(viTri).getNguoiDung_id()) > 0){
            Toast.makeText(context, "Da xoa khoi gio hang", Toast.LENGTH_SHORT).show();

            list.remove(viTri);
            notifyDataSetChanged();
        }else{
            Toast.makeText(context, "Chua xoa khoi gio hang", Toast.LENGTH_SHORT).show();
        }
    }


}
