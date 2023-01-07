//package declaration
package ch.nolix.systemtest.objectdatabasetest.databasetest;

//own imports
import ch.nolix.core.document.node.MutableNode;
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.core.testing.test.Test;
import ch.nolix.system.objectdatabase.database.Entity;
import ch.nolix.system.objectdatabase.database.MultiValue;
import ch.nolix.system.objectdatabase.database.Schema;
import ch.nolix.system.objectdatabase.databaseadapter.NodeDatabaseAdapter;

//class
public final class MultiValueOnDatabaseTest extends Test {
	
	//static class
	private static final class Round extends Entity {
		
		//attribute
		public final MultiValue<Integer> amounts = new MultiValue<>();
		
		//constructor
		public Round() {
			initialize();
		}
	}
	
	//method
	@TestCase
	public void testCase_isSaved_whenIsNewAndEmpty() {
		
		//setup
		final var nodeDatabase = new MutableNode();
		final var schema = Schema.withEntityType(Round.class);
		final var nodeDatabaseAdapter =
		NodeDatabaseAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").usingSchema(schema);
		final var round = new Round();
		nodeDatabaseAdapter.insert(round);
		nodeDatabaseAdapter.saveChangesAndReset();
		
		//execution
		final var loadedRound =
		nodeDatabaseAdapter.getRefTableByEntityType(Round.class).getRefEntityById(round.getId());
		
		//verification
		expect(loadedRound.amounts.getRefValues().isEmpty());
	}
	
	//method
	@TestCase
	public void testCase_isSaved_whenIsNewAndContainsValue() {
		
		//setup
		final var nodeDatabase = new MutableNode();
		final var schema = Schema.withEntityType(Round.class);
		final var nodeDatabaseAdapter =
		NodeDatabaseAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").usingSchema(schema);
		final var round = new Round();
		round.amounts.addValue(10);
		round.amounts.addValue(20);
		round.amounts.addValue(30);
		round.amounts.addValue(40);
		nodeDatabaseAdapter.insert(round);
		nodeDatabaseAdapter.saveChangesAndReset();
		
		//execution
		final var loadedRound =
		nodeDatabaseAdapter.getRefTableByEntityType(Round.class).getRefEntityById(round.getId());
		
		//verification
		final var loadedValues = loadedRound.amounts.getRefValues();
		expect(loadedValues.getElementCount()).isEqualTo(4);
		expect(loadedValues.containsAll(10, 20, 30, 40));
	}
	
	//method
	@TestCase
	public void testCase_removeValue_whenIsLoadedAndContainsValue() {
		
		//setup part 1
		final var nodeDatabase = new MutableNode();
		final var schema = Schema.withEntityType(Round.class);
		final var nodeDatabaseAdapter =
		NodeDatabaseAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").usingSchema(schema);
		final var round = new Round();
		round.amounts.addValue(10);
		round.amounts.addValue(20);
		round.amounts.addValue(30);
		round.amounts.addValue(40);
		nodeDatabaseAdapter.insert(round);
		nodeDatabaseAdapter.saveChangesAndReset();
		
		//setup part 2
		final var loadedRound =
		nodeDatabaseAdapter.getRefTableByEntityType(Round.class).getRefEntityById(round.getId());
		
		//execution
		loadedRound.amounts.removeValue(40);
		
		//verification
		final var loadedValues = loadedRound.amounts.getRefValues();
		expect(loadedValues.getElementCount()).isEqualTo(3);
		expect(loadedValues.containsAll(10, 20, 30));
	}
}
