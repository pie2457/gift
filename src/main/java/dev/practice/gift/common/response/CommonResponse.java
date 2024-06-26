package dev.practice.gift.common.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommonResponse<T> {
	private Result result;
	private T data;
	private String message;
	private String errorCode;

	public static <T> CommonResponse<T> success(T data, String message) {
		return (CommonResponse<T>)CommonResponse.builder()
			.result(Result.SUCCESS)
			.data(data)
			.message(message)
			.build();
	}

	public enum Result {
		SUCCESS, FAIL
	}
}
