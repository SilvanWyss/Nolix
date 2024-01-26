//package declaration
package ch.nolix.core.programcontrol.usercontrol;

//own imports
import ch.nolix.core.programstructure.builder.andargumentcapturer.AndPasswordCapturer;
import ch.nolix.core.programstructure.builder.withargumentcapturer.WithLoginNameCapturer;

//class
public final class CredentialBuilder extends WithLoginNameCapturer<AndPasswordCapturer<Credential>> {

  //constructor
  private CredentialBuilder() {

    super(new AndPasswordCapturer<>());

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
