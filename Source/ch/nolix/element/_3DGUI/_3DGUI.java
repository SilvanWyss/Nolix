//package declaration
package ch.nolix.element._3DGUI;

//own imports
import ch.nolix.core.container.AccessorContainer;
import ch.nolix.core.container.List;
import ch.nolix.core.container.Pair;
import ch.nolix.core.invalidArgumentException.InvalidArgumentException;
import ch.nolix.core.invalidStateException.InvalidStateException;
import ch.nolix.core.invalidStateException.UnexistingAttributeException;
import ch.nolix.core.specification.StandardSpecification;
import ch.nolix.core.specificationInterfaces.Configurable;
import ch.nolix.core.validator2.Validator;
import ch.nolix.element.GUIoid.GUIoid;

//abstract class
/**
 * @author Silvan Wyss
 * @month 2017-11
 * @lines 240
 * @param <G> The type of a 3D GUI.
 */
public abstract class _3DGUI<G extends _3DGUI<G>> extends GUIoid<G> {

	//optional element
	private Shape<?> rootShape;
	
	//multiple element
	private List<Pair<Class<?>, IShapeRenderer<?, ?, ?>>> shapeClasses = new List<>();
	
	//method
	/**
	 * Adds or changes the given attribute to this 3D GUI.
	 * 
	 * @param attribute
	 * @throws InvalidArgumentException if the given attribute is not valid.
	 */
	public final void addOrChangeAttribute(final StandardSpecification attribute) {
		
		//Handles the option that the given attribute specifies a shape.
		if (canCreateShape(attribute.getHeader())) {
			setRootShape(createShape(attribute.getHeader()));
			return;
		}
		
		//Enumerates the header of the given attribute.
		switch (attribute.getHeader()) {
			default:
				
				//Calls method of the base class.
				super.addOrChangeAttribute(attribute);
		}
	}
	
	//method
	/**
	 * Adds the given shape class with the givne shape renderer to this 3D GUI.
	 * 
	 * @param shapeClass
	 * @param shapeRenderer
	 * @return this 3D GUI.
	 * @throws NullArgumentException if the given shape class is null.
	 * @throws NullArgumentException if the given shape renderer is null.
	 * 
	 */
	public G addShapeClass(final Class<?> shapeClass, IShapeRenderer<?, ?, ?> shapeRenderer) {
		
		//Checks if the given shape class is not null.
		Validator.suppose(shapeClass).isOfType(Shape.class);
		
		//Checks if this
		if (canCreateShape(shapeClass.getSimpleName())) {
			throw new InvalidStateException(this, "contains already a shape class '" + shapeClass + "'");
		}
		
		shapeClasses.addAtEnd(new Pair<>(shapeClass, shapeRenderer));
		
		return getInstance();
	}
	
	//method
	/**
	 * @param type
	 * @return true if this 3D GUI can create a shape of the given type.
	 */
	public final boolean canCreateShape(final String type) {
		return shapeClasses.contains(sc -> sc.getRefElement1().getSimpleName().equals(type));
	}
	
	//method
	/**
	 * Removes the root sahpe of this 3D GUI.
	 */
	public final G clear() {
		
		removeRootShape();
		
		return getInstance();
	}
	
	//method
	/**
	 * @return the attributes of this 3D GUI.
	 */
	public List<StandardSpecification> getAttributes() {
		
		//Calls method of the base class.
		final List<StandardSpecification> attributes = super.getAttributes();
		
		//Handles the option that this 3D GUI has a root shape.
		if (hasRootShape()) {
			attributes.addAtEnd(getRefRootShape().getSpecification());
		}
		
		return attributes;
	}
	
	//method
	/**
	 * @return the shapes of this 3D GUI.
	 */
	public final AccessorContainer<Configurable> getRefConfigurables() {
		return new AccessorContainer<>(getRefShapes().to(s -> s));
	}

	//method
	/**
	 * @return the root shape of this 3D GUI.
	 */
	public final Shape<?> getRefRootShape() {
		
		//Checks if this 3D GUI has a root shape.
		if (!hasRootShape()) {
			throw new UnexistingAttributeException(this, "root shape");
		}
		
		return rootShape;
	}
	
	//method
	/**
	 * @return true if this 3D GUI has a root shape.
	 */
	public final boolean hasRootShape() {
		return (rootShape != null);
	}
	
	//method
	/**
	 * @return true if this 3D GUI has a root shape.
	 */
	public final boolean isEmpty() {
		return hasRootShape();
	}
	
	//method
	/**
	 * Removes the root shape of this 3D GUI.
	 * 
	 * @return this 3D GUI.
	 */
	public final G removeRootShape() {
		
		rootShape = null;
		
		return getInstance();
	}
	
	//method
	/**
	 * Refreshes this 3D GUI.
	 */
	public void refresh() {
		
		//Handles the option that this 3D GUI has a root shape.
		if (hasRootShape()) {
			getRefRootShape().renderRecursively();
		}
	}
	
	//method
	/**
	 * Sets the root shape of this 3D GUI.
	 * 
	 * @param rootShape
	 * @return this main frame.
	 * @throws NullArgumentException if the given root shape is null.
	 */
	public G setRootShape(final Shape<?> rootShape) {
		
		//Adds the given root shape to this 3D GUI.
		rootShape.setGUI(this);
		
		//Sets the root shape of this 3D GUI.
		this.rootShape = rootShape;
		
		return getInstance();
	}
	
	/**
	 * @param shape
	 * @return a new shape renderer for the given shape from this 3D GUI.
	 */
	protected final IShapeRenderer<?, ?, ?> getShapeRendererFor(final Shape<?> shape) {
		return
		shapeClasses
		.getRefFirst(sc -> sc.getRefElement1() == shape.getClass())
		.getRefElement2();
	}
	
	//method
	/**
	 * @param type
	 * @return a new shape of the given type with default values.
	 * @throws InvalidArgumentException if this 3D GUI cannot create a shape of the given type.
	 */
	private Shape<?> createShape(final String type) {
		try {
			return
			(Shape<?>)
			shapeClasses
			.getRefFirst(sc -> sc.getRefElement1().getSimpleName().equals(type))
			.getRefElement1().newInstance();
		}
		catch (final InstantiationException | IllegalAccessException exception) {
			throw new RuntimeException(exception);
		}
	}
	
	//method
	/**
	 * @return the shapes of this 3D GUI.
	 */
	private final AccessorContainer<Shape<?>> getRefShapes() {
		
		final List<Shape<?>> shapes = new List<>();
		
		//Handles the option that this 3D GUI has a root shape.
		if (hasRootShape()) {
			shapes.addAtEnd(getRefRootShape());
		}
		
		return new AccessorContainer<>(shapes);
	}
}
