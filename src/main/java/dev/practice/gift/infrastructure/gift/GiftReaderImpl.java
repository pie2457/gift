package dev.practice.gift.infrastructure.gift;

import javax.persistence.EntityNotFoundException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

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
}
