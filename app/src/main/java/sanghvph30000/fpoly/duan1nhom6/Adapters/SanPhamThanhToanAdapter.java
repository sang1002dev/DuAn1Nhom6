package sanghvph30000.fpoly.duan1nhom6.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import sanghvph30000.fpoly.duan1nhom6.Models.GioHang;
import sanghvph30000.fpoly.duan1nhom6.R;


public class SanPhamThanhToanAdapter extends RecyclerView.Adapter<SanPhamThanhToanAdapter.ViewHolder> {
    List<GioHang> list;
    Context context;

    public SanPhamThanhToanAdapter(List<GioHang> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_san_pham_thanh_toan, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GioHang gioHang = list.get(position);
        holder.tvTen_sp.setText(gioHang.getTenSanPham());
        holder.tvSo_luong_sp.setText("SL: "+gioHang.getSoLuong());
        holder.tvTong_gia_san_pham.setText("Giá mua: "+((gioHang.getGiaSanPham()) * (gioHang.getSoLuong())));
        String srcImg = gioHang.getAnhSanPham();
        int resourceId = context.getResources().getIdentifier(srcImg, "drawable", context.getPackageName());
        Picasso.get().load(resourceId).into(holder.imgAnh_sp);
    }

    @Override
    public int getItemCount() {
        if(list != null)
            return list.size();
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvTen_sp, tvTong_gia_san_pham, tvSo_luong_sp;
        ImageView imgAnh_sp;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTen_sp = itemView.findViewById(R.id.tvTen_sp);
            tvTong_gia_san_pham = itemView.findViewById(R.id.tvTong_gia_san_pham);
            tvSo_luong_sp = itemView.findViewById(R.id.tvSo_luong_sp);
            imgAnh_sp = itemView.findViewById(R.id.imgAnh_sp);
        }
    }
    public int getSoLuongMua(int position) {
        return list.get(position).getSoLuong();
    }
}
