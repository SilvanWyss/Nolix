//package declaration
package ch.nolix.core.SQL;

//own imports
import ch.nolix.core.constants.VariableNameCatalogue;
import ch.nolix.core.container.IContainer;
import ch.nolix.core.container.List;
import ch.nolix.core.skillAPI.Resettable;
import ch.nolix.core.validator.Validator;

//class
public final class SQLExecutor implements Resettable<SQLExecutor> {
	
	//attribute
	private final SQLConnection mSQLConnection;
	
	//multi-attribute
	private final List<String> statements = new List<>();
	
	//package-visible constructor
	SQLExecutor(final SQLConnection SQLConnection) {
		
		Validator.suppose(SQLConnection).isOfType(SQLConnection.class);
		
		this.mSQLConnection = SQLConnection;
	}
	
	//method
	public SQLExecutor addStatement(final String statement) {
		
		Validator
		.suppose(statement)
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
