// --== CS400 File Header Information ==--
// Name: Jack Walter
// Email: jwalker4@wisc.edu
// Team: AA blue
// Role: Data Wrangler
// TA: Mu
// Lecturer: Gary

/**
 * This class creates an object to hold all the data for the backend to receive in a nice package
 * 
 * @author Jack W
 *
 */
public class EdgeData {

  public String startingVertex;
  public String endingVertex;
  public double weight;

  public EdgeData(String startingVertex, String endingVertex, double weight) {
    this.startingVertex = startingVertex;
    this.endingVertex = endingVertex;
    this.weight = weight;
  }
  
  @Override
  public String toString() {
    return this.startingVertex + ", " + this.endingVertex + ", " + weight;
  }
  
}
