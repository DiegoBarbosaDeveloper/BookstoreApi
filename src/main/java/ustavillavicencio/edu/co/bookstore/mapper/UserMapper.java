package ustavillavicencio.edu.co.bookstore.mapper;

import ustavillavicencio.edu.co.bookstore.dto.request.RegisterRequest;
import ustavillavicencio.edu.co.bookstore.entity.UserEntity;
import ustavillavicencio.edu.co.bookstore.enums.UserRole;

public class UserMapper {

	private UserMapper() {
	}

	public static UserEntity toEntity(RegisterRequest request) {
		if (request == null) {
			return null;
		}

		UserEntity user = new UserEntity();
		user.setUsername(request.getUsername());
		user.setEmail(request.getEmail());
		user.setPassword(request.getPassword());
		user.setRole(UserRole.USER);
		return user;
	}
}
