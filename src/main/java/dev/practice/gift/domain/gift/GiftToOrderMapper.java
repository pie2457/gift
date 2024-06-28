package dev.practice.gift.domain.gift;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import dev.practice.gift.domain.gift.order.OrderApiCommand;

@Mapper(
	componentModel = "spring",
	injectionStrategy = InjectionStrategy.CONSTRUCTOR,
	unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface GiftToOrderMapper {
	OrderApiCommand.Register of(GiftCommand.Register register);

	OrderApiCommand.RegisterOrderItem of(GiftCommand.RegisterOrderItem register);

	OrderApiCommand.RegisterOrderItemOptionGroup of(GiftCommand.RegisterOrderItemOptionGroup register);

	OrderApiCommand.RegisterOrderItemOption of(GiftCommand.RegisterOrderItemOption register);
}
