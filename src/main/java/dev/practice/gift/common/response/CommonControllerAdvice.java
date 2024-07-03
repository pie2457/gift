package dev.practice.gift.common.response;

import java.util.List;

import org.slf4j.MDC;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.google.common.collect.Lists;

import dev.practice.gift.common.exception.BaseException;
import dev.practice.gift.common.interceptor.CommonHttpRequestInterceptor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class CommonControllerAdvice {

	private static final List<ErrorCode> SPECIFIC_ALERT_TARGET_ERROR_CODE_LIST = Lists.newArrayList();

	/**
	 * http status: 500 AND result: FAIL
	 * 시스템 예외 상황. 집중 모니터링 대상
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(value = Exception.class)
	public CommonResponse onException(Exception e) {
		String eventId = MDC.get(CommonHttpRequestInterceptor.HEADER_REQUEST_UUID_KEY);
		log.error("eventId = {}", eventId, e);
		return CommonResponse.fail(ErrorCode.COMMON_SYSTEM_ERROR);
	}

	/**
	 * http status: 200 AND result: FAIL
	 * 시스템은 이슈 없고, 비즈니스 로직 처리에서 에러가 발생함
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@ExceptionHandler(value = BaseException.class)
	public CommonResponse onBaseException(BaseException e) {
		String eventId = MDC.get(CommonHttpRequestInterceptor.HEADER_REQUEST_UUID_KEY);
		if (SPECIFIC_ALERT_TARGET_ERROR_CODE_LIST.contains(e.getErrorCode())) {
			log.error("[BaseException] eventId = {}, cause = {}", eventId, NestedExceptionUtils.getMostSpecificCause(e),
				NestedExceptionUtils.getMostSpecificCause(e).getMessage());
		} else {
			log.warn("[BaseException] eventId = {}, cause = {}", eventId, NestedExceptionUtils.getMostSpecificCause(e),
				NestedExceptionUtils.getMostSpecificCause(e).getMessage());
		}
		return CommonResponse.fail(e.getMessage(), e.getErrorCode().name());
	}

	/**
	 * http status: 400 AND result: FAIL
	 * request parameter 에러
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = {MethodArgumentNotValidException.class})
	public CommonResponse methodArgumentNotValidException(MethodArgumentNotValidException e) {
		String eventId = MDC.get(CommonHttpRequestInterceptor.HEADER_REQUEST_UUID_KEY);
		log.warn("[BaseException] eventId = {}, errorMsg = {}", eventId,
			NestedExceptionUtils.getMostSpecificCause(e).getMessage());
		BindingResult bindingResult = e.getBindingResult();
		FieldError fieldError = bindingResult.getFieldError();
		if (fieldError != null) {
			String message = "Request Error" + " " + fieldError.getField() + "=" + fieldError.getRejectedValue() + " ("
				+ fieldError.getDefaultMessage() + ")";
			return CommonResponse.fail(message, ErrorCode.COMMON_INVALID_PARAMETER.name());
		} else {
			return CommonResponse.fail("요청한 값이 올바르지 않습니다.", ErrorCode.COMMON_INVALID_PARAMETER.name());
		}
	}
}
