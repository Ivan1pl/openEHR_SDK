package org.ehrbase.client.classgenerator.examples.coronaanamnesecomposition.definition;

import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

@Entity
@Archetype("openEHR-EHR-SECTION.adhoc.v1")
public class KontaktSection {
  /**
   * Bericht/Kontakt/Personenkontakt
   */
  @Path("/items[openEHR-EHR-OBSERVATION.exposure_assessment.v0 and name/value='Personenkontakt']")
  private PersonenkontaktObservation personenkontakt;

  /**
   * Bericht/Kontakt/Aufenthalt in Gesundheitseinrichtung
   */
  @Path("/items[openEHR-EHR-OBSERVATION.exposure_assessment.v0 and name/value='Aufenthalt in Gesundheitseinrichtung']")
  private AufenthaltInGesundheitseinrichtungObservation aufenthaltInGesundheitseinrichtung;

  public void setPersonenkontakt(PersonenkontaktObservation personenkontakt) {
     this.personenkontakt = personenkontakt;
  }

  public PersonenkontaktObservation getPersonenkontakt() {
     return this.personenkontakt ;
  }

  public void setAufenthaltInGesundheitseinrichtung(
      AufenthaltInGesundheitseinrichtungObservation aufenthaltInGesundheitseinrichtung) {
     this.aufenthaltInGesundheitseinrichtung = aufenthaltInGesundheitseinrichtung;
  }

  public AufenthaltInGesundheitseinrichtungObservation getAufenthaltInGesundheitseinrichtung() {
     return this.aufenthaltInGesundheitseinrichtung ;
  }
}
