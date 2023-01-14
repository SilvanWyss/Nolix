//package declaration
package ch.nolix.system.application.main;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;

//Class
final class BasicApplication<
	BC extends BackendClient<BC, AC>,
	AC
>
extends Application<BC, AC> {
	
	//attribute
	private final Class<?> initialSessionClass;
	
	//static method
	public static <BC2 extends BackendClient<BC2, AC2>, S extends Session<BC2, AC2>, AC2> BasicApplication<BC2, AC2>
	withInstanceNameAndInitialSessionClassAndApplicationContext(
		final String instanceName,
		final Class<S> initialSessionClass,
		final AC2 applicationContext
	) {
		return new BasicApplication<>(instanceName, initialSessionClass, applicationContext);
	}	
	
	//constructor
	private <S extends Session<BC, AC>> BasicApplication(
		final String instanceName,
		final Class<S> initialSessionClass,
		final AC applicationContext
	) {
		
		super(instanceName, applicationContext);
		
		GlobalValidator.assertThat(initialSessionClass).thatIsNamed("initial session class").isNotNull();
		
		this.initialSessionClass = initialSessionClass;
	}
	
	//method
	@Override
	public String getApplicationName() {
		return getInstanceName();
	}
	
	@Override
	protected Class<?> getRefInitialSessionClass() {
		return initialSessionClass;
	}
}
