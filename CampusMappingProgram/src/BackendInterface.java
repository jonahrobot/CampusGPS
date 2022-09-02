// --== CS400 File Header Information ==--
// Name: Jonah Peter Ryan
// Email: jpryan5@wisc.edu
// Team: Purple
// Group: Group: AA
// TA: Mu Cai
// Lecturer: Florian Heimerl
// Notes to Grader: N/A

/**
 * @author Jonah Ryan
 *
 */
public interface BackendInterface {

  /**
   * Gets the quickest directions between two locations 
   * 
   * @param startLocation The name of the starting location
   * @param endLocation The name of the destination
   * @return An array of location names that make up the shortest path between two places
   */
  public String[] getDirections(String startLocation, String endLocation);
  
  /**
   * Gets the fastest travel time between two locations, depending on if your running or walking
   * 
   * @param startLocation The name of the starting location
   * @param endLocation The name of the destination
   * @param Running = true if the user is running, false if otherwise.
   * @return the travel time of the shortest path
   */
  public int getTimeBetween(String startLocation, String endLocation, Boolean Running);

  /**
   * Finds the Dining Hall closest to a given location
   * 
   * @param startLocation the name of the starting location
   * @return the name of the closest Dining Hall.
   */
  public String findNearestDining(String startLocation);
  
  /**
   * Finds the Library closest to a given location
   * 
   * @param startLocation the name of the starting location
   * @return the name of the closest Library
   */
  public String findNearestLibrary(String startLocation);
  
  /**
   * Gets a list of all the places on the UW-Madison campus
   * 
   * @return a Array of Strings that contain all the places on the UW-Madison campus
   */
  public String[] getAllPlaces();
}
