import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.NoSuchElementException;

// --== CS400 File Header Information ==--
// Name: Jonah Peter Ryan
// Email: jpryan5@wisc.edu
// Team: Red
// Group: Group: AA
// TA: Mu Cai
// Lecturer: Florian Heimerl
// Notes to Grader: N/A

/**
 * @author Jonah Ryan
 *
 */
public class Backend implements BackendInterface {

  ArrayList<EdgeData> edgeArray; // Array of edges to add to graph
  ArrayList<NodeData> nodeArray; // Array of nodes to add to graph
  CS400Graph<String> graph; // The main graph
  MapDataReader data;

  /**
   * This is the main backend constructor. This method triggers the graph construction along with
   * the rest of the project.
   * 
   * This method will also store the information provided by the MapDataReader.
   * 
   * @throws FileNotFoundException when the user doesn't have the provided csv files.
   */
  public Backend() throws FileNotFoundException {
    // Instantiate the MapDataReader, using the provided csv files.
    try {
      data = new MapDataReader(new FileReader("BuildingData.csv"), new FileReader("EdgeData.csv"));
    } catch (FileNotFoundException e) {
      throw new FileNotFoundException("BuildingData.csv and EdgeData.csv was not found in your file system.");
    }

    // Store the MapDatReaders information
    edgeArray = data.getEdgeData();
    nodeArray = data.getVertices();
    graph = new CS400Graph<String>();

    // Build the graph and start the program.
    buildGraph();
  }

  /**
   * This is the backend test constructor using pre-built information instead of reading the
   * provided csv files.
   * 
   * @param test this helps distinct the two constructors
   */
  public Backend(Boolean test) {
    graph = new CS400Graph<String>();
    graph.insertVertex("A", new NodeData("A", "Dorm"));
    graph.insertVertex("B", new NodeData("B", "Dorm"));
    graph.insertVertex("C", new NodeData("C", "Dining Hall"));
    graph.insertVertex("D", new NodeData("D", "Library"));
    graph.insertEdge("A", "B", 2.1);
    graph.insertEdge("B", "A", 2.1);
    graph.insertEdge("B", "D", 4.21);
    graph.insertEdge("A", "C", 2.26);
  }

  /**
   * This method builds the graph with the provided information from the MapDataReader
   */
  private void buildGraph() {
    // Add the Vertices
    for (NodeData n : nodeArray) {
      graph.insertVertex(n.name, n); // This uses a modified insertVertex method.
    }
    // Add the edges
    for (EdgeData e : edgeArray) {
      graph.insertEdge(e.startingVertex, e.endingVertex, e.weight);
    }
  }


  @Override
  /**
   * This method gets the shortest directions between two specified locations
   * 
   * @param startLocation is the location the path will start from
   * @param endLocation   is the paths target destination
   * @return String[] that stores the found directions
   */
  public String[] getDirections(String startLocation, String endLocation) {
    // Find the shortest path between two places
    List<String> pathList = graph.shortestPath(startLocation, endLocation);

    // Format the List<String> as a String[] by iterating over the List.
    String[] finalPath = new String[pathList.size()];

    for (int i = 0; i < pathList.size(); i++) {
      finalPath[i] = pathList.get(i);
    }

    // Return the found path as a String[]
    return finalPath;
  }

  @Override
  /**
   * This method gets the shortest time between two locations, accounting for if the user is walking
   * or biking.
   * 
   * This website: https://www.active.com/fitness/calculators/pace helped was used to test the
   * accuracy of the result.
   * 
   * @param startLocation is the location the user will start from
   * @param endLocation   is the target destination
   * @param biking        tells the program if the user is biking, true if biking, false if walking
   * @return the time between the two locations in the format hhmmss, as a int
   */
  public int getTimeBetween(String startLocation, String endLocation, Boolean biking) {

    // Gets the quickest path to two locations on campus, in miles.
    double pathCost = graph.getPathCost(startLocation, endLocation);

    int hr, min, sec;

    /*
     * To account for the difference between biking and walking times, the program multiplies the
     * cost by 10 or 20. The 10 and 20 are the average biking and running paces in minutes.
     * 
     * By multiplying the quickest distance, in miles, by the mile pace, we get the total minutes
     * traveled.
     */
    if (biking) {
      pathCost *= 5;
    } else {
      pathCost *= 20;
    }
    // This spits up the total minutes into two chunks, the decimal section and the non decimal
    // part.

    int pathCostPart1 = (int) pathCost;
    double pathCostPart2 = pathCost - pathCostPart1;

    // Gets the hour, minutes and seconds of the trip.
    hr = (int) pathCostPart1 / 60;
    min = (int) (((pathCost / 60) - hr) * 60);
    sec = (int) (pathCostPart2 * 60);
    
    return formatTime(hr, min, sec);
  }

  /**
   * Formats a time with the style hhmmss.
   * 
   * @param hr
   * @param min
   * @param sec
   * @return the time in hhmmss format as a int.
   */
  private int formatTime(int hr, int min, int sec) {
    String finalHr, finalMin, finalSec;

    // Format the hours
    if (hr < 10) {
      finalHr = "0" + String.valueOf(hr);
    } else {
      finalHr = String.valueOf(hr);
    }

    // Format the minutes
    if (min < 10) {
      finalMin = "0" + String.valueOf(min);
    } else {
      finalMin = String.valueOf(min);
    }

    // Format the seconds
    if (sec < 10) {
      finalSec = "0" + String.valueOf(sec);
    } else {
      finalSec = String.valueOf(sec);
    }

    // Return the formatted time
    return Integer.valueOf(finalHr + finalMin + finalSec);
  }

  @Override
  /**
   * Finds the Dining Hall closest to a given location
   * 
   * @param startLocation the name of the starting location
   * @return the name of the closest Dining Hall.
   */
  public String findNearestDining(String startLocation) {
    return findNearestType(startLocation, "Dining Hall");
  }

  @Override
  /**
   * Finds the Library closest to a given location
   * 
   * @param startLocation the name of the starting location
   * @return the name of the closest Library
   */
  public String findNearestLibrary(String startLocation) {
    return findNearestType(startLocation, "Library");
  }

  /**
   * This methods handles all versions of the findNearest... method
   * 
   * @param startLocation the current user location
   * @param type          is the type of location the user is trying to find nearby
   * @return the name of the nearest inputted type, or "N/A, no [type] nearby." if none are
   *         reachable.
   */
  private String findNearestType(String startLocation, String type) {
    Hashtable<Double, String> distances = new Hashtable<Double, String>();

    // Go through each vertex, finding the locations matching the type, and storing them in the
    // table
    for (CS400Graph<String>.Vertex v : graph.vertices.values()) {
      if (v.info.isType(type)) {
        try {
          distances.put(graph.getPathCost(startLocation, v.info.name), v.data);
        } catch (NoSuchElementException e) { // Expected for locations that don't connect
        }
      }
    }

    // Check if no locations are found to connect with the start location
    if (distances.isEmpty()) {
      return "N/A, no " + type + " nearby.";
    }

    // Find the shortest path
    double min = 10000.00; // No distance will be above 10,000 miles.
    for (double b : distances.keySet()) {
      if (b < min) {
        min = b;
      }
    }

    // Return the location with the matching distance
    return distances.get(min);
  }

  @Override
  /**
   * Gets a list of all the places on the UW-Madison campus
   * 
   * @return a Array of Strings that contain all the places on the UW-Madison campus
   */
  public String[] getAllPlaces() {
    String[] allplaces = new String[graph.getVertexCount()];
    int i = 0;

    // Iterate through all the locations on campus, (vertices in the graph) and add their names
    // (data) to the returned array.
    for (CS400Graph<String>.Vertex v : graph.vertices.values()) {
      allplaces[i] = v.data;
      i++;
    }

    return allplaces;
  }


}

