//package declaration
package ch.nolix.commontest.commontypetest;

//own imports
import ch.nolix.common.testing.basetest.TestPool;
import ch.nolix.commontest.commontypetest.commontypehelpertest.CommonTypeHelpersTestPool;
import ch.nolix.commontest.commontypetest.commontypewrappertest.CommonTypeWrapperTestPool;

//class
public final class CommonTypeTestPool extends TestPool {
	
	//constructor
	public CommonTypeTestPool() {
		super(new CommonTypeHelpersTestPool(), new CommonTypeWrapperTestPool());
	}
}
