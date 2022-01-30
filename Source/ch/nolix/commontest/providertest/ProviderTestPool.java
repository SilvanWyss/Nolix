//package declaration
package ch.nolix.commontest.providertest;

import ch.nolix.commontest.providertest.implprovidertest.ImplProviderTestPool;
import ch.nolix.core.testing.basetest.TestPool;

//class
public final class ProviderTestPool extends TestPool {
	
	//constructor
	public ProviderTestPool() {
		super(new ImplProviderTestPool());
	}
}
