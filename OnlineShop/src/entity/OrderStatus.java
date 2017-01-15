package entity;

/**
 * Created by laonen on 15.01.2017.
 */
public enum OrderStatus {
    ORDER_IN_ACCEPTED,                                      /*Заказ принят*/
    ORDER_SUBMITTED_FOR_EXECUTION,                          /*Заказ передан на исполнение*/
    THE_ORDER_IS_DELIVERED,                                 /*Заказ доставляется*/
    ORDER_WAS_PREPARED_TO_ISSUE,                            /*Заказ подготовлен к выдаче*/
    ORDER_ISSUED,                                           /*Заказ выдан*/
    CHANGE_THE_ORDER_TO_BE_CONFIRMED,                       /*Изменение заказа, требуется подтверждение*/
    THE_ORDER_IS_NOT_ISSUED_TO_THE_CLIENT_TO_BE_SPECIFIED,  /*Заказ не выдан клиенту, требуется уточнение*/
    ORDER_CANCELED_BY_THE_CLIENT,                           /*Заказ отменен клиентом*/
    ORDER_CANCELLED_OUTDATED_TERM_STORAGE_OF_GOODS,         /*Заказ отменен, истек срок хранения товара*/
    ORDER_CANCELED_DUE_TO_TECHNICAL_REASONS                 /*Заказ отменен по техническим причинам*/
}
