//package declaration
package ch.nolix.system.sqlrawobjectdata.sqlapi;

//interface
public interface ISQLSyntaxProvider {
	
	//method declaration
	IMultiReferenceQueryCreator getMultiReferenceQueryCreator();
	
	//method declaration
	IMultiValueQueryCreator getMultiValueQueryCreator();
	
	//method declaration
	IMultiValueStatementCreator getMultiValueStatemeentCreator();
	
	//method declaration
	IRecordQueryCreator getRecordQueryCreator();
	
	//method declaration
	IRecordStatementCreator getRecordStatementCreator();
}
