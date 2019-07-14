//package declaration
package ch.nolix.element._3DGUI;

//own imports
import ch.nolix.core.constants.PascalCaseNameCatalogue;
import ch.nolix.element.base.MutableProperty;
import ch.nolix.element.core.PositiveFloatingPointNumber;

//class
public final class Pyramid extends BaseShape<Pyramid> {
	
	//default values
	public static final double DEFAULT_SIDE_LENGTH = 1.0;
	public static final double DEFAULT_HEIGHT = 2.0;

	//attribute
	private final MutableProperty<PositiveFloatingPointNumber> sideLength =
	new MutableProperty<>(
		PascalCaseNameCatalogue.SIDE_LENGTH,
		sl -> setSideLength(sl.getValue()),
		s -> PositiveFloatingPointNumber.createFromSpecification(s),
		sl -> sl.getSpecification()
	);
	
	//attribute
	private final MutableProperty<PositiveFloatingPointNumber> height =
	new MutableProperty<>(
		PascalCaseNameCatalogue.HEIGHT,
		h -> setHeight(h.getValue()),
		s -> PositiveFloatingPointNumber.createFromSpecification(s),
		h -> h.getSpecification()
	);
	
	//constructor
	public Pyramid() {
		reset();
	}
	
	//method
	public double getHeight() {
		return height.getValue().getValue();
	}
	
	//method
	public float getHeightAsFloat() {
		
		//For a better performance, this implementation does not use all comfortable methods.
		return (float)sideLength.getValue().getValue();
	}
	
	//method
	public double getSideLength() {
		return sideLength.getValue().getValue();
	}
	
	//method
	public float getSideLengthAsFloat() {
		
		//For a better performance, this implementation does not use all comfortable methods.
		return (float)sideLength.getValue().getValue();
	}
	
	//method
	@Override
	public Pyramid reset() {
		
		//Calls method of the base class.
		super.reset();
		
		setSideLength(DEFAULT_SIDE_LENGTH);
		setHeight(DEFAULT_HEIGHT);
		
		return this;
	}
	
	//method
	public Pyramid setHeight(final double height) {
		
		this.height.setValue(new PositiveFloatingPointNumber(height));
		
		return this;
	}
	
	//method
	public Pyramid setSideLength(final double sideLength) {
		
		this.sideLength.setValue(new PositiveFloatingPointNumber(sideLength));
		
		return this;
	}
}
