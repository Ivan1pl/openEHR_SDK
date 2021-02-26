package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import java.lang.String;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.LocatableEntity;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-02-16T12:59:53.775777800+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null"
)
public class RecommendationRecommendationElement implements LocatableEntity {
  /**
   * Path: open_eREACT-Care/Response/Recommendation/Recommendation
   * Description: Narrative description of the recommendation.
   * Comment: May be coded, using a terminology, if required.
   */
  @Path("/value|value")
  private String value;

  /**
   * Path: open_eREACT-Care/Response/Recommendation/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setValue(String value) {
     this.value = value;
  }

  public String getValue() {
     return this.value ;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
     this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
     return this.feederAudit ;
  }
}
