package montesino.translation.montesinotranslation.api;

import androidx.annotation.Nullable;

import com.getkeepsafe.relinker.BuildConfig;

import org.json.JSONObject;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    public static final String BASE_URL = "https://dev.bellavidahotel.com/montesino/app/";
   // public static final String BASE_URL = "https://api.perfectbookkeeping.com/";
    private static ApiClient mInstance;
    public static Retrofit montesinoRetrofit;
    public static synchronized ApiClient getInstance(){
        if(mInstance == null) {
            mInstance = new ApiClient();
        }
        return mInstance;
    }

    private ApiClient() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder okhttp = new OkHttpClient.Builder();
        okhttp.addInterceptor(loggingInterceptor);
        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(200, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .addInterceptor(getHttpLoggingInterceptor())
                .build();

        //Book Keeping API
        montesinoRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                //.addConverterFactory(new JSONObjectWithoutNamespacesConverterFactory())
                .build();

    }
    public Api getMontesinoApi(){
        return montesinoRetrofit.create(Api.class);
    }


    public static HttpLoggingInterceptor getHttpLoggingInterceptor() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
//        if(BuildConfig.DEBUG){
//            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//        } else {
//            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
//        }
        return httpLoggingInterceptor;
    }

    public class JSONObjectWithoutNamespacesConverterFactory extends Converter.Factory {
        @Override
        public @Nullable Converter<?, RequestBody> requestBodyConverter(
                Type type,
                Annotation[] parameterAnnotations,
                Annotation[] methodAnnotations,
                Retrofit retrofit) {
            if (type == JSONObject.class) {
                return new JSONObjectWithoutNamespacesRequestBodyConverter();
            }
            return null;
        }

        private static class JSONObjectWithoutNamespacesRequestBodyConverter implements Converter<JSONObject, RequestBody> {
            @Override
            public RequestBody convert(JSONObject value) throws IOException {
                MediaType mediaType = MediaType.parse("application/json; charset=UTF-8");
                String jsonString = removeNamespaces(value.toString());
                return RequestBody.create(jsonString, mediaType);
            }

            private String removeNamespaces(String jsonString) {
                // Replace all occurrences of "namespaceValues" with an empty string
                return jsonString.replaceAll("\"namespaceValues\":\\{[^}]*\\},?", "");
            }
        }
    }
}
