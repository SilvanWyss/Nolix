//package declaration
package ch.nolix.core.builder.argumentcapturer;

//own imports
import ch.nolix.core.builder.base.ArgumentCapturer;
import ch.nolix.core.builder.base.BaseArgumentCapturer;
import ch.nolix.core.constant.LowerCaseCatalogue;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;

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
