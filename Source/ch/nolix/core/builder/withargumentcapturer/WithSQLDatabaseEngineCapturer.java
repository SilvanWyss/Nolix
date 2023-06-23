//package declaration
package ch.nolix.core.builder.withargumentcapturer;

//own imports
import ch.nolix.core.builder.main.ArgumentCapturer;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.sql.SqlDatabaseEngine;

//class
public class WithSQLDatabaseEngineCapturer<N>
extends ArgumentCapturer<SqlDatabaseEngine, N> {
	
	//constructor
	public WithSQLDatabaseEngineCapturer(final N nextArgumentCapturer) {
		super(nextArgumentCapturer);
	}
	
	//method
	public final SqlDatabaseEngine getSQLDatabaseEngine() {
		return getOriArgument();
	}
	
	//method
	public final N withSQLDatabaseEngine(final SqlDatabaseEngine pSQLDatabaseEngine) {
		
		GlobalValidator.assertThat(pSQLDatabaseEngine).thatIsNamed(SqlDatabaseEngine.class).isNotNull();
		
		return setArgumentAndGetNext(pSQLDatabaseEngine);
	}
}
