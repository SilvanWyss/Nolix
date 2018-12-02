//package declaration
package ch.nolix.core.databaseSchemaAdapter;

//own imports
import ch.nolix.core.SQL.SQLDatabaseEngine;
import ch.nolix.core.validator2.Validator;

//abstract class
public abstract class EntitySetSQLHelper {
	
	//attribute
	private final EntitySet entitySet;
	
	//package-visible constructor
	EntitySetSQLHelper(final EntitySet entitySet) {
		
		Validator.suppose(entitySet).isInstanceOf(EntitySet.class);
		
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
	
	//abstract method
	public abstract SQLDatabaseEngine getDatabaseEngine();
	
	//method
	public final String getDeleteSQLStatement() {
		return ("DROP TABLE " + entitySet.getName());
	}
}
