package toyrobot.exception;

public class RobotNotPlacedException extends RuntimeException {
    public RobotNotPlacedException(String message) {
        super(message);
    }
}