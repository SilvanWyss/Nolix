//package declaration
package ch.nolix.core.builder.argumentcapturer;

//own imports
import ch.nolix.core.builder.main.ArgumentCapturer;
import ch.nolix.core.builder.main.BaseArgumentCapturer;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.sql.SQLDatabaseEngine;

//class
public class WithSQLDatabaseEngineCapturer<NAC extends BaseArgumentCapturer<?>>
extends ArgumentCapturer<SQLDatabaseEngine, NAC> {
	
	//constructor
	public WithSQLDatabaseEngineCapturer(final NAC nextArgumentCapturer) {
		super(nextArgumentCapturer);
	}
	
	//method
	public final SQLDatabaseEngine getSQLDatabaseEngine() {
		return getRefArgument();
	}
	
	//method
	public final NAC withSQLDatabaseEngine(final SQLDatabaseEngine pSQLDatabaseEngine) {
		
		GlobalValidator.assertThat(pSQLDatabaseEngine).thatIsNamed(SQLDatabaseEngine.class).isNotNull();
		
		return setArgumentAndGetRefNextArgumentCapturer(pSQLDatabaseEngine);
	}
}
