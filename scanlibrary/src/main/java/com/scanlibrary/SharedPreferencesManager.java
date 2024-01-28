package com.scanlibrary;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class SharedPreferencesManager {
    private static final String PREF_NAME = "scanlibrary";
    private static final String KEY_ARRAY_LIST = "key_array_list";
    private static final String KEY_ARRAY_LIST_UPLOAD = "key_array_list_upload";
    public static final String URL   = "url";
    private static SharedPreferencesManager mInstance;
    private static Context mCtx;
    private SharedPreferencesManager(Context context) {
        mCtx = context;
    }
    public static synchronized SharedPreferencesManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPreferencesManager(context);
        }
        return mInstance;
    }
    public static void saveArrayList(Context context, ArrayList<ImageListModel> arrayList) {
        SharedPreferences.Editor editor = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE).edit();
        Gson gson = new Gson();
        String json = gson.toJson(arrayList);
        editor.putString(KEY_ARRAY_LIST, json);
        editor.apply();
    }

    public static ArrayList<ImageListModel> getArrayList(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        String json = prefs.getString(KEY_ARRAY_LIST, null);
        Type type = new TypeToken<ArrayList<ImageListModel>>() {}.getType();
        Gson gson = new Gson();
        return gson.fromJson(json, type);
    }
    public void clearData() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
        editor.apply();
    }

    public static void saveImageUploadArrayList(Context context, List<UploadImageListModel> arrayList) {
        SharedPreferences.Editor editor = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE).edit();
        Gson gson = new Gson();
        String json = gson.toJson(arrayList);
        editor.putString(KEY_ARRAY_LIST_UPLOAD, json);
        editor.apply();
    }

    public static List<UploadImageListModel> getImageUploadArrayList(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        String json = prefs.getString(KEY_ARRAY_LIST_UPLOAD, null);
        Type type = new TypeToken<List<UploadImageListModel>>() {}.getType();
        Gson gson = new Gson();
        return gson.fromJson(json, type);
    }
}
