//package declaration
package ch.nolix.core.builder.toargumentcapturer;

//own imports
import ch.nolix.core.builder.main.ArgumentCapturer;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;

//class
public class ToDatabaseNameCapturer<N> extends ArgumentCapturer<String, N> {
	
	//constructor
	public ToDatabaseNameCapturer(final N nextArgumentCapturer) {
		super(nextArgumentCapturer);
	}
	
	//method
	public final String getDatabaseName() {
		return getOriArgument();
	}
	
	//method
	public final N toDatabase(final String databaseName) {
		
		GlobalValidator.assertThat(databaseName).thatIsNamed(LowerCaseCatalogue.DATABASE_NAME).isNotBlank();
		
		return setArgumentAndGetNext(databaseName);
	}
}
