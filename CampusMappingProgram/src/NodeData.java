// --== CS400 File Header Information ==--
// Name: Jack Walter
// Email: jwalker4@wisc.edu
// Team: AA blue
// Role: Data Wrangler
// TA: Mu
// Lecturer: Gary

/**
 * Parent class for the objects in the map
 * 
 * @author Jack W.
 *
 */
public class NodeData{
public String name;
public String type;

public NodeData(String name, String type) {
  this.name = name;
  this.type = type;
}

public boolean isType(String type) {
  return (this.type.equals(type));
}

@Override
public String toString() {
  return this.name + ", " + this.type;
}

}
