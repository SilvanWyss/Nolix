//package declaration
package ch.nolix.coretest.providertest;

//own imports
import ch.nolix.core.testing.basetest.TestPool;
import ch.nolix.coretest.providertest.implprovidertest.ImplProviderTestPool;

//class
public final class ProviderTestPool extends TestPool {

  // constructor
  public ProviderTestPool() {
    super(new ImplProviderTestPool());
  }
}
