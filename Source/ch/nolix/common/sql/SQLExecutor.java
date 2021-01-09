//package declaration
package ch.nolix.common.sql;

//own imports
import ch.nolix.common.constant.VariableNameCatalogue;
import ch.nolix.common.container.IContainer;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.skillapi.Resettable;
import ch.nolix.common.validator.Validator;

//class
public final class SQLExecutor implements Resettable {
	
	//attribute
	private final SQLConnection mSQLConnection;
	
	//multi-attribute
	private final LinkedList<String> statements = new LinkedList<>();
	
	//constructor
	SQLExecutor(final SQLConnection pSQLConnection) {
		
		Validator.assertThat(pSQLConnection).isOfType(SQLConnection.class);
		
		this.mSQLConnection = pSQLConnection;
	}
	
	//method
	public SQLExecutor addStatement(final String statement) {
		
		Validator
		.assertThat(statement)
		.thatIsNamed(VariableNameCatalogue.STATEMENT)
		.isNotBlank();
		
		if (!statement.endsWith(";")) {
			statements.addAtEnd(statement + ';');
		} else {
			statements.addAtEnd(statement);
		}
		
		return this;
	}
	
	//method
	public void execute() {
		try {
			mSQLConnection.execute(statements.toString());
		} finally {
			reset();
		}
	}
	
	//method
	public IContainer<String> getStatements() {
		return statements.getCopy();
	}
	
	//method
	@Override
	public void reset() {
		statements.clear();
	}
}
