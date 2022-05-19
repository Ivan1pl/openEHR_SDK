package org.ehrbase.client.classgenerator.examples.laborbefundcomposition.definition;

import java.net.URI;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.RMEntity;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-06-10T14:23:37.621321+07:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.4.0"
)
@OptionFor("DV_URI")
public class LaboranalytResultatProbeIdDvUri implements RMEntity, LaboranalytResultatProbeIdChoice {
  /**
   * Path: Laborbefund/Laborergebnis/Jedes Ereignis/Laboranalyt-Resultat/Probe ID/Probe ID
   * Description: Kennung der Probe, die für das Analyseergebnis verwendet wurde.
   */
  @Path("|value")
  private URI probeIdValue;

  public void setProbeIdValue(URI probeIdValue) {
     this.probeIdValue = probeIdValue;
  }

  public URI getProbeIdValue() {
     return this.probeIdValue ;
  }
}
