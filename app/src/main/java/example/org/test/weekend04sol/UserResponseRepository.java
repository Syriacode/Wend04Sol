package example.org.test.weekend04sol;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class UserResponseRepository {
    private RetrofitHelper retrofitHelper;

    public UserResponseRepository(){
        this.retrofitHelper = new RetrofitHelper();
    }


    public void getUserResponse(String results, String zipcode, UserResponseCallback userResponseCallback){
        retrofitHelper.getUserResponseObservable(results,zipcode)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new UserResponseObserver(userResponseCallback));
    }
}
