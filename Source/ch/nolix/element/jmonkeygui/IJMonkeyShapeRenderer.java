//package declaration
package ch.nolix.element.jmonkeygui;

//JMonkey imports
import com.jme3.scene.Spatial;

import ch.nolix.element.gui3d.base.IShapeRenderer;
import ch.nolix.element.gui3d.base.Shape;

//interface
/**
 * @author Silvan Wyss
 * @month 2017-11
 * @lines 20
 * @param <S> is the type of the shape of a JMonkey shape renderer.
 * @param <RO> is the type of the render object of a JMonkey shape renderer.
 */
public interface IJMonkeyShapeRenderer<S extends Shape<S>, RO extends Spatial>
extends IShapeRenderer<S, RO, Spatial>{}
