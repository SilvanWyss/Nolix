//package declaration
package ch.nolix.tech;

//own imports
import ch.nolix.tech.texture.ConcreteTextureCreator;
import ch.nolix.tech.texture.JuteTextureCreator;
import ch.nolix.techAPI.ITechFactory;

//class
public final class TechFactory implements ITechFactory {

	//method
	public ConcreteTextureCreator createConreteTextureCreator() {
		return new ConcreteTextureCreator();
	}

	//method
	public JuteTextureCreator createJuteTextureCreator() {
		return new JuteTextureCreator();
	}
}
