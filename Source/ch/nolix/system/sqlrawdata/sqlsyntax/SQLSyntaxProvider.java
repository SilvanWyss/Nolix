//package declaration
package ch.nolix.system.sqlrawdata.sqlsyntax;

import ch.nolix.system.sqlrawdata.sqlsyntaxapi.IEntityQueryCreator;
import ch.nolix.system.sqlrawdata.sqlsyntaxapi.IEntityStatementCreator;
import ch.nolix.system.sqlrawdata.sqlsyntaxapi.IMultiReferenceQueryCreator;
import ch.nolix.system.sqlrawdata.sqlsyntaxapi.IMultiReferenceStatementCreator;
import ch.nolix.system.sqlrawdata.sqlsyntaxapi.IMultiValueQueryCreator;
import ch.nolix.system.sqlrawdata.sqlsyntaxapi.IMultiValueStatementCreator;
import ch.nolix.system.sqlrawdata.sqlsyntaxapi.ISQLSyntaxProvider;

//class
public final class SQLSyntaxProvider implements ISQLSyntaxProvider {
	
	//static attribute
	private static final IEntityQueryCreator entityQueryCreator = new EntityQueryCreator();
	
	//static attribute
	private static final IEntityStatementCreator entityStatementCreator = new EntityStatementCreator();
	
	//static attribute
	private static final IMultiValueQueryCreator multiValueQueryCreator = new MultiValueQueryCreator();
	
	//static attribute
	private static final IMultiValueStatementCreator multiValueStatementCreator = new MultiValueStatementCreator();
	
	//static attribute
	private static final IMultiReferenceQueryCreator multiReferenceQueryCreator = new MultiReferenceQueryCreator();
	
	//static attribute
	private static final IMultiReferenceStatementCreator multiReferenceStatementCreator =
	new MultiReferenceStatementCreator();
	
	//method
	@Override
	public IMultiReferenceQueryCreator getMultiReferenceQueryCreator() {
		return multiReferenceQueryCreator;
	}
	
	//method
	@Override
	public IMultiValueQueryCreator getMultiValueQueryCreator() {
		return multiValueQueryCreator;
	}
	
	//method
	@Override
	public IMultiReferenceStatementCreator getMultiReferenceStatemeentCreator() {
		return multiReferenceStatementCreator;
	}
	
	//method
	@Override
	public IMultiValueStatementCreator getMultiValueStatemeentCreator() {
		return multiValueStatementCreator;
	}
	
	//method
	@Override
	public IEntityQueryCreator getEntityQueryCreator() {
		return entityQueryCreator;
	}
	
	//method
	@Override
	public IEntityStatementCreator getEntityStatementCreator() {
		return entityStatementCreator;
	}
}
