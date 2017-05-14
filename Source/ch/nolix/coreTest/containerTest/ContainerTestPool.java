/*
 * file:	ContainerTestPool.java
 * author:	Silvan Wyss
 * month:	2016-08
 * lines:	20
 */

//package declaration
package ch.nolix.coreTest.containerTest;

//own import
import ch.nolix.core.test.TestPool;

//class
public class ContainerTestPool extends TestPool {

	//constructor
	/**
	 * Creates new container test pool.
	 */
	public ContainerTestPool() {
		
		//Adds tests to this container test pool.
		addTest(new ListTest());
		addTest(new MatrixTest());
	}
}
