package dev.practice.gift.infrastructure.retrofit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import dev.practice.gift.infrastructure.gift.order.RetrofitOrderApi;

@Configuration
public class RetrofitServiceRegistry {

	@Value("${example.order.base-url}")
	private String baseUrl;

	@Bean
	public RetrofitOrderApi retrofitOrderApi() {
		var retrofit = RetrofitUtils.initRetrofit(baseUrl);
		return retrofit.create(RetrofitOrderApi.class);
	}
}
