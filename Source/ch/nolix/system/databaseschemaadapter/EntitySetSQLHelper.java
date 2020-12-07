//package declaration
package ch.nolix.system.databaseschemaadapter;

//own imports
import ch.nolix.common.sql.SQLDatabaseEngine;
import ch.nolix.common.validator.Validator;

//class
public abstract class EntitySetSQLHelper {
	
	//attribute
	private final EntitySet entitySet;
	
	//constructor
	EntitySetSQLHelper(final EntitySet entitySet) {
		
		Validator.assertThat(entitySet).isOfType(EntitySet.class);
		
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
			
			stringBuilder
			.append(c.getHeader())
			.append(" ")
			.append(c.getSQLHelper(getDatabaseEngine()).getSQLDataType())
			.append(",");
			
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
