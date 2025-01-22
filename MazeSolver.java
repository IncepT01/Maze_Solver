package pgdp.maze;

import java.util.ArrayList;

public final class MazeSolver {

    // Man soll von außen keine Objekte der Klasse erzeugen können. Alle Methoden
    // sind static. Daher ist der Konstruktor private
    private MazeSolver() {

    }

    // TODO: Implementiere diese Methode, sodass sie einen Pfad vom Eingang
    // ('entrance') zum Ausgang ('exit')
    // des übergebenen Labyrinths 'maze' zurückgibt.
    public static Path solveMaze(Maze maze) {
        Path result;

        result = solveMazeFrom(maze,maze.getEntrance());
        //System.out.println(result.toString());

        return result;
    }

    // TODO: Implementiere diese Methode, sodass sie einen Pfad vom der übergebenen
    // Position 'position'
    // zum Ausgang ('exit') des übergebenen Labyrinths 'maze' zurückgibt.
    // Sie muss rekursiv implementiert werden.
    public static Path solveMazeFrom(Maze maze, Position position) {
        Path result;
        Position startPos = position;
        ArrayList<Position> deadEnd = new ArrayList<>();
        ArrayList<Direction> directions = new ArrayList<>();
        ArrayList<Position> path = new ArrayList<>();

        result = MazeSolver.helpRec(maze,position,startPos,deadEnd,directions,path);

        return result;
    }

    // Zum Testen
    public static void main(String[] args) {
        Maze maze = MazeParser.parseFromFile("resources/Maze.txt");
        if (maze == null) {
            return;
        }

        System.out.println(maze);

        Path path = solveMaze(maze);
        System.out.println(path);
        System.out.println();
        System.out.println(maze.toString(path));
    }


    public static Path helpRec(Maze maze, Position position, Position startPos ,ArrayList<Position> deadEnd, ArrayList<Direction> directions, ArrayList<Position> path){

        Path res = new Path();

        if(position.getI() == maze.getExit().getI() && position.getJ() == maze.getExit().getJ()){
            //Path res = new Path();
            for (int i = directions.size()-1; i >= 0; i--){
                res.prepend(directions.get(i));
            }
            return res;
        }

        Position left = new Position(position.getI(), position.getJ()-1);
        Position down = new Position(position.getI()+1, position.getJ());
        Position right = new Position(position.getI(), position.getJ()+1);
        Position up = new Position(position.getI()-1, position.getJ());


        //checking the tile to the left
        if (maze.isEmptyTile(left) && !deadEnd.contains(left) && !path.contains(left)){
            directions.add(Direction.LEFT);
            path.add(left);
            res = helpRec(maze,left,startPos,deadEnd,directions,path);
        }
        //checking the lower tile
        else if (maze.isEmptyTile(down) && !deadEnd.contains(down) && !path.contains(down)){
            directions.add(Direction.DOWN);
            path.add(down);
            res = helpRec(maze,down,startPos,deadEnd,directions,path);
        }
        //checking the tile to the right
        else if (maze.isEmptyTile(right) && !deadEnd.contains(right) && !path.contains(right)){
            directions.add(Direction.RIGHT);
            path.add(right);
            res = helpRec(maze,right,startPos,deadEnd,directions,path);
        }
        //checking the upper tile
        else if (maze.isEmptyTile(up) && !deadEnd.contains(up) && !path.contains(up)){
            directions.add(Direction.UP);
            path.add(up);
            res = helpRec(maze,up,startPos,deadEnd,directions,path);
        }
        //if we can't go anywhere, we add our position to the dead end list, and start the recursion again from the original start.
        else {
            deadEnd.add(position);
            //If we can go anywhere from the entrance or the given position, then there is no solution.
            if (position.getI() == maze.getEntrance().getI() && position.getJ() == maze.getEntrance().getJ()){
                return null;
            }
            directions.clear();
            path.clear();
            path.add(startPos);
            res = helpRec(maze,startPos,startPos,deadEnd,directions,path);
        }

        return res;
    }


}
