//package declaration
package ch.nolix.core.sql;

//own imports
import ch.nolix.core.container.LinkedList;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.core.skillapi.Clearable;

//class
public final class SQLCollector implements Clearable {
	
	//multi-attribute
	private final LinkedList<String> mSQLStatements = new LinkedList<>();
	
	//method
	public SQLCollector addSQLStatement(final String pSQLstatement) {
		
		Validator.assertThat(pSQLstatement)	.thatIsNamed("SQL statement").isNotBlank();
		
		mSQLStatements.addAtEnd(getSQLStatementWithSemicolonAtEnd(pSQLstatement));
		
		return this;
	}
	
	//method
	public SQLCollector addSQLStatements(final Iterable<String> pSQLStatements) {
		
		pSQLStatements.forEach(this::addSQLStatement);
		
		return this;
	}
	
	//method
	@Override
	public void clear() {
		mSQLStatements.clear();
	}
	
	//method
	public void executeUsingConnection(final SQLConnection pSQLConnection) {
		try {
			pSQLConnection.execute(mSQLStatements);
		} finally {
			clear();
		}
	}
	
	//method
	@Override
	public boolean isEmpty() {
		return mSQLStatements.isEmpty();
	}
	
	//method
	private String getSQLStatementWithSemicolonAtEnd(final String pSQLStatement) {
		
		if (!pSQLStatement.endsWith(";")) {
			return (pSQLStatement + ";");
		}
		
		return pSQLStatement;
	}
}
