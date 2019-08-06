//package declaration
package ch.nolix.element.JMonkeyGUI;

//JMonkey imports
import com.jme3.scene.Spatial;

import ch.nolix.element._3D_GUI.IShapeRenderer;
import ch.nolix.element._3D_GUI.Shape;

//interface
/**
 * @author Silvan Wyss
 * @month 2017-11
 * @lines 20
 * @param <S> The type of the shape of a JMonkey shape renderer.
 * @param <RO> The type of the render object of a JMonkey shape renderer.
 */
public interface IJMonkeyShapeRenderer<S extends Shape<S>, RO extends Spatial>
extends IShapeRenderer<S, RO, Spatial>{}
