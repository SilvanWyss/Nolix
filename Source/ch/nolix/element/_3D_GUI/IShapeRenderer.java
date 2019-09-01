//package declaration
package ch.nolix.element._3D_GUI;

import ch.nolix.common.invalidArgumentExceptions.ArgumentDoesNotSupportMethodException;

//interface
/**
 * @author Silvan Wyss
 * @month 2017-11
 * @lines 60
 * @param <S> The type of the shape a shape renderer can render.
 */
public interface IShapeRenderer<S extends Shape<S>, RO, SRO> {

	//default method
	/**
	 * Lets this shape renderer
	 * add the given sub render object to the given render object.
	 * 
	 * @param renderObject
	 * @param subRenderObject
	 */
	public default void addSubRenderObject(
		final RO renderObject,
		final SRO subRenderObject
	) {
		throw new ArgumentDoesNotSupportMethodException(
			this,
			"add sub render object"
		);
	}
	
	//abstract method
	/**
	 * @return a new render object for the shape of this shape renderer.
	 */
	public abstract RO createRenderObject();
	
	//abstract method
	/**
	 * Lets this shape renderer render the given shape.
	 * 
	 * @param shape
	 */
	public abstract void render(final S shape, final RO renderObject);
	
	//default method
	/**
	 * Lets this shape renderer
	 * remove the given sub render object from the given render object.
	 * 
	 * @param renderObject
	 * @param subRederObject
	 */
	public default void removeSubRenderObject(
		RO renderObject,
		SRO subRederObject
	) {
		throw new ArgumentDoesNotSupportMethodException(
			this,
			"remove sub render object"
		);
	}
}
