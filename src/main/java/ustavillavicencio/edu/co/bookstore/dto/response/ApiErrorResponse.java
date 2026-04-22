package ustavillavicencio.edu.co.bookstore.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ApiErrorResponse {

    private boolean success;
    private String message;
    private List<String> errors;
    private int errorCode;
    private long timestamp;
    private String path;
    
}
