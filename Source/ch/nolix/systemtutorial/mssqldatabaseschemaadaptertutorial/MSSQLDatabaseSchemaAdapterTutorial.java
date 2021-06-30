package ch.nolix.systemtutorial.mssqldatabaseschemaadaptertutorial;

import ch.nolix.system.database.entity.Entity;
import ch.nolix.system.database.entity.Value;
import ch.nolix.system.database.entitysetmapper.EntitySetMapper;
import ch.nolix.system.databaseschema.mssqldatabaseschemaadapter.MSSQLDatabaseSchemaAdapter;

public final class MSSQLDatabaseSchemaAdapterTutorial {
	
	public static void main(String[] args) {
		
		final var sqlDatabaseSchemaAdapter = new MSSQLDatabaseSchemaAdapter(1433, "TestDB", "sa", "123456");
		
		if (!sqlDatabaseSchemaAdapter.databaseIsInitialized()) {
			sqlDatabaseSchemaAdapter
			.addEntitySet(new EntitySetMapper(sqlDatabaseSchemaAdapter).createEntitySetFrom(Person.class))
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
