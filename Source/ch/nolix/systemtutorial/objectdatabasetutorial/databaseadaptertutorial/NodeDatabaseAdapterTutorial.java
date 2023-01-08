package ch.nolix.systemtutorial.objectdatabasetutorial.databaseadaptertutorial;

import ch.nolix.core.document.node.MutableNode;
import ch.nolix.system.objectdatabase.database.Entity;
import ch.nolix.system.objectdatabase.database.Schema;
import ch.nolix.system.objectdatabase.database.Value;
import ch.nolix.system.objectdatabase.databaseadapter.NodeDatabaseAdapter;

public final class NodeDatabaseAdapterTutorial {
	
	private static final class Person extends Entity {
		
		private final Value<String> firstName = new Value<>();
		private final Value<String> lastName = new Value<>();
		
		@Override
		public String toString() {
			return (firstName.getRefValue() + " " + lastName.getRefValue());
		}
	}
	
	public static void main(String[] args) {
		
		final var nodeDatabase = new MutableNode();
		
		final var schema = Schema.withEntityType(Person.class);
		
		final var nodeDatabaseAdapter =
		NodeDatabaseAdapter.forNodeDatabase(nodeDatabase).withName("TestDB").usingSchema(schema);
		
		final var donaldDuck = new Person();
		donaldDuck.firstName.setValue("Donald");
		donaldDuck.lastName.setValue("Duck");
		nodeDatabaseAdapter.insert(donaldDuck);
		
		nodeDatabaseAdapter.saveChangesAndReset();
		
		final var loadedDonaldDuck =
		nodeDatabaseAdapter.getRefTableByEntityType(Person.class).getRefEntityById(donaldDuck.getId());
		
		System.out.println(loadedDonaldDuck.toString());
	}
	
	private NodeDatabaseAdapterTutorial() {}
}
