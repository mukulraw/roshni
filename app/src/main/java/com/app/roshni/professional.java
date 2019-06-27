package com.app.roshni;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class professional extends Fragment {

    Spinner sector , skills , experience , employment , home , workers , looms , location;

    List<String> sec , ski , exp , emp , hom , wor , loc;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.professional_layout, container, false);

        sec = new ArrayList<>();
        ski = new ArrayList<>();
        exp = new ArrayList<>();
        emp = new ArrayList<>();
        hom = new ArrayList<>();
        wor = new ArrayList<>();
        loc = new ArrayList<>();

        sector = view.findViewById(R.id.sector);
        skills = view.findViewById(R.id.skills);
        experience = view.findViewById(R.id.experience);
        employment = view.findViewById(R.id.employment);
        home = view.findViewById(R.id.home);
        workers = view.findViewById(R.id.workers);
        looms = view.findViewById(R.id.looms);
        location = view.findViewById(R.id.location);


        sec.add("Carpet");

        ski.add("Handloom");
        ski.add("Pitloom");
        ski.add("Hand Tufted");
        ski.add("Machine Tufted");
        ski.add("Hand Knotted");
        ski.add("Dari");
        ski.add("Stitching");
        ski.add("Finishing");
        ski.add("Flipping-Embossing");
        ski.add("Backing-Latexing");

        exp.add("0 to 2 years");
        exp.add("3 to 5 years");
        exp.add("5 to 10 years");
        exp.add("more then 10 years");

        emp.add("Employed");
        emp.add("Unemployed");

        hom.add("Yes");
        hom.add("No");

        wor.add("1");
        wor.add("2");
        wor.add("3");
        wor.add("4");
        wor.add("5");
        wor.add("6");
        wor.add("7");
        wor.add("8");
        wor.add("9");
        wor.add("10");
        wor.add("11");
        wor.add("12");
        wor.add("13");
        wor.add("14");
        wor.add("15");
        wor.add("16");
        wor.add("17");
        wor.add("18");
        wor.add("19");
        wor.add("20");

        loc.add("Panipat");
        loc.add("Bhadohi");
        loc.add("Mirzapur");
        loc.add("Jaipur");


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                R.layout.spinner_model, sec);

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getContext(),
                R.layout.spinner_model, ski);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getContext(),
                R.layout.spinner_model, exp);

        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(getContext(),
                R.layout.spinner_model, emp);

        ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(getContext(),
                R.layout.spinner_model, hom);

        ArrayAdapter<String> adapter5 = new ArrayAdapter<String>(getContext(),
                R.layout.spinner_model, wor);

        ArrayAdapter<String> adapter6 = new ArrayAdapter<String>(getContext(),
                R.layout.spinner_model, loc);


        sector.setAdapter(adapter);
        skills.setAdapter(adapter1);
        experience.setAdapter(adapter2);
        employment.setAdapter(adapter3);
        home.setAdapter(adapter4);
        workers.setAdapter(adapter5);
        looms.setAdapter(adapter5);
        location.setAdapter(adapter6);

        return view;
    }
}
