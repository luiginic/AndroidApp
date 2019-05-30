package data_collection;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface PacientDataEntryClient {

    @POST("/send_data")
    Call<GenericMessage> sendDailyData(@Body PacientDailyInfo info);

    @GET("/get")
    Call<List<PacientDailyInfo>>  getDailyInfo(@Query("info") String info,
                                               @Query("id") int id,
                                               @Query("day") String day);

}
