//package declaration
package ch.nolix.coreapitest.environmentapitest;

//own imports
import ch.nolix.core.testing.basetest.TestPool;
import ch.nolix.coreapitest.environmentapitest.filesystemapitest.FileSystemApiTestPool;

//class
public final class EnvironmentApiTestPool extends TestPool {

  //constructor
  public EnvironmentApiTestPool() {
    super(new FileSystemApiTestPool());
  }
}
