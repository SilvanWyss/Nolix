//package declaration
package ch.nolix.tech.genericMath;

//Java import
import java.util.ArrayList;

//own imports
import ch.nolix.core.functionAPI.IElementTakerElementGetter;
import ch.nolix.techAPI.genericMathAPI.IComplexNumber;
import ch.nolix.techAPI.genericMathAPI.IImplicitComplexSequenceBuilder;

//class
public final class ImplicitComplexSequenceBuilder implements IImplicitComplexSequenceBuilder {
	
	//constant
	public static final int DEFAULT_START_INDEX = 1;
	public static final ComplexNumber DEFAULT_START_VALUE = new ComplexNumber(0.0, 0.0);
	
	//constant
	public static final IElementTakerElementGetter<ArrayList<IComplexNumber>, IComplexNumber>
	DEFAULT_NEXT_VALUE_FUNCTION =
	z -> new ComplexNumber(0.0, 0.0);
	
	//attribute
	private int startIndex = DEFAULT_START_INDEX;
	
	//multi-attribute
	private ArrayList<IComplexNumber> startValues = new ArrayList<IComplexNumber>();
	
	//attribute
	private IElementTakerElementGetter<ArrayList<IComplexNumber>, IComplexNumber> nextValueFunction;
	
	//constructor
	public ImplicitComplexSequenceBuilder() {
		startValues.add(DEFAULT_START_VALUE);
	}
	
	//method
	@Override
	public ImpliciteSequence<IComplexNumber> build() {
		return
		new ImpliciteSequence<IComplexNumber>(
			startIndex,
			startValues,
			nextValueFunction,
			z -> z.getSquaredMagnitude()
		);
	}
	
	//method
	@Override
	public ImplicitComplexSequenceBuilder setNextValueFunction(
		final IElementTakerElementGetter<ArrayList<IComplexNumber>, IComplexNumber> nextValueFunction
	){
		this.nextValueFunction = nextValueFunction;
		
		return this;
	}
	
	//method
	@Override
	public ImplicitComplexSequenceBuilder setStartIndex(final int startIndex) {
		
		this.startIndex = startIndex;
		
		return this;
	}
	
	//method
	@Override
	public ImplicitComplexSequenceBuilder setStartValues(final Iterable<IComplexNumber> startValues) {
		
		this.startValues.clear();
		
		for (final var sv : startValues) {
			this.startValues.add(sv);
		}
		
		return this;
	}
}
