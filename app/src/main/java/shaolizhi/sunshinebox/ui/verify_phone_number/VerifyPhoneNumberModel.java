package shaolizhi.sunshinebox.ui.verify_phone_number;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import shaolizhi.sunshinebox.data.ApiService;

import static shaolizhi.sunshinebox.data.ApiService.BASE_URL;

/**
 * 由邵励治于2017/11/29创造.
 */

public class VerifyPhoneNumberModel implements VerifyPhoneNumberContract.Model {

    private VerifyPhoneNumberContract.CallBack callBack;

    VerifyPhoneNumberModel(VerifyPhoneNumberContract.CallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    public void requestSendCaptchaBean(@NonNull String phoneNumber) {
        Gson gson = new GsonBuilder().create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        ApiService service = retrofit.create(ApiService.class);

        Call<SendCaptchaBean> call = service.sendCaptchaAPI(phoneNumber);

        call.enqueue(new Callback<SendCaptchaBean>() {
            @Override
            public void onResponse(@NonNull Call<SendCaptchaBean> call, @NonNull Response<SendCaptchaBean> response) {
                SendCaptchaBean bean = response.body();
                if (bean != null) {
                    callBack.requestSendCaptchaBeanSuccess(bean);
                }
            }

            @Override
            public void onFailure(@NonNull Call<SendCaptchaBean> call, @NonNull Throwable t) {
                callBack.requestSendCaptchaBeanFailure();
            }
        });
    }
}
