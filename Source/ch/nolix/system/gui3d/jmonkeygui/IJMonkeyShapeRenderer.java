//package declaration
package ch.nolix.system.gui3d.jmonkeygui;

//JMonkey imports
import com.jme3.scene.Spatial;

//own imports
import ch.nolix.system.gui3d.main.IShapeRenderer;
import ch.nolix.system.gui3d.main.Shape;

//interface
/**
 * @author Silvan Wyss
 * @date 2017-11-11
 * @param <S> is the type of the shape of a JMonkey shape renderer.
 * @param <RO> is the type of the render object of a JMonkey shape renderer.
 */
public interface IJMonkeyShapeRenderer<
	S extends Shape<S>,
	RO extends Spatial
>
extends IShapeRenderer<S, RO, Spatial>{}
