//package declaration
package ch.nolix.core.builder.argumentcapturer;

import ch.nolix.core.builder.main.ArgumentCapturer;
import ch.nolix.core.builder.main.BaseArgumentCapturer;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;

//class
public class AndDatabaseNameCapturer<NAC extends BaseArgumentCapturer<?>> extends ArgumentCapturer<String, NAC> {
	
	//method
	public AndDatabaseNameCapturer(final NAC nextArgumentCapturer) {
		super(nextArgumentCapturer);
	}
	
	//method
	public final NAC andDatabase(final String databaseName) {
		
		GlobalValidator.assertThat(databaseName).thatIsNamed(LowerCaseCatalogue.DATABASE_NAME).isNotBlank();
		
		return setArgumentAndGetRefNextArgumentCapturer(databaseName);
	}
	
	//method
	public final String getDatabaseName() {
		return getRefArgument();
	}
}
