//package declaration
package ch.nolix.businessapi.dynamicmathapi;

//own imports
import ch.nolix.core.functionapi.IElementTakerElementGetter;
import ch.nolix.core.skillapi.IBuilder;

//interface
public interface IImplicitComplexSequenceBuilder extends IBuilder<IImplicitSequence<IComplexNumber>> {
	
	//method declaration
	IImplicitComplexSequenceBuilder setNextValueFunction(
		IElementTakerElementGetter<IComplexNumber[], IComplexNumber> nextValueFunction
	);
	
	//method declaration
	IImplicitComplexSequenceBuilder setStartIndex(int startIndex);
	
	//method declaration
	IImplicitComplexSequenceBuilder setStartValues(IComplexNumber... startValues);
}
