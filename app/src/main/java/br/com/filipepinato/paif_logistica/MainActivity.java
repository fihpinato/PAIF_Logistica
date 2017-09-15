package br.com.filipepinato.paif_logistica;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private EditText etCep;
    private TextView tvTipoLogradouro;
    private TextView tvLogradouro;
    private TextView tvBairro;
    private TextView tvCidade;
    private TextView tvEstado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etCep = (EditText) findViewById(R.id.etCep);
        tvTipoLogradouro = (TextView) findViewById(R.id.tvTipoLogradouro);
        tvLogradouro = (TextView) findViewById(R.id.tvLogradouro);
        tvBairro = (TextView) findViewById(R.id.tvBairro);
        tvCidade = (TextView) findViewById(R.id.tvCidade);
        tvEstado = (TextView) findViewById(R.id.tvEstado);
    }

    public void pesquisar (View view){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://correiosapi.apphb.com/cep/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CorreiosAPIService service = retrofit.create(CorreiosAPIService.class);

        service.buscarCep(etCep.getText().toString())
                .enqueue(new Callback<Consulta>() {
                    @Override
                    public void onResponse(Call<Consulta> call, Response<Consulta> response) {
                        if (response.isSuccessful()){
                            tvTipoLogradouro.setText(response.body().getTipoLogradouro());
                            tvLogradouro.setText(response.body().getLogradouro());
                            tvBairro.setText(response.body().getBairro());
                            tvCidade.setText(response.body().getCidade());
                            tvEstado.setText(response.body().getEstado());
                        } else {
                            Toast.makeText(MainActivity.this, "CEP Inválido", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Consulta> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "Sem conexão", Toast.LENGTH_SHORT).show();
                    }
                });

    }
}
