//package declaration
package ch.nolix.core.sql;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.functionapi.mutationuniversalapi.Clearable;

//class
public final class SqlCollector implements Clearable {
	
	//multi-attribute
	private final LinkedList<String> sqlStatements = new LinkedList<>();
	
	//method
	public SqlCollector addSQLStatement(final String sqlstatement) {
		
		GlobalValidator.assertThat(sqlstatement)	.thatIsNamed("SQL statement").isNotBlank();
		
		sqlStatements.addAtEnd(getSQLStatementWithSemicolonAtEnd(sqlstatement));
		
		return this;
	}
	
	//method
	public SqlCollector addSQLStatements(final Iterable<String> sqlStatements) {
		
		sqlStatements.forEach(this::addSQLStatement);
		
		return this;
	}
	
	//method
	@Override
	public void clear() {
		sqlStatements.clear();
	}
	
	//method
	public void executeAndClearUsingConnection(final SqlConnection sqlConnection) {
		try {
			executeUsingConnection(sqlConnection);
		} finally {
			clear();
		}
	}
	
	//method
	public void executeUsingConnection(final SqlConnection sqlConnection) {
		sqlConnection.execute(sqlStatements);
	}
	
	//method
	public IContainer<String> getSQLStatements() {
		return sqlStatements;
	}
	
	//method
	@Override
	public boolean isEmpty() {
		return sqlStatements.isEmpty();
	}
	
	//method
	private String getSQLStatementWithSemicolonAtEnd(final String sqlStatement) {
		
		if (!sqlStatement.endsWith(";")) {
			return (sqlStatement + ";");
		}
		
		return sqlStatement;
	}
}
