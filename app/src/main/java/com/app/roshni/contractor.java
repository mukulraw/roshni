package com.app.roshni;

import android.app.Dialog;
import android.content.ContentUris;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.app.roshni.verifyPOJO.Data;
import com.app.roshni.verifyPOJO.verifyBean;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;
import mabbas007.tagsedittext.TagsEditText;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import static android.app.Activity.RESULT_OK;

public class contractor extends Fragment {

    private Spinner gender, establishment, experience, work, availability;

    private String gend, esta, expe, wtyp, avai;

    private EditText name, dob, business, cpin, cstate, cdistrict, carea, cstreet, ppin, pstate, pdistrict, parea, pstreet, home_based, employer, male, female , about;

    TagsEditText location;

    private CircleImageView image;

    CheckBox check;

    private Button upload, submit;

    private List<String> gen, est, exp, wty, ava;

    private Uri uri;
    private File f1;

    private boolean che = false;

    private LinearLayout permanent;

    private ProgressBar progress;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.contractor_layout, container, false);

        gen = new ArrayList<>();
        est = new ArrayList<>();
        exp = new ArrayList<>();
        wty = new ArrayList<>();
        ava = new ArrayList<>();

        name = view.findViewById(R.id.editText);
        dob = view.findViewById(R.id.dob);
        business = view.findViewById(R.id.business);
        cpin = view.findViewById(R.id.editText3);
        cstate = view.findViewById(R.id.editText4);
        cdistrict = view.findViewById(R.id.editText5);
        carea = view.findViewById(R.id.editText6);
        cstreet = view.findViewById(R.id.editText7);
        ppin = view.findViewById(R.id.editText20);
        pstate = view.findViewById(R.id.editText22);
        pdistrict = view.findViewById(R.id.editText24);
        parea = view.findViewById(R.id.editText26);
        pstreet = view.findViewById(R.id.editText28);
        home_based = view.findViewById(R.id.home_based);
        employer = view.findViewById(R.id.employer);
        progress = view.findViewById(R.id.progress);
        location = view.findViewById(R.id.location);
        male = view.findViewById(R.id.male);
        female = view.findViewById(R.id.female);
        about = view.findViewById(R.id.about);

        gen.add("Select one --- ");
        gen.add("Male");
        gen.add("Female");


        est.add("Select one --- ");
        est.add("1970");
        est.add("1971");
        est.add("1972");
        est.add("1973");
        est.add("1974");
        est.add("1975");
        est.add("1976");
        est.add("1977");
        est.add("1978");
        est.add("1979");
        est.add("1980");
        est.add("1981");
        est.add("1982");
        est.add("1983");
        est.add("1984");
        est.add("1985");
        est.add("1986");
        est.add("1987");
        est.add("1988");
        est.add("1989");
        est.add("1990");
        est.add("1991");
        est.add("1992");
        est.add("1993");
        est.add("1994");
        est.add("1995");
        est.add("1996");
        est.add("1997");
        est.add("1998");
        est.add("1999");
        est.add("2000");
        est.add("2001");
        est.add("2002");
        est.add("2003");
        est.add("2004");
        est.add("2005");
        est.add("2006");
        est.add("2007");
        est.add("2008");
        est.add("2009");
        est.add("2010");
        est.add("2011");
        est.add("2012");
        est.add("2013");
        est.add("2014");
        est.add("2015");
        est.add("2016");
        est.add("2017");
        est.add("2018");
        est.add("2019");


        exp.add("Select one --- ");
        exp.add("0 to 2 years");
        exp.add("3 to 5 years");
        exp.add("5 to 10 years");
        exp.add("more than 10 years");


        wty.add("Select one --- ");
        wty.add("Adda Work");
        wty.add("Fashion Jewelry");
        wty.add("Zari Work");
        wty.add("Embroidery");
        wty.add("Tassel Work");


        ava.add("Select one --- ");
        ava.add("Available");
        ava.add("Within a Month");
        ava.add("Within Two Months");


        permanent = view.findViewById(R.id.permanent);


        check = view.findViewById(R.id.check);

        image = view.findViewById(R.id.imageView3);

        upload = view.findViewById(R.id.button7);
        submit = view.findViewById(R.id.submit);


        gender = view.findViewById(R.id.gender);
        establishment = view.findViewById(R.id.establishment);
        experience = view.findViewById(R.id.experience);
        work = view.findViewById(R.id.work);
        availability = view.findViewById(R.id.availability);


        ArrayAdapter<String> adapter = new ArrayAdapter<>(Objects.requireNonNull(getContext()),
                R.layout.spinner_model, gen);

        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(getContext(),
                R.layout.spinner_model, est);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getContext(),
                R.layout.spinner_model, exp);

        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(getContext(),
                R.layout.spinner_model, wty);

        ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(getContext(),
                R.layout.spinner_model, ava);


        gender.setAdapter(adapter);
        establishment.setAdapter(adapter1);
        experience.setAdapter(adapter2);
        work.setAdapter(adapter3);
        availability.setAdapter(adapter4);


        gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if (i > 0) {
                    gend = gen.get(i);
                } else {
                    gend = "";
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        establishment.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i > 0) {
                    esta = est.get(i);
                } else {
                    esta = "";
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
                    expe = exp.get(i);
                } else {
                    expe = "";
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        work.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if (i > 0) {
                    wtyp = wty.get(i);
                } else {
                    wtyp = "";
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        availability.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if (i > 0) {
                    avai = ava.get(i);
                } else {
                    avai = "";
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if (b) {
                    che = true;
                    permanent.setVisibility(View.GONE);
                } else {
                    che = false;
                    permanent.setVisibility(View.VISIBLE);
                }

            }
        });

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final CharSequence[] items = {"Take Photo from Camera",
                        "Choose from Gallery",
                        "Cancel"};
                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(getActivity());
                builder.setTitle("Add Photo!");
                builder.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        if (items[item].equals("Take Photo from Camera")) {
                            final String dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/Folder/";
                            File newdir = new File(dir);
                            try {
                                newdir.mkdirs();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }


                            String file = dir + DateFormat.format("yyyy-MM-dd_hhmmss", new Date()).toString() + ".jpg";


                            f1 = new File(file);
                            try {
                                f1.createNewFile();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            uri = FileProvider.getUriForFile(Objects.requireNonNull(getContext()), BuildConfig.APPLICATION_ID + ".provider", f1);

                            Intent getpic = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            getpic.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                            getpic.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                            startActivityForResult(getpic, 1);
                        } else if (items[item].equals("Choose from Gallery")) {
                            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            startActivityForResult(intent, 2);
                        } else if (items[item].equals("Cancel")) {
                            dialog.dismiss();
                        }
                    }
                });
                builder.show();

            }
        });


        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Dialog dialog = new Dialog(Objects.requireNonNull(getActivity()));
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dob_popup);
                dialog.setCancelable(true);
                dialog.show();

                Button submit = dialog.findViewById(R.id.button11);
                final DatePicker dp = dialog.findViewById(R.id.view14);

                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String dd = dp.getDayOfMonth() + "-" + (dp.getMonth() + 1) + "-" + dp.getYear();

                        Log.d("dddd", dd);

                        dob.setText(dd);

                        dialog.dismiss();

                    }
                });

            }
        });


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String n = name.getText().toString();
                String d = dob.getText().toString();
                String b = business.getText().toString();

                String cp = cpin.getText().toString();
                String cs = cstate.getText().toString();
                String cd = cdistrict.getText().toString();
                String ca = carea.getText().toString();
                String cst = cstreet.getText().toString();
                String ab = about.getText().toString();

                String h = home_based.getText().toString();

                List<String> loc = location.getTags();
                String l = TextUtils.join(",", loc);


                String m = male.getText().toString();
                String f = female.getText().toString();
                String e = employer.getText().toString();


                String pp;
                String ps;
                String pd;
                String pa;
                String pst;

                if (che) {
                    pp = cp;
                    ps = cs;
                    pd = cd;
                    pa = ca;
                    pst = cst;
                } else {
                    pp = ppin.getText().toString();
                    ps = pstate.getText().toString();
                    pd = pdistrict.getText().toString();
                    pa = parea.getText().toString();
                    pst = pstreet.getText().toString();
                }


                if (n.length() > 0) {
                    if (d.length() > 0) {
                        if (gend.length() > 0) {
                            if (cp.length() > 0) {
                                if (cs.length() > 0) {
                                    if (cd.length() > 0) {
                                        if (ca.length() > 0) {
                                            if (cst.length() > 0) {
                                                if (pp.length() > 0) {
                                                    if (ps.length() > 0) {
                                                        if (pd.length() > 0) {
                                                            if (pa.length() > 0) {
                                                                if (pst.length() > 0) {


                                                                    if (h.length() > 0) {
                                                                        if (l.length() > 0) {
                                                                            if (m.length() > 0) {
                                                                                if (f.length() > 0) {
                                                                                    if (expe.length() > 0) {
                                                                                        if (wtyp.length() > 0) {
                                                                                            if (avai.length() > 0) {


                                                                                                MultipartBody.Part body = null;

                                                                                                try {

                                                                                                    RequestBody reqFile1 = RequestBody.create(MediaType.parse("multipart/form-data"), f1);
                                                                                                    body = MultipartBody.Part.createFormData("photo", f1.getName(), reqFile1);


                                                                                                } catch (Exception e1) {
                                                                                                    e1.printStackTrace();
                                                                                                }

                                                                                                progress.setVisibility(View.VISIBLE);

                                                                                                Bean b1 = (Bean) Objects.requireNonNull(getContext()).getApplicationContext();

                                                                                                Retrofit retrofit = new Retrofit.Builder()
                                                                                                        .baseUrl(b1.baseurl)
                                                                                                        .addConverterFactory(ScalarsConverterFactory.create())
                                                                                                        .addConverterFactory(GsonConverterFactory.create())
                                                                                                        .build();

                                                                                                AllApiIneterface cr = retrofit.create(AllApiIneterface.class);

                                                                                                Call<verifyBean> call = cr.update_contractor(
                                                                                                        SharePreferenceUtils.getInstance().getString("user_id"),
                                                                                                        n,
                                                                                                        d,
                                                                                                        gend,
                                                                                                        b,
                                                                                                        esta,
                                                                                                        cp,
                                                                                                        cs,
                                                                                                        cd,
                                                                                                        ca,
                                                                                                        cst,
                                                                                                        pp,
                                                                                                        ps,
                                                                                                        pd,
                                                                                                        pa,
                                                                                                        pst,
                                                                                                        h,
                                                                                                        l,
                                                                                                        m,
                                                                                                        f,
                                                                                                        expe,
                                                                                                        wtyp,
                                                                                                        avai,
                                                                                                        e,
                                                                                                        ab,
                                                                                                        body
                                                                                                );

                                                                                                call.enqueue(new Callback<verifyBean>() {
                                                                                                    @Override
                                                                                                    public void onResponse(Call<verifyBean> call, Response<verifyBean> response) {

                                                                                                        assert response.body() != null;
                                                                                                        if (response.body().getStatus().equals("1")) {
                                                                                                            Data item = response.body().getData();


                                                                                                            SharePreferenceUtils.getInstance().saveString("name", item.getName());
                                                                                                            SharePreferenceUtils.getInstance().saveString("photo", item.getPhoto());
                                                                                                            SharePreferenceUtils.getInstance().saveString("dob", item.getDob());
                                                                                                            SharePreferenceUtils.getInstance().saveString("gender", item.getGender());
                                                                                                            SharePreferenceUtils.getInstance().saveString("phone", item.getPhone());
                                                                                                            SharePreferenceUtils.getInstance().saveString("business_name", item.getBusiness_name());
                                                                                                            SharePreferenceUtils.getInstance().saveString("establishment_year", item.getEstablishment_year());
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
                                                                                                            SharePreferenceUtils.getInstance().saveString("home_units", item.getHome_units());
                                                                                                            SharePreferenceUtils.getInstance().saveString("home_location", item.getHome_location());
                                                                                                            SharePreferenceUtils.getInstance().saveString("workers_male", item.getWorkers_male());
                                                                                                            SharePreferenceUtils.getInstance().saveString("workers_female", item.getWorkers_female());
                                                                                                            SharePreferenceUtils.getInstance().saveString("work_type", item.getWork_type());
                                                                                                            SharePreferenceUtils.getInstance().saveString("availability", item.getAvailability());
                                                                                                            SharePreferenceUtils.getInstance().saveString("employer", item.getEmployer());
                                                                                                            SharePreferenceUtils.getInstance().saveString("experience", item.getExperience());
                                                                                                            SharePreferenceUtils.getInstance().saveString("about", item.getAbout());


                                                                                                            Intent registrationComplete = new Intent("photo");

                                                                                                            LocalBroadcastManager.getInstance(getContext()).sendBroadcast(registrationComplete);

                                                                                                            Intent intent = new Intent(getContext(), MainActivity3.class);
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
                                                                                                Toast.makeText(getActivity(), "Invalid availability", Toast.LENGTH_SHORT).show();
                                                                                            }
                                                                                        } else {
                                                                                            Toast.makeText(getActivity(), "Invalid work type", Toast.LENGTH_SHORT).show();
                                                                                        }
                                                                                    } else {
                                                                                        Toast.makeText(getActivity(), "Invalid experience", Toast.LENGTH_SHORT).show();
                                                                                    }
                                                                                } else {
                                                                                    Toast.makeText(getActivity(), "Invalid female workers", Toast.LENGTH_SHORT).show();
                                                                                }
                                                                            } else {
                                                                                Toast.makeText(getActivity(), "Invalid male workers", Toast.LENGTH_SHORT).show();
                                                                            }
                                                                        } else {
                                                                            Toast.makeText(getActivity(), "Invalid home based units lcoation", Toast.LENGTH_SHORT).show();
                                                                        }
                                                                    } else {
                                                                        Toast.makeText(getActivity(), "Invalid home based units", Toast.LENGTH_SHORT).show();
                                                                    }


                                                                } else {
                                                                    Toast.makeText(getContext(), "Invalid permanent street", Toast.LENGTH_SHORT).show();
                                                                }
                                                            } else {
                                                                Toast.makeText(getContext(), "Invalid permanent area", Toast.LENGTH_SHORT).show();
                                                            }
                                                        } else {
                                                            Toast.makeText(getContext(), "Invalid permanent district", Toast.LENGTH_SHORT).show();
                                                        }
                                                    } else {
                                                        Toast.makeText(getContext(), "Invalid permanent state", Toast.LENGTH_SHORT).show();
                                                    }
                                                } else {
                                                    Toast.makeText(getContext(), "Invalid permanent PIN", Toast.LENGTH_SHORT).show();
                                                }

                                            } else {
                                                Toast.makeText(getContext(), "Invalid current street", Toast.LENGTH_SHORT).show();
                                            }
                                        } else {
                                            Toast.makeText(getContext(), "Invalid current area", Toast.LENGTH_SHORT).show();
                                        }
                                    } else {
                                        Toast.makeText(getContext(), "Invalid current district", Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    Toast.makeText(getContext(), "Invalid current state", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(getContext(), "Invalid current PIN", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(getContext(), "Invalid gender", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getContext(), "Invalid D.O.B.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getContext(), "Invalid name", Toast.LENGTH_SHORT).show();
                }


            }
        });

        setPrevious();

        return view;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 2 && resultCode == RESULT_OK && null != data) {
            uri = data.getData();

            Log.d("uri", String.valueOf(uri));

            String ypath = getPath(getContext(), uri);
            assert ypath != null;
            f1 = new File(ypath);

            Log.d("path", ypath);


            ImageLoader loader = ImageLoader.getInstance();

            Bitmap bmp = loader.loadImageSync(String.valueOf(uri));

            Log.d("bitmap", String.valueOf(bmp));

            image.setImageBitmap(bmp);

        } else if (requestCode == 1 && resultCode == RESULT_OK) {
            image.setImageURI(uri);
        }


    }

    private static Bitmap decodeUriToBitmap(Context mContext, Uri sendUri) {
        Bitmap getBitmap = null;
        try {
            InputStream image_stream;
            try {
                image_stream = mContext.getContentResolver().openInputStream(sendUri);
                getBitmap = BitmapFactory.decodeStream(image_stream);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getBitmap;
    }


    private static String getPath(final Context context, final Uri uri) {

        // DocumentProvider
        if (DocumentsContract.isDocumentUri(context, uri)) {
            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }

                // TODO handle non-primary volumes
            }
            // DownloadsProvider
            else if (isDownloadsDocument(uri)) {

                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));

                return getDataColumn(context, contentUri, null, null);
            }
            // MediaProvider
            else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }

                final String selection = "_id=?";
                final String[] selectionArgs = new String[]{
                        split[1]
                };

                return getDataColumn(context, contentUri, selection, selectionArgs);
            }
        }
        // MediaStore (and general)
        else if ("content".equalsIgnoreCase(uri.getScheme())) {
            return getDataColumn(context, uri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }

        return null;
    }

    private static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    private static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    private static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    private static String getDataColumn(Context context, Uri uri, String selection,
                                        String[] selectionArgs) {

        final String column = "_data";
        final String[] projection = {
                column
        };
        try (Cursor cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
                null)) {
            if (cursor != null && cursor.moveToFirst()) {
                final int column_index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(column_index);
            }
        }
        return null;
    }


    private void setPrevious() {
        name.setText(SharePreferenceUtils.getInstance().getString("name"));
        DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisk(true).resetViewBeforeLoading(false).build();

        ImageLoader loader = ImageLoader.getInstance();
        loader.displayImage(SharePreferenceUtils.getInstance().getString("photo"), image, options);

        dob.setText(SharePreferenceUtils.getInstance().getString("dob"));
        business.setText(SharePreferenceUtils.getInstance().getString("business_name"));

        home_based.setText(SharePreferenceUtils.getInstance().getString("home_units"));

        String ppp = SharePreferenceUtils.getInstance().getString("home_location");


        location.setTags(ppp.split(","));


        employer.setText(SharePreferenceUtils.getInstance().getString("employer"));
        male.setText(SharePreferenceUtils.getInstance().getString("workers_male"));
        female.setText(SharePreferenceUtils.getInstance().getString("workers_female"));
        ppin.setText(SharePreferenceUtils.getInstance().getString("ppin"));
        pstate.setText(SharePreferenceUtils.getInstance().getString("pstate"));
        pdistrict.setText(SharePreferenceUtils.getInstance().getString("pdistrict"));
        parea.setText(SharePreferenceUtils.getInstance().getString("parea"));
        pstreet.setText(SharePreferenceUtils.getInstance().getString("pstreet"));
        cpin.setText(SharePreferenceUtils.getInstance().getString("cpin"));
        cstate.setText(SharePreferenceUtils.getInstance().getString("cstate"));
        cdistrict.setText(SharePreferenceUtils.getInstance().getString("cdistrict"));
        carea.setText(SharePreferenceUtils.getInstance().getString("carea"));
        cstreet.setText(SharePreferenceUtils.getInstance().getString("cstreet"));
        about.setText(SharePreferenceUtils.getInstance().getString("about"));


        int gp = 0;
        for (int i = 0; i < gen.size(); i++) {
            if (SharePreferenceUtils.getInstance().getString("gender").equals(gen.get(i))) {
                gp = i;
            }
        }
        gender.setSelection(gp);


        int ep = 0;
        for (int i = 0; i < est.size(); i++) {
            if (SharePreferenceUtils.getInstance().getString("establishment_year").equals(est.get(i))) {
                ep = i;
            }
        }
        establishment.setSelection(ep);

        int rp = 0;
        for (int i = 0; i < exp.size(); i++) {
            if (SharePreferenceUtils.getInstance().getString("experience").equals(exp.get(i))) {
                rp = i;
            }
        }
        experience.setSelection(rp);

        int wp = 0;
        for (int i = 0; i < wty.size(); i++) {
            if (SharePreferenceUtils.getInstance().getString("work_type").equals(wty.get(i))) {
                wp = i;
            }
        }
        work.setSelection(wp);


        int ap = 0;
        for (int i = 0; i < ava.size(); i++) {
            if (SharePreferenceUtils.getInstance().getString("availability").equals(ava.get(i))) {
                ap = i;
            }
        }
        availability.setSelection(ap);


    }


}
