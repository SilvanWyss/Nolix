//package declaration
package ch.nolix.systemtest.objectdatatest.datatest;

//own imports
import ch.nolix.common.testing.basetest.TestCase;
import ch.nolix.common.testing.test.Test;
import ch.nolix.system.objectdata.data.Entity;
import ch.nolix.systemapi.databaseapi.databaseobjectapi.DatabaseObjectState;

//class
public final class EntityTest extends Test {
	
	//static attribute
	private static final class Thing extends Entity {}
	
	//method
	@TestCase
	public void testCase_creation() {
		try (final var result = new Thing()) {
			
			//verification
			expect(result.getState()).isEqualTo(DatabaseObjectState.NEW);
			expect(result.getId()).hasLength(32);
			expect(result.getShortDescription()).startsWith("Thing");
			expectNot(result.knowsParentTable());
		}
	}
}
