//package declaration
package ch.nolix.systemtest.sqldatabaserawdatatest.sqlsyntaxtest;

//own imports
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.core.testing.test.Test;
import ch.nolix.system.sqldatabaserawdata.sqlsyntax.EntityQueryCreator;

//class
public final class EntityQueryCreatorTest extends Test {
	
	//method
	@TestCase
	public void testCase_createQueryToCountEntitiesWithGivenValueAtGivenColumn() {
		
		//setup
		final var testUnit = new EntityQueryCreator();
		
		//execution
		final var result =
		testUnit.createQueryToCountEntitiesWithGivenValueAtGivenColumn("MyTable", "MyColumn", "my_value");
		
		//verification
		final var expectedResult = "SELECT COUNT(MyColumn) FROM EMyTable WHERE MyColumn = 'my_value';";
		expect(result).isEqualTo(expectedResult);
	}
	
	//method
	@TestCase
	public void testCase_createQueryToLoadSchemaTimestamp() {
		
		//setup
		final var testUnit = new EntityQueryCreator();
		
		//execution
		final var result = testUnit.createQueryToLoadSchemaTimestamp();
		
		//verification
		final var expectedResult = "SELECT Value FROM SDatabaseProperty WHERE ValueKey = 'SchemaTimestamp';";
		expect(result).isEqualTo(expectedResult);
	}
}
