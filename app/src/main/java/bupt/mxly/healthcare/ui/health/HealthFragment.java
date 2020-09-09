package bupt.mxly.healthcare.ui.health;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

import bupt.mxly.healthmonitor.R;
import tech.linjiang.suitlines.SuitLines;
import tech.linjiang.suitlines.Unit;

public class HealthFragment extends Fragment {

    private HealthViewModel healthViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        healthViewModel =
                ViewModelProviders.of(this).get(HealthViewModel.class);
        View root = inflater.inflate(R.layout.fragment_health, container, false);
        SuitLines suitLines=root.findViewById(R.id.suitlines);
        SuitLines suitLines_2=root.findViewById(R.id.suitlines_2);
        SuitLines suitLines_3=root.findViewById(R.id.suitlines_3);
        List<Unit> lines = new ArrayList<>();
        List<Unit> lines2 = new ArrayList<>();
        List<Unit> lines3 = new ArrayList<>();

        SecureRandom a = new SecureRandom();
        for (int i = 0; i < 14; i++) {
            lines.add(new Unit(110+a.nextInt(20), i + ""));
            lines2.add(new Unit(6+a.nextInt(3), i + ""));
            lines3.add(new Unit(60+a.nextInt(40), i + ""));
        }
        suitLines.feedWithAnim(lines);
        suitLines_2.feedWithAnim(lines2);
        suitLines_3.feedWithAnim(lines3);
        return root;
    }

}