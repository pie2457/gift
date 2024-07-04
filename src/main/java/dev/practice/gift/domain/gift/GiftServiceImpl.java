package dev.practice.gift.domain.gift;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.practice.gift.domain.gift.order.OrderApiCaller;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
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
	@Transactional
	public GiftInfo registerOrder(GiftCommand.Register request) {
		var orderCommand = giftToOrderMapper.of(request);
		var orderToken = orderApiCaller.registerGiftOrder(orderCommand);
		var initGift = request.toEntity(orderToken);
		var gift = giftStore.store(initGift);
		return new GiftInfo(gift);
	}

	@Override
	@Transactional
	public void requestPaymentProcessing(String giftToken) {
		var gift = giftReader.getGiftBy(giftToken);
		gift.inPayment();
	}

	@Override
	@Transactional
	public void completePayment(String orderToken) {
		var gift = giftReader.getGiftByOrderToken(orderToken);
		gift.completePayment();
	}

	@Override
	@Transactional
	public void acceptGift(GiftCommand.Accept request) {
		var gift = giftReader.getGiftBy(request.getGiftToken());
		gift.accept(request);

		orderApiCaller.updateReceiverInfo(gift.getOrderToken(), request);
	}
}
