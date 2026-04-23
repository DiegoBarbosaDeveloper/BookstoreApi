package ustavillavicencio.edu.co.bookstore.dto.response;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class BookSummaryResponse {

    private Long       id;
    private String     title;
    private String     isbn;
    private BigDecimal price;
    private Integer    stock;

}
