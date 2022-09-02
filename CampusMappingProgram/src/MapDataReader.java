// --== CS400 File Header Information ==--
// Name: Caleb Guenther
// Email: cgguenther@wisc.edu
// Team: AA
// Role: Data Wrangler
// TA: Mu
// Lecturer: Gary
// Notes to Grader:

import java.io.Reader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class reads in the data from the csv files, packages it up nicely, and ships it to the
 * backend
 * 
 * @author Caleb G
 *
 */
public class MapDataReader implements MapDataReaderInterface {
  private ArrayList<EdgeData> edgeData;
  private ArrayList<NodeData> nodeData;

  protected ArrayList<String> edgeStringData;
  protected ArrayList<String> vertexStringData;

  private Scanner readInData;

  MapDataReader(Reader vertexReader, Reader edgeReader) {
    // initialize fields
    this.edgeData = new ArrayList<EdgeData>();
    this.nodeData = new ArrayList<NodeData>();
    this.edgeStringData = new ArrayList<String>();
    this.vertexStringData = new ArrayList<String>();
    
    // read in the data to the string array lists
    this.edgeStringData = getStringData(edgeReader);
    this.vertexStringData = getStringData(vertexReader);

    // create the data to send to the back end
    makeEdgeData(edgeStringData);
    makeVerticesData(vertexStringData);
  }

  /**
   * This method returns the data packaged as edgeData objects in an array list for the backend to
   * use
   * 
   * @return an arrayList of EdgeData objects
   */
  @Override
  public ArrayList<EdgeData> getEdgeData() {
    return this.edgeData;
  }

  /**
   * This method returns the data packaged as NodeData objects in an array list for the backend to
   * use
   * 
   * @return an arrayList of NodeData objects
   */
  @Override
  public ArrayList<NodeData> getVertices() {
    return this.nodeData;
  }

  /**
   * this method makes the arrayList of EdgeData objects for the backend to use
   * 
   * @param stringData the string data to be converted to EdgeData objects
   */
  public void makeEdgeData(ArrayList<String> stringData) {
    ArrayList<EdgeData> edgeData = new ArrayList<EdgeData>();
    
    for (String data : stringData) {
      
      String startPoint;
      String endPoint;
      double weight;
      
      startPoint = data.substring(0, data.indexOf(','));
      data = data.substring(data.indexOf(',') + 1);
      endPoint = data.substring(0, data.indexOf(','));
      data = data.substring(data.indexOf(',') + 1);
      weight = Double.parseDouble(data);
      
      edgeData.add(new EdgeData(startPoint, endPoint, weight));
    }
    
    this.edgeData = edgeData;
  }

  /**
   * this method makes the arrayList of NodeData for the backend to use
   * 
   * @param stringData the string data to be converted to NodeData objects
   */
  public void makeVerticesData(ArrayList<String> stringData) {
    ArrayList<NodeData> nodeData = new ArrayList<NodeData>();
    
    for (String data : stringData) {
      String name;
      String type;
      
      name = data.substring(0, data.indexOf(','));
      data = data.substring(data.indexOf(',') + 1);    
      type = data;
      
      nodeData.add(new NodeData(name, type));
    }
    
    this.nodeData = nodeData;
  }

  /**
   * This method reads in a reader file and store the data into strings in an ArrayList
   * 
   * @param r the reader to store the data from
   * @return an arrayList of the read in data
   */
  public ArrayList<String> getStringData(Reader r) {
    ArrayList<String> edgeStringData = new ArrayList<String>();
    readInData = new Scanner(r);

    readInData.nextLine();
    while (readInData.hasNextLine()) {
      edgeStringData.add(readInData.nextLine());
    }

    return edgeStringData;
  }

}
