package com.scanlibrary;

import static android.app.Activity.RESULT_OK;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.OpenableColumns;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.androidnetworking.interfaces.UploadProgressListener;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ResultFragment extends Fragment {

    private View view;
    private ImageView scannedImageView;
    private Button doneButton;
    private Bitmap original;
    LinearLayout originalButton, MagicColorButton, grayModeButton, bwButton, add_new, openCamera, gallery, cancel, retake_photo, re_crop, finish_scan;
    private Bitmap transformed;
    private Uri transformedUri;
    private Uri currentUri;
    RecyclerView imageList;
    int position = 0;
    private static ProgressDialogFragment progressDialogFragment;
    List<ImageListModel> imageListModels = new ArrayList<>();
    ImageListAdapter imageListAdapter;
    BottomSheetDialog sheetDialog;
    private static final int REQUEST_CODE = 99;
    int imgPos;
    ProgressBar progressBar;
    RelativeLayout progress_layout;
    List<UploadImageListModel> uploadImageListModels;
    public ResultFragment() {
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.result_layout, null);
        init();
        return view;
    }

    private void init() {
        originalButton = (LinearLayout) view.findViewById(R.id.original);
        originalButton.setOnClickListener(new OriginalButtonClickListener());
        MagicColorButton = (LinearLayout) view.findViewById(R.id.magicColor);
        MagicColorButton.setOnClickListener(new MagicColorButtonClickListener());
        grayModeButton = (LinearLayout) view.findViewById(R.id.grayMode);
        grayModeButton.setOnClickListener(new GrayButtonClickListener());
        bwButton = (LinearLayout) view.findViewById(R.id.BWMode);
        imageList = (RecyclerView)view.findViewById(R.id.imageList);
        bwButton.setOnClickListener(new BWButtonClickListener());
        getUri();
        doneButton = (Button) view.findViewById(R.id.doneButton);
        doneButton.setOnClickListener(new DoneButtonClickListener());
        add_new = (LinearLayout) view.findViewById(R.id.add_new);
        add_new.setOnClickListener(new AddNewClickListener());
        retake_photo = (LinearLayout) view.findViewById(R.id.retake_photo);
        retake_photo.setOnClickListener(new RetakeListener());
        re_crop = (LinearLayout) view.findViewById(R.id.re_crop);
        re_crop.setOnClickListener(new ReCropListener());
        finish_scan = (LinearLayout) view.findViewById(R.id.finish_scan);
        finish_scan.setOnClickListener(new FinishScan());
        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        progress_layout = (RelativeLayout) view.findViewById(R.id.progress_layout);
        imageList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    position = getCurrentItem();
                }
            }
        });
        uploadImageListModels = SharedPreferencesManager.getImageUploadArrayList(getContext());
        if(uploadImageListModels == null) {
            uploadImageListModels = new ArrayList<>();
        }

    }

    private String getIntentPreference() {
        String langPreference = getArguments().getString(ScanConstants.LANGUAGE_INTENT_PREFERENCE, "");
        return langPreference;
    }

    private int getCurrentItem() {
        return ((LinearLayoutManager) imageList.getLayoutManager()).findFirstVisibleItemPosition();
    }

    private Uri getUri() {
        Uri uri = getArguments().getParcelable(ScanConstants.SCANNED_RESULT);
        if(getArguments().getInt("retake") >= 0) {
            imgPos = getArguments().getInt("retake");
        }
        ArrayList<ImageListModel> retrievedArrayList = SharedPreferencesManager.getArrayList(getContext());
        if (retrievedArrayList != null) {
            for(int i = 0; i < retrievedArrayList.size(); i++) {
                if(getArguments().getInt("retake") < 0) {
                    imgPos = i;
                }
                imageListModels.add(
                        new ImageListModel(retrievedArrayList.get(i).getOriginalUri(), retrievedArrayList.get(i).getOriginalLink(), "", "", "", retrievedArrayList.get(i).getOriginalLink()));
            }
            if(imageListModels.size() > 0) {
                Log.e("Img POS",""+imgPos);
                imageListAdapter = new ImageListAdapter(getContext(), imageListModels);
                imageList.setAdapter(imageListAdapter);
                imageList.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
                imageList.smoothScrollToPosition(imgPos);
                LinearSnapHelper linearSnapHelper = new SwipeItemTouchHelper();
                linearSnapHelper.attachToRecyclerView(imageList);
                imageListAdapter.notifyDataSetChanged();
            }

        } else {
        }

        return uri;
    }

    private class FinishScan implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            ArrayList<File> imageLink = new ArrayList<>();
            for(int i = 0 ; i < imageListModels.size(); i++) {
                Log.e("FILE PATH",""+imageListModels.get(i).getShowLink());
                imageLink.add(new File(imageListModels.get(i).getShowLink()));
            }

            progress_layout.setVisibility(View.VISIBLE);
            progressBar.getIndeterminateDrawable().setColorFilter(
                    getResources().getColor(R.color.header_color),
                    android.graphics.PorterDuff.Mode.SRC_IN);
            AndroidNetworking.upload(ApiClient.BASE_URL+"upload-files")
                    .addMultipartFileList("files[]",imageLink)
                    .addMultipartParameter("langName",getIntentPreference())
                    .addMultipartParameter("source","camera")
                    .setPriority(Priority.HIGH)
                    .build()
                    .setUploadProgressListener(new UploadProgressListener() {
                        @Override
                        public void onProgress(long bytesUploaded, long totalBytes) {
                            int percentUploaded = (int) ((bytesUploaded * 100) / totalBytes);
                            Log.e("rex", percentUploaded+"");
                            progressBar.setProgress(percentUploaded);
                            Log.e("rex", "onProgress: called..." );
                        }
                    }).getAsJSONObject(new JSONObjectRequestListener() {

                        @Override
                        public void onResponse(JSONObject response) {
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
                                    Log.e("STATUS",""+status);
                                    uploadImageListModels.add(new UploadImageListModel(id, name,  image_type, file_size, file_path, words, translate_from, translate_to, status));
                                }
                                SharedPreferencesManager.saveImageUploadArrayList(getContext(), uploadImageListModels);
                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }
                            progress_layout.setVisibility(View.GONE);
                            getActivity().finish();
                        }

                        @Override
                        public void onError(ANError anError) {
                            Log.e("response", ""+anError.toString());
                        }
                    });
        }
    }

    @SuppressLint("Range")
    public String getFileName(Uri uri) {
        String result = null;
        if (uri.getScheme().equals("content")) {
            Cursor cursor = getContext().getContentResolver().query(uri, null, null, null, null);
            try {
                if (cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            } finally {
                cursor.close();
            }
        }
        if (result == null) {
            result = uri.getPath();
            int cut = result.lastIndexOf('/');
            if (cut != -1) {
                result = result.substring(cut + 1);
            }
        }
        return result;
    }

    private class AddNewClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            PickImageFragment fragment = new PickImageFragment();
            Bundle bundle = new Bundle();
            bundle.putInt(ScanConstants.OPEN_INTENT_PREFERENCE, ScanConstants.OPEN_CAMERA);
            bundle.putInt("retake", -1);
            fragment.setArguments(bundle);
            android.app.FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.content, fragment);
            fragmentTransaction.commit();
           // showOptions("add");
        }
    }

    private class RetakeListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
           // showOptions("retake");
            PickImageFragment fragment = new PickImageFragment();
            Bundle bundle = new Bundle();
            bundle.putInt(ScanConstants.OPEN_INTENT_PREFERENCE, ScanConstants.OPEN_CAMERA);
            bundle.putInt("retake", position);
            fragment.setArguments(bundle);
            android.app.FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.content, fragment);
            fragmentTransaction.commit();
        }
    }

    private class ReCropListener implements  View.OnClickListener {
        @Override
        public void onClick(View v) {
            reCrop();
        }
    }

    private void showOptions(String mode) {
        sheetDialog = new BottomSheetDialog(getContext(), R.style.BottomSheetStyle);
        View choose = getActivity().getLayoutInflater().inflate(R.layout.image_options, null);
        openCamera = choose.findViewById(R.id.openCamera);
        gallery = choose.findViewById(R.id.gallery);
        cancel = choose.findViewById(R.id.cancel);

        openCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("MODE",""+mode);
                if(mode.equals("add")) {
                    PickImageFragment fragment = new PickImageFragment();
                    Bundle bundle = new Bundle();
                    bundle.putInt(ScanConstants.OPEN_INTENT_PREFERENCE, ScanConstants.OPEN_CAMERA);
                    bundle.putInt("retake", -1);
                    fragment.setArguments(bundle);
                    android.app.FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.content, fragment);
                    fragmentTransaction.commit();
                    sheetDialog.dismiss();
                } else {
                    PickImageFragment fragment = new PickImageFragment();
                    Bundle bundle = new Bundle();
                    bundle.putInt(ScanConstants.OPEN_INTENT_PREFERENCE, ScanConstants.OPEN_CAMERA);
                    bundle.putInt("retake", position);
                    fragment.setArguments(bundle);
                    android.app.FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.content, fragment);
                    fragmentTransaction.commit();
                    sheetDialog.dismiss();
                }
            }
        });

        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mode.equals("add")) {
                    PickImageFragment fragment = new PickImageFragment();
                    Bundle bundle = new Bundle();
                    bundle.putInt(ScanConstants.OPEN_INTENT_PREFERENCE,  ScanConstants.OPEN_MEDIA);
                    bundle.putInt("retake",  -1);
                    fragment.setArguments(bundle);
                    android.app.FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.content, fragment);
                    fragmentTransaction.commit();
                    sheetDialog.dismiss();
                } else {
                    PickImageFragment fragment = new PickImageFragment();
                    Bundle bundle = new Bundle();
                    bundle.putInt(ScanConstants.OPEN_INTENT_PREFERENCE,  ScanConstants.OPEN_MEDIA);
                    bundle.putInt("retake",  position);
                    fragment.setArguments(bundle);
                    android.app.FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.content, fragment);
                    fragmentTransaction.commit();
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

    private void reCrop() {
        Log.e("IMAGE LINK",""+imageListModels.get(position).getOriginalUri());
        Log.e("IMAGE Position",""+position);
        ScanFragment fragment = new ScanFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(ScanConstants.SELECTED_BITMAP, Uri.parse(imageListModels.get(position).getOriginalUri()));
        bundle.putInt("retake",  position);
        fragment.setArguments(bundle);
        android.app.FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content, fragment);
        fragmentTransaction.addToBackStack(ScanFragment.class.toString());
        fragmentTransaction.commit();
    }

    private class DoneButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            showProgressDialog(getResources().getString(R.string.loading));
            AsyncTask.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Intent data = new Intent();
                        Bitmap bitmap = transformed;
                        if (bitmap == null) {
                            bitmap = original;
                        }
                        Uri uri = Utils.getUri(getActivity(), bitmap);
                        Log.e("URIs",""+uri);
                        data.putExtra(ScanConstants.SCANNED_RESULT, uri);
                        getActivity().setResult(RESULT_OK, data);
                        original.recycle();
                        System.gc();
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                dismissDialog();
                                getActivity().finish();
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
    //Working
    private class BWButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(final View v) {
            Bitmap bitmap = BitmapFactory.decodeFile(imageListModels.get(position).getOriginalLink());
            showProgressDialog(getResources().getString(R.string.applying_filter));
            AsyncTask.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        if(imageListModels.get(position).getBwLink().equals("")) {
                            transformed = ((ScanActivity) getActivity()).getBWBitmap(bitmap);
                            transformedUri = Uri.parse(saveBitmapToCache(transformed, "BWBScale"));
                        } else {
                            transformedUri = Uri.parse(imageListModels.get(position).getBwLink());
                        }
                    } catch (final OutOfMemoryError e) {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                transformed = original;
                                scannedImageView.setImageBitmap(original);
                                e.printStackTrace();
                                dismissDialog();
                                onClick(v);
                            }
                        });
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            String originalLink = imageListModels.get(position).getOriginalLink();
                            String grayScaleLink = imageListModels.get(position).getGreyScaleLink();
                            String magicLink = imageListModels.get(position).getMagicColorLink();
                            String originalUri = imageListModels.get(position).getOriginalUri();
                            ImageListModel snapImg = new ImageListModel(originalUri, originalLink,magicLink,grayScaleLink,String.valueOf(transformedUri), String.valueOf(transformedUri));
                            imageListModels.set(position, snapImg);
                            imageListAdapter.notifyItemChanged(position);
                            dismissDialog();
                        }
                    });
                }
            });
        }
    }
    //Working
    private class MagicColorButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(final View v) {
            Bitmap bitmap = BitmapFactory.decodeFile(imageListModels.get(position).getOriginalLink());
            showProgressDialog(getResources().getString(R.string.applying_filter));
            AsyncTask.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        if(imageListModels.get(position).getMagicColorLink().equals("")) {
                            transformed = ((ScanActivity) getActivity()).getMagicColorBitmap(bitmap);
                            transformedUri = Uri.parse(saveBitmapToCache(transformed, "MagicScale"));
                        } else {
                            transformedUri = Uri.parse(imageListModels.get(position).getMagicColorLink());
                        }
                    } catch (final OutOfMemoryError e) {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                transformed = original;
                                //scannedImageView.setImageBitmap(original);
                                e.printStackTrace();
                                dismissDialog();
                                onClick(v);
                            }
                        });
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            String originalLink = imageListModels.get(position).getOriginalLink();
                            String grayScaleLink = imageListModels.get(position).getGreyScaleLink();
                            String bwLink = imageListModels.get(position).getBwLink();
                            String originalUri = imageListModels.get(position).getOriginalUri();
                            ImageListModel snapImg = new ImageListModel(originalUri,originalLink,String.valueOf(transformedUri),grayScaleLink,bwLink, String.valueOf(transformedUri));
                            imageListModels.set(position, snapImg);
                            imageListAdapter.notifyItemChanged(position);
                            dismissDialog();
                        }
                    });
                }
            });
        }
    }
    //Working
    private class OriginalButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            try {
                showProgressDialog(getResources().getString(R.string.applying_filter));
                transformedUri = Uri.parse(imageListModels.get(position).getOriginalLink());
                String magicColorLink = imageListModels.get(position).getMagicColorLink();
                String gryScaleLink = imageListModels.get(position).getGreyScaleLink();
                String bwLink = imageListModels.get(position).getBwLink();
                String originalUri = imageListModels.get(position).getOriginalUri();
                ImageListModel snapImg = new ImageListModel(originalUri, String.valueOf(transformedUri), magicColorLink, gryScaleLink, bwLink, String.valueOf(transformedUri));
                imageListModels.set(position, snapImg);
                imageListAdapter.notifyItemChanged(position);
                dismissDialog();
            } catch (OutOfMemoryError e) {
                e.printStackTrace();
                dismissDialog();
            }
        }
    }
    //Working
    private class GrayButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(final View v) {
            Bitmap bitmap = BitmapFactory.decodeFile(imageListModels.get(position).getOriginalLink());
            showProgressDialog(getResources().getString(R.string.applying_filter));
            AsyncTask.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        if(imageListModels.get(position).getGreyScaleLink().equals("")) {
                            transformed = ((ScanActivity) getActivity()).getGrayBitmap(bitmap);
                            transformedUri = Uri.parse(saveBitmapToCache(transformed, "GreyScale"));
                        } else {
                            transformedUri = Uri.parse(imageListModels.get(position).getGreyScaleLink());
                        }

                    } catch (final OutOfMemoryError e) {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                transformed = original;
                               // scannedImageView.setImageBitmap(original);
                                e.printStackTrace();
                                dismissDialog();
                                onClick(v);
                            }
                        });
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            String originalLink = imageListModels.get(position).getOriginalLink();
                            String magicColorLink = imageListModels.get(position).getMagicColorLink();
                            String bwLink = imageListModels.get(position).getBwLink();
                            String originalUri = imageListModels.get(position).getOriginalUri();
                            ImageListModel snapImg = new ImageListModel(originalUri, originalLink,magicColorLink,String.valueOf(transformedUri),bwLink, String.valueOf(transformedUri));
                            imageListModels.set(position, snapImg);
                            imageListAdapter.notifyItemChanged(position);
                            dismissDialog();
                        }
                    });
                }
            });
        }
    }

    public String saveBitmapToCache(Bitmap bitmap, String mode) throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        String file_name = "Mon_Snap_" + timeStamp + ".jpg";
        File directory = null;
        if(mode.equals("GreyScale")) {
           directory = new File(getContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES).toString(), "SnapsGrey");
        } else if (mode.equals("MagicScale")) {
            directory = new File(getContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES).toString(), "SnapsMagic");
        } else {
            directory = new File(getContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES).toString(), "SnapsBW");
        }
        if (!directory.exists()) {
            if (!directory.mkdirs()) {
                return null;
            }
        }
        File imageFile = new File(directory, file_name);
        OutputStream out = new FileOutputStream(imageFile);
        bitmap.compress(Bitmap.CompressFormat.JPEG, (int)100, out);
        out.flush();
        out.close();
        return imageFile.getAbsolutePath();
    }
    protected synchronized void showProgressDialog(String message) {
        if (progressDialogFragment != null && progressDialogFragment.isVisible()) {
            // Before creating another loading dialog, close all opened loading dialogs (if any)
            progressDialogFragment.dismissAllowingStateLoss();
        }
        progressDialogFragment = null;
        progressDialogFragment = new ProgressDialogFragment(message);
        FragmentManager fm = getFragmentManager();
        progressDialogFragment.show(fm, ProgressDialogFragment.class.toString());
    }
    protected synchronized void dismissDialog() {
        progressDialogFragment.dismissAllowingStateLoss();
    }

    public class ImageListAdapter extends RecyclerView.Adapter<ImageListAdapter.ImageListViewHolder> {
        private Context mCtx;
        private List<ImageListModel> imageListModels;
        public ImageListAdapter(Context mCtx, List<ImageListModel> imageListModels) {
            this.mCtx = mCtx;
            this.imageListModels = imageListModels;
        }
        @Override
        public ImageListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View snapImage = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_list_adapter, parent, false);
            return new ImageListViewHolder(snapImage);
        }
        @Override
        public void onBindViewHolder(ImageListViewHolder holder, @SuppressLint("RecyclerView") int position) {
            ImageListModel imageListModel = imageListModels.get(position);
            Uri uri = Uri.parse(imageListModel.getShowLink());
            holder.image_element.setImageURI(uri);

        }
        @Override
        public int getItemCount() {
            return imageListModels.size();
        }
        class ImageListViewHolder extends RecyclerView.ViewHolder {
            ImageView image_element;
            public ImageListViewHolder(View itemView) {
                super(itemView);
                image_element = itemView.findViewById(R.id.image_element);
            }
        }
    }

}