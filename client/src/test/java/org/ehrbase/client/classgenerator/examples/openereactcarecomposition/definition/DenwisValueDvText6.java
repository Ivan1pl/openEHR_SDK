package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import java.lang.String;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;

@Entity
@OptionFor("DV_TEXT")
public class DenwisValueDvText6 implements DenwisValueChoice6 {
  /**
   * open_eREACT-Care/Assessment/DENWIS/Point in time/value/value
   */
  @Path("|value")
  private String valueValue;

  public void setValueValue(String valueValue) {
     this.valueValue = valueValue;
  }

  public String getValueValue() {
     return this.valueValue ;
  }
}
