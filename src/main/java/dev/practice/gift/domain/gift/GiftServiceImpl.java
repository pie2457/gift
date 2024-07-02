package dev.practice.gift.domain.gift;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.practice.gift.domain.gift.order.OrderApiCaller;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class GiftServiceImpl implements GiftService {
	private final GiftReader giftReader;
	private final GiftStore giftStore;
	private final OrderApiCaller orderApiCaller;
	private final GiftToOrderMapper giftToOrderMapper;

	@Override
	public GiftInfo getGiftInfo(String giftToken) {
		Gift gift = giftReader.getGiftBy(giftToken);
		return new GiftInfo(gift);
	}

	@Override
	public GiftInfo registerOrder(GiftCommand.Register request) {
		var orderCommand = giftToOrderMapper.of(request);
		var orderToken = orderApiCaller.registerGiftOrder(orderCommand);
		var initGift = request.toEntity(orderToken);
		var gift = giftStore.store(initGift);
		return new GiftInfo(gift);
	}
}
