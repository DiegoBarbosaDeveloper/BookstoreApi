package ustavillavicencio.edu.co.bookstore.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryRequest {

    @NotBlank(message = "El nombre de la categoria es obligatorio")
    @Size(max = 120, message = "El nombre no puede superar 120 caracteres")
    private String name;

    @NotBlank(message = "La descripcion de la categoria es obligatoria")
    @Size(max = 800, message = "La descripcion no puede superar 800 caracteres")
    private String description;
}
