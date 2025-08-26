package toyrobot.controller;


import toyrobot.exception.GlobalExceptionHandler;
import toyrobot.service.RobotService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Slf4j
@Component
@RequiredArgsConstructor
public class RobotController implements CommandLineRunner {

    private final RobotService robotService;
    private final GlobalExceptionHandler exceptionHandler;

    @Override
    public void run(String... args) throws Exception {
        log.info("Starting Toy Robot Simulator ...");

        System.out.println("#############################################");
        System.out.println("Toy Robot Simulator");
        System.out.println("Commands: PLACE X,Y,F | MOVE | LEFT | RIGHT | REPORT");
        System.out.println("Type 'exit' to quit");
        System.out.println("#############################################");

        processConsoleInput();
        log.info("Toy Robot Simulator terminated ...");
    }

    private void processConsoleInput() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String input;
            while ((input = reader.readLine()) != null) {
                input = input.trim();

                if ("exit".equalsIgnoreCase(input)) {
                    System.out.println("Goodbye");
                    break;
                }

                if (!input.isEmpty()) {
                    log.debug("Processing command: {}", input);
                    executeCommand(input);
                }
            }
        } catch (IOException e) {
            log.error("Error reading console input", e);
        }
    }

    private void executeCommand(String command) {
        try {
            robotService.executeCommand(command);
        } catch (Exception e) {
            exceptionHandler.handleException(e);
        }
    }
}
