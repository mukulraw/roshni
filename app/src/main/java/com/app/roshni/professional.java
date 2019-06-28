package com.app.roshni;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.app.roshni.verifyPOJO.Data;
import com.app.roshni.verifyPOJO.verifyBean;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class professional extends Fragment {

    Spinner sector, skills, experience, employment, home, workers, looms, location;

    String sect, skil, expe, empl, hhom, work, loom, loca;

    List<String> sec, ski, exp, emp, hom, wor, loc;

    ProgressBar progress;

    EditText employer;

    Button submit;

    LinearLayout yes;

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
        progress = view.findViewById(R.id.progress);
        employer = view.findViewById(R.id.employer);
        submit = view.findViewById(R.id.submit);
        yes = view.findViewById(R.id.yes);

        sec.add("Select one --- ");
        sec.add("Carpet");

        ski.add("Select one --- ");
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

        exp.add("Select one --- ");
        exp.add("0 to 2 years");
        exp.add("3 to 5 years");
        exp.add("5 to 10 years");
        exp.add("more then 10 years");

        emp.add("Select one --- ");
        emp.add("Employed");
        emp.add("Unemployed");

        hom.add("Select one --- ");
        hom.add("Yes");
        hom.add("No");

        wor.add("Select one --- ");
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

        loc.add("Select one --- ");
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

        sector.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if (i > 0) {
                    sect = sec.get(i);
                } else {
                    sect = "";
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        skills.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if (i > 0) {
                    skil = ski.get(i);
                } else {
                    skil = "";
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        experience.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if (i > 0) {
                    expe = exp.get(i - 1);
                } else {
                    expe = "";
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        employment.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if (i > 0) {
                    empl = emp.get(i);
                } else {
                    empl = "";
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        home.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if (i > 0) {
                    hhom = hom.get(i);

                    if (hhom.equals("Yes")) {

                        yes.setVisibility(View.VISIBLE);

                    } else {

                        yes.setVisibility(View.GONE);
                        work = "0";
                        loom = "0";
                    }

                } else {
                    hhom = "";
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        workers.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if (i > 0) {
                    work = wor.get(i);
                } else {
                    work = "";
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        looms.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if (i > 0) {
                    loom = wor.get(i);
                } else {
                    loom = "";
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        location.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if (i > 0) {
                    loca = loc.get(i);
                } else {
                    loca = "";
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String emplo = employer.getText().toString();

                if (sect.length() > 0) {

                    if (skil.length() > 0) {
                        if (expe.length() > 0) {
                            if (empl.length() > 0) {
                                if (hhom.length() > 0) {
                                    if (work.length() > 0) {
                                        if (loom.length() > 0) {
                                            if (loca.length() > 0) {


                                                progress.setVisibility(View.VISIBLE);

                                                Bean b = (Bean) getContext().getApplicationContext();

                                                Retrofit retrofit = new Retrofit.Builder()
                                                        .baseUrl(b.baseurl)
                                                        .addConverterFactory(ScalarsConverterFactory.create())
                                                        .addConverterFactory(GsonConverterFactory.create())
                                                        .build();

                                                AllApiIneterface cr = retrofit.create(AllApiIneterface.class);

                                                Call<verifyBean> call = cr.updateWorkerProfessional(
                                                        SharePreferenceUtils.getInstance().getString("user_id"),
                                                        sect,
                                                        skil,
                                                        expe,
                                                        empl,
                                                        emplo,
                                                        hhom,
                                                        work,
                                                        loom,
                                                        loca
                                                );

                                                call.enqueue(new Callback<verifyBean>() {
                                                    @Override
                                                    public void onResponse(Call<verifyBean> call, Response<verifyBean> response) {

                                                        if (response.body().getStatus().equals("1")) {
                                                            Data item = response.body().getData();


                                                            SharePreferenceUtils.getInstance().saveString("name", item.getName());
                                                            SharePreferenceUtils.getInstance().saveString("photo", item.getPhoto());
                                                            SharePreferenceUtils.getInstance().saveString("dob", item.getDob());
                                                            SharePreferenceUtils.getInstance().saveString("gender", item.getGender());
                                                            SharePreferenceUtils.getInstance().saveString("phone", item.getPhone());
                                                            SharePreferenceUtils.getInstance().saveString("cpin", item.getCpin());
                                                            SharePreferenceUtils.getInstance().saveString("cstate", item.getCstate());
                                                            SharePreferenceUtils.getInstance().saveString("cdistrict", item.getCdistrict());
                                                            SharePreferenceUtils.getInstance().saveString("carea", item.getCarea());
                                                            SharePreferenceUtils.getInstance().saveString("cstreet", item.getCstreet());
                                                            SharePreferenceUtils.getInstance().saveString("ppin", item.getPpin());
                                                            SharePreferenceUtils.getInstance().saveString("pstate", item.getPstate());
                                                            SharePreferenceUtils.getInstance().saveString("pdistrict", item.getPdistrict());
                                                            SharePreferenceUtils.getInstance().saveString("parea", item.getParea());
                                                            SharePreferenceUtils.getInstance().saveString("pstreet", item.getPstreet());
                                                            SharePreferenceUtils.getInstance().saveString("category", item.getCategory());
                                                            SharePreferenceUtils.getInstance().saveString("religion", item.getReligion());
                                                            SharePreferenceUtils.getInstance().saveString("educational", item.getEducational());
                                                            SharePreferenceUtils.getInstance().saveString("marital", item.getMarital());
                                                            SharePreferenceUtils.getInstance().saveString("children", item.getChildren());
                                                            SharePreferenceUtils.getInstance().saveString("belowsix", item.getBelowsix());
                                                            SharePreferenceUtils.getInstance().saveString("sixtofourteen", item.getSixtofourteen());
                                                            SharePreferenceUtils.getInstance().saveString("fifteentoeighteen", item.getFifteentoeighteen());
                                                            SharePreferenceUtils.getInstance().saveString("goingtoschool", item.getGoingtoschool());
                                                            SharePreferenceUtils.getInstance().saveString("sector", item.getSector());
                                                            SharePreferenceUtils.getInstance().saveString("skills", item.getSkills());
                                                            SharePreferenceUtils.getInstance().saveString("experience", item.getExperience());
                                                            SharePreferenceUtils.getInstance().saveString("employment", item.getEmployment());
                                                            SharePreferenceUtils.getInstance().saveString("employer", item.getEmployer());
                                                            SharePreferenceUtils.getInstance().saveString("home", item.getHome());
                                                            SharePreferenceUtils.getInstance().saveString("workers", item.getWorkers());
                                                            SharePreferenceUtils.getInstance().saveString("looms", item.getLooms());
                                                            SharePreferenceUtils.getInstance().saveString("location", item.getLocation());


                                                            Intent intent = new Intent(getContext(), MainActivity.class);
                                                            startActivity(intent);
                                                            getActivity().finishAffinity();


                                                            Log.d("respo", response.body().getMessage());

                                                            Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                                        } else {
                                                            Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                                        }


                                                        progress.setVisibility(View.GONE);

                                                    }

                                                    @Override
                                                    public void onFailure(Call<verifyBean> call, Throwable t) {
                                                        progress.setVisibility(View.GONE);
                                                    }
                                                });


                                            } else {
                                                Toast.makeText(getContext(), "Invalid location", Toast.LENGTH_SHORT).show();
                                            }
                                        } else {
                                            Toast.makeText(getContext(), "Invalid no. of looms", Toast.LENGTH_SHORT).show();
                                        }

                                    } else {
                                        Toast.makeText(getContext(), "Invalid no. of workers", Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    Toast.makeText(getContext(), "Invalid home based unit", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(getContext(), "Invalid employment status", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(getContext(), "Invalid experience", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getContext(), "Invalid skill", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getContext(), "Invalid sector", Toast.LENGTH_SHORT).show();
                }

            }
        });

        setPrevious();

        return view;
    }


    void setPrevious() {
        employer.setText(SharePreferenceUtils.getInstance().getString("employer"));


        int gp = 0;
        for (int i = 0; i < sec.size(); i++) {
            if (SharePreferenceUtils.getInstance().getString("sector").equals(sec.get(i))) {
                gp = i;
            }
        }
        sector.setSelection(gp);


        int cp = 0;
        for (int i = 0; i < ski.size(); i++) {
            if (SharePreferenceUtils.getInstance().getString("skills").equals(ski.get(i))) {
                cp = i;
            }
        }
        skills.setSelection(cp);


        int rp = 0;
        for (int i = 0; i < exp.size(); i++) {
            if (SharePreferenceUtils.getInstance().getString("experience").equals(exp.get(i))) {
                rp = i;
            }
        }
        experience.setSelection(rp);


        int mp = 0;
        for (int i = 0; i < emp.size(); i++) {
            if (SharePreferenceUtils.getInstance().getString("employment").equals(emp.get(i))) {
                mp = i;
            }
        }
        employment.setSelection(mp);

        int ep = 0;
        for (int i = 0; i < hom.size(); i++) {
            if (SharePreferenceUtils.getInstance().getString("home").equals(hom.get(i))) {
                ep = i;
            }
        }
        home.setSelection(ep);

        int chp = 0;
        for (int i = 0; i < wor.size(); i++) {
            if (SharePreferenceUtils.getInstance().getString("workers").equals(wor.get(i))) {
                chp = i;
            }
        }
        workers.setSelection(chp);


        int bp = 0;
        for (int i = 0; i < wor.size(); i++) {
            if (SharePreferenceUtils.getInstance().getString("looms").equals(wor.get(i))) {
                bp = i;
            }
        }
        looms.setSelection(bp);

        int sp = 0;
        for (int i = 0; i < loc.size(); i++) {
            if (SharePreferenceUtils.getInstance().getString("location").equals(loc.get(i))) {
                sp = i;
            }
        }
        location.setSelection(sp);


    }


}
