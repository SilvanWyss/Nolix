//package declaration
package ch.nolix.techtest.relationaldoctest;

//own imports
import ch.nolix.core.testing.basetest.TestPool;
import ch.nolix.techtest.relationaldoctest.datamodeltest.DataModelTestPool;

//class
public final class RelationalDocTestPool extends TestPool {
	
	//constructor
	public RelationalDocTestPool() {
		super(new DataModelTestPool());
	}
}
