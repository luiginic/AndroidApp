package client.test.networking;

import client.test.model.PacientDailyInfo;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface PacientDataEntryClient {

    @POST("/anomaly")
    Call<PacientDailyInfo>  sendDailyData(@Body PacientDailyInfo info);

}
