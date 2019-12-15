//package declaration
package ch.nolix.system.databaseSchemaAdapter;

import ch.nolix.common.SQL.SQLDatabaseEngine;
import ch.nolix.common.validator.Validator;

//class
public abstract class EntitySetSQLHelper {
	
	//attribute
	private final EntitySet entitySet;
	
	//package-visible constructor
	EntitySetSQLHelper(final EntitySet entitySet) {
		
		Validator.suppose(entitySet).isOfType(EntitySet.class);
		
		this.entitySet = entitySet;
	}
	
	//method
	public final String getCreateSQLStatement() {
		
		final var stringBuilder = new StringBuilder();
		
		stringBuilder
		.append("CREATE TABLE ")
		.append(entitySet.getName())
		.append(" (");
		
		var begin = true;
		for (final var c : entitySet.getRefColumns()) {
			
			if (c.isDataColumn()) {
				stringBuilder
				.append(c.getHeader())
				.append(" ")
				.append(c.getSQLHelper(getDatabaseEngine()).getSQLDataType())
				.append(",");
			}
			
			if (begin) {
				begin = false;
			}
		}
		
		stringBuilder.append(")");
		
		return stringBuilder.toString();
	}
	
	//method declaration
	public abstract SQLDatabaseEngine getDatabaseEngine();
	
	//method
	public final String getDeleteSQLStatement() {
		return ("DROP TABLE " + entitySet.getName());
	}
}
