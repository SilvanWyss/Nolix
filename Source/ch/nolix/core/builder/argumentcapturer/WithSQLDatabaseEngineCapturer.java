//package declaration
package ch.nolix.core.builder.argumentcapturer;

//own imports
import ch.nolix.core.builder.base.ArgumentCapturer;
import ch.nolix.core.builder.base.BaseArgumentCapturer;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.core.sql.SQLDatabaseEngine;

//class
public class WithSQLDatabaseEngineCapturer<NAC extends BaseArgumentCapturer<?>>
extends ArgumentCapturer<SQLDatabaseEngine, NAC> {
	
	//constructor
	public WithSQLDatabaseEngineCapturer(final NAC nextArgumentCapturer) {
		super(nextArgumentCapturer);
	}
	
	//method
	public NAC withSQLDatabaseEngine(final SQLDatabaseEngine pSQLDatabaseEngine) {
		
		Validator.assertThat(pSQLDatabaseEngine).thatIsNamed(SQLDatabaseEngine.class).isNotNull();
		
		return setArgumentAndGetRefNextArgumentCapturer(pSQLDatabaseEngine);
	}
}
