//package declaration
package ch.nolix.tech;

//own imports
import ch.nolix.tech.resource.Resource;
import ch.nolix.tech.texture.ConcreteTextureCreator;
import ch.nolix.tech.texture.JuteTextureCreator;
import ch.nolix.techAPI.ITechFactory;
import ch.nolix.techAPI.resourceAPI.IResource;

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

	//method
	public IResource createResource(final String name) {
		return new Resource(name);
	}

	//method
	public IResource createResource(final String name, final IResource... baseResources) {
		return new Resource(name, baseResources);
	}
}
