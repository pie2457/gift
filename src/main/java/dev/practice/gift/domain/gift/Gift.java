package dev.practice.gift.domain.gift;

import java.time.ZonedDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.util.StringUtils;

import dev.practice.gift.common.exception.InvalidParamException;
import dev.practice.gift.common.util.TokenGenerator;
import dev.practice.gift.domain.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Entity
@ToString
@NoArgsConstructor
@Table(name = "gifts")
public class Gift extends AbstractEntity {
	private static final String GIFT_PREFIX = "gt_";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String giftToken;
	private Long buyerUserId;
	private String orderToken;

	@Enumerated(EnumType.STRING)
	private Status status;

	@Enumerated(EnumType.STRING)
	private PushType pushType;
	private String giftReceiverName;
	private String giftReceiverPhone;
	private String giftMessage;

	private String receiverName;
	private String receiverPhone;
	private String receiverZipcode;
	private String receiverAddress;
	private String receiverDetailAddress;
	private String etcMessage;

	private ZonedDateTime paidAt;
	private ZonedDateTime pushedAt;
	private ZonedDateTime acceptedAt;
	private ZonedDateTime expiredAt;

	@Getter
	@AllArgsConstructor
	public enum Status {
		INIT("선물 주문 생성"),
		IN_PAYMENT("결제 중"),
		ORDER_COMPLETE("주문 완료"),
		PUSH_COMPLETE("선물 링크 발송 완료"),
		ACCEPT("선물 수락"),
		DELIVERY_PREPARED("배송 준비"),
		IN_DELIVERY("배송중"),
		DELIVERY_COMPLETE("배송완료"),
		EXPIRATION("선물 수락 만료");

		private final String description;
	}

	@Getter
	@AllArgsConstructor
	public enum PushType {
		KAKAO("카카오톡"),
		LMS("문자");

		private final String description;
	}

	@Builder
	public Gift(
		Long buyerUserId,
		String orderToken,
		PushType pushType,
		String giftReceiverName,
		String giftReceiverPhone,
		String giftMessage
	) {
		if (buyerUserId == null)
			throw new InvalidParamException("Gift constructor buyerUserId is null");
		if (pushType == null)
			throw new InvalidParamException("Gift constructor pushType is null");
		if (StringUtils.isEmpty(giftReceiverName))
			throw new InvalidParamException("Gift constructor giftReceiverName is null");
		if (StringUtils.isEmpty(giftReceiverPhone))
			throw new InvalidParamException("Gift constructor giftReceiverPhone is null");
		if (StringUtils.isEmpty(giftMessage))
			throw new InvalidParamException("Gift constructor giftMessage is null");

		this.giftToken = TokenGenerator.randomCharacterWithPrefix(GIFT_PREFIX);
		this.buyerUserId = buyerUserId;
		this.orderToken = orderToken;
		this.status = Status.INIT;
		this.pushType = pushType;
		this.giftReceiverName = giftReceiverName;
		this.giftReceiverPhone = giftReceiverPhone;
		this.giftMessage = giftMessage;
		this.expiredAt = ZonedDateTime.now().plusDays(7);
	}
}
