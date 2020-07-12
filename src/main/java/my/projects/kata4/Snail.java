package my.projects.kata4;

import java.security.InvalidParameterException;
import java.util.List;

public class Snail {

    public static int[] snail(int[][] array) {
        int xSize = array.length;
        int ySize = xSize == 0 ? 0 : array[0].length;
        if(ySize > 0 && xSize != ySize) {
            throw new InvalidParameterException("the array should have same size in both dimensions!");
        }
        Cursor cursor = new Cursor(ySize);
        MovingPattern movement = new AntiClockWiseMovement(cursor);
        do {
            movement.makeMove();
        } while (movement.cursorHasLastMoved());

        return extractSequence(array, cursor.getVisitedPositions());
    }

    private static int[] extractSequence(int[][] array, List<Position> visitedPositions) {
        if(visitedPositions.size() == 0)
            return new int[0];
        int[] sequence = new int[visitedPositions.size()];
        for (int i = 0; i < visitedPositions.size(); i++) {
            Position position = visitedPositions.get(i);
            sequence[i] = array[position.getX()][position.getY()];
        }
        return sequence;
    }

}
