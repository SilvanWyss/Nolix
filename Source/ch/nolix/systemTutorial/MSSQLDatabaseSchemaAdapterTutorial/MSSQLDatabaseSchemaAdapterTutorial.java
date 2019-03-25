//package declaration
package ch.nolix.systemTutorial.MSSQLDatabaseSchemaAdapterTutorial;

import ch.nolix.system.MSSQLDatabaseSchemaAdapter.MSSQLDatabaseSchemaAdapter;
import ch.nolix.system.databaseAdapter.Entity;
import ch.nolix.system.databaseAdapter.Property;

//class
public final class MSSQLDatabaseSchemaAdapterTutorial {
	
	//main method
	public static void main(String[] args) {
		
		final var sqlDatabaseSchemaAdapter =
		new MSSQLDatabaseSchemaAdapter(1433, "TestDB", "sa", "123456");
		
		if (!sqlDatabaseSchemaAdapter.databaseIsInitialized()) {
			sqlDatabaseSchemaAdapter
			.initializeDatabase()
			.addEntitySet(Person.class)
			.saveChanges();
		}
	}
	
	//static class
	private static class Person extends Entity {
		
		@SuppressWarnings("unused")
		private final Property<String> Surname = new Property<String>();
		
		@SuppressWarnings("unused")
		private final Property<String> Name = new Property<String>();
	}
	
	//private constructor
	private MSSQLDatabaseSchemaAdapterTutorial() {}
}
