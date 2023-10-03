//package declaration
package ch.nolix.techapitest.relationaldocapitest;

//own imports
import ch.nolix.core.testing.basetest.TestPool;
import ch.nolix.techapitest.relationaldocapitest.baseapitest.BaseApiTestPool;

//class
public final class RelationalDocApiTestPool extends TestPool {
	
	//constructor
	public RelationalDocApiTestPool() {
		super(new BaseApiTestPool());
	}
}
