//package declaration
package ch.nolix.tech.genericMath;

//own imports
import ch.nolix.common.instanceProvider.CentralInstanceProvider;
import ch.nolix.techAPI.genericMathAPI.IClosedIntervalFactory;
import ch.nolix.techAPI.genericMathAPI.IComplexNumberFactory;
import ch.nolix.techAPI.genericMathAPI.IFractalBuilder;
import ch.nolix.techAPI.genericMathAPI.IImplicitComplexSequenceBuilder;

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
