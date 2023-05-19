//package declaration
package ch.nolix.core.builder.withargumentcapturer;

//own imports
import ch.nolix.core.builder.main.ArgumentCapturer;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.sql.SQLDatabaseEngine;

//class
public class WithSQLDatabaseEngineCapturer<N>
extends ArgumentCapturer<SQLDatabaseEngine, N> {
	
	//constructor
	public WithSQLDatabaseEngineCapturer(final N nextArgumentCapturer) {
		super(nextArgumentCapturer);
	}
	
	//method
	public final SQLDatabaseEngine getSQLDatabaseEngine() {
		return getOriArgument();
	}
	
	//method
	public final N withSQLDatabaseEngine(final SQLDatabaseEngine pSQLDatabaseEngine) {
		
		GlobalValidator.assertThat(pSQLDatabaseEngine).thatIsNamed(SQLDatabaseEngine.class).isNotNull();
		
		return setArgumentAndGetNext(pSQLDatabaseEngine);
	}
}
