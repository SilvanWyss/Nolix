//package declaration
package ch.nolix.commontest.providertest;

//own imports
import ch.nolix.common.testing.basetest.TestPool;
import ch.nolix.commontest.providertest.implprovidertest.ImplProviderTestPool;

//class
public final class ProviderTestPool extends TestPool {
	
	//constructor
	public ProviderTestPool() {
		super(new ImplProviderTestPool());
	}
}
