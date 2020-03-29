//package declaration
package ch.nolix.techAPI.genericMathAPI;

//own imports
import ch.nolix.common.functionAPI.IElementTakerElementGetter;
import ch.nolix.common.skillAPI.IBuilder;

//interface
public interface IImplicitComplexSequenceBuilder extends IBuilder<IImplicitSequence<IComplexNumber>> {
	
	//method declaration
	public abstract IImplicitComplexSequenceBuilder setNextValueFunction(
		IElementTakerElementGetter<IComplexNumber[], IComplexNumber> nextValueFunction
	);
	
	//method declaration
	public abstract IImplicitComplexSequenceBuilder setStartIndex(int startIndex);
	
	//method declaration
	public abstract IImplicitComplexSequenceBuilder setStartValues(IComplexNumber... startValues);
}
