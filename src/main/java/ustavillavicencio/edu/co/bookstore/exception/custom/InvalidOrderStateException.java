package ustavillavicencio.edu.co.bookstore.exception.custom;

import ustavillavicencio.edu.co.bookstore.entity.OrderStatus;

public class InvalidOrderStateException extends RuntimeException {

    public InvalidOrderStateException(OrderStatus status) {
        super("Operación inválida sobre un pedido en estado " + status);
    }

    

}
