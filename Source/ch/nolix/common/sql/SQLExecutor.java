//package declaration
package ch.nolix.common.sql;

//own imports
import ch.nolix.common.container.IContainer;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.errorcontrol.invalidargumentexception.ClosedArgumentException;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.common.requestapi.CloseStateRequestable;

//class
public final class SQLExecutor implements AutoCloseable, CloseStateRequestable {
	
	//attributes
	private boolean closed;
	private final SQLConnection mSQLConnection;
	
	//multi-attribute
	private final LinkedList<String> mSQLStatements = new LinkedList<>();
	
	//constructor
	public SQLExecutor(final SQLConnection pSQLConnection) {
		
		Validator.assertThat(pSQLConnection).isOfType(SQLConnection.class);
		
		this.mSQLConnection = pSQLConnection;
	}
	
	//method
	public SQLExecutor addSQLStatement(final String pSQLstatement) {
		
		Validator.assertThat(pSQLstatement)	.thatIsNamed("SQL statement").isNotBlank();
		
		assertIsOpen();
		mSQLStatements.addAtEnd(getSQLStatementWithSemicolonAtEnd(pSQLstatement));
		
		return this;
	}
	
	@Override
	public void close() {
		closed = true;
	}
	
	//method
	public void execute() {
		
		assertIsOpen();
		
		try {
			mSQLConnection.execute(mSQLStatements.toString());
		} finally {
			close();
		}
	}
	
	//method
	public IContainer<String> getStatements() {
		return mSQLStatements;
	}
	
	//method
	@Override
	public boolean isClosed() {
		return closed;
	}
	
	//method
	private void assertIsOpen() {
		if (isClosed()) {
			throw new ClosedArgumentException(this);
		}
	}
	
	//method
	private String getSQLStatementWithSemicolonAtEnd(String pSQLStatement) {
		
		if (!pSQLStatement.endsWith(";")) {
			return (pSQLStatement + ";");
		}
		
		return pSQLStatement;
	}
}
