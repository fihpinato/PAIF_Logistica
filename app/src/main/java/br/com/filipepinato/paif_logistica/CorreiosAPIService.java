package br.com.filipepinato.paif_logistica;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CorreiosAPIService {

    @GET("/cep/{cep}")
    Call<Consulta> buscarCep(@Path(value = "cep")String cep);

}
