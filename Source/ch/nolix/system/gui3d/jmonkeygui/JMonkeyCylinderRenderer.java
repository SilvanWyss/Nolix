//package declaration
package ch.nolix.system.gui3d.jmonkeygui;

//JMonkey imports
import com.jme3.material.Material;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Spatial;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotSupportMethodException;
import ch.nolix.core.math.NumberCatalogue;
import ch.nolix.system.gui3d.shape.Cylinder;

//class
public final class JMonkeyCylinderRenderer implements IJMonkeyShapeRenderer<Cylinder, Geometry> {
	
	//method
	@Override
	public void addSubRenderObject(final Geometry renderObject, final Spatial subRenderObject) {
		throw ArgumentDoesNotSupportMethodException.forArgumentAndMethodName(this, "addSubRenderObject");
	}
	
	//method
	@Override
	public Geometry createRenderObject() {
		
		final var cylinder = new com.jme3.scene.shape.Cylinder(100, 100, 1.0F, 1.0F, true);
		
		return new Geometry("Cylinder", cylinder);
	}
	
	//method
	@Override
	public void removeSubRenderObject(final Geometry renderObject, final Spatial subRederObject) {
		throw ArgumentDoesNotSupportMethodException.forArgumentAndMethodName(this, "removeSubRenderObject");
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
