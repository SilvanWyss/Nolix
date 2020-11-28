//package declaration
package ch.nolix.techapi.genericmathapi;

//own imports
import ch.nolix.common.functionAPI.IElementTakerElementGetter;
import ch.nolix.common.skillAPI.IBuilder;

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
