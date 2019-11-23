//package declaration
package ch.nolix.systemTutorial.MSSQLDatabaseSchemaAdapterTutorial;

import ch.nolix.system.MSSQLDatabaseSchemaAdapter.MSSQLDatabaseSchemaAdapter;
import ch.nolix.system.entity.Entity;
import ch.nolix.system.entity.Property;

//class
public final class MSSQLDatabaseSchemaAdapterTutorial {
	
	//main method
	public static void main(String[] args) {
		
		final var sqlDatabaseSchemaAdapter =
		new MSSQLDatabaseSchemaAdapter(1433, "TestDB", "sa", "123456");
		
		if (!sqlDatabaseSchemaAdapter.databaseIsInitialized()) {
			sqlDatabaseSchemaAdapter
			.addEntitySet(Person.class)
			.saveChanges();
		}
	}
	
	//static class
	private static class Person extends Entity {
		
		@SuppressWarnings("unused")
		private final Property<String> Surname = new Property<>();
		
		@SuppressWarnings("unused")
		private final Property<String> Name = new Property<>();
	}
	
	//private constructor
	private MSSQLDatabaseSchemaAdapterTutorial() {}
}
