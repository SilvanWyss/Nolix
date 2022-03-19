//package declaration
package ch.nolix.coretest.commontypetest;

//own imports
import ch.nolix.core.testing.basetest.TestPool;
import ch.nolix.coretest.commontypetest.commontypehelpertest.CommonTypeHelpersTestPool;
import ch.nolix.coretest.commontypetest.commontypewrappertest.CommonTypeWrapperTestPool;

//class
public final class CommonTypeTestPool extends TestPool {
	
	//constructor
	public CommonTypeTestPool() {
		super(new CommonTypeHelpersTestPool(), new CommonTypeWrapperTestPool());
	}
}
