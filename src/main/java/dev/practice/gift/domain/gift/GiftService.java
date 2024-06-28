package dev.practice.gift.domain.gift;

public interface GiftService {
	GiftInfo getGiftInfo(String giftToken);

	GiftInfo registerOrder(GiftCommand.Register request);
}
