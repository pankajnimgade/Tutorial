package viewpager.list.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import com.nimgade.pk.mytutorialapplication.R;

public class ViewPagerTestOneActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private MyFragmentAdapter myFragmentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager_test_one);
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_custom_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initializeUI();
    }

    private void initializeUI() {
        viewPager = (ViewPager) findViewById(R.id.ViewPagerTestOneActivity_viewPager);
        myFragmentAdapter = new MyFragmentAdapter(getSupportFragmentManager());
        viewPager.setAdapter(myFragmentAdapter);

    }

    private class MyFragmentAdapter extends FragmentPagerAdapter {

        public MyFragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return ImageFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            return 9;
        }
    }

    public static class ImageFragment extends Fragment {

        private SubsamplingScaleImageView imageView;
        private int position_Index = 0;

        public static ImageFragment newInstance(int index) {
            ImageFragment fragment = new ImageFragment();
            Bundle args = new Bundle();
            args.putInt("INDEX", index);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            if (getArguments() != null) {
                position_Index = getArguments().getInt("INDEX", 0);
            }
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_one, container, false);
            imageView = (SubsamplingScaleImageView) view.findViewById(R.id.fragment_one_imageView);
            return view;
        }

        @Override
        public void onActivityCreated(@Nullable Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);
            switch (position_Index) {
                case 0:
                    imageView.setImage(ImageSource.asset("one.png"));
                    imageView.setMaxScale(3.0f);
                    break;
                case 1:
                    imageView.setImage(ImageSource.asset("two.png"));
                    break;
                case 2:
                    imageView.setImage(ImageSource.asset("three.png"));
                    break;
                case 3:
                    imageView.setImage(ImageSource.asset("four.png"));
                    break;
                case 4:
                    imageView.setImage(ImageSource.asset("five.png"));
                    break;
                case 5:
                    imageView.setImage(ImageSource.asset("six.png"));
                    break;
                case 6:
                    imageView.setImage(ImageSource.asset("seven.png"));
                    break;
                case 7:
                    imageView.setImage(ImageSource.asset("eight.png"));
                    break;
                case 8:
                    imageView.setImage(ImageSource.asset("nine.png"));
                    break;
                default:
                    imageView.setImage(ImageSource.asset("nine.png"));
                    break;
            }

        }
    }

}
