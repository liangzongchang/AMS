package me.ghui.AMS.UI;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import me.ghui.AMS.R;
import org.jsoup.select.Elements;

/**
 * Created by ghui on 4/12/14.
 */
public class CourseItemFragment extends Fragment implements CourseActivity.onElementsChangedListener{
    public static final String POS = "pos";
    private int pos;
    private TextView[] tvs;
    private View root;
    private int ids[] = {
            R.id.line1,
            R.id.line2,
            R.id.line3,
            R.id.line4,
            R.id.line5,
            R.id.line6,
            R.id.line7,
            R.id.line8,
            R.id.line9,
            R.id.line10,
            R.id.line11,
            R.id.line12,
            R.id.line13,
            R.id.line14,
            R.id.line15
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pos = getArguments().getInt(POS);
        Log.e("ghui", "Fragment onCreate pos: " + pos);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.course_item_fragment, container, false);
        Log.e("ghui", "onCreateView pos: " + pos);
        findView();
        Elements es = ((CourseActivity) getActivity()).es;
        fillData(es);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e("ghui", "Fragment onDestroyView pos: " + pos);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("ghui", "Fragment onDestroy pos: " + pos);
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("ghui", "Fragment onPause pos: " + pos);
    }

    private void findView() {
        tvs = new TextView[15];
        for (int i = 0; i < 15; i++) {
            tvs[i] = (TextView) root.findViewById(ids[i]);
        }
    }

    private void fillData(Elements es) {
        if (es == null) {
            return;
        }
        Elements elements = es.get(pos).children();
        Log.e("ghui", "size:" + elements.size());
        for (int i = 1; i < elements.size(); i++) {
            tvs[i - 1].setText(elements.get(i).text());
        }
    }

    @Override
    public void onElementsChanged(Elements es) {
        fillData(es);
    }
}