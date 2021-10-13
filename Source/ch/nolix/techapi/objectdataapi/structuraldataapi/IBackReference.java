//package declaration
package ch.nolix.techapi.objectdataapi.structuraldataapi;

//interface
public interface IBackReference<
	BR extends IBackReference<BR, SE>,
	SE extends IStructuralEntity<SE, BR>
> extends ISingleBackReference<BR, SE> {}
