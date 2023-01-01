//package declaration
package ch.nolix.system.sqlrawdata.mssql;

//own imports
import ch.nolix.system.sqlrawdata.sqlapi.IMultiReferenceQueryCreator;
import ch.nolix.system.sqlrawdata.sqlapi.IMultiReferenceStatementCreator;
import ch.nolix.system.sqlrawdata.sqlapi.IMultiValueQueryCreator;
import ch.nolix.system.sqlrawdata.sqlapi.IMultiValueStatementCreator;
import ch.nolix.system.sqlrawdata.sqlapi.IEntityQueryCreator;
import ch.nolix.system.sqlrawdata.sqlapi.IEntityStatementCreator;
import ch.nolix.system.sqlrawdata.sqlapi.ISQLSyntaxProvider;

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
	public IEntityQueryCreator getRecordQueryCreator() {
		return entityQueryCreator;
	}
	
	//method
	@Override
	public IEntityStatementCreator getRecordStatementCreator() {
		return entityStatementCreator;
	}
}
