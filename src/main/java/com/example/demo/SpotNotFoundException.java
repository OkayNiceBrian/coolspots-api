package coolspots;

public class SpotNotFoundException extends RuntimeException {

	public SpotNotFoundException(Long id) {
		super("Spot not found: spotId " + id);
	}
}
