package dev.practice.gift.interfaces.api;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import dev.practice.gift.domain.gift.GiftInfo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class GiftDto {

	@Getter
	@Setter
	@ToString
	public static class RegisterRequest {
		@NotNull(message = "userId 는 필수값입니다")
		private Long userId;

		@NotBlank(message = "payMethod 는 필수값입니다")
		private String payMethod;

		@NotBlank(message = "pushType 는 필수값입니다")
		private String pushType;

		@NotBlank(message = "giftReceiverName 는 필수값입니다")
		private String giftReceiverName;

		@NotBlank(message = "giftReceiverPhone 는 필수값입니다")
		private String giftReceiverPhone;

		@NotBlank(message = "giftMessage 는 필수값입니다")
		private String giftMessage;

		private List<RegisterOrderItemRequest> orderItemList;

	}

	@Getter
	@Setter
	@ToString
	public static class RegisterOrderItemRequest {
		@NotNull(message = "orderCount 는 필수값입니다")
		private Integer orderCount;

		@NotBlank(message = "itemToken 는 필수값입니다")
		private String itemToken;

		@NotBlank(message = "itemName 는 필수값입니다")
		private String itemName;

		@NotNull(message = "itemPrice 는 필수값입니다")
		private Long itemPrice;

		private List<RegisterOrderItemOptionGroupRequest> orderItemOptionGroupList;
	}

	@Getter
	@Setter
	@ToString
	public static class RegisterOrderItemOptionGroupRequest {
		@NotNull(message = "ordering 는 필수값입니다")
		private Integer ordering;

		@NotBlank(message = "itemOptionGroupName 는 필수값입니다")
		private String itemOptionGroupName;

		private List<RegisterOrderItemOptionRequest> orderItemOptionList;
	}

	@Getter
	@Setter
	@ToString
	public static class RegisterOrderItemOptionRequest {
		@NotNull(message = "ordering 는 필수값입니다")
		private Integer ordering;

		@NotBlank(message = "itemOptionName 는 필수값입니다")
		private String itemOptionName;

		@NotNull(message = "itemOptionPrice 는 필수값입니다")
		private Long itemOptionPrice;
	}

	@Getter
	public static class RegisterResponse {
		private final String orderToken;
		private final String giftToken;

		public RegisterResponse(GiftInfo giftInfo) {
			this.orderToken = giftInfo.getOrderToken();
			this.giftToken = giftInfo.getGiftToken();
		}
	}

	@Getter
	@Setter
	@ToString
	public static class CompletePaymentRequest {
		private String orderToken;
	}
}
