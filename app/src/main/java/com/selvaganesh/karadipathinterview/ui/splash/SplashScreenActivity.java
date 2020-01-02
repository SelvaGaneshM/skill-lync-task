package com.selvaganesh.karadipathinterview.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.airbnb.lottie.LottieAnimationView;
import com.selvaganesh.karadipathinterview.R;
import com.selvaganesh.karadipathinterview.base.BaseActivity;
import com.selvaganesh.karadipathinterview.ui.productlist.ProductListActivity;
import com.selvaganesh.karadipathinterview.utils.NetworkUtils;
import com.selvaganesh.karadipathinterview.utils.UiUtils;


import java.io.Serializable;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashScreenActivity extends BaseActivity {

    @BindView(R.id.splash_screen_loader)
    LottieAnimationView splashScreenLoader;

    private SplashScreenViewModel viewModel;

    private void invokeApiCall(int whichApi, int timeDelay) {
        Message msgObj = apiCallHandler.obtainMessage();
        Bundle b = new Bundle();
        b.putInt("message", whichApi);
        msgObj.setData(b);
        apiCallHandler.sendMessageDelayed(msgObj, timeDelay);
    }

    private final Handler apiCallHandler = new Handler() {
        public void handleMessage(Message message) {
            int apiCall = message.getData().getInt("message");
            if (NetworkUtils.isConnected()) {
                switch (apiCall) {
                    case 1:
                        viewModel.getProducts();
                        break;
                }
            } else {
                viewModel.showFailureMessage("No Internet Connection!..");
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        viewModel = new SplashScreenViewModel();
        viewModel.setContext(this);
        splashScreenLoader.playAnimation();
        setObservable();
        invokeApiCall(1, 0);
    }

    private void setObservable() {
        viewModel.productsResponseMLD.observe(this, productListResposen -> {
            if (productListResposen != null && productListResposen.getResultCnt() == 72) {
                Intent intent = new Intent(this, ProductListActivity.class);
                Bundle args = new Bundle();
                args.putSerializable("ARRAYLIST",(Serializable) productListResposen);
                intent.putExtra("BUNDLE",args);
                startActivity(intent);
            }
        });
    }
}
