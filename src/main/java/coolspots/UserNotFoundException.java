package coolspots;

public class UserNotFoundException extends RuntimeException {
	
	public UserNotFoundException(Long id) {
		super("User not found: userId " + id);
	}
}
