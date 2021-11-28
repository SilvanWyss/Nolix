//package declaration
package ch.nolix.system.objectschema.schema;

//own imports
import ch.nolix.techapi.objectschemaapi.schemaapi.ITable;

//class
final class DatabaseMutationExecutor {
	
	//method
	public void addTableToDatabase(final Database database, final ITable table) {
		
		database.addTableAttribute(table);
		((Table)table).setParentDatabase(database);
		
		if (database.isLinkedWithRealDatabase()) {
			database.getRefRealSchemaAdapter().getRefRawSchemaWriter().addTable(table);
		}
		
		database.setEdited();
	}
}
