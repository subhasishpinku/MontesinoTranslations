package com.scanlibrary;

import android.net.Uri;

public interface IScanner {

    void onBitmapSelect(Uri uri);

    void onScanFinish(Uri uri, Uri originalUri);

    void onRetake(int position, Uri uri);

    void onRetakeFinish(int position, Uri uri, Uri originalUri);
}
