package org.sonarsource.plugins.qiskit.measures;

import org.sonar.api.ce.measure.Measure;
import org.sonar.api.ce.measure.MeasureComputer;

import static org.sonarsource.plugins.qiskit.measures.QiskitMetrics.Nº3CG;
import static org.sonarsource.plugins.qiskit.measures.QiskitMetrics.NºToff;
import static org.sonarsource.plugins.qiskit.measures.QiskitMetrics.NºFred;

public class Nº3CGmeasure implements MeasureComputer {

  @Override
  public MeasureComputerDefinition define(MeasureComputerDefinitionContext def) {
    return def.newDefinitionBuilder()
      .setInputMetrics(NºToff.key(), NºFred.key())
      .setOutputMetrics(Nº3CG.key())
      .build();
  }

  public void compute(MeasureComputerContext context) {
    Measure Toff = context.getMeasure(NºToff.key());
    Measure Fred = context.getMeasure(NºFred.key());
    
    int C3Gates = Toff.getIntValue() + Fred.getIntValue();

    context.addMeasure(Nº3CG.key(), C3Gates);
  }
}
