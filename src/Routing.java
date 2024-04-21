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


        Map<Coord, Boolean> visited = new HashMap<>();
        Map<Coord, Coord> parent = new HashMap<>();
        Map<Coord, Integer> illegalCoords = new HashMap<>();


        for(Endpoints currentEndpoints : goals){
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
                        /*if(board.isObstacle(adjCoord) || illegalCoords.get(adjCoord) == currentEndpoints.id){
                            continue;
                        }*/
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

            /*for(Coord coord : currentPath){
                if(board.isOccupied(parent.get(coord))){
                    for (Wire path : paths) {
                        if (path.id == board.getValue(parent.get(coord))) {
                            illegalCoords.putIfAbsent(parent.get(coord), currentEndpoints.id);
                            Endpoints endpoint = new Endpoints(path.id, path.start(), path.end());
                            *//*badEndpoints.add(0, endpoint);*//*
                            goals.remove(currentEndpoints);
                            board.removeWire(path);
                            goals.add(endpoint);
                            System.out.println("Wire " + path.id + " removed.");
                            System.out.println("Board: ");
                            board.show();
                            break;
                        }
                    }
                }
            }*/
            /*System.out.println("Wire being placed: " + currentEndpoints.id);
            System.out.println("Board: ");
            board.show();*/


            Wire currWire = new Wire(currentEndpoints.id, currentPath);
            paths.add(currWire);
            board.placeWire(currWire);

            /*Test Prints*/
            /*System.out.println("Current Endpoints: " + currentEndpoints.id);
            System.out.println("Board: ");
            board.show();*/

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


    /*
     * Following code creates a list that acts as the path for the wire
     * connecting the start and end of the "currentEndpoints". Does so by
     * starting at the end Node, then looping through the "parent" list,
     * adding the parent of each node to the beginning of the list. Which
     * means in the end, the "currentPath" list starts with the "currentEndpoints.start" node
     * and ends with the "currentEndpoints.end" node, with the in between values being the path.
     * That path is then used to create a Wire which is then added to the "paths" list
     */


    /*
    * Potentially useful for later code:
    */

    /*ArrayList<Endpoints> badEndpoints = new ArrayList<>();*/

    /*if(board.isObstacle(adjCoord)){
                            continue;
                        }*/

    /*if(board.isOccupied(parent.get(currNode))){
                    for (Wire path : paths) {
                        if (path.id == board.getValue(parent.get(currNode))) {
                            Endpoints endpoint = new Endpoints(path.id, path.start(), path.end());
                            badEndpoints.add(endpoint);
                            board.removeWire(path);
                            break;
                        }
                    }
                }*/

    /*if(!badEndpoints.isEmpty()){
            ArrayList<Wire> badPaths = findPaths(board, badEndpoints);
            paths.addAll(badPaths);
        }*/
}
