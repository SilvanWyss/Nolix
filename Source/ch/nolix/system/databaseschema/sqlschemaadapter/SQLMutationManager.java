//package declaration
package ch.nolix.system.databaseschema.sqlschemaadapter;

//own imports
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.common.requestapi.EmptinessRequestable;
import ch.nolix.common.skillapi.Resettable;
import ch.nolix.common.sql.SQLConnection;

//class
public final class SQLMutationManager implements EmptinessRequestable, Resettable {
	
	//multi-attribute
	private final LinkedList<String> mSQLStatements = new LinkedList<>();
	
	//method
	public SQLMutationManager addSQLStatement(final String pSQLStatement) {
		
		Validator.assertThat(pSQLStatement).thatIsNamed("SQL statement").isNotBlank();
		
		mSQLStatements.addAtEnd(pSQLStatement);
		
		return this;
	}
	
	//method
	public SQLMutationManager addSQLStatement(final String... pSQLStatements) {
		
		for (final var s : pSQLStatements) {
			addSQLStatement(s);
		}
		
		return this;
	}
	
	//method
	public void execute(final SQLConnection pSQLConnection) {
		pSQLConnection.execute(mSQLStatements);
	}
	
	//method
	@Override
	public boolean isEmpty() {
		return mSQLStatements.isEmpty();
	}
	
	//method
	@Override
	public void reset() {
		mSQLStatements.clear();
	}
}
