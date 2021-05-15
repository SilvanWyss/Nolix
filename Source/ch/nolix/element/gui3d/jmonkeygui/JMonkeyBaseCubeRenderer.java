//package declaration
package ch.nolix.element.gui3d.jmonkeygui;

//JMonkey imports
import com.jme3.material.Material;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;
import com.jme3.texture.Texture2D;
import com.jme3.texture.plugins.AWTLoader;

//own imports
import ch.nolix.element.gui3d.shape.BaseCube;

//class
/**
 * A {@link JMonkeyBaseCubeRenderer} is a base cube renderer for JMonkey.
 * 
 * @author Silvan Wyss
 * @date 2017-11-11
 * @lines 80
 */
public final class JMonkeyBaseCubeRenderer implements IJMonkeyShapeRenderer<BaseCube, Geometry> {
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Geometry createRenderObject() {
		
		//Creates box.
		final var box = new Box(1, 1, 1);
		
		return new Geometry("Box", box);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void render(final BaseCube baseCube, final Geometry geometry) {
		
		//Sets the position to the given geometry.
		geometry.setLocalTranslation(
			baseCube.getXPositionAsFloat(),
			baseCube.getYPositionAsFloat(),
			baseCube.getZPositionAsFloat()
		);
		
		//Sets the size to the given geometry.
		geometry.scale(
			baseCube.getXLengthAsFloat(),
			baseCube.getYLengthAsFloat(),
			baseCube.getZLengthAsFloat()
		);
		
		//Handles the case that the given baseCube does not have a default texture.
		if (!baseCube.hasDefaultTexture()) {
			
			final var material = new Material(JMonkeyHelper.getAssetManager(), "Common/MatDefs/Light/Lighting.j3md");
			
			material.setBoolean("UseMaterialColors", true);
			
			material.setColor(
				"Diffuse",
				JMonkeyColorHelper.createColorRGBA(baseCube.getDefaultColor())
			);
			
			geometry.setMaterial(material);
			
		//Handles the case that the given baseCube has a default texture.
		} else {
			
			final var material = new Material(JMonkeyHelper.getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");
			
			final var texture =
			new Texture2D(new AWTLoader().load(baseCube.getRefDefaultTexture().toBufferedImage(),true));
			
			material.setTexture("ColorMap", texture);
			
			geometry.setMaterial(material);
		}
	}
}
