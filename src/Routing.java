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
        * BFS Loop:
        */

        for(Endpoints endpoint : goals){

        }


        /*
        General outline of everything(initial idea):
        Create: Array of Lists? Maybe a 2d array instead? where each -row? column?-
                one of the two is storing, in order the coords of each node in the path
        for each starting point in "goals":
            do bfs on the grid, searching for the "endpoint", storing each node on the path
            in the correct array/list


        NOTE TO SELF:
            eventually will want to figure out how to "experiment with improvements to BFS"
            or "alternative algorithms"
        */

        return null;  // replace this line with your code
    }

}
