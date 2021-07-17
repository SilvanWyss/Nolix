//package declaration
package ch.nolix.system.databaseschema.schema;

//class
final class DatabaseMutationExecutor {
	
	//method
	public void addTableToDatabase(final Database database, final Table table) {
		
		database.addTableAttribute(table);
		
		if (database.isLinkedWithActualDatabase()) {
			database.getRefAccessor().addTableToCurrentDatabase(table.toDTO());
		}
		
		database.setEdited();
	}
}
