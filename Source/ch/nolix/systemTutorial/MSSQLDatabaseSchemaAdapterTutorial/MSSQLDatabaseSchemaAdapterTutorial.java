package ch.nolix.systemTutorial.MSSQLDatabaseSchemaAdapterTutorial;

import ch.nolix.system.MSSQLDatabaseSchemaAdapter.MSSQLDatabaseSchemaAdapter;
import ch.nolix.system.entity.Entity;
import ch.nolix.system.entity.Value;

public final class MSSQLDatabaseSchemaAdapterTutorial {
	
	public static void main(String[] args) {
		
		final var sqlDatabaseSchemaAdapter =
		new MSSQLDatabaseSchemaAdapter(1433, "TestDB", "sa", "123456");
		
		if (!sqlDatabaseSchemaAdapter.databaseIsInitialized()) {
			sqlDatabaseSchemaAdapter
			//.addEntitySet(Person.class) //TODO: Implement DatabaseSchemaAdapter.addEntitySet().
			.saveChanges();
		}
	}
		
	@SuppressWarnings("unused")
	private static class Person extends Entity {
		
		@SuppressWarnings("unused")
		private final Value<String> Surname = new Value<>();
		
		@SuppressWarnings("unused")
		private final Value<String> Name = new Value<>();
	}
	
	private MSSQLDatabaseSchemaAdapterTutorial() {}
}
