package ch.nolix.systemtutorial.objectdatabasetutorial.databaseadaptertutorial;

import ch.nolix.system.objectdatabase.database.Entity;
import ch.nolix.system.objectdatabase.database.Value;
import ch.nolix.system.objectdatabase.databaseadapter.MsSqlDatabaseAdapter;
import ch.nolix.system.objectdatabase.schema.Schema;

public final class MsSqlDatabaseAdapterTutorial {
	
	private static final class Person extends Entity {
		
		private final Value<String> firstName = new Value<>();
		private final Value<String> lastName = new Value<>();
		
		@Override
		public String toString() {
			return (firstName.getStoredValue() + " " + lastName.getStoredValue());
		}
	}
	
	public static void main(String[] args) {
		
		final var schema = Schema.withEntityType(Person.class);
		
		final var msSqlNodeDatabaseAdapter =
		MsSqlDatabaseAdapter
		.toLocalHost()
		.andDefaultPort()
		.toDatabase("TestDB")
		.usingLoginName("sa")
		.andLoginPassword("sa1234")
		.andSchema(schema);
		
		final var donaldDuck = new Person();
		donaldDuck.firstName.setValue("Donald");
		donaldDuck.lastName.setValue("Duck");
		msSqlNodeDatabaseAdapter.insert(donaldDuck);
		
		msSqlNodeDatabaseAdapter.saveChangesAndReset();
		
		final var loadedDonaldDuck =
		msSqlNodeDatabaseAdapter.getStoredTableByEntityType(Person.class).getStoredEntityById(donaldDuck.getId());
		
		System.out.println(loadedDonaldDuck.toString());
	}
	
	private MsSqlDatabaseAdapterTutorial() {}
}
