//package declaration
package ch.nolix.common.SQL;

import ch.nolix.common.constant.VariableNameCatalogue;
import ch.nolix.common.containers.IContainer;
import ch.nolix.common.containers.LinkedList;
import ch.nolix.common.skillAPI.Resettable;
import ch.nolix.common.validator.Validator;

//class
public final class SQLExecutor implements Resettable<SQLExecutor> {
	
	//attribute
	private final SQLConnection mSQLConnection;
	
	//multi-attribute
	private final LinkedList<String> statements = new LinkedList<>();
	
	//constructor
	SQLExecutor(final SQLConnection SQLConnection) {
		
		Validator.assertThat(SQLConnection).isOfType(SQLConnection.class);
		
		this.mSQLConnection = SQLConnection;
	}
	
	//method
	public SQLExecutor addStatement(final String statement) {
		
		Validator
		.assertThat(statement)
		.thatIsNamed(VariableNameCatalogue.STATEMENT)
		.isNotBlank();
		
		if (!statement.endsWith(";")) {
			statements.addAtEnd(statement + ';');
		}
		else {
			statements.addAtEnd(statement);
		}
		
		return this;
	}
	
	//method
	public void execute() {
		try {
			mSQLConnection.execute(statements.toString());
		}
		finally {
			/*
			 * For a better performance, this implementation does not use all comfortable methods.
			 * 
			 * A shorter implementation is:
			 * 
			 * reset();
			 */
			statements.clear();
		}
	}
	
	//method
	public IContainer<String> getStatements() {
		return statements.getCopy();
	}
	
	//method
	@Override
	public SQLExecutor reset() {
		
		statements.clear();
		
		return this;
	}
}
