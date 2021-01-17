//package declaration
package ch.nolix.techapi.dynamicmathapi;

//own imports
import ch.nolix.common.implproviderapi.IImplRegistrator;
import ch.nolix.common.implproviderapi.IImplProvider;

//class
public abstract class BaseDynamicMathImplRegistrator implements IImplRegistrator {
	
	//method
	@Override
	public final void registerImplementationTo(final IImplProvider implProvider) {
		implProvider.forInterface(IClosedIntervalFactory.class).registerImplementation(getClosedIntervalFactoryImpl());
		implProvider.forInterface(IComplexNumberFactory.class).registerImplementation(getCompleNumberFactoryImpl());
		implProvider.forInterface(IFractalBuilder.class).registerImplementation(getFractalBuilderImpl());
	}
	
	//method declaration
	protected abstract Class<? extends IClosedIntervalFactory> getClosedIntervalFactoryImpl();
	
	//method declaration
	protected abstract Class<? extends IComplexNumberFactory> getCompleNumberFactoryImpl();
	
	//method declaration
	protected abstract Class<? extends IFractalBuilder> getFractalBuilderImpl();
}
