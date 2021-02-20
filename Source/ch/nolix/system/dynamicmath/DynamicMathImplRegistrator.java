//package declaration
package ch.nolix.system.dynamicmath;

import ch.nolix.businessapi.dynamicmathapi.BaseDynamicMathImplRegistrator;

//class
public final class DynamicMathImplRegistrator extends BaseDynamicMathImplRegistrator {
	
	//method
	@Override
	protected Class<ClosedIntervalFactory> getClosedIntervalFactoryImpl() {
		return ClosedIntervalFactory.class;
	}
	
	//method
	@Override
	protected Class<ComplexNumberFactory> getCompleNumberFactoryImpl() {
		return ComplexNumberFactory.class;
	}
	
	//method
	@Override
	protected Class<FractalBuilder> getFractalBuilderImpl() {
		return FractalBuilder.class;
	}
}
