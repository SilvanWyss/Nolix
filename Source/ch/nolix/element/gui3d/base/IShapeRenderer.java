//package declaration
package ch.nolix.element.gui3d.base;

import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentDoesNotSupportMethodException;

//interface
/**
 * @author Silvan Wyss
 * @date 2017-11-11
 * @lines 60
 * @param <S> is the type of the shape a shape renderer can render.
 * @param <RO> is the type of the render objects a {@link IShapeRenderer} provides.
 * @param <SRO> is the type of the sub render objects of a {@link IShapeRenderer}.
 */
public interface IShapeRenderer<S extends Shape<S>, RO, SRO> {

	//method
	/**
	 * Lets this shape renderer
	 * add the given sub render object to the given render object.
	 * 
	 * @param renderObject
	 * @param subRenderObject
	 */
	default void addSubRenderObject(
		final RO renderObject,
		final SRO subRenderObject
	) {
		throw new ArgumentDoesNotSupportMethodException(
			this,
			"add sub render object"
		);
	}
	
	//method declaration
	/**
	 * @return a new render object for the shape of this shape renderer.
	 */
	RO createRenderObject();
	
	//method declaration
	/**
	 * Lets this shape renderer render the given shape.
	 * 
	 * @param shape
	 * @param renderObject
	 */
	void render(final S shape, final RO renderObject);
	
	//method
	/**
	 * Lets this shape renderer
	 * remove the given sub render object from the given render object.
	 * 
	 * @param renderObject
	 * @param subRederObject
	 */
	default void removeSubRenderObject(
		RO renderObject,
		SRO subRederObject
	) {
		throw new ArgumentDoesNotSupportMethodException(
			this,
			"remove sub render object"
		);
	}
}
