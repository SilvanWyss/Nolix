//package declaration
package ch.nolix.tech.dynamicmath;

//own import
import ch.nolix.techapi.dynamicmathapi.BaseDynamicMathImplRegistrator;

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
