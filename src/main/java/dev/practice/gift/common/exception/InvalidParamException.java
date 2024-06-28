package dev.practice.gift.common.exception;

import dev.practice.gift.common.response.ErrorCode;

public class InvalidParamException extends BaseException {

	public InvalidParamException() {
		super(ErrorCode.COMMON_INVALID_PARAMETER);
	}

	public InvalidParamException(String errorMsg) {
		super(errorMsg, ErrorCode.COMMON_INVALID_PARAMETER);
	}

}
