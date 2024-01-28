package com.scanlibrary;

import android.net.Uri;

public class DataModel {
    Uri filePath;
    String mimeType;
    String fileName;

    public DataModel(Uri filePath, String mimeType, String fileName) {
        this.filePath = filePath;
        this.mimeType = mimeType;
        this.fileName = fileName;
    }

    public Uri getFilePath() {
        return filePath;
    }

    public String getMimeType() {
        return mimeType;
    }

    public String getFileName() {
        return fileName;
    }
}
