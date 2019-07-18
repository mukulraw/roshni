package com.app.roshni;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.app.roshni.workerJobListPOJO.Datum;
import com.app.roshni.workerJobListPOJO.workerJobDetailBean;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class bottomBrand extends BottomSheetDialogFragment {

    TextView company , products , allJobs , certification , reg , person , phone , address , headOffice , manufacturing , factory , countries , workers , website , email;
    CircleImageView image;

    String co , pr , al , ce , re , pe , ph , ad , he , ma , fa , cou , wo , we , em , im  ,jid;

    ProgressBar progress;

    String bid;

    static bottomBrand newInstance() {

        return new bottomBrand();
    }



    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_layout , container , false);


        jid = getArguments().getString("jid");

        progress = view.findViewById(R.id.progressBar4);
        company = view.findViewById(R.id.textView30);
        products = view.findViewById(R.id.textView31);
        allJobs = view.findViewById(R.id.textView33);
        certification = view.findViewById(R.id.textView32);
        reg = view.findViewById(R.id.reg);
        person = view.findViewById(R.id.person);
        phone = view.findViewById(R.id.phone);
        address = view.findViewById(R.id.address);
        headOffice = view.findViewById(R.id.head_office);
        manufacturing = view.findViewById(R.id.manufacturing);
        factory = view.findViewById(R.id.factory);
        countries = view.findViewById(R.id.countries);
        workers = view.findViewById(R.id.workers);
        website = view.findViewById(R.id.website);
        email = view.findViewById(R.id.email);
        image = view.findViewById(R.id.imageView6);


        allJobs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (bid.length() > 0)
                {

                    Intent intent = new Intent(getContext() , WorkerJobByCompany.class);
                    intent.putExtra("bid" , bid);
                    startActivity(intent);

                }

            }
        });



        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        progress.setVisibility(View.VISIBLE);

        Bean b = (Bean) getActivity().getApplicationContext();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(b.baseurl)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AllApiIneterface cr = retrofit.create(AllApiIneterface.class);

        Call<workerJobDetailBean> call = cr.getJobDetailForWorker(SharePreferenceUtils.getInstance().getString("user_id"), jid);

        call.enqueue(new Callback<workerJobDetailBean>() {
            @Override
            public void onResponse(Call<workerJobDetailBean> call, Response<workerJobDetailBean> response) {


                if (response.body().getStatus().equals("1")) {

                    Datum item = response.body().getData();

                    DisplayImageOptions options = new DisplayImageOptions.Builder().cacheOnDisk(true).cacheInMemory(true).resetViewBeforeLoading(false).build();
                    ImageLoader loader = ImageLoader.getInstance();
                    loader.displayImage(item.getLogo() , image , options);

                    bid = item.getBrandId();

                    company.setText(item.getBrandName());
                    address.setText(item.getBrandStreet() + ", " + item.getBrandArea() + ", " + item.getBrandDistrict() + ", " + item.getBrandState() + "-" + item.getBrandPin());
                    products.setText(item.getProducts());

                    if (item.getCertification().equals("Yes"))
                    {
                        certification.setVisibility(View.VISIBLE);
                    }
                    else
                    {
                        certification.setVisibility(View.GONE);
                    }

                    reg.setText(item.getReg());
                    person.setText(item.getContactPerson());
                    phone.setText("+" + item.getPhone());
                    headOffice.setText(item.getPstreet() + ", " + item.getParea() + ", " + item.getPdistrict() + ", " + item.getPstate() + "-" + item.getPpin());
                    manufacturing.setText(item.getManufacturingUnits());
                    factory.setText(item.getFactoryOutlet());
                    countries.setText(item.getCountry());
                    workers.setText(item.getWorkers());
                    website.setText(item.getWebsite());
                    email.setText(item.getEmail());

                    /*preferred.setText(item.getPreferred());
                    location.setText(item.getLocation());
                    experience.setText(item.getExperience());
                    role.setText(item.getRole());
                    gender.setText(item.getGender());
                    education.setText(item.getEducation());
                    hours.setText(item.getHours());
                    salary.setText(item.getSalary());
                    stype.setText(item.getStype());
*/


                }


                progress.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<workerJobDetailBean> call, Throwable t) {
                progress.setVisibility(View.GONE);
            }
        });


    }

}
