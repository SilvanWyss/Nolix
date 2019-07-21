//package declaration
package ch.nolix.coreTest;

//own imports
import ch.nolix.core.testoid.TestPool;
import ch.nolix.coreTest.containerTest.ContainerTestPool;
import ch.nolix.coreTest.documentNodeTest.DocumentNodeTestPool;
import ch.nolix.coreTest.helperTest.HelperTestPool;
import ch.nolix.coreTest.licenseTest.LicenseTestPool;
import ch.nolix.coreTest.mathTest.MathematicsTestPool;
import ch.nolix.coreTest.sequencerTest.SequencerTestPool;
import ch.nolix.coreTest.statementTest.StatementTestPool;
import ch.nolix.coreTest.utilTest.UtilTestPool;
import ch.nolix.coreTest.validatorTest.ValidatorTestPool;
import ch.nolix.elementTest.elementEnumTest.ElementEnumTestPool;
import ch.nolix.systemTest.databaseAdapterTest.DatabaseAdapterTestPool;

//class
/**
 * @author Silvan Wyss
 * @month 2016-08
 * @lines 40
 */
public final class CoreTestPool extends TestPool {
	
	//constructor
	/**
	 * Creates a new {@link CoreTestPool}.
	 */
	public CoreTestPool() {
		addTestPool(
			new ContainerTestPool(),
			new DatabaseAdapterTestPool(),
			new DocumentNodeTestPool(),
			new ch.nolix.coreTest.endPointTest.EndPointTestPool(),
			new ch.nolix.coreTest.endPoint2Test.EndPointTestPool(),
			new ch.nolix.coreTest.endPoint3Test.EndPointTestPool(),
			new ElementEnumTestPool(),
			new HelperTestPool(),
			new LicenseTestPool(),
			new MathematicsTestPool(),
			new SequencerTestPool(),
			new StatementTestPool(),
			new UtilTestPool(),
			new ValidatorTestPool()
		);
	}
}
