package dev.practice.gift.application;

import org.springframework.stereotype.Service;

import dev.practice.gift.domain.gift.GiftCommand;
import dev.practice.gift.domain.gift.GiftInfo;
import dev.practice.gift.domain.gift.GiftService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class GiftFacade {
	private final GiftService giftService;

	public GiftInfo getOrder(String giftToken) {
		log.info("getOrder giftToken = {}", giftToken);
		return giftService.getGiftInfo(giftToken);
	}

	public GiftInfo registerOrder(GiftCommand.Register command) {
		var giftInfo = giftService.registerOrder(command);
		log.info("registerOrder orderToken = {}", giftInfo);
		return giftInfo;
	}
}
