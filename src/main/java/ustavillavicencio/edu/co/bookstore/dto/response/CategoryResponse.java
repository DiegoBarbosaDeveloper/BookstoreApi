package ustavillavicencio.edu.co.bookstore.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder

public class CategoryResponse {

    private Long   id;
    private String name;
public class CategoryResponse {

    private Long id;

    private String name;

    private String description;
}
