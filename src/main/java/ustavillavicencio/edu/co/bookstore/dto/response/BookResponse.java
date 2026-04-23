package ustavillavicencio.edu.co.bookstore.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ustavillavicencio.edu.co.bookstore.enums.BookState;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
// DTO de salida para libros.
// authorId evita serializar el objeto AuthorEntity completo en la respuesta.
public class BookResponse {

	private Long id;

	private String title;

	private BookState state;

	private Integer stock;

	private Double price;

	private String isbn;

	private Long authorId;

	private Long categoryId;
}
