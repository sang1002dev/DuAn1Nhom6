package sanghvph30000.fpoly.duan1nhom6.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import sanghvph30000.fpoly.duan1nhom6.Fragments.ChoXacNhan_Fragment;
import sanghvph30000.fpoly.duan1nhom6.Fragments.DaGiao_Fragment;
import sanghvph30000.fpoly.duan1nhom6.Fragments.DaXacNhan_Fragment;
import sanghvph30000.fpoly.duan1nhom6.Fragments.DangGiao_Fragment;


public class TabLayoutDonHangAdapter extends FragmentStateAdapter {

    public TabLayoutDonHangAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new ChoXacNhan_Fragment();
            case 1:
                return new DaXacNhan_Fragment();
            case 2:
                return new DangGiao_Fragment();
            case 3:
                return new DaGiao_Fragment();
        }
        return null;
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
