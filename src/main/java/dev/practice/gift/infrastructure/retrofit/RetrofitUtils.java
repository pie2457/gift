package dev.practice.gift.infrastructure.retrofit;

import java.io.IOException;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dev.practice.gift.common.response.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Slf4j
@Component
public class RetrofitUtils {
	private static final HttpLoggingInterceptor loggingInterceptor =
		new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);

	private static final OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
		.addInterceptor(loggingInterceptor)
		.connectTimeout(3, TimeUnit.SECONDS)
		.readTimeout(10, TimeUnit.SECONDS);

	private static final Gson gson = new GsonBuilder()
		.setLenient()
		.create();

	public static Retrofit initRetrofit(String baseUrl) {
		return new Retrofit.Builder()
			.baseUrl(baseUrl)
			.addConverterFactory(GsonConverterFactory.create(gson))
			.client(httpClient.build())
			.build();
	}

	public <T extends CommonResponse> Optional<T> responseSync(Call<T> call) {
		try {
			Response<T> execute = call.execute();
			if (execute.isSuccessful()) {
				return Optional.ofNullable(execute.body());
			} else {
				log.error("requestSync errorBody = {}", execute.errorBody());
				throw new RuntimeException("retrofit execute response error");
			}
		} catch (IOException e) {
			log.error("", e);
			throw new RuntimeException("retrofit execute IOException");
		}
	}
}
