//package declaration
package ch.nolix.systemapi.rawdatabaseapi.sqlsyntaxapi;

//interface
public interface ISQLSyntaxProvider {
	
	//method declaration
	IEntityQueryCreator getEntityQueryCreator();
	
	//method declaration
	IEntityStatementCreator getEntityStatementCreator();
	
	//method declaration
	IMultiReferenceQueryCreator getMultiReferenceQueryCreator();
	
	//method declaration
	IMultiValueQueryCreator getMultiValueQueryCreator();
	
	//method declaration
	IMultiReferenceStatementCreator getMultiReferenceStatemeentCreator();
	
	//method declaration
	IMultiValueStatementCreator getMultiValueStatemeentCreator();
}