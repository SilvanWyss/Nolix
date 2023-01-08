package ch.nolix.systemtutorial.objectdatabasetutorial.databaseadaptertutorial;

import ch.nolix.system.objectdatabase.database.Entity;
import ch.nolix.system.objectdatabase.database.Schema;
import ch.nolix.system.objectdatabase.database.Value;
import ch.nolix.system.objectdatabase.databaseadapter.MSSQLDatabaseAdapter;

public final class MSSQLDatabaseAdapterTutorial {
	
	private static final class Person extends Entity {
		
		private final Value<String> firstName = new Value<>();
		private final Value<String> lastName = new Value<>();
		
		@Override
		public String toString() {
			return (firstName.getRefValue() + " " + lastName.getRefValue());
		}
	}
	
	public static void main(String[] args) {
		
		final var schema = Schema.withEntityType(Person.class);
		
		final var lMSSQLNodeDatabaseAdapter =
		MSSQLDatabaseAdapter
		.toLocalHost()
		.andDefaultPort()
		.toDatabase("TestDB")
		.usingLoginName("sa")
		.andLoginPassword("sa1234")
		.andSchema(schema);
		
		final var donaldDuck = new Person();
		donaldDuck.firstName.setValue("Donald");
		donaldDuck.lastName.setValue("Duck");
		lMSSQLNodeDatabaseAdapter.insert(donaldDuck);
		
		lMSSQLNodeDatabaseAdapter.saveChangesAndReset();
		
		final var loadedDonaldDuck =
		lMSSQLNodeDatabaseAdapter.getRefTableByEntityType(Person.class).getRefEntityById(donaldDuck.getId());
		
		System.out.println(loadedDonaldDuck.toString());
	}
	
	private MSSQLDatabaseAdapterTutorial() {}
}
