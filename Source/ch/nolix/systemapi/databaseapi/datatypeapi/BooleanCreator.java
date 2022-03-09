//package declaration
package ch.nolix.systemapi.databaseapi.datatypeapi;

//own imports
import ch.nolix.core.commontype.commontypehelper.GlobalStringHelper;

//class
final class BooleanCreator implements IValueCreator<Boolean> {
	
	//method
	@Override
	public Boolean createValueFromString(final String string) {
		return GlobalStringHelper.toBoolean(string);
	}
}
