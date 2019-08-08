package com.app.roshni;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.FileProvider;

import android.content.ContentUris;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.app.roshni.contractorJobDetailsPOJO.Data;
import com.app.roshni.contractorJobDetailsPOJO.contractorJobDetailsBean;
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

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class UpdateContractorJob extends AppCompatActivity {

    Toolbar toolbar;
    Button submit , upload;
    ProgressBar progress;
    Spinner type , experience , days;
    EditText rate;

    ImageView image;

    List<String> typ , exp , day;

    String ty , ex , da;

    private Uri uri;
    private File f1;

    String jid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_contractor_job);

        jid = getIntent().getStringExtra("jid");

        typ = new ArrayList<>();
        exp = new ArrayList<>();
        day = new ArrayList<>();

        toolbar = findViewById(R.id.toolbar);
        submit = findViewById(R.id.submit);
        progress = findViewById(R.id.progress);

        type = findViewById(R.id.type);
        experience = findViewById(R.id.experience);
        days = findViewById(R.id.days);
        rate = findViewById(R.id.rate);
        image = findViewById(R.id.image);
        upload = findViewById(R.id.upload);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });

        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setTitle("UPDATE JOB");

        exp.add("Select one --- ");
        exp.add("0 to 2 years");
        exp.add("3 to 5 years");
        exp.add("5 to 10 years");
        exp.add("more than 10 years");


        typ.add("Select one --- ");
        typ.add("Embroidery");
        typ.add("Adda-Work");
        typ.add("Fashion Jewelry");
        typ.add("Bead-Work");
        typ.add("Stone-Work");
        typ.add("Artificial Jewelry");


        ArrayAdapter<String> adapter = new ArrayAdapter<>(Objects.requireNonNull(this),
                R.layout.spinner_model, typ);


        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this,
                R.layout.spinner_model, exp);


        day.add("Select one --- ");

        for (int i = 1 ; i <=500 ; i++)
        {
            day.add(String.valueOf(i));
        }


        ArrayAdapter<String> adapter3 = new ArrayAdapter<>(this,
                R.layout.spinner_model, day);

        type.setAdapter(adapter);
        experience.setAdapter(adapter2);
        days.setAdapter(adapter3);


        type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if (i > 0) {
                    ty = typ.get(i);
                } else {
                    ty = "";
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
                    ex = exp.get(i);
                } else {
                    ex = "";
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        days.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if (i > 0) {
                    da = day.get(i);
                } else {
                    da = "";
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                String r = rate.getText().toString();

                if (ty.length() > 0)
                {
                    if (ex.length() > 0)
                    {
                        if (da.length() > 0)
                        {

                            MultipartBody.Part body = null;

                            try {

                                RequestBody reqFile1 = RequestBody.create(MediaType.parse("multipart/form-data"), f1);
                                body = MultipartBody.Part.createFormData("sample", f1.getName(), reqFile1);


                            } catch (Exception e1) {
                                e1.printStackTrace();
                            }

                            progress.setVisibility(View.VISIBLE);

                            Bean b = (Bean) getApplicationContext();

                            Retrofit retrofit = new Retrofit.Builder()
                                    .baseUrl(b.baseurl)
                                    .addConverterFactory(ScalarsConverterFactory.create())
                                    .addConverterFactory(GsonConverterFactory.create())
                                    .build();

                            AllApiIneterface cr = retrofit.create(AllApiIneterface.class);

                            Call<verifyBean> call = cr.UpdateContractorJob(
                                    jid,
                                    ty,
                                    ex,
                                    da,
                                    r,
                                    body
                            );

                            call.enqueue(new Callback<verifyBean>() {
                                @Override
                                public void onResponse(Call<verifyBean> call, Response<verifyBean> response) {

                                    if (response.body().getStatus().equals("1"))
                                    {
                                        Toast.makeText(UpdateContractorJob.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                                        finish();

                                    }


                                    progress.setVisibility(View.GONE);

                                }

                                @Override
                                public void onFailure(Call<verifyBean> call, Throwable t) {
                                    progress.setVisibility(View.GONE);
                                }
                            });


                        }
                        else
                        {
                            Toast.makeText(UpdateContractorJob.this, "Invalid days", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                    {
                        Toast.makeText(UpdateContractorJob.this, "Invalid experience", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(UpdateContractorJob.this, "Invalid job type", Toast.LENGTH_SHORT).show();
                }


            }
        });


        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final CharSequence[] items = {"Take Photo from Camera",
                        "Choose from Gallery",
                        "Cancel"};
                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(UpdateContractorJob.this);
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

                            uri = FileProvider.getUriForFile(Objects.requireNonNull(UpdateContractorJob.this), BuildConfig.APPLICATION_ID + ".provider", f1);

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

        setPrevious();

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 2 && resultCode == RESULT_OK && null != data) {
            uri = data.getData();

            Log.d("uri", String.valueOf(uri));

            String ypath = getPath(UpdateContractorJob.this, uri);
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

    void setPrevious()
    {

        progress.setVisibility(View.VISIBLE);

        Bean b = (Bean) getApplicationContext();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(b.baseurl)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AllApiIneterface cr = retrofit.create(AllApiIneterface.class);

        Call<contractorJobDetailsBean> call = cr.getJobDetailsForContractor(SharePreferenceUtils.getInstance().getString("user_id"), jid);

        call.enqueue(new Callback<contractorJobDetailsBean>() {
            @Override
            public void onResponse(Call<contractorJobDetailsBean> call, Response<contractorJobDetailsBean> response) {


                if (response.body().getStatus().equals("1")) {

                    Data item = response.body().getData();

                    DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisk(true).resetViewBeforeLoading(false).build();
                    ImageLoader loader = ImageLoader.getInstance();
                    loader.displayImage(item.getSample() , image , options);

                    rate.setText(item.getRate());


                    int cp = 0;
                    for (int i = 0; i < typ.size(); i++) {
                        if (item.getJobType().equals(typ.get(i))) {
                            cp = i;
                        }
                    }
                    type.setSelection(cp);

                    int cp1 = 0;
                    for (int i = 0; i < day.size(); i++) {
                        if (item.getDays().equals(day.get(i))) {
                            cp1 = i;
                        }
                    }
                    days.setSelection(cp1);



                    int gp = 0;
                    for (int i = 0 ; i < exp.size() ; i++)
                    {
                        if (item.getExperience().equals(exp.get(i)))
                        {
                            gp = i;
                        }
                    }
                    experience.setSelection(gp);





                }





                progress.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<contractorJobDetailsBean> call, Throwable t) {
                progress.setVisibility(View.GONE);
            }
        });

    }


}
