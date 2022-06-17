//package declaration
package ch.nolix.system.discretegeometry;

import ch.nolix.core.container.main.LinkedList;
import ch.nolix.core.document.node.BaseNode;
import ch.nolix.core.document.node.Node;
import ch.nolix.systemapi.elementapi.mainuniversalapi.Specified;

//class
/**
 * A {@link Discrete2DPoint} is a point with 2 discrete coordinates.
 * A {@link Discrete2DPoint} is not mutable.
 * 
 * @author Silvan Wyss
 * @date 2019-05-19
 */
public final class Discrete2DPoint implements Specified {
	
	//static method
	/**
	 * Creates a new {@link Discrete2DPoint} from the given specification.
	 * 
	 * @param specification
	 * @return a new {@link Discrete2DPoint} from the given specification.
	 */
	public static Discrete2DPoint fromSpecification(final BaseNode specification) {
		return
		new Discrete2DPoint(specification.getRefAttributeAt(1).toInt(), specification.getRefAttributeAt(2).toInt());
	}
	
	//attributes
	private final int x;
	private final int y;
	
	//method
	/**
	 * Creates a new {@link Discrete2DPoint} with the given x and y.
	 * 
	 * @param x
	 * @param y
	 */
	public Discrete2DPoint(final int x, final int y) {
		this.x = x;
		this.y = y;
	}
		
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void fillUpAttributesInto(final LinkedList<Node> list) {
		list.addAtEnd(Node.withHeader(x), Node.withHeader(y));
	}
	
	//method
	/**
	 * @return the x-coordinate of the current {@link Discrete2DPoint}.
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * @return the y-coordinate of the current {@link Discrete2DPoint}.
	 */
	//method
	public int getY() {
		return y;
	}
}
