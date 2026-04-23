package ustavillavicencio.edu.co.bookstore.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
// DTO que representa los datos de salida expuestos para un autor.
public class AuthorResponse {

	private Long id;

	private String name;

	private String biography;

	private String email;

	private String phoneNumber;

	private Byte age;
}
