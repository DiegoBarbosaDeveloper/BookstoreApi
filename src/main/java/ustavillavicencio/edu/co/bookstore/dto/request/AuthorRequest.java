package ustavillavicencio.edu.co.bookstore.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
// DTO que representa los datos de entrada para crear/actualizar un autor.
public class AuthorRequest {

	@NotBlank(message = "El nombre del autor es obligatorio")
	@Size(max = 120, message = "El nombre no puede superar 120 caracteres")
	private String name;

	@NotBlank(message = "La biografia es obligatoria")
	@Size(max = 2000, message = "La biografia no puede superar 2000 caracteres")
	private String biography;

	@NotBlank(message = "El email es obligatorio")
	@Email(message = "El email no tiene un formato valido")
	private String email;

	@NotBlank(message = "El telefono es obligatorio")
	@Size(min = 7, max = 20, message = "El telefono debe tener entre 7 y 20 caracteres")
	private String phoneNumber;

	@NotNull(message = "La edad es obligatoria")
	@Min(value = 16, message = "La edad minima es 16")
	@Max(value = 120, message = "La edad maxima es 120")
	private Byte age;
}
