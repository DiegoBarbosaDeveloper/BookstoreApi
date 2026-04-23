package ustavillavicencio.edu.co.bookstore.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ustavillavicencio.edu.co.enums.BookState;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
// DTO de entrada para crear/actualizar libros.
// authorId se usa para buscar y asociar el autor existente.
public class BookRequest {

	@NotBlank(message = "El titulo es obligatorio")
	@Size(max = 250, message = "El titulo no puede superar 250 caracteres")
	private String title;

	@NotNull(message = "El estado del libro es obligatorio")
	private BookState state;

	@NotNull(message = "El stock es obligatorio")
	@Min(value = 0, message = "El stock no puede ser negativo")
	private Integer stock;

	@NotNull(message = "El precio es obligatorio")
	@DecimalMin(value = "0.0", inclusive = false, message = "El precio debe ser mayor a 0")
	private Double price;

	@NotBlank(message = "El ISBN es obligatorio")
	@Size(min = 10, max = 20, message = "El ISBN debe tener entre 10 y 20 caracteres")
	private String isbn;

	@NotNull(message = "El authorId es obligatorio")
	private Long authorId;

	@NotNull(message = "El categoryId es obligatorio")
	private Long categoryId;
}
