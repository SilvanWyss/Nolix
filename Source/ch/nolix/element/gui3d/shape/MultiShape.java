//package declaration
package ch.nolix.element.gui3d.shape;

//own imports
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.container.ReadContainer;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.common.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.element.gui3d.base.Shape;

//class
/**
 * @author Silvan Wyss
 * @date 2017-11-11
 * @lines 170
 */
public final class MultiShape extends Shape<MultiShape> {

	//attribute
	private final LinkedList<Shape<?>> shapes = new LinkedList<>();
	
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
	 * @throws ArgumentIsNullException if the given shape is null.
	 * @throws InvalidArgumentException if the given shape belongs to a GUI.
	 */
	public MultiShape addShape(final Shape<?> shape) {
		
		//Asserts that the given shape is not null.
		Validator.assertThat(shape).isOfType(Shape.class);
		
		//Asserts that the given shape belongs not to a GUI.
		if (shape.belongsToAGUI()) {
			throw new InvalidArgumentException(shape, "belongs already to a GUI");
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
	 * @throws ArgumentIsNullException if one of the given shapes is null.
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
	 * @throws ArgumentIsNullException if one of the given shapes is null.
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
	 * Removes all {@link Shape}s of the current {@link MultiShape}.
	 */
	public void clear() {
		shapes.clear();
	}
	
	//method
	/**
	 * @return the shapes of this multi shape.
	 */
	@Override
	public ReadContainer<Shape<?>> getRefShapes() {
		return ReadContainer.forIterable(shapes);
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
	 * {@inheritDoc}
	 */
	@Override
	public void resetConfigurationOnSelf() {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void resetShape() {
		clear();
	}
}
