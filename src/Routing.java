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

        //Might need another auxiliary data structure to keep track of each list of coords
        //at a time before each "wire" is finalized then added to this list
        ArrayList<Wire> paths = new ArrayList<>();

        /*
        * If visited continues causing problems. might have to come up with
        * a way to just set Coord's values to some int (maybe -2? for not visited
        * and -3 for visited?)
        */
        Map<Coord, Boolean> visited = new HashMap<>();
        Map<Coord, Coord> parent = new HashMap<>();




        for(Endpoints currentEndpoints : goals){
            /*
            * BFS Loop:
            */
            Queue<Coord> Q = new LinkedList<>();
            Q.add(currentEndpoints.start);
            parent.put(currentEndpoints.start, currentEndpoints.start);
            visited.put(currentEndpoints.start, true);
            while (!Q.isEmpty()) {
                Coord u = Q.remove();
                for(Coord coord : board.adj(u)){
                    visited.putIfAbsent(coord, false);
                }
                for (Coord adjCoord : board.adj(u)) {
                    if (!visited.get(adjCoord)) {
                        /*
                        * Need to figure out when to update a Coord's value
                        * so that the board.isOccupied check is real and useful
                        */
                        if(board.isObstacle(adjCoord) ||
                                (board.isOccupied(adjCoord) &&
                                        board.getValue(adjCoord) != currentEndpoints.id)){
                            continue;
                        }
                        parent.put(adjCoord, u);
                        Q.add(adjCoord);
                        visited.put(adjCoord, true);
                    }
                }
            }
            /*
            * END of BFS Loop.
            */

            /*
            * Following code creates a list that acts as the path for the wire
            * connecting the start and end of the "currentEndpoints". Does so by
            * starting at the end Node, then looping through the "parent" list,
            * adding the parent of each node to the beginning of the list. Which
            * means in the end, the "currentPath" list starts with the "currentEndpoints.start" node
            * and ends with the "currentEndpoints.end" node, with the in between values being the path.
            * That path is then used to create a Wire which is then added to the "paths" list
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



        /*
        NOTE TO SELF:
            eventually will want to figure out how to "experiment with improvements to BFS"
            or "alternative algorithms"
        */

        return paths;
    }

}
