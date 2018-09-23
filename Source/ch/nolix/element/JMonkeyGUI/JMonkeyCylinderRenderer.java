//package declaration
package ch.nolix.element.JMonkeyGUI;

import com.jme3.material.Material;
//JMonkey import
import com.jme3.scene.Geometry;

//own import
import ch.nolix.element._3DGUI.Cylinder;

//class
public final class JMonkeyCylinderRenderer
implements IJMonkeyShapeRenderer<Cylinder, Geometry> {

	//method
	public Geometry createRenderObject() {
		
		final var cylinder = new com.jme3.scene.shape.Cylinder(100, 100, 1.0f, 1.0f, true);
		
		return new Geometry("Cylinder", cylinder);
	}

	//method
	public void render(final Cylinder cylinder, final Geometry geometry) {
		
		geometry.setLocalTranslation(
			cylinder.getXPositionAsFloat(),
			cylinder.getYPositionAsFloat(),
			cylinder.getZPositionAsFloat()
		);
		
		geometry.scale(cylinder.getRadiusAsFloat(), cylinder.getRadiusAsFloat(), cylinder.getHeightAsFloat());
		
		final var material =
		new Material(JMonkeyHelper.getAssetManager(), "Common/MatDefs/Light/Lighting.j3md");
		
		material.setBoolean("UseMaterialColors", true);
						
		material.setColor(
			"Diffuse",
			JMonkeyColorHelper.createColorRGBA(cylinder.getDefaultColor())
		);
		
		geometry.setMaterial(material);
	}
}
