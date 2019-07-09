//package declaration
package ch.nolix.element._3DGUI;

//own imports
import ch.nolix.core.constants.PascalCaseNameCatalogue;
import ch.nolix.element.core.PositiveFloatingPointNumber;
import ch.nolix.element.element.MutableProperty;

//abstract class
public abstract class Prisma<P extends Prisma<P>> extends BaseShape<P> {
	
	//default value
	public static final double DEFAULT_HEIGHT = 2.0;

	//attribute
	private final MutableProperty<PositiveFloatingPointNumber> height =
	new MutableProperty<>(
		PascalCaseNameCatalogue.HEIGHT,
		h -> setHeight(h.getValue()),
		s -> PositiveFloatingPointNumber.createFromSpecification(s),
		h -> h.getSpecification()
	);
	
	//method
	public final double getHeight() {
		return height.getValue().getValue();
	}
	
	//method
	public final float getHeightAsFloat() {
		return (float)getHeight();
	}
	
	//method
	@Override
	public P reset() {
		
		//Calls method of base class.
		super.reset();
		
		setHeight(DEFAULT_HEIGHT);
		
		return asConcreteType();
	}
	
	//method
	public final P setHeight(final double height) {
		
		this.height.setValue(new PositiveFloatingPointNumber(height));
		
		return asConcreteType();
	}
}
