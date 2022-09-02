// --== CS400 File Header Information ==--
// Name: Jonah Peter Ryan
// Email: jpryan5@wisc.edu
// Team: Red
// Group: Group: AA
// TA: Mu Cai
// Lecturer: Florian Heimerl
// Notes to Grader: N/A

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Arrays;

/**
 * @author Jonah Ryan
 *
 */
public class BackendDeveloperTests {

  /**
   * Test the getTimeBetween method by using three different situations.
   * 
   * Using results from (https://www.active.com/fitness/calculators/pace) to test against.
   */
  @Test
  public void testGetTimeBetween() {
    Backend testBackend = new Backend(true);

    int timeOne = testBackend.getTimeBetween("A", "B", false); // A to D not biking
    int timeTwo = testBackend.getTimeBetween("A", "B", true); // A to D biking
    int timeThree = testBackend.getTimeBetween("A", "D", false); // A to D not biking

    assertEquals(4200, timeOne);
    assertEquals(1030, timeTwo);
    assertEquals(20612, timeThree);
  }

  /**
   * Test the getDirections method by using three different situations.
   */
  @Test
  public void testGetDirections() {
    Backend testBackend = new Backend(true);

    String[] timeOne = testBackend.getDirections("A", "D");
    String[] timeTwo = testBackend.getDirections("B", "C");
    String[] timeThree = testBackend.getDirections("A", "B");

    assertEquals(true, Arrays.equals(timeOne, new String[] {"A", "B", "D"}));
    assertEquals(true, Arrays.equals(timeTwo, new String[] {"B", "A", "C"}));
    assertEquals(true, Arrays.equals(timeThree, new String[] {"A", "B"}));
  }

  /**
   * Test the findNearestDining and findNearestLibrary methods
   */
  @Test
  public void testfindNearest() {
    Backend testBackend = new Backend(true);

    assertEquals("C", testBackend.findNearestDining("B"));
    assertEquals("D", testBackend.findNearestLibrary("A"));

    // Test if the case when no dining hall is reachable from a location is handled.
    assertEquals("N/A, no Dining Hall nearby.", testBackend.findNearestDining("D"));

  }
}
