package dev.practice.gift.interfaces.api;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.practice.gift.application.GiftFacade;
import dev.practice.gift.common.response.CommonResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/gifts")
public class GiftApiController {
	private final GiftFacade giftFacade;
	private final GiftDtoMapper giftDtoMapper;

	@GetMapping("/{giftToken}")
	public CommonResponse retrieveOrder(@PathVariable String giftToken) {
		var giftInfo = giftFacade.getOrder(giftToken);
		return CommonResponse.success(giftInfo);
	}

	@PostMapping
	public CommonResponse registerOrder(@RequestBody @Valid GiftDto.RegisterRequest request) {
		var command = giftDtoMapper.of(request);
		var giftInfo = giftFacade.registerOrder(command);
		return CommonResponse.success(new GiftDto.RegisterResponse(giftInfo));
	}

	@PostMapping("/{giftToken}/payment-processing")
	public CommonResponse requestPaymentProcessing(@PathVariable String giftToken) {
		giftFacade.requestPaymentProcessing(giftToken);
		return CommonResponse.success("OK");
	}
}
