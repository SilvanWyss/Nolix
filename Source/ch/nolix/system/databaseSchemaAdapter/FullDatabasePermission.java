//package declaration
package ch.nolix.system.databaseSchemaAdapter;

//own imports
import ch.nolix.core.license.Permission;
import ch.nolix.core.validator.Validator;

//class
public final class FullDatabasePermission extends Permission {
	
	//constructor
	//key = '452349'
	public FullDatabasePermission(final String key) {
		
		final var keyAsInt = Integer.valueOf(key);
		
		Validator.suppose(keyAsInt / 143 + keyAsInt % 21055).thatIsNamed("key transformation").isEqualTo(13357);
	}
}
