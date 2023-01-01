//package declaration
package ch.nolix.system.sqlrawdata.sqlapi;

//interface
public interface ISQLSyntaxProvider {
	
	//method declaration
	IMultiReferenceQueryCreator getMultiReferenceQueryCreator();
	
	//method declaration
	IMultiValueQueryCreator getMultiValueQueryCreator();
	
	//method declaration
	IMultiReferenceStatementCreator getMultiReferenceStatemeentCreator();
	
	//method declaration
	IMultiValueStatementCreator getMultiValueStatemeentCreator();
	
	//method declaration
	IEntityQueryCreator getRecordQueryCreator();
	
	//method declaration
	IRecordStatementCreator getRecordStatementCreator();
}
