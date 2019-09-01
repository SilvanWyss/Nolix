//package declaration
package ch.nolix.techAPI.genericMathAPI;

import ch.nolix.common.functionAPI.IElementTakerElementGetter;
import ch.nolix.common.skillAPI.IBuilder;

//interface
public interface IImplicitComplexSequenceBuilder extends IBuilder<IImplicitSequence<IComplexNumber>> {
	
	//abstract method
	public abstract IImplicitComplexSequenceBuilder setNextValueFunction(
		IElementTakerElementGetter<IComplexNumber[], IComplexNumber> nextValueFunction
	);
	
	//abstract method
	public abstract IImplicitComplexSequenceBuilder setStartIndex(int startIndex);
	
	//abstract method
	public abstract IImplicitComplexSequenceBuilder setStartValues(IComplexNumber... startValues);
}
