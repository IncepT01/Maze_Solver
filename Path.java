package pgdp.maze;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

// TODO: Implementiere diese Klasse, sodass sie eine Sequenz von Directions repräsentiert
public class Path {
    // TODO: Attribut(e)

    private ArrayList<Direction> directions;

    // TODO: Soll einen leeren Pfad erzeugen
    public Path() {
        this.directions = new ArrayList<Direction>();
    }

    // TODO: Soll ein HashSet<Position> mit allen Positionen zurückgeben, die man beim Ablaufen des Pfades 'this'
    //  besucht, wenn man bei der Position 'start' beginnt (ungeachtet irgendwelcher WALLs o.Ä.)
    public Set<Position> toPositionSet(Position start) {
        HashSet<Position> positions = new HashSet<Position>();
        int currentI = start.getI();
        int currentJ = start.getJ();

        positions.add(start);

        for (int i = 0; directions.size()>i;i++){
            if (directions.get(i) == Direction.UP){
                currentI--;
                positions.add(new Position(currentI,currentJ));
            }
            if (directions.get(i) == Direction.DOWN){
                currentI++;
                positions.add(new Position(currentI,currentJ));
            }
            if (directions.get(i) == Direction.LEFT){
                currentJ--;
                positions.add(new Position(currentI,currentJ));
            }
            if (directions.get(i) == Direction.RIGHT){
                currentJ++;
                positions.add(new Position(currentI,currentJ));
            }

        }
        return positions;
    }

    // TODO: Soll die übergebene Richtung 'direction' vorne in die bisherige Sequenz einfügen
    public void prepend(Direction direction) {
        directions.add(0,direction);
    }

    // TODO: Soll die Richtung am übergebenen 'index' zurückgeben
    public Direction getStep(int index) {
        return directions.get(index);
    }

    // TODO: Soll eine String-Repräsentation des Pfades 'this' zurückgeben, wie in der Aufgabenstellung beschrieben
    @Override
    public String toString() {
        return this.directions.toString();
    }

}
