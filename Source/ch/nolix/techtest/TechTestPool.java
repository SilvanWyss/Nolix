//package declaration
package ch.nolix.techtest;

//own imports
import ch.nolix.core.testing.basetest.TestPool;
import ch.nolix.techtest.mathtest.MathTestPool;
import ch.nolix.techtest.relationaldoctest.RelationalDocTestPool;
import ch.nolix.techtest.serverdashboardtest.ServerDashboardTestPool;

//class
public final class TechTestPool extends TestPool {

  //constructor
  public TechTestPool() {
    super(new MathTestPool(), new RelationalDocTestPool(), new ServerDashboardTestPool());
  }
}
