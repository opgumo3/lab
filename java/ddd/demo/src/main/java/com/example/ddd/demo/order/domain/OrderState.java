package order.domain;

public enum OrderState {
    ORDER_RECEIVED, // 주문 접수
    PAYMENT_PENDING, // 결제 대기
    PAYMENT_COMPLETED, // 결제 완료
    SHIPPED, // 배송 중
    DELIVERED, // 배송 완료
    CANCELLED, // 취소됨
    RETURN_REQUESTED, // 반품 요청됨
    RETURNED, // 반품됨
    REFUNDED // 환불됨
    ;
}