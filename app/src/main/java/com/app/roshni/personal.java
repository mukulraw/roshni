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

import com.app.roshni.R;

import java.util.ArrayList;
import java.util.List;

public class personal extends Fragment {

    Spinner gender , category , religion , educational , marital , children , below6 , sixto14 , fifteento18 , goingtoschool;

    List<String> gend , cat , rel , edu , mar , chi;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.personal_layout, container, false);

        gend = new ArrayList<>();
        cat = new ArrayList<>();
        rel = new ArrayList<>();
        edu = new ArrayList<>();
        mar = new ArrayList<>();
        chi = new ArrayList<>();

        gender = view.findViewById(R.id.gender);
        category = view.findViewById(R.id.category);
        religion = view.findViewById(R.id.religion);
        educational = view.findViewById(R.id.educational);
        marital = view.findViewById(R.id.marital);
        children = view.findViewById(R.id.children);
        below6 = view.findViewById(R.id.belowsix);
        sixto14 = view.findViewById(R.id.sixto14);
        fifteento18 = view.findViewById(R.id.fifteento18);
        goingtoschool = view.findViewById(R.id.goingtoschool);


        gend.add("Male");
        gend.add("Female");

        cat.add("SC");
        cat.add("ST");
        cat.add("OBC");
        cat.add("General");

        rel.add("Hindu");
        rel.add("Muslim");
        rel.add("Sikh");
        rel.add("Christian");
        rel.add("Others");

        edu.add("Uneducated");
        edu.add("Primary (Class 1-5)");
        edu.add("Middle (Class 6-8)");
        edu.add("Secondary (Class 9-10)");
        edu.add("Senior Secondary (Class 11-12)");
        edu.add("Graduation");
        edu.add("Post Graduation");

        mar.add("Single");
        mar.add("Married");
        mar.add("Divorcee");
        mar.add("Separated");

        chi.add("1");
        chi.add("2");
        chi.add("3");
        chi.add("4");
        chi.add("5");
        chi.add("6");
        chi.add("7");
        chi.add("8");
        chi.add("9");
        chi.add("10");
        chi.add("11");
        chi.add("12");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                R.layout.spinner_model, gend);

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getContext(),
                R.layout.spinner_model, cat);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getContext(),
                R.layout.spinner_model, rel);

        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(getContext(),
                R.layout.spinner_model, edu);

        ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(getContext(),
                R.layout.spinner_model, mar);

        ArrayAdapter<String> adapter5 = new ArrayAdapter<String>(getContext(),
                R.layout.spinner_model, chi);


        gender.setAdapter(adapter);
        category.setAdapter(adapter1);
        religion.setAdapter(adapter2);
        educational.setAdapter(adapter3);
        marital.setAdapter(adapter4);
        children.setAdapter(adapter5);
        below6.setAdapter(adapter5);
        sixto14.setAdapter(adapter5);
        fifteento18.setAdapter(adapter5);
        goingtoschool.setAdapter(adapter5);


        return view;
    }
}
