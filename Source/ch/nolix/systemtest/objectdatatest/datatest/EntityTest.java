//package declaration
package ch.nolix.systemtest.objectdatatest.datatest;

//own imports
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.core.testing.test.Test;
import ch.nolix.system.objectdata.data.Entity;
import ch.nolix.systemapi.databaseapi.databaseobjectapi.DatabaseObjectState;

//class
public final class EntityTest extends Test {
	
	//static attribute
	private static final class Thing extends Entity {}
	
	//method
	@TestCase
	public void testCase_creation() {
		
		//execution
		final var result = new Thing();
		
		//verification
		expect(result.getState()).isEqualTo(DatabaseObjectState.NEW);
		expectNot(result.isClosed());
		expectNot(result.isDeleted());
		expect(result.getId()).hasLength(10);
		expect(result.getShortDescription()).startsWith("Thing");
		expectNot(result.isLinkedWithRealDatabase());
		expectNot(result.knowsParentTable());
	}
}
