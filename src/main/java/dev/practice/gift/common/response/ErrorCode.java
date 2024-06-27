package dev.practice.gift.common.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
	COMMON_INVALID_PARAMETER("요청한 값이 올바르지 않습니다."),
	COMMON_ILLEGAL_STATUS("잘못된 상태값 잆니다.");

	private final String errorMsg;

	public String getErrorMsg(Object... arg) {
		return String.format(errorMsg, arg);
	}
}
