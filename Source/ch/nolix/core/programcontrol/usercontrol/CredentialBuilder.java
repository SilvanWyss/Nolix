//package declaration
package ch.nolix.core.programcontrol.usercontrol;

import ch.nolix.core.argumentcaptor.andargumentcaptor.AndPasswordCaptor;
import ch.nolix.core.argumentcaptor.withargumentcaptor.WithLoginNameCaptor;

//class
public final class CredentialBuilder extends WithLoginNameCaptor<AndPasswordCaptor<Credential>> {

  //constructor
  private CredentialBuilder() {

    super(new AndPasswordCaptor<>());

    setBuilder(this::buildCredential);
  }

  //static method
  public static CredentialBuilder createCredential() {
    return new CredentialBuilder();
  }

  //method
  private Credential buildCredential() {
    return new Credential(getLoginName(), next().getPassword());
  }
}
