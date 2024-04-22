import javax.crypto.EncryptedPrivateKeyInfo;
import java.util.*;

public class Routing {

    /**
     * TODO
     * <p>
     * The findPaths function takes a board and a list of goals that contain
     * endpoints that need to be connected. The function returns a list of
     * Paths that connect the points.
     */
    public static ArrayList<Wire>
    findPaths(Board board, ArrayList<Endpoints> goals) {

        ArrayList<Wire> paths = new ArrayList<>();


        Map<Coord, Boolean> visited = new HashMap<>();
        Map<Coord, Coord> parent = new HashMap<>();


        ArrayList<Endpoints> sortedGoals = (ArrayList<Endpoints>) goals.clone();
        sortedGoals.sort(Routing::shorterHypotenuse);


        /*
        * Find/Create Paths Loop:
        */
        for(Endpoints currentEndpoints : sortedGoals){
            if(currentEndpoints.start.equals(currentEndpoints.end)){
                ArrayList<Coord> selfList = new ArrayList<>();
                selfList.add(currentEndpoints.start);
                Wire selfWire = new Wire(currentEndpoints.id, selfList);
                paths.add(selfWire);
                continue;
            }
            /*
            * BFS Loop:
            */
            Queue<Coord> Q = new LinkedList<>();
            Q.add(currentEndpoints.start);
            parent.put(currentEndpoints.start, currentEndpoints.start);
            visited.put(currentEndpoints.start, true);
            while (!Q.isEmpty()) {
                Coord u = Q.remove();
                visited.putIfAbsent(u, true);
                for(Coord coord : board.adj(u)){
                    visited.putIfAbsent(coord, false);
                }
                for (Coord adjCoord : board.adj(u)) {
                    if (!visited.get(adjCoord)) {
                        if(board.isObstacle(adjCoord) ||
                                (board.isOccupied(adjCoord) &&
                                        board.getValue(adjCoord) != currentEndpoints.id)){
                            continue;
                        }
                        parent.put(adjCoord, u);
                        Q.add(adjCoord);
                        visited.put(adjCoord, true);
                        if(board.getValue(adjCoord) == currentEndpoints.id){
                            Q.clear();
                            break;
                        }
                    }
                }
            }
            /*
            * END of BFS Loop.
            */

            /*
            * Loop that creates list for wire path
            */
            Coord currNode = currentEndpoints.end;
            List<Coord> currentPath = new ArrayList<>();
            currentPath.add(currNode);
            while(!currNode.equals(currentEndpoints.start)){
                currNode = parent.get(currNode);
                currentPath.add(0, currNode);
            }

            Wire currWire = new Wire(currentEndpoints.id, currentPath);
            paths.add(currWire);
            board.placeWire(currWire);

            parent.clear();
            visited.clear();
        }

        return paths;
    }

    public static int shorterHypotenuse(Endpoints a, Endpoints b){
        double distanceA = (Math.pow((a.start.row - a.end.row), 2) + Math.pow((a.start.column - a.end.column), 2));
        double distanceB = (Math.pow((b.start.row - b.end.row), 2) + Math.pow((b.start.column - b.end.column), 2));
        return Double.compare(distanceA, distanceB);
    }

}
