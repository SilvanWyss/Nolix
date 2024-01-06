//package declaration
package ch.nolix.techapi.relationaldocapi.datamodelapi;

//Java imports
import java.util.function.Predicate;

//interface
public interface IAbstractReferenceContent extends IReferenceContent {

  //method declaration
  IAbstractReferenceContent addConstraint(Predicate<IAbstractableObject> constraint);

  //method declaration
  void removeConstraint(Predicate<IAbstractableObject> constraint);

  //method declaration
  void removeConstraints();

  //method declaration
  IAbstractReferenceContent setReferencedType(IAbstractableObject referencedType);
}
