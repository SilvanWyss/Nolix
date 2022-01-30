//package declaration
package ch.nolix.commontest.commontypetest;

import ch.nolix.commontest.commontypetest.commontypehelpertest.CommonTypeHelpersTestPool;
import ch.nolix.commontest.commontypetest.commontypewrappertest.CommonTypeWrapperTestPool;
import ch.nolix.core.testing.basetest.TestPool;

//class
public final class CommonTypeTestPool extends TestPool {
	
	//constructor
	public CommonTypeTestPool() {
		super(new CommonTypeHelpersTestPool(), new CommonTypeWrapperTestPool());
	}
}
