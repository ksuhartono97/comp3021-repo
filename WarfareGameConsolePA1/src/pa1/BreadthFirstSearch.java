package pa1;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class BreadthFirstSearch {
    private Queue<MapLoc> queue;
    private ArrayList<MapLoc> locationArray = new ArrayList<MapLoc>();
    private int movementRange;
    private int[][] terrain;
    private MapLoc startNode;
    private MapLoc targetNode;
    private int width;
    private int height;

    static class MapLoc {
        int locationX;
        int locationY;
        boolean visited;
        int distance;
        public MapLoc(int locX, int locY) {
            this.locationX = locX;
            this.locationY = locY;
            visited = false;
            distance = 0;
        }

        @Override
        public String toString() {
            return "(" + this.locationX + ", " + this.locationY + ")";
        }

        @Override
        public boolean equals(Object obj) {
            if(obj instanceof MapLoc)
                return (this.locationX == ((MapLoc)obj).locationX && this.locationY == ((MapLoc)obj).locationY);
            else return false;
        }
    }

    public BreadthFirstSearch (int[][] terrain, int movementRange, int locationX, int locationY, int targetX, int targetY, int w, int h) {
        queue = new LinkedList<MapLoc>();
        this.movementRange = movementRange;
        this.terrain = terrain;
        this.startNode = new MapLoc(locationX, locationY);
        this.targetNode = new MapLoc(targetX, targetY);
        this.width = w;
        this.height = h;
        locationArray.add(this.startNode);

        // Convert all map locations for BFS use
        for(int i = 0; i < height; i++) {
            for(int j = 0; j< width; j++) {
                if(i == locationY && j == locationY) {
                    continue;
                }
                locationArray.add(new MapLoc(j, i));
            }
        }
    }

    public ArrayList<MapLoc> findNeighbours (MapLoc maploc) {
        ArrayList<MapLoc> neighbours = new ArrayList<MapLoc>();
        //Check E
        for (MapLoc obj : locationArray) {
            if (obj.equals(new MapLoc(maploc.locationX + 1, maploc.locationY)) && !obj.visited) {
                if(isValid(obj)) {
                    neighbours.add(obj);
                }
            }
        }
        //Check N
        for (MapLoc obj : locationArray) {
            if (obj.equals(new MapLoc(maploc.locationX, maploc.locationY+1)) && !obj.visited) {
                if(isValid(obj)) {
                    neighbours.add(obj);
                }
            }
        }
        //Check W
        for (MapLoc obj : locationArray) {
            if (obj.equals(new MapLoc(maploc.locationX-1, maploc.locationY)) && !obj.visited) {
                if(isValid(obj)) {
                    neighbours.add(obj);
                }
            }
        }
        //Check S
        for (MapLoc obj : locationArray) {
            if (obj.equals(new MapLoc(maploc.locationX, maploc.locationY-1)) && !obj.visited) {
                if(isValid(obj)) {
                    neighbours.add(obj);
                }
            }
        }
        return neighbours;
    }

    public boolean bfs () {
        queue.add(this.startNode);
        this.startNode.visited = true;

        while (!queue.isEmpty()) {
            MapLoc loc = queue.remove();
            if(loc.distance > this.movementRange) break;

            ArrayList<MapLoc> neighbours = findNeighbours(loc);

            for (MapLoc node : neighbours) {
                if(node.equals(targetNode)) {
                    return true;
                }
                if(!node.visited) {
                    queue.add(node);
                    node.visited = true;
                    node.distance = loc.distance+1;
                }
            }
        }
        return false;
    }

    private boolean isValid (MapLoc loc) {
        if(loc.locationX >= width && loc.locationY >= height) return false;

        if (terrain[loc.locationY][loc.locationX] == GameMap.TERRAIN_EMPTY) {
            return true;
        }
        else {
            return false;
        }
    }
}
