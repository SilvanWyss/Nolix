//package declaration
package ch.nolix.tech.genericMath;

//own imports
import ch.nolix.core.classProvider.ClassProvider;
import ch.nolix.core.invalidArgumentException.UninstantiableClassException;
import ch.nolix.techAPI.genericMathAPI.IClosedIntervalFactory;
import ch.nolix.techAPI.genericMathAPI.IComplexNumberFactory;
import ch.nolix.techAPI.genericMathAPI.IFractalBuilder;

//class
public final class GenericMathRegistrator {
	
	//static method
	public static void register() {
		ClassProvider
		.register(IClosedIntervalFactory.class, ClosedIntervalFactory.class)
		.register(IComplexNumberFactory.class, ComplexNumberFactory.class)
		.register(IFractalBuilder.class, FractalBuilder.class);
	}
	
	//private constructor
	private GenericMathRegistrator() {
		throw new UninstantiableClassException(GenericMathRegistrator.class);
	}
}
