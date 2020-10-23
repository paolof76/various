package my.projects.katas.kata4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Cursor {
    private List<Position> visitedPositions;
    private Position currentPosition;
    private int dimension;
    private boolean hasMoved;

    public Cursor(int dimension) {
        this.dimension = dimension;
        this.currentPosition = new Position(0, 0);
        this.visitedPositions = dimension > 0 ? new ArrayList<>(List.of(currentPosition)) : Collections.emptyList();
        this.hasMoved = false;
    }

    public List<Position> getVisitedPositions() {
        return visitedPositions;
    }

    public void moveLeft() {
        while(currentPosition.getY() + 1 <= dimension-1 &&
                !visitedPositions.contains(new Position(currentPosition.getX(), currentPosition.getY() + 1))) {
            currentPosition = new Position(currentPosition.getX(), currentPosition.getY() + 1);
            visitedPositions.add(currentPosition);
            hasMoved = true;
        }
    }

    public void moveDown() {
        while(currentPosition.getX() + 1 <= dimension-1 &&
                !visitedPositions.contains(new Position(currentPosition.getX() + 1, currentPosition.getY()))) {
            currentPosition = new Position(currentPosition.getX() + 1, currentPosition.getY());
            visitedPositions.add(currentPosition);
            hasMoved = true;
        }
    }

    public void moveRight() {
        while(currentPosition.getY() - 1 >= 0 &&
                !visitedPositions.contains(new Position(currentPosition.getX(), currentPosition.getY() - 1))) {
            currentPosition = new Position(currentPosition.getX(), currentPosition.getY() - 1);
            visitedPositions.add(currentPosition);
            hasMoved = true;
        }
    }

    public void moveUp() {
        while(currentPosition.getX() - 1 >= 0 &&
                !visitedPositions.contains(new Position(currentPosition.getX() - 1, currentPosition.getY()))) {
            currentPosition = new Position(currentPosition.getX() - 1, currentPosition.getY());
            visitedPositions.add(currentPosition);
            hasMoved = true;
        }
    }

    public boolean hasLastMoved() {
        boolean hasLastMoved = hasMoved;
        hasMoved = false;
        return hasLastMoved;
    }
}
