package toyrobot.model;

import toyrobot.exception.InvalidMoveException;
import toyrobot.exception.RobotNotPlacedException;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class Robot {
    private Position position;
    private Direction direction;
    private boolean placed = false;

    public void place(int x, int y, Direction direction) {
        Position newPosition = new Position(x, y);
        if (newPosition.isValidPosition()) {
            throw new InvalidMoveException("Cannot place robot outside table");
        }

        this.position = newPosition;
        this.direction = direction;
        this.placed = true;
    }

    public void move() {
        if (!placed) {
            throw new RobotNotPlacedException("Robot must be placed first");
        }

        Position newPosition = position.move(direction);
        if (newPosition.isValidPosition()) {
            throw new InvalidMoveException("Cannot move - robot would fall");
        }

        this.position = newPosition;
    }

    public void turnLeft() {
        if (!placed) {
            throw new RobotNotPlacedException("Robot must be placed first");
        }
        this.direction = direction.turnLeft();
    }

    public void turnRight() {
        if (!placed) {
            throw new RobotNotPlacedException("Robot must be placed first");
        }
        this.direction = direction.turnRight();
    }

    public String getCurrentPosition() {
        if (!placed) {
            return "Robot is not placed on the table";
        }
        return String.format("%d,%d,%s", position.x(), position.y(), direction);
    }
}