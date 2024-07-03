package dev.practice.gift.common.exception;

import dev.practice.gift.common.response.ErrorCode;

public class IllegalStatusException extends BaseException {

	public IllegalStatusException(String message) {
		super(message, ErrorCode.COMMON_ILLEGAL_STATUS);
	}
}
