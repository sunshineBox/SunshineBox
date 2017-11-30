package shaolizhi.sunshinebox.data;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import shaolizhi.sunshinebox.ui.verify_phone_number.SendCaptchaBean;

/**
 * 由邵励治于2017/11/29创造.
 */

public interface ApiService {
    String BASE_URL = "http://111.231.71.150/";

    @FormUrlEncoded
    @POST("sunshinebox/activation_system/SendCaptcha.php")
    Call<SendCaptchaBean> sendCaptchaAPI(@Field("phone_number") String phoneNumber);
}
