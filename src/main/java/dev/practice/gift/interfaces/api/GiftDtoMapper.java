package dev.practice.gift.interfaces.api;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import dev.practice.gift.domain.gift.GiftCommand;

@Mapper(
	componentModel = "spring",
	injectionStrategy = InjectionStrategy.CONSTRUCTOR,
	unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface GiftDtoMapper {
	GiftCommand.Register of(GiftDto.RegisterRequest request);

	GiftCommand.RegisterOrderItem of(GiftDto.RegisterOrderItemRequest request);

	GiftCommand.RegisterOrderItemOptionGroup of(GiftDto.RegisterOrderItemOptionGroupRequest request);

	GiftCommand.RegisterOrderItemOption of(GiftDto.RegisterOrderItemOptionRequest request);

	GiftCommand.Accept of(String giftToken, GiftDto.AcceptGiftRequest request);
}
