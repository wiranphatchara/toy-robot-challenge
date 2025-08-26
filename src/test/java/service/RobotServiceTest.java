package service;

import toyrobot.exception.InvalidCommandException;
import toyrobot.model.Direction;
import toyrobot.model.Robot;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import toyrobot.service.RobotService;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RobotServiceTest {

    @Mock
    private Robot robot;

    @InjectMocks
    private RobotService robotService;

    @Test
    void shouldExecutePlaceCommand() {
        robotService.executeCommand("PLACE 1,2,NORTH");
        verify(robot).place(1, 2, Direction.NORTH);
    }

    @Test
    void shouldExecuteMoveCommand() {
        robotService.executeCommand("MOVE");
        verify(robot).move();
    }

    @Test
    void shouldExecuteLeftCommand() {
        robotService.executeCommand("LEFT");
        verify(robot).turnLeft();
    }

    @Test
    void shouldExecuteRightCommand() {
        robotService.executeCommand("RIGHT");
        verify(robot).turnRight();
    }

    @Test
    void shouldExecuteReportCommand() {
        when(robot.getCurrentPosition()).thenReturn("1,2,NORTH");
        robotService.executeCommand("REPORT");
        verify(robot).getCurrentPosition();
    }

    @Test
    void shouldThrowExceptionForInvalidCommand() {
        assertThatThrownBy(() -> robotService.executeCommand("INVALID"))
                .isInstanceOf(InvalidCommandException.class);
    }

    @Test
    void shouldThrowExceptionForEmptyCommand() {
        assertThatThrownBy(() -> robotService.executeCommand(""))
                .isInstanceOf(InvalidCommandException.class);
    }

    @Test
    void shouldThrowExceptionForInvalidPlaceFormat() {
        assertThatThrownBy(() -> robotService.executeCommand("PLACE 1,2"))
                .isInstanceOf(InvalidCommandException.class);

        assertThatThrownBy(() -> robotService.executeCommand("PLACE"))
                .isInstanceOf(InvalidCommandException.class);
    }

    @Test
    void shouldThrowExceptionForInvalidCoordinates() {
        assertThatThrownBy(() -> robotService.executeCommand("PLACE a,b,NORTH"))
                .isInstanceOf(InvalidCommandException.class);
    }

    @Test
    void shouldThrowExceptionForInvalidDirection() {
        assertThatThrownBy(() -> robotService.executeCommand("PLACE 1,2,INVALID"))
                .isInstanceOf(InvalidCommandException.class);
    }

    @Test
    void shouldHandleLowercaseCommands() {
        robotService.executeCommand("move");
        verify(robot).move();
    }
}
