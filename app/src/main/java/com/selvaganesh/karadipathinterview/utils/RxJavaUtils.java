package com.selvaganesh.karadipathinterview.utils;

import androidx.annotation.NonNull;

import com.selvaganesh.karadipathinterview.exceptions.AccessTokenException;
import com.selvaganesh.karadipathinterview.exceptions.ApiHttpException;
import com.selvaganesh.karadipathinterview.exceptions.AppException;
import com.selvaganesh.karadipathinterview.exceptions.LimitReachedException;
import com.selvaganesh.karadipathinterview.exceptions.ResouceNotFoundException;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.reactivex.CompletableTransformer;
import io.reactivex.FlowableTransformer;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.exceptions.OnErrorNotImplementedException;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;
import retrofit2.Response;

public class RxJavaUtils {
    public static <T> ObservableTransformer<T, T> applyObserverSchedulers() {
        return observable -> observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public static <T> FlowableTransformer<T, T> applyFlowableSchedulers() {
        return observable -> observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public static <T> FlowableTransformer<T, T> applyNewFlowableSchedulers() {
        return observable -> observable.subscribeOn(Schedulers.newThread());
    }

    public static <T> ObservableTransformer<T, T> applyNewObserverSchedulers() {
        return observable -> observable.subscribeOn(Schedulers.newThread());
    }

    public static CompletableTransformer applyCompletableSchedulers() {
        return completable -> completable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public static CompletableTransformer applyNewCompletableSchedulers() {
        return completable -> completable.subscribeOn(Schedulers.newThread());
    }

    public static <T> ObservableTransformer<T, T> applyErrorTransformer() {
        return observable -> observable.onErrorResumeNext((Function<Throwable, ObservableSource<? extends T>>) throwable -> {
            try {
                if (throwable instanceof ApiHttpException) {
                    Response response = ((ApiHttpException) throwable).response();
                    return setException(response);
                } else if (throwable instanceof ConnectException || throwable instanceof UnknownHostException) {
                    if (!NetworkUtils.isConnected())
                        return Observable.error(new RuntimeException("No Internet Connection."));
                    else
                        return Observable.error(new RuntimeException("Unknown Host."));

                } else if (throwable instanceof SocketTimeoutException) {
                    return Observable.error(new RuntimeException("Slow internet Connection."));

                } else {
                    Response response = ((HttpException) throwable).response();
                    return setException(response);
                }
            } catch (IOException exception) {
                exception.printStackTrace();
                return Observable.error(exception);
            }
        });
    }


    @NonNull
    private static <T> Observable<? extends T> setException(Response response) throws IOException {
        switch (response.code()) {
            case 400:
                return Observable.error(AppException.create(response));
            case 401:
                return Observable.error(AccessTokenException.create(response.message()));
            case 404:
                return Observable.error(ResouceNotFoundException.create(response));
            case 429:
                return Observable.error(LimitReachedException.create(response.raw().header("Retry-After"), response.message(),
                        "" + response.code()));
            case 403:
            default:
                return Observable.error(AppException.create(response));

        }
    }

    public static <T> ObservableTransformer<T, T> applyOnErrorCrasher() {
        return upstream -> upstream.doOnError((Consumer<Throwable>) throwable -> {
            final Throwable checkpoint = new Throwable();
            StackTraceElement[] stackTrace = checkpoint.getStackTrace();
            StackTraceElement element = stackTrace[1]; // First element after `crashOnError()`
            String msg = String.format("showFailureMessage() crash from subscribe() in %s.%s(%s:%s)",
                    element.getClassName(),
                    element.getMethodName(),
                    element.getFileName(),
                    element.getLineNumber());

            throw new OnErrorNotImplementedException(msg, throwable);
        });
    }
}
