package sanghvph30000.fpoly.duan1nhom6.Adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

import sanghvph30000.fpoly.duan1nhom6.DAO.LoaiSanPhamDAO;
import sanghvph30000.fpoly.duan1nhom6.DAO.SanPhamDAO;
import sanghvph30000.fpoly.duan1nhom6.Models.LoaiSanPham;
import sanghvph30000.fpoly.duan1nhom6.Models.SanPham;
import sanghvph30000.fpoly.duan1nhom6.R;


public class SanPhamAdapter extends RecyclerView.Adapter<SanPhamAdapter.MyViewHover> {
    private Context context;
    private ArrayList<SanPham> list;
    private SanPhamDAO sanPhamDAO;

    private AdapterView.OnItemClickListener mListener;


    public SanPhamAdapter(Context context, ArrayList<SanPham> list, AdapterView.OnItemClickListener listener) {
        this.context = context;
        this.list = list;
        this.mListener = listener;

    }

    @NonNull
    @Override
    public MyViewHover onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sanpham, parent, false);
        return new MyViewHover(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHover holder, @SuppressLint("RecyclerView") int position) {
        holder.tvID.setText("Loại: " + String.valueOf(list.get(position).getTenloaisanpham()));
        holder.tvTen.setText("Tên Sản Phẩm: " + list.get(position).getTenSanPham());
        holder.tvMota.setTextColor(Color.BLACK);
        holder.tvGia.setText("Giá Tiền: " + String.valueOf(list.get(position).getGiaSanPham()));
        holder.tvMota.setText("Mô Tă Sản Phẩm: " + list.get(position).getMoTa());
        holder.tvSoLuong.setText("Số Lượng Còn: " + String.valueOf(list.get(position).getSoLuongConLai()));


        String srcImg = list.get(position).getAnhSanPham();

        // Kiểm tra xem ảnh có phải là đường dẫn URI hay không
        boolean isUri = srcImg.startsWith("content://");

        if (isUri) {
            // Nếu là đường dẫn URI, sử dụng Picasso để tải ảnh từ đường dẫn URI
            Picasso.get().load(Uri.parse(srcImg)).into(holder.imgSanPham);
        } else {
            // Nếu không phải là đường dẫn URI, sử dụng cách khác để hiển thị ảnh (ví dụ: từ nguồn drawable)
            int resourceId = context.getResources().getIdentifier(srcImg, "drawable", context.getPackageName());
            holder.imgSanPham.setImageResource(resourceId);
        }


        // ...
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toast.makeText(context, "tien"+mListener, Toast.LENGTH_SHORT).show();
                mListener.onItemClick(null, v, position, v.getId());
            }
        });

    }

    @Override
    public int getItemCount() {
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    public class MyViewHover extends RecyclerView.ViewHolder {
        TextView tvID, tvTen, tvGia, tvMota, tvSoLuong;
        ImageView imgSanPham;
        Spinner spnTen;
        CardView cardView;

        public MyViewHover(@NonNull View itemView) {
            super(itemView);
            tvID = itemView.findViewById(R.id.idLoaiSanPham);
            tvTen = itemView.findViewById(R.id.idTenSanPham);
            tvGia = itemView.findViewById(R.id.idGiaSanPham);
            tvMota = itemView.findViewById(R.id.idMoTaSanPham);
            tvSoLuong = itemView.findViewById(R.id.idSoLuongSanpham);
            imgSanPham = itemView.findViewById(R.id.imgSanPhamADM);
            // sửa thông tin
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    LayoutInflater inflater = ((Activity) context).getLayoutInflater();
                    View view = inflater.inflate(R.layout.dialog_sua_sanpham, null);
                    builder.setView(view);
                    spnTen = view.findViewById(R.id.edSuaTenLoaiSP);

                    EditText edten = view.findViewById(R.id.edSuaTenSP);
                    EditText edGia = view.findViewById(R.id.edSuaGiaSP);
                    EditText edMota = view.findViewById(R.id.edSuaMoTaSP);
                    EditText edSoLuong = view.findViewById(R.id.edSuaSoluongSP);
                    SanPham sanPham = list.get(getLayoutPosition());


                    getDataLoaiSanPham(spnTen);
                    edten.setText(sanPham.getTenSanPham());
                    edGia.setText(String.valueOf(sanPham.getGiaSanPham()));
//                    edTen.setText(sanPham.getTenSanPham());
                    edMota.setText(sanPham.getMoTa());
                    edSoLuong.setText(String.valueOf(sanPham.getSoLuongConLai()));


//                    imgSuaAnhSP.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            if (activity != null) {
//                                pickImageFromGallery();
//                            }
//                        }
//                    });


                    builder.setNegativeButton("Xoá Mềm", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            sanPhamDAO = new SanPhamDAO(context);
                            int position = getLayoutPosition();
                            int isCheck = sanPhamDAO.xoaMemSP(list.get(position).getSanPham_id());
                            if (isCheck > 0) {
                                Toast.makeText(context, "Xoá Mềm Thành Công", Toast.LENGTH_SHORT).show();
                                sanPham.setXoamen(0);
                                notifyDataSetChanged();
                            } else {
                                Toast.makeText(context, "Xoá Mền Thất Bại", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }).setPositiveButton("Sửa", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            String ten = edten.getText().toString().trim();
                            String gia = edGia.getText().toString().trim();
                            String mota = edMota.getText().toString().trim();
                            String soluong = edSoLuong.getText().toString().trim();

                            boolean check = ten.isEmpty() || gia.isEmpty() || mota.isEmpty() || soluong.isEmpty();
                            if (check) {
                                Toast.makeText(context, "Không Được Bỏ Chống", Toast.LENGTH_SHORT).show();
                            } else {
                                int soluongconai = Integer.parseInt(soluong);
                                int giaban = Integer.parseInt(gia);
                                sanPhamDAO = new SanPhamDAO(context);

                                HashMap<String, Object> hsTV = (HashMap<String, Object>) spnTen.getSelectedItem();
                                int tenLoaiSP = (int) hsTV.get("loaiSanPham_id");
                                sanPham.setTenSanPham(ten);
                                sanPham.setGiaSanPham(giaban);
                                sanPham.setMoTa(mota);
                                sanPham.setSoLuongConLai(soluongconai);
                                sanPham.setLoaiSanPham_id(tenLoaiSP);
                                //                             sanPham.setAnhSanPham(duongDanAnh);
                                if (sanPhamDAO.SuaSanPham(sanPham) > 0) {
                                    Toast.makeText(context, "Sủa Thành Công", Toast.LENGTH_SHORT).show();
                                    list.clear();
                                    list = sanPhamDAO.getDsSanPhamADM();
                                    notifyDataSetChanged();
                                } else {
                                    Toast.makeText(context, "Sửa Thất Bại", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    });

                    Dialog dialog = builder.create();
                    dialog.show();
                }
            });
        }
    }

    public void getDataLoaiSanPham(Spinner spnSach) {
        LoaiSanPhamDAO sachDao = new LoaiSanPhamDAO(context);
        ArrayList<LoaiSanPham> list = sachDao.getDsLoaiSanPham();
        ArrayList<HashMap<String, Object>> listHM = new ArrayList<>();
        for (LoaiSanPham sc : list) {
            HashMap<String, Object> hs = new HashMap<>();
            hs.put("loaiSanPham_id", sc.getLoaiSanPham_id());
            hs.put("tenLoai", sc.getTenLoai());
            listHM.add(hs);
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(context, listHM,
                android.R.layout.simple_list_item_1,
                new String[]{"tenLoai"},
                new int[]{android.R.id.text1});
        spnSach.setAdapter(simpleAdapter);
    }
}