package toyrobot.exception;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class GlobalExceptionHandler {

    public void handleException(Exception exception) {
        if (exception instanceof InvalidCommandException) {
            log.error("Invalid command: {}", exception.getMessage());
            return;
        }

        if (exception instanceof RobotNotPlacedException) {
            log.error("Robot not placed: {}", exception.getMessage());
            return;
        }

        if (exception instanceof InvalidMoveException) {
            log.error("Invalid move: {}", exception.getMessage());
            return;
        }

        log.error("System error: {}", exception.getMessage());
    }
}