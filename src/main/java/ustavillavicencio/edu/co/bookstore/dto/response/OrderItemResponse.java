package ustavillavicencio.edu.co.bookstore.dto.response;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class OrderItemResponse {

    private Long bookId;
    private String bookTitle;
    private Integer quantity;
    private BigDecimal subtotal;
}
