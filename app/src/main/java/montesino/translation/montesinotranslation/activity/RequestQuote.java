package montesino.translation.montesinotranslation.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.androidnetworking.interfaces.UploadProgressListener;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.scanlibrary.ScanActivity;
import com.scanlibrary.ScanConstants;
import com.scanlibrary.SharedPreferencesManager;
import com.scanlibrary.UploadImageListModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import montesino.translation.montesinotranslation.R;
import montesino.translation.montesinotranslation.api.ApiClient;
import montesino.translation.montesinotranslation.fragment.BackButtonHandlerInterface;
import montesino.translation.montesinotranslation.fragment.SubmitAQuoteFragment;
import montesino.translation.montesinotranslation.global.FileUtil;
import montesino.translation.montesinotranslation.model.LangModel;
import montesino.translation.montesinotranslation.response.LanguageResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class RequestQuote extends AppCompatActivity {
    EditText Firstname,lastName,emailAddress,customInstruction,uploadFile,chooseYourLanguage,chooseYourLanguage1,page_1;
    LinearLayout submit_layout;
    String emailPattern = "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
            "\\@" +

            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +

            "(" +

            "\\." +

            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +

            ")+";
    EditText first_name, last_name, email_address, phoneNumber, custom_instruction, upload_file, translate_to, searchBy;
    List<LangModel> langModel = new ArrayList<>();
    Dialog langDialog;
    LanguageAdapter languageAdapter;
    RecyclerView language_list;
    ImageView close;
    String selectLangName = "", selectLangId;
    private BackButtonHandlerInterface backButtonHandler;
    boolean doubleBackToExitPressedOnce = false;
    private static final int PERMISSION_REQUEST_CODE = 2011;
    private static final int MANAGE_REQUEST_CODE = 2091;
    private static final int ACTIVITY_REQUEST_CODE = 1000;
    private static final int ACTIVITY_REQUEST_GALLERY_CODE = 2000;
    private static final int REQUEST_CODE = 99;
    Uri imageUri;
    BottomSheetDialog sheetDialog;
    LinearLayout openCamera, gallery, cancel, back;
    List<UploadImageListModel> uploadImageListModelList;
    UploadFileAdapter uploadFileAdapter;
    RecyclerView uploadFileList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//       setContentView(R.layout.activity_request_quote);
         setContentView(R.layout.activity_request_quote_new);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        android.graphics.drawable.Drawable background = RequestQuote.this.getDrawable(R.drawable.screen_header);
        getWindow().setBackgroundDrawable(background);
        initialize();
    }
    private void initialize() {
//        Firstname = findViewById(R.id.Firstname);
//        lastName = findViewById(R.id.lastName);
//        emailAddress = findViewById(R.id.emailAddress);
//        phoneNumber = findViewById(R.id.phoneNumber);
//        customInstruction = findViewById(R.id.customInstruction);
//        uploadFile = findViewById(R.id.uploadFile);
//        chooseYourLanguage = findViewById(R.id.chooseYourLanguage);
//        chooseYourLanguage1 = findViewById(R.id.chooseYourLanguage1);
//        page_1 = findViewById(R.id.page_1);
//        submit_layout = findViewById(R.id.submit_layout);
//        submit_layout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                validate();
//            }
//        });
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        getLanguage();
        first_name = findViewById(R.id.first_name);
        last_name = findViewById(R.id.last_name);
        email_address = findViewById(R.id.email_address);
        phoneNumber = findViewById(R.id.phoneNumber);
        custom_instruction = findViewById(R.id.custom_instruction);
        upload_file = findViewById(R.id.upload_file);
        back = findViewById(R.id.back);
        upload_file.setFocusable(false);
        upload_file.setClickable(true);
        translate_to = findViewById(R.id.translate_to);
        translate_to.setFocusable(false);
        translate_to.setClickable(true);
        uploadFileList = findViewById(R.id.uploadFileList);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RequestQuote.this,Choice.class);
                startActivity(intent);
            }
        });
        upload_file.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ContextCompat.checkSelfPermission(RequestQuote.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
                    // ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, PERMISSION_REQUEST_CODE);
                    requestPermissions(new String[]{Manifest.permission.CAMERA}, PERMISSION_REQUEST_CODE);
                    return;
                } else {
                    showOptions();
                }
            }
        });

        translate_to.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLangList();
            }
        });
        SharedPreferencesManager.getInstance(RequestQuote.this).clearData();
    }
    private void showOptions() {
        sheetDialog = new BottomSheetDialog(RequestQuote.this, R.style.BottomSheetStyle);
        View choose = getLayoutInflater().inflate(R.layout.image_options, null);
        openCamera = choose.findViewById(R.id.openCamera);
        gallery = choose.findViewById(R.id.gallery);
        cancel = choose.findViewById(R.id.cancel);

        openCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //        openCameraReq();
                if(!selectLangName.equals("")) {
                    Intent intent = new Intent(RequestQuote.this, ScanActivity.class);
                    intent.putExtra(ScanConstants.OPEN_INTENT_PREFERENCE, ScanConstants.OPEN_CAMERA);
                    intent.putExtra(ScanConstants.LANGUAGE_INTENT_PREFERENCE, selectLangName);
                    startActivityForResult(intent, REQUEST_CODE);
                } else {
                    Toast.makeText(RequestQuote.this, ""+getResources().getString(R.string.lang_select), Toast.LENGTH_SHORT).show();
                    sheetDialog.dismiss();
                }
            }
        });

        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!selectLangName.equals("")) {
                    openGalleryReq();
//                    Intent intent = new Intent(getContext(), ScanActivity.class);
//                    intent.putExtra(ScanConstants.OPEN_INTENT_PREFERENCE, ScanConstants.OPEN_MEDIA);
//                    startActivityForResult(intent, REQUEST_CODE);
                } else {
                    Toast.makeText(RequestQuote.this, ""+getResources().getString(R.string.lang_select), Toast.LENGTH_SHORT).show();
                    sheetDialog.dismiss();
                }

            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sheetDialog.dismiss();
            }
        });

        sheetDialog.setContentView(choose);
        sheetDialog.show();
    }
    private void openGalleryReq() {
        if(ContextCompat.checkSelfPermission(RequestQuote.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            // ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, PERMISSION_REQUEST_CODE);
            requestPermissions(new String[]{Manifest.permission.CAMERA}, PERMISSION_REQUEST_CODE);
            return;
        } else {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("*/*");
            intent.putExtra("return-data", true);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            startActivityForResult(Intent.createChooser(intent, "Select Media"), ACTIVITY_REQUEST_GALLERY_CODE);
        }
    }
    private void openCameraReq() {
        if(ContextCompat.checkSelfPermission(RequestQuote.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            // ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, PERMISSION_REQUEST_CODE);
            requestPermissions(new String[]{Manifest.permission.CAMERA}, PERMISSION_REQUEST_CODE);
            return;
        } else {
            try {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                File imageFile = createImageFile();
                String imageFilePath = imageFile.getAbsolutePath();
                imageUri = FileProvider.getUriForFile(RequestQuote.this, getApplicationContext().getPackageName()+".provider", imageFile);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                startActivityForResult(intent, ACTIVITY_REQUEST_CODE);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }
    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";

        // Get the directory where the image file will be saved
        File storageDir = getApplicationContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES);

        // Create the image file
        File imageFile = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        return imageFile;
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.e("REQUEST CODE",""+requestCode);
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    showOptions();
                }
                break;
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK ) {
            Log.e("IMAGE PATH",""+imageUri);

            sheetDialog.dismiss();
        } else if(requestCode == ACTIVITY_REQUEST_GALLERY_CODE && resultCode == RESULT_OK && data != null) {
            ArrayList<File> imageLink = new ArrayList<>();
            try {
                File actualImage = FileUtil.from(getApplicationContext(), data.getData());
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), Uri.fromFile(actualImage));
                String fileImage = String.valueOf(actualImage);
                String file_extn = fileImage.substring(fileImage.lastIndexOf("/") + 1);
                Log.e("file_extn", file_extn);
                Log.e("IMAGE PATH",""+fileImage);
                Log.e("IMAGE BITMAP",""+bitmap);
                imageLink.add(new File(fileImage));
                // uploadImageListModelList = SharedPreferencesManager.getImageUploadArrayList(getContext());



                // progress_layout.setVisibility(View.VISIBLE);
                //  progressBar.getIndeterminateDrawable().setColorFilter(
//                    getResources().getColor(com.scanlibrary.R.color.header_color),
//                    android.graphics.PorterDuff.Mode.SRC_IN);
                AndroidNetworking.upload(ApiClient.BASE_URL+"upload-files")
                        .addMultipartFileList("files[]",imageLink)
                        .addMultipartParameter("langName",selectLangName)
                        .setPriority(Priority.HIGH)
                        .build()
                        .setUploadProgressListener(new UploadProgressListener() {
                            @Override
                            public void onProgress(long bytesUploaded, long totalBytes) {
                                int percentUploaded = (int) ((bytesUploaded * 100) / totalBytes);
                                Log.e("rex", percentUploaded+"");
                                // progressBar.setProgress(percentUploaded);
                                Log.e("rex", "onProgress: called..." );
                            }
                        }).getAsJSONObject(new JSONObjectRequestListener() {

                            @Override
                            public void onResponse(JSONObject response) {
                                Log.e("RESPONSE FOR FILE UPLOAD",""+response.toString());
                                if(uploadImageListModelList == null) {
                                    uploadImageListModelList = new ArrayList<UploadImageListModel>();
                                }
                                Log.e("TEST MODEL DEFINE",""+uploadImageListModelList);
                                try {
                                    JSONArray dataArray = response.getJSONArray("data");
                                    for(int k = 0; k < dataArray.length(); k++) {
                                        JSONObject jsonObject = dataArray.getJSONObject(k);
                                        int id = jsonObject.getInt("id");
                                        String name = jsonObject.getString("name");
                                        String image_type = jsonObject.getString("image_type");
                                        String file_size = jsonObject.getString("file_size");
                                        String file_path = jsonObject.getString("file_path");
                                        int words = jsonObject.getInt("words");
                                        String translate_from = jsonObject.getString("translate_from");
                                        String translate_to = jsonObject.getString("translate_to");
                                        int status = jsonObject.getInt("status");
                                        uploadImageListModelList.add(new UploadImageListModel(id, name,  image_type, file_size, file_path, words, translate_from, translate_to, status));
                                    }
                                    SharedPreferencesManager.saveImageUploadArrayList(getApplicationContext(), uploadImageListModelList);
                                    reCallAdapter();
                                } catch (JSONException e) {
                                    throw new RuntimeException(e);
                                }
                                // progress_layout.setVisibility(View.GONE);
                            }

                            @Override
                            public void onError(ANError anError) {
                                Log.e("response", ""+anError.toString());
                            }
                        });
            } catch (IOException e) {
                e.printStackTrace();
            }
            sheetDialog.dismiss();
        } else {
            sheetDialog.dismiss();
        }
    }
    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }
    public String getRealPathFromURII(Uri uri) {
        String path = "";
        if (getApplicationContext().getContentResolver() != null) {
            Cursor cursor = getApplicationContext().getContentResolver().query(uri, null, null, null, null);
            if (cursor != null) {
                cursor.moveToFirst();
                int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                path = cursor.getString(idx);
                cursor.close();
            }
        }
        return path;
    }
    public String getPath(Uri uri) {
        Cursor cursor = getApplicationContext().getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        String document_id = cursor.getString(0);
        document_id = document_id.substring(document_id.lastIndexOf(":") + 1);
        cursor.close();
        cursor = getApplicationContext().getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                null, MediaStore.Images.Media._ID + " = ? ", new String[]{document_id}, null);

        cursor.moveToFirst();
        if(cursor.getCount() > 0){
            @SuppressLint("Range") String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            cursor.close();
            return path;
        }
        return null;
    }
    private void openLangList() {
        langDialog = new Dialog(RequestQuote.this);
        langDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        langDialog.setContentView(R.layout.lang_spinner);
        langDialog.getWindow().setBackgroundDrawable(new ColorDrawable((Color.TRANSPARENT)));

        language_list =langDialog.findViewById(R.id.language_list);
        close = langDialog.findViewById(R.id.close);
        searchBy = langDialog.findViewById(R.id.searchBy);

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                langDialog.dismiss();
            }
        });

        searchBy.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(langModel.size() > 0) {
                    languageAdapter.getFilter().filter(s);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        languageAdapter=new LanguageAdapter(langModel, getApplicationContext(), "quote");
        language_list.setAdapter(languageAdapter);
        language_list.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        languageAdapter.notifyDataSetChanged();

        langDialog.show();
    }
    private void getLanguage() {
        langModel.clear();
        Call<LanguageResponse> lang = ApiClient.getInstance().getMontesinoApi().getLang();
        lang.enqueue(new Callback<LanguageResponse>() {
            @Override
            public void onResponse(Call<LanguageResponse> call, Response<LanguageResponse> response) {
                if(response.body().getStatusCode() == 200) {
                    for(int i = 0; i < response.body().getData().size(); i++) {
                        langModel.add(new LangModel(
                                response.body().getData().get(i).getId(),
                                response.body().getData().get(i).getName()
                        ));
                    }

                }
            }
            @Override
            public void onFailure(Call<LanguageResponse> call, Throwable t) {

            }
        });
    }
    public void setLanguage(String langName, String langId) {
        selectLangName = langName;
        selectLangId = langId;
        translate_to.setText(langName);
        langDialog.dismiss();
    }
    public class LanguageAdapter extends  RecyclerView.Adapter<LanguageAdapter.ViewHolder> {

        List<LangModel> langList;
        Context context;
        String mode;
        List<LangModel> langFilterList;
        public LanguageAdapter(List<LangModel> langList, Context context, String mode) {
            this.langList = langList;
            this.context = context;
            this.mode = mode;
            this.langFilterList = langList;
        }

        @NonNull
        @Override
        public LanguageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.translate_to_row, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull LanguageAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
            holder.lang_rec.setText(langList.get(position).getLangName());

            holder.lang_rec.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mode.equals("quote")) {

                        setLanguage(langList.get(position).getLangName(), langList.get(position).getId());
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return langList.size();
        }

        public Filter getFilter() {
            Filter filter = new Filter() {
                @Override
                protected FilterResults performFiltering(CharSequence charSequence) {
                    FilterResults filterResults = new FilterResults();
                    if(charSequence == null || charSequence.length() == 0) {
                        filterResults.values = langFilterList;
                        filterResults.count = langFilterList.size();
                    } else {
                        String search = charSequence.toString().toLowerCase();
                        List<LangModel> lList = new ArrayList<>();
                        for(LangModel langLst: langFilterList) {
                            if(langLst.getLangName().toLowerCase().contains(search)) {
                                lList.add(langLst);
                            }
                        }
                        filterResults.values = lList;
                        filterResults.count = lList.size();
                    }
                    return filterResults;
                }
                @Override
                protected void publishResults(CharSequence constraint, FilterResults results) {
                    langList =(List<LangModel>)results.values;
                    notifyDataSetChanged();
                }
            };
            return filter;
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            TextView lang_rec;
            public ViewHolder(@NonNull View itemView) {
                super(itemView);

                lang_rec = itemView.findViewById(R.id.lang_rec);
            }
        }
    }
    public class UploadFileAdapter extends RecyclerView.Adapter<UploadFileAdapter.ViewHolder> {
        List<UploadImageListModel> imageList;
        Context context;

        public UploadFileAdapter(List<UploadImageListModel> imageList, Context context) {
            this.imageList = imageList;
            this.context = context;
        }

        @NonNull
        @Override
        public UploadFileAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.uploaded_file, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull UploadFileAdapter.ViewHolder holder, int position) {
            holder.file_name.setText(imageList.get(position).getName());
            if(imageList.get(position).getImage_type().equals("PDF")) {
                holder.file_type.setBackground(context.getResources().getDrawable(R.drawable.pdf_viewer));
            } else if ((imageList.get(position).getImage_type().equals("JPG")) || imageList.get(position).getImage_type().equals("JPEG") || imageList.get(position).getImage_type().equals("PNG") || imageList.get(position).getImage_type().equals("WEBP")) {
                holder.file_type.setBackground(context.getResources().getDrawable(R.drawable.image_viewer));
            } else if ((imageList.get(position).getImage_type().equals("XLS")) || imageList.get(position).getImage_type().equals("XLSX") || imageList.get(position).getImage_type().equals("CSV")) {
                holder.file_type.setBackground(context.getResources().getDrawable(R.drawable.excel_viewer));
            } else if ((imageList.get(position).getImage_type().equals("DOC")) || imageList.get(position).getImage_type().equals("DOCX")) {
                holder.file_type.setBackground(context.getResources().getDrawable(R.drawable.doc_viewer));
            } else {
                holder.file_type.setBackground(context.getResources().getDrawable(R.drawable.unknown_viewer));
            }
            Log.e("Image Text Size",""+imageList.get(position).getImage_type());
            holder.file_size.setText(context.getResources().getString(R.string.file_size) + " " + imageList.get(position).getFile_size());
            holder.word_count.setText(context.getResources().getString(R.string.word_count) + " " + imageList.get(position).getWords());
            holder.translateFrom_text.setText(imageList.get(position).getTranslate_from());
            holder.translateTo_text.setText(imageList.get(position).getTranslate_to());
        }

        @Override
        public int getItemCount() {
            return imageList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            ImageView file_type, view, remove;
            TextView file_name, file_size, word_count, translateFrom_text, translateTo_text;
            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                file_type = itemView.findViewById(R.id.file_type);
                view = itemView.findViewById(R.id.view);
                remove = itemView.findViewById(R.id.remove);
                file_name = itemView.findViewById(R.id.file_name);
                file_size = itemView.findViewById(R.id.file_size);
                word_count = itemView.findViewById(R.id.word_count);
                translateFrom_text = itemView.findViewById(R.id.translateFrom_text);
                translateTo_text = itemView.findViewById(R.id.translateTo_text);
            }
        }
    }
    public void reCallAdapter() {
        uploadImageListModelList = SharedPreferencesManager.getImageUploadArrayList(getApplicationContext());
        if(uploadImageListModelList != null) {
            Log.e("ARRAY LIST SIZE",""+ uploadImageListModelList.size());
            uploadFileAdapter = new UploadFileAdapter(uploadImageListModelList, getApplicationContext());
            uploadFileList.setAdapter(uploadFileAdapter);
            uploadFileList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            uploadFileAdapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        reCallAdapter();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
    //
//    private void validate() {
//        if(Firstname.getText().toString().equals("")) {
//            Firstname.setError(getResources().getString(R.string.firstName));
//        } else if (lastName.getText().toString().equals("")) {
//            lastName.setError(getResources().getString(R.string.lastName));
//        } else if(!emailAddress.getText().toString().matches(emailPattern)) {
//            emailAddress.setError(getResources().getString(R.string.email));
//        } else if (phoneNumber.getText().toString().equals("")) {
//            phoneNumber.setError(getResources().getString(R.string.contact_number));
//        }else if (customInstruction.getText().toString().equals("")) {
//            customInstruction.setError(getResources().getString(R.string.custom_Instruction));
//        }else if (uploadFile.getText().toString().equals("")) {
//            uploadFile.setError(getResources().getString(R.string.upload_File));
//        }else if (chooseYourLanguage.getText().toString().equals("")) {
//            chooseYourLanguage.setError(getResources().getString(R.string.choose_Your_Language));
//        }else if (chooseYourLanguage1.getText().toString().equals("")) {
//            chooseYourLanguage1.setError(getResources().getString(R.string.choose_Your_Language));
//        }else if (page_1.getText().toString().equals("")) {
//            page_1.setError(getResources().getString(R.string.page_1));
//        }else {
//            Toast.makeText(this, "Api Implement Pending", Toast.LENGTH_SHORT).show();
//        }
//    }

}