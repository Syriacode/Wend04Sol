package example.org.test.weekend04sol;

import android.util.Log;

import example.org.test.weekend04sol.model.cities.UserResponse;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class UserResponseObserver implements Observer<UserResponse> {
    private static final String TAG= "TAG_OBSERVER";
    UserResponse returnUserResponse;
    UserResponseCallback userResponseCallback;

    public UserResponseObserver(UserResponseCallback userResponseCallback) {
        this.userResponseCallback = userResponseCallback;
    }

    @Override
    public void onSubscribe(Disposable d) {
        this.userResponseCallback = userResponseCallback;
    }

    @Override
    public void onNext(UserResponse userResponse) {
        Log.d(TAG, "onNext: RESPONSE RECEIVED ");
        returnUserResponse = userResponse;

    }

    @Override
    public void onError(Throwable e) {
        Log.e(TAG, "onError: ERROR RETURNED", e);

    }

    @Override
    public void onComplete() {
        Log.d(TAG, "onComplete: TASK IS COMPLETE RETURNING USER RESPONSE");
        userResponseCallback.OnSucess(returnUserResponse);
    }
}
