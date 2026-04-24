package ustavillavicencio.edu.co.bookstore.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ustavillavicencio.edu.co.bookstore.dto.request.AuthorRequest;
import ustavillavicencio.edu.co.bookstore.dto.response.ApiResponse;
import ustavillavicencio.edu.co.bookstore.dto.response.AuthorResponse;
import ustavillavicencio.edu.co.bookstore.dto.response.BookResponse;
import ustavillavicencio.edu.co.bookstore.service.AuthorService;

@RestController
@RequestMapping("/authors")
@RequiredArgsConstructor
@Validated
public class AuthorController {

    private final AuthorService authorService;

    @PostMapping
    public ResponseEntity<ApiResponse<AuthorResponse>> create(@Valid @RequestBody AuthorRequest request) {
        AuthorResponse response = authorService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.created(response, "Autor creado exitosamente"));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<AuthorResponse>>> findAll() {
        List<AuthorResponse> response = authorService.findAll();
        return ResponseEntity.ok(ApiResponse.success(response, "Autores obtenidos exitosamente"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<AuthorResponse>> findById(@PathVariable Long id) {
        AuthorResponse response = authorService.findById(id);
        return ResponseEntity.ok(ApiResponse.success(response, "Autor obtenido exitosamente"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<AuthorResponse>> update(
            @PathVariable Long id,
            @Valid @RequestBody AuthorRequest request) {
        AuthorResponse response = authorService.update(id, request);
        return ResponseEntity.ok(ApiResponse.success(response, "Autor actualizado exitosamente"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        authorService.delete(id);
        return ResponseEntity.ok(ApiResponse.success(null, "Autor eliminado exitosamente"));
    }

    @GetMapping("/{id}/books")
    public ResponseEntity<ApiResponse<List<BookResponse>>> findBooksByAuthor(@PathVariable Long id) {
        List<BookResponse> response = authorService.findBooksByAuthorId(id);
        return ResponseEntity.ok(ApiResponse.success(response, "Libros del autor obtenidos exitosamente"));
    }
}
