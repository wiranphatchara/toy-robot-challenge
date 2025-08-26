package toyrobot.model;

public record Position(int x, int y) {
    public Position move(Direction direction) {
        return new Position(
                x + direction.getDeltaX(),
                y + direction.getDeltaY()
        );
    }

    public boolean isValidPosition() {
        return x < 0 || x > 4 || y < 0 || y > 4;
    }
}