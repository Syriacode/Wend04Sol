package example.org.test.weekend04sol;

import java.util.Observable;

import example.org.test.weekend04sol.model.cities.UserResponse;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class RetrofitHelper {
    private static OkHttpClient getOkHttpClientWithInterceptor(){
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();
    }
    private static Retrofit getRetrofit(){
        return new Retrofit
                .Builder()
                .baseUrl(ApiConstants.BASE_URL)
                .client(getOkHttpClientWithInterceptor())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }
    private ObservableInterface createUserResponseInterface(){
        return getRetrofit().create(ObservableInterface.class);
    }

    public Observable<UserResponse> getUserResponseObservable(String numResults, String zipcode){
        return createUserResponseInterface().getUserResponseObservable(numResults, zipcode);
    }

    public interface ObservableInterface{
        @GET(ApiConstants.PATH_API)
        Observable<UserResponse> getUserResponseObservable(
                @Query(ApiConstants.QUERY_APPID) String numOfResultsDesired,
                @Query(ApiConstants.QUERY_ZIP) String requestedZipcode);

    }
}
