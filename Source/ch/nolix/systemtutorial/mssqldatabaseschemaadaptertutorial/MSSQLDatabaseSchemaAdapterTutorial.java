package ch.nolix.systemtutorial.mssqldatabaseschemaadaptertutorial;

//own imports
import ch.nolix.system.entity.Entity;
import ch.nolix.system.entity.Value;
import ch.nolix.system.mssqldatabaseschemaadapter.MSSQLDatabaseSchemaAdapter;

public final class MSSQLDatabaseSchemaAdapterTutorial {
	
	public static void main(String[] args) {
		
		final var sqlDatabaseSchemaAdapter = new MSSQLDatabaseSchemaAdapter(1433, "TestDB", "sa", "123456");
		
		if (!sqlDatabaseSchemaAdapter.databaseIsInitialized()) {
			sqlDatabaseSchemaAdapter
			//.addEntitySet(Person.class) //TODO: Implement DatabaseSchemaAdapter.addEntitySet().
			.saveChanges();
		}
	}
	
	@SuppressWarnings("unused")
	private static class Person extends Entity {
		
		@SuppressWarnings("unused")
		private final Value<String> surname = new Value<>();
		
		@SuppressWarnings("unused")
		private final Value<String> name = new Value<>();
	}
	
	private MSSQLDatabaseSchemaAdapterTutorial() {}
}
