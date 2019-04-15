package client.test.data_collection.networking;

import client.test.data_collection.model.PacientDailyInfo;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface PacientDataEntryClient {

    @POST("/index")
    Call<PacientDailyInfo>  sendDailyData(@Body PacientDailyInfo info);

}
