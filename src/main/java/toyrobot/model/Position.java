package toyrobot.model;

public record Position(int x, int y) {
    public Position move(Direction direction) {
        return new Position(
                x + direction.getDeltaX(),
                y + direction.getDeltaY()

                // ex. new Position(1, 1);
                // Position newPos = current.move(Direction.NORTH);
                // newPos will be Position(1, 2) cuz NORTH direction is (0, 1)
        );
    }

    public boolean isValidPosition() {
        return x < 0 || x > 4 || y < 0 || y > 4;
    }
}