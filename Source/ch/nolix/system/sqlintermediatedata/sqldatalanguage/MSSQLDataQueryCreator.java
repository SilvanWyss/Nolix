//package declaration
package ch.nolix.system.sqlintermediatedata.sqldatalanguage;

//own imports
import ch.nolix.techapi.intermediatedataapi.sqldatalanguageapi.IDataQueryCreator;

//class
public final class MSSQLDataQueryCreator implements IDataQueryCreator{
	
	//method
	@Override
	public String createQueryToLoadAllRecordsFromTable(final String tableName) {
		return ("SELECT * FROM " + tableName);
	}
}
