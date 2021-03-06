//package declaration
package ch.nolix.element.gui3d.jmonkeygui;

//JMonkey import
import com.jme3.material.Material;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;

//own imports
import ch.nolix.common.constant.NumberCatalogue;
import ch.nolix.element.gui3d.shape.Cylinder;

//class
public final class JMonkeyCylinderRenderer
implements IJMonkeyShapeRenderer<Cylinder, Geometry> {

	//method
	@Override
	public Geometry createRenderObject() {
		
		final var cylinder = new com.jme3.scene.shape.Cylinder(100, 100, 1.0F, 1.0F, true);
		
		return new Geometry("Cylinder", cylinder);
	}

	//method
	@Override
	public void render(final Cylinder cylinder, final Geometry geometry) {
		
		geometry.setLocalTranslation(
			cylinder.getXPositionAsFloat(),
			cylinder.getYPositionAsFloat(),
			cylinder.getZPositionAsFloat()
		);
		
		final var radius = cylinder.getRadiusAsFloat();
		final var height = cylinder.getHeightAsFloat();
		
		geometry.scale(radius, radius, height);
		
		final var rotation = new Quaternion();
		rotation.fromAngleAxis((float)NumberCatalogue.PI, new Vector3f(0.0F, 1.0F, 1.0F));
		geometry.setLocalRotation(rotation);
		
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
