package toyrobot.service;

import toyrobot.exception.InvalidCommandException;
import toyrobot.model.Direction;
import toyrobot.model.Robot;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RobotService {
    //Call robot model
    private final Robot robot;

    public void executeCommand(String command) {

        // Clean command from user input
        String[] parts = command.trim().toUpperCase().split("\\s+");
        // Retrieve only command ex. "PLACE"
        String action = parts[0];
        switch (action) {
            case "PLACE" -> handlePlaceCommand(parts);
            case "MOVE" -> robot.move();
            case "LEFT" -> robot.turnLeft();
            case "RIGHT" -> robot.turnRight();
            case "REPORT" -> System.out.println(robot.getCurrentPosition());
            default -> throw new InvalidCommandException("Unknown command : " + action);
        }
    }

    private void handlePlaceCommand(String[] parts) {

        if (parts.length != 2) {
            throw new InvalidCommandException("Invalid PLACE command format");
        }
        // parts[1] should be ex. "1,2,NORTH"
        String[] placeParams = parts[1].split(",");
        //placeParams should be ex. "1","2","NORTH"
        if (placeParams.length != 3) {
            throw new InvalidCommandException("PLACE requires X,Y,Direction");
        }

        try {
            int x = Integer.parseInt(placeParams[0]);
            int y = Integer.parseInt(placeParams[1]);
            Direction direction = Direction.valueOf(placeParams[2]);
            robot.place(x, y, direction);
        }catch (IllegalArgumentException e) {
            throw new InvalidCommandException("Invalid direction");
        }
    }
}