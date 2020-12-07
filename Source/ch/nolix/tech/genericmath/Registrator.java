//package declaration
package ch.nolix.tech.genericmath;

//own imports
import ch.nolix.common.instanceprovider.CentralInstanceProvider;
import ch.nolix.techapi.dynamicmathapi.IClosedIntervalFactory;
import ch.nolix.techapi.dynamicmathapi.IComplexNumberFactory;
import ch.nolix.techapi.dynamicmathapi.IFractalBuilder;
import ch.nolix.techapi.dynamicmathapi.IImplicitComplexSequenceBuilder;

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
