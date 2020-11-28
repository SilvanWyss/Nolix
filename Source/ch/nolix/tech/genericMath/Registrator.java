//package declaration
package ch.nolix.tech.genericMath;

//own imports
import ch.nolix.common.instanceProvider.CentralInstanceProvider;
import ch.nolix.techapi.genericmathapi.IClosedIntervalFactory;
import ch.nolix.techapi.genericmathapi.IComplexNumberFactory;
import ch.nolix.techapi.genericmathapi.IFractalBuilder;
import ch.nolix.techapi.genericmathapi.IImplicitComplexSequenceBuilder;

//class
public final class Registrator {
	
	//static method
	public static void register() {
		CentralInstanceProvider
		.register(IClosedIntervalFactory.class, ClosedIntervalFactory.class)
		.register(IComplexNumberFactory.class, ComplexNumberFactory.class)
		.register(IFractalBuilder.class, FractalBuilder.class)
		.register(IImplicitComplexSequenceBuilder.class, ImplicitComplexSequenceBuilder.class);
	}
	
	//visibility-reduced constructor
	private Registrator() {}
}
