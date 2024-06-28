package dev.practice.gift.infrastructure.gift;

import org.springframework.stereotype.Component;

import dev.practice.gift.common.exception.InvalidParamException;
import dev.practice.gift.domain.gift.Gift;
import dev.practice.gift.domain.gift.GiftStore;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class GiftStoreImpl implements GiftStore {
	private final GiftRepository giftRepository;

	@Override
	public Gift store(Gift gift) {
		if (gift == null)
			throw new InvalidParamException();
		return giftRepository.save(gift);
	}
}
