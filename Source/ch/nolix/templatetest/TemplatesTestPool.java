//package declaration
package ch.nolix.templatetest;

//own imports
import ch.nolix.core.testing.basetest.TestPool;
import ch.nolix.templatetest.mathtest.MathTestPool;
import ch.nolix.templatetest.texturetest.TextureTestPool;

//class
public final class TemplatesTestPool extends TestPool {
	
	//constructor
	public TemplatesTestPool() {
		super(new MathTestPool(), new TextureTestPool());
	}
}
