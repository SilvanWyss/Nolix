//package declaration
package ch.nolix.tech.project;

import ch.nolix.businessapi.projectapi.IProject;
import ch.nolix.businessapi.projectapi.ITask;
import ch.nolix.common.instanceprovider.CentralInstanceProvider;

//class
public final class Registrator {
	
	//static method
	public static void register() {
		CentralInstanceProvider
		.register(IProject.class, Project.class)
		.register(ITask.class, Task.class);
	}
	
	//visibility-reduced constructor
	private Registrator() {}
}
