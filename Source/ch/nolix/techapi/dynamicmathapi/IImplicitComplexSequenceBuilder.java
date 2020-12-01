//package declaration
package ch.nolix.techapi.dynamicmathapi;

import ch.nolix.common.functionapi.IElementTakerElementGetter;
import ch.nolix.common.skillapi.IBuilder;

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
