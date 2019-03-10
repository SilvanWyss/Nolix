//package declaration
package ch.nolix.techAPI.genericMathAPI;

//Java import
import java.util.ArrayList;

//own imports
import ch.nolix.core.functionAPI.IElementTakerElementGetter;
import ch.nolix.core.skillAPI.IBuilder;

//interface
public interface IImplicitComplexSequenceBuilder extends IBuilder<IImplicitSequence<IComplexNumber>> {
	
	//abstract method
	public abstract IImplicitComplexSequenceBuilder setNextValueFunction(
		IElementTakerElementGetter<ArrayList<IComplexNumber>, IComplexNumber> nextValueFunction
	);
	
	//abstract method
	public abstract IImplicitComplexSequenceBuilder setStartIndex(int startIndex);
	
	//abstract method
	public abstract IImplicitComplexSequenceBuilder setStartValues(Iterable<IComplexNumber> startValues);
}
