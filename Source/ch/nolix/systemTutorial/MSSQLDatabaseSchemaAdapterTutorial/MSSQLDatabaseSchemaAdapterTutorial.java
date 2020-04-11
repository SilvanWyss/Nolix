package ch.nolix.systemTutorial.MSSQLDatabaseSchemaAdapterTutorial;

import ch.nolix.system.MSSQLDatabaseSchemaAdapter.MSSQLDatabaseSchemaAdapter;
import ch.nolix.system.entity.Entity;
import ch.nolix.system.entity.ValueProperty;

public final class MSSQLDatabaseSchemaAdapterTutorial {
	
	public static void main(String[] args) {
		
		final var sqlDatabaseSchemaAdapter =
		new MSSQLDatabaseSchemaAdapter(1433, "TestDB", "sa", "123456");
		
		if (!sqlDatabaseSchemaAdapter.databaseIsInitialized()) {
			sqlDatabaseSchemaAdapter
			.addEntitySet(Person.class)
			.saveChanges();
		}
	}
	
	private static class Person extends Entity {
		
		@SuppressWarnings("unused")
		private final ValueProperty<String> Surname = new ValueProperty<>();
		
		@SuppressWarnings("unused")
		private final ValueProperty<String> Name = new ValueProperty<>();
	}
	
	private MSSQLDatabaseSchemaAdapterTutorial() {}
}
