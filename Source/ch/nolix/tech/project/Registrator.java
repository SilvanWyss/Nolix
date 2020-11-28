//package declaration
package ch.nolix.tech.project;

import ch.nolix.common.instanceprovider.CentralInstanceProvider;
import ch.nolix.techapi.projectapi.IProject;
import ch.nolix.techapi.projectapi.ITask;

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
