//package declaration
package ch.nolix.element._3DGUI;

//own imports
import ch.nolix.core.container.ReadContainer;
import ch.nolix.core.container.List;
import ch.nolix.primitive.invalidArgumentException.Argument;
import ch.nolix.primitive.invalidArgumentException.ErrorPredicate;
import ch.nolix.primitive.invalidArgumentException.InvalidArgumentException;
import ch.nolix.primitive.validator2.Validator;

//class
/**
 * @author Silvan Wyss
 * @month 2017-11
 * @lines 150
 */
public final class MultiShape extends Shape<MultiShape> {

	//attribute
	private final List<Shape<?>> shapes = new List<>();
	
	//constructor
	/**
	 * Creates a new multi shape.
	 */
	public MultiShape() {}
	
	//constructor
	/**
	 * Creates a new multi shape with the given shapes.
	 * 
	 * @param shapes
	 */
	public MultiShape(final Shape<?>... shapes) {
		addShape(shapes);
	}
	
	//constructor
	/**
	 * Creates a new mutli shape with the given shapes.
	 * 
	 * @param shapes
	 */
	public MultiShape(final Iterable<Shape<?>> shapes) {
		addShapes(shapes);
	}
	
	//method
	/**
	 * Adds the given shape to this multi shape.
	 * 
	 * @param shape
	 * @return this multi shape.
	 * @throws NullArgumentException if the given shape is null.
	 * @throws InvalidStateException if the given shape belongs to a GUI.
	 */
	public MultiShape addShape(final Shape<?> shape) {
		
		//Checks if the given shape is not null.
		Validator.suppose(shape).thatIsOfType(Shape.class).isNotNull();
		
		//Checks if the given shape belongs not to a GUI.
		if (shape.belongsToAGUI()) {
			throw new InvalidArgumentException(
				new Argument(shape),
				new ErrorPredicate("belongs to a GUI")
			);
		}
		
		//Handles the case that this multi shape belongs to a GUI.
		if (belongsToAGUI()) {
			shape.setGUI(getRefGUI());
			getRefShapeRenderManager().addSubRenderObject(shape);
		}
		
		shapes.addAtEnd(shape);
		
		return this;
	}
	
	//method
	/**
	 * Adds the given shapes to this multi shape.
	 * 
	 * @param shapes
	 * @return this multi shape.
	 * @throws NullArgumentException if one of the given shapes is null.
	 * @throws InvalidArgumentException if one of the given shapes belongs to a GUI.
	 */
	public MultiShape addShape(final Shape<?>... shapes) {
		
		//Iterates the given shapes.
		for (final Shape<?> s : shapes) {
			addShape(s);
		}
		
		return this;
	}
	
	//method
	/**
	 * Adds the given shapes to this multi shape.
	 * 
	 * @param shapes
	 * @return this multi shape.
	 * @throws NullArgumentException if one of the given shapes is null.
	 * @throws InvalidArgumentException if one of the given shapes belongs to a GUI.
	 */
	public MultiShape addShapes(final Iterable<Shape<?>> shapes) {
		
		//Iterates the given shapes.
		for (final Shape<?> s : shapes) {
			addShape(s);
		}
		
		return this;
	}

	//method
	/**
	 * @return the shapes of this multi shape.
	 */
	public ReadContainer<Shape<?>> getRefShapes() {
		return new ReadContainer<>(shapes);
	}
	
	//method
	/**
	 * Removes the given shape from this multi shape.
	 * 
	 * @param shape
	 * @return this shape.
	 * @throws InvalidArgumentException
	 * if this multi shape does not contain the given shape.
	 */
	public MultiShape removeShape(final Shape<?> shape) {
		
		shapes.removeFirst(shape);
		
		//Handles the case that this multi shape belongs to a GUI.
		if (belongsToAGUI()) {
			getRefShapeRenderManager().removeSubRenderObject(shape);
		}
		
		return this;
	}
	
	//method
	/**
	 * Resets the configuration of this multi shape.
	 * 
	 * @return this multi shape.
	 */
	public MultiShape resetConfiguration() {
		return this;
	}
}
