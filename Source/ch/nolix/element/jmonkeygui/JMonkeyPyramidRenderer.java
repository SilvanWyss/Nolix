//package declaration
package ch.nolix.element.jmonkeygui;

//JMonkey imports
import com.jme3.material.Material;
import com.jme3.scene.Geometry;
import com.jme3.texture.Texture2D;
import com.jme3.texture.plugins.AWTLoader;

import ch.nolix.element.shape.Pyramid;

//class
final class JMonkeyPyramidRenderer implements IJMonkeyShapeRenderer<Pyramid, Geometry> {
	
	//method
	@Override
	public Geometry createRenderObject() {
		return new Geometry(
			"Dome",
			new com.jme3.scene.shape.Dome(2, 4, 1.0F)
		);
	}
	
	//method
	@Override
	public void render(final Pyramid pyramid, final Geometry geometry) {
		
		geometry.setLocalTranslation(
			pyramid.getXPositionAsFloat(),
			pyramid.getYPositionAsFloat(),
			pyramid.getZPositionAsFloat()
		);
		
		geometry.scale(
			pyramid.getSideLengthAsFloat(),
			pyramid.getSideLengthAsFloat(),
			pyramid.getHeightAsFloat()
		);
		
		if (!pyramid.hasDefaultTexture()) {
			
			final var material =
			new Material(JMonkeyHelper.getAssetManager(), "Common/MatDefs/Light/Lighting.j3md");
			
			material.setBoolean("UseMaterialColors", true);
							
			material.setColor(
				"Diffuse",
				JMonkeyColorHelper.createColorRGBA(pyramid.getDefaultColor())
			);
			
			geometry.setMaterial(material);
		}
		else {
			
			final var material
			= new Material(JMonkeyHelper.getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");
			
			final var texture =
			new Texture2D(new AWTLoader().load(pyramid.getDefaultTextureAsBufferedImage(), true));
			
			material.setTexture("ColorMap", texture);
			
			geometry.setMaterial(material);
		}
	}
}
