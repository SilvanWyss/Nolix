//package declaration
package ch.nolix.techapi.relationaldocapi.baseapi;

//own imports
import ch.nolix.coreapi.datamodelapi.entityrequestapi.ContentTypeRequestable;

//interface
public interface ContentTypeAssignable<CTA extends ContentTypeAssignable<CTA>> extends ContentTypeRequestable {

  //method declaration
  CTA setForReferences();

  //method declaration
  CTA setForValues();
}
