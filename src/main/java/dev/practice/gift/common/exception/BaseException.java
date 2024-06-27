package dev.practice.gift.common.exception;

import dev.practice.gift.common.response.ErrorCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BaseException extends RuntimeException {
	private ErrorCode errorCode;

	public BaseException(ErrorCode errorCode) {
		super(errorCode.getErrorMsg());
		this.errorCode = errorCode;
	}

	public BaseException(String message, ErrorCode errorCode) {
		super(message);
		this.errorCode = errorCode;
	}
}
