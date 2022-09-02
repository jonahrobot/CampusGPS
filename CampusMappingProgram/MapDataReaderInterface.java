// --== CS400 File Header Information ==--
// Name: Jack Walter and Caleb Guenther
// Email: jwalker4@wisc.edu cgguenther@wisc.edu
// Team: AA blue red
// Role: Data Wrangler
// TA: Mu
// Lecturer: Gary

/*
 * This class provides methods for the DataWrangler to use
 * 
 * @author Jack W and Caleb G
 */
import java.util.ArrayList;

/**
 * This interface will be implemented in the MapDataReader class
 * 
 * @author Jack W. and Caleb G
 *
 */
public interface MapDataReaderInterface {
  
  /**
   * will return an arrayList of edgeData objects that contains all the data to create edges for the
   * graph
   * 
   * @return an ArrayList of Vertice's data
   */
  public ArrayList<EdgeData> getEdgeData();

  /**
   * This method will return an ArrayList of NodeData to be used as vertices in the graph
   * 
   * @return an ArrayList of Vertice's data
   */
  public ArrayList<NodeData> getVertices();

}
