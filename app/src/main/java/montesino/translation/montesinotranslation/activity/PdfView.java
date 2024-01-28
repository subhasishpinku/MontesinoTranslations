package montesino.translation.montesinotranslation.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.barteksc.pdfviewer.PDFView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import montesino.translation.montesinotranslation.R;
import montesino.translation.montesinotranslation.api.ApiClient;
import montesino.translation.montesinotranslation.global.FileDownloadUtil;

public class PdfView extends AppCompatActivity {

    LinearLayout back, downloadPdf;
    String url, file_name, pdfLink;
    PDFView pdfView;
    Dialog progressDialog;
    TextView header_title;
    FileDownloadUtil fileDownloadUtil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_view);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        android.graphics.drawable.Drawable background = PdfView.this.getDrawable(R.drawable.screen_header);
        getWindow().setBackgroundDrawable(background);
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        initialize();
    }

    private void initialize() {
        pdfView = findViewById(R.id.idPDFView);
        back = findViewById(R.id.back);
        header_title = findViewById(R.id.header_title);
        downloadPdf = findViewById(R.id.downloadPdf);
        fileDownloadUtil = new FileDownloadUtil(PdfView.this);
        file_name = getIntent().getStringExtra("invoiceId");
        pdfLink = getIntent().getStringExtra("pdfLink");
        url = pdfLink;
        progressDialog = new Dialog(PdfView.this);
        progressDialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        progressDialog.setCancelable(false);
        progressDialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        progressDialog.setContentView(R.layout.progress);
        if (progressDialog.getWindow() != null) {
            progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        progressDialog.show();
        loadFile();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.e("Loader SHOW","CALLING");
                progressDialog.show();
            }
        }, 1500);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirect();
            }
        });

        downloadPdf.setOnClickListener(v -> {
            fileDownloadUtil.downloadFile(url, file_name+".pdf");
        });
    }

    private void loadFile() {

        progressDialog.show();
        new RetrievePdfFromUrl().execute(url);
        // InputStream file = performAsyncTask(url);
    }

    public class RetrievePdfFromUrl extends AsyncTask<String, Void, InputStream> {
        @SuppressLint("WrongThread")
        @Override
        protected InputStream doInBackground(String... strings) {
            InputStream inputStream = null;
            Log.e("Loading", "Initialize");

            try {
                URL url = new URL(strings[0]);
                Log.e("Loading URL", "Initialize Connection"+strings[0]);
                HttpURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
                Log.e("Loading Initialize Connection", "Initialize Connection"+urlConnection);
                if (urlConnection.getResponseCode() == 200) {

                    Log.e("Loading", "Loading File");
                    inputStream = new BufferedInputStream(urlConnection.getInputStream());
                    System.out.println("LoadPf "+"0");
                } else {
                    Log.e("Loading Failed", "Loading File");
                }

            } catch (IOException e) {
                Log.e("Loading Error", "Loading Error"+e.getLocalizedMessage());
                progressDialog.show();
                loadFile();

                e.printStackTrace();
                return null;
            }
            return inputStream;
        }

        @Override
        protected void onPostExecute(InputStream inputStream) {
            System.out.println("LoadPf "+inputStream);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Log.e("Loader SHOW","End");
                    progressDialog.dismiss();
                }
            }, 1000);
            pdfView.fromStream(inputStream).load();
        }
    }

    @Override
    public void onBackPressed() {
        redirect();
    }

    public void redirect() {
        finish();
    }
}