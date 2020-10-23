package my.projects.katas.kata4;

class AntiClockWiseMovement implements MovingPattern {
    private final Cursor cursor;

    public AntiClockWiseMovement(Cursor cursor) {
        this.cursor = cursor;
    }

    @Override
    public void makeMove() {
        cursor.moveLeft();
        cursor.moveDown();
        cursor.moveRight();
        cursor.moveUp();
    }

    @Override
    public boolean cursorHasLastMoved() {
        return cursor.hasLastMoved();
    }
}
