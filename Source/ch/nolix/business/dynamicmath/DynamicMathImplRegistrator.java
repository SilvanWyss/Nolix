//package declaration
package ch.nolix.business.dynamicmath;

//own imports
import ch.nolix.businessapi.dynamicmathapi.IClosedIntervalFactory;
import ch.nolix.businessapi.dynamicmathapi.IComplexNumberFactory;
import ch.nolix.businessapi.dynamicmathapi.IFractalBuilder;
import ch.nolix.core.provider.implproviderapi.IImplProvider;
import ch.nolix.core.provider.implproviderapi.IImplRegistrator;

//class
public final class DynamicMathImplRegistrator implements IImplRegistrator {
	
	//method
	@Override
	public void registerImplementationTo(final IImplProvider implProvider) {
		implProvider.forInterface(IClosedIntervalFactory.class).registerImplementation(ClosedIntervalFactory.class);
		implProvider.forInterface(IComplexNumberFactory.class).registerImplementation(ComplexNumberFactory.class);
		implProvider.forInterface(IFractalBuilder.class).registerImplementation(FractalBuilder.class);
	}
}
