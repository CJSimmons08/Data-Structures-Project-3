<h2>Overview of the Algorithm:</h2>
<p>At it's core the algorithm is BFS, however, to avoid 
wires blocking each other, the list of goals is first
sorted by shortest distance between end and start points.
Then, to also improve BFS, once the search has found the 
needed end point the search ends to not waste time 
searching the entire grid over and over again. Then
the parent map that stores the path is used to create
the list passed to the Wire constructor, then the Wire
is added to the board.
</p>
<h2> Interesting Board Examples:</h2>
<p> </p>
<h2> Finding and minimizing wire layouts: </h2>
<p> Since the algorithm is mostly just BFS with some 
slight modifications, it naturally finds the shortest
path to the goal. However, because the algorithm first
sorts the list by shortest distance, the total wire
distance is made as short as possible while avoiding wire overlap.</p>
<h2>Time complexity evaluation:</h2>
<p> Because the algorithm is BFS and thus would eventually
check every Coord on the board in the worst case scenario,
the overall time complexity of my algorithm is O(n).</p>