//package declaration
package ch.nolix.coretest.environmenttest;

//own imports
import ch.nolix.core.testing.basetest.TestPool;
import ch.nolix.coretest.environmenttest.filesystemtest.FileSystemTestPool;

//class
public final class EnvironmentTestPool extends TestPool {

  //constructor
  public EnvironmentTestPool() {
    super(new FileSystemTestPool());
  }
}
