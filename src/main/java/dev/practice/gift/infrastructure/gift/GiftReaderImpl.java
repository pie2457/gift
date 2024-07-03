package dev.practice.gift.infrastructure.gift;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import dev.practice.gift.common.exception.EntityNotFoundException;
import dev.practice.gift.common.exception.InvalidParamException;
import dev.practice.gift.domain.gift.Gift;
import dev.practice.gift.domain.gift.GiftReader;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class GiftReaderImpl implements GiftReader {
	private final GiftRepository giftRepository;

	@Override
	public Gift getGiftBy(String giftToken) {
		if (StringUtils.isBlank(giftToken))
			throw new InvalidParamException();
		return giftRepository.findByGiftToken(giftToken)
			.orElseThrow(EntityNotFoundException::new);
	}

	@Override
	public Gift getGiftByOrderToken(String orderToken) {
		if (StringUtils.isEmpty(orderToken))
			throw new InvalidParamException();
		return giftRepository.findByOrderToken(orderToken)
			.orElseThrow(EntityNotFoundException::new);
	}
}
