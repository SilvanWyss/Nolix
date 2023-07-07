//package declaration
package ch.nolix.templatetest;

//own imports
import ch.nolix.core.testing.basetest.TestPool;
import ch.nolix.templatetest.graphictest.GraphicTestPool;
import ch.nolix.templatetest.mathtest.MathTestPool;

//class
public final class TemplatesTestPool extends TestPool {
	
	//constructor
	public TemplatesTestPool() {
		super(new GraphicTestPool(), new MathTestPool());
	}
}
