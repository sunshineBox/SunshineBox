package shaolizhi.sunshinebox.ui.activation_code;

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

public class ActivationCodeModel implements ActivationCodeContract.Model {

    private ActivationCodeContract.CallBack callBack;

    ActivationCodeModel(ActivationCodeContract.CallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    public void requestVerificationCodeBean(@NonNull String phoneNumber) {
        Gson gson = new GsonBuilder().create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        ApiService service = retrofit.create(ApiService.class);

        Call<VerificationCodeBean> call = service.sendCaptchaAPI(phoneNumber);

        call.enqueue(new Callback<VerificationCodeBean>() {
            @Override
            public void onResponse(@NonNull Call<VerificationCodeBean> call, @NonNull Response<VerificationCodeBean> response) {
                VerificationCodeBean bean = response.body();
                if (bean != null) {
                    callBack.requestVerificationCodeBeanSuccess(bean);
                }
            }

            @Override
            public void onFailure(@NonNull Call<VerificationCodeBean> call, @NonNull Throwable t) {
                callBack.requestVerificationCodeBeanFailure();
            }
        });
    }
}
