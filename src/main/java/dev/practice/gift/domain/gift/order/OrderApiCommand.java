package dev.practice.gift.domain.gift.order;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

public class OrderApiCommand {

	@Getter
	@Builder
	@ToString
	public static class Register {
		private final Long userId;
		private final String payMethod;
		private final List<RegisterOrderItem> orderItemList;

		private final String receiverName = "TEMP_VALUE";
		private final String receiverPhone = "TEMP_VALUE";
		private final String receiverZipcode = "TEMP_VALUE";
		private final String receiverAddress = "TEMP_VALUE";
		private final String receiverDetailAddress = "TEMP_VALUE";
		private final String etcMessage = "TEMP_VALUE";
	}

	@Getter
	@Builder
	@ToString
	public static class RegisterOrderItem {
		private final String orderCount;
		private final String itemToken;
		private final String itemName;
		private final String itemPrice;
		private final List<RegisterOrderItemOptionGroup> orderItemOptionGroupList;
	}

	@Getter
	@Builder
	@ToString
	public static class RegisterOrderItemOptionGroup {
		private final Integer ordering;
		private final String itemOptionGroupName;
		private final List<RegisterOrderItemOption> orderItemOptionList;
	}

	@Getter
	@Builder
	@ToString
	public static class RegisterOrderItemOption {
		private final Integer ordering;
		private final String itemOptionName;
		private final Long itemOptionPrice;
	}
}
