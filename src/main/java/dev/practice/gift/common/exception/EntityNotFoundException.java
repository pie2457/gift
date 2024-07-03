package dev.practice.gift.common.exception;

import dev.practice.gift.common.response.ErrorCode;

public class EntityNotFoundException extends BaseException {

	public EntityNotFoundException() {
		super(ErrorCode.COMMON_INVALID_PARAMETER);
	}
}
