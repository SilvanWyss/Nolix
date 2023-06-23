//package declaration
package ch.nolix.system.sqldatabaserawdata.sqlsyntax;

import ch.nolix.systemapi.rawdatabaseapi.sqlsyntaxapi.IEntityQueryCreator;
import ch.nolix.systemapi.rawdatabaseapi.sqlsyntaxapi.IEntityStatementCreator;
import ch.nolix.systemapi.rawdatabaseapi.sqlsyntaxapi.IMultiReferenceQueryCreator;
import ch.nolix.systemapi.rawdatabaseapi.sqlsyntaxapi.IMultiReferenceStatementCreator;
import ch.nolix.systemapi.rawdatabaseapi.sqlsyntaxapi.IMultiValueQueryCreator;
import ch.nolix.systemapi.rawdatabaseapi.sqlsyntaxapi.IMultiValueStatementCreator;
import ch.nolix.systemapi.rawdatabaseapi.sqlsyntaxapi.ISqlSyntaxProvider;

//class
public final class SqlSyntaxProvider implements ISqlSyntaxProvider {
	
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
