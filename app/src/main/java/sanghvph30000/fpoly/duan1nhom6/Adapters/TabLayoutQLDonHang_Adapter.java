package sanghvph30000.fpoly.duan1nhom6.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import sanghvph30000.fpoly.duan1nhom6.Fragments.QL_DaHuy_Fragment;
import sanghvph30000.fpoly.duan1nhom6.Fragments.QL_choXacNhan_Fragment;
import sanghvph30000.fpoly.duan1nhom6.Fragments.QL_daGiao_Fragment;
import sanghvph30000.fpoly.duan1nhom6.Fragments.QL_daXacNhan_Fragment;
import sanghvph30000.fpoly.duan1nhom6.Fragments.QL_dangGiao_Fragment;


public class TabLayoutQLDonHang_Adapter extends FragmentStateAdapter {
    public TabLayoutQLDonHang_Adapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new QL_choXacNhan_Fragment();
            case 1:
                return new QL_daXacNhan_Fragment();
            case 2 :
                return new QL_dangGiao_Fragment();
            case 3:
                return new QL_daGiao_Fragment();
            case 4:
                return new QL_DaHuy_Fragment();
        }
        return null;
    }

    @Override
    public int getItemCount() {
        return 5;
    }
}
