package itacademy.snowadv.javaedu.api;

import itacademy.snowadv.javaedu.data.CompilationRequest;
import itacademy.snowadv.javaedu.data.CompilationResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface CompilationApiInterface {
    @POST("/v1/execute")
    Call<CompilationResponse> compile(@Body CompilationRequest request);
}
