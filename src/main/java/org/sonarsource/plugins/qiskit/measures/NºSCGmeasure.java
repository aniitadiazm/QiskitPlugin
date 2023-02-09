package org.sonarsource.plugins.qiskit.measures;

import org.sonar.api.ce.measure.Measure;
import org.sonar.api.ce.measure.MeasureComputer;

import static org.sonarsource.plugins.qiskit.measures.QiskitMetrics.NºSCG;
import static org.sonarsource.plugins.qiskit.measures.QiskitMetrics.NºPG;
import static org.sonarsource.plugins.qiskit.measures.QiskitMetrics.NºCliffG;
import static org.sonarsource.plugins.qiskit.measures.QiskitMetrics.NºC3G;
import static org.sonarsource.plugins.qiskit.measures.QiskitMetrics.NºSRG;
import static org.sonarsource.plugins.qiskit.measures.QiskitMetrics.NºU;
import static org.sonarsource.plugins.qiskit.measures.QiskitMetrics.NºCU;
import static org.sonarsource.plugins.qiskit.measures.QiskitMetrics.NºP;
import static org.sonarsource.plugins.qiskit.measures.QiskitMetrics.NºI;

public class NºSCGmeasure implements MeasureComputer {

  @Override
  public MeasureComputerDefinition define(MeasureComputerDefinitionContext def) {
    return def.newDefinitionBuilder()
      .setInputMetrics(NºPG.key(), NºCliffG.key(), NºC3G.key(), NºSRG.key(), NºU.key(), NºCU.key(), NºP.key(), NºI.key())
      .setOutputMetrics(NºSCG.key())
      .build();
  }

  public void compute(MeasureComputerContext context) {
    Measure PG = context.getMeasure(NºPG.key());
    Measure CliffG = context.getMeasure(NºCliffG.key());
    Measure C3G = context.getMeasure(NºC3G.key());
    Measure SRG = context.getMeasure(NºSRG.key());
    Measure U = context.getMeasure(NºU.key());
    Measure P = context.getMeasure(NºP.key());
    Measure I = context.getMeasure(NºI.key());
    
    int SCGates = PG.getIntValue() + CliffG.getIntValue() + C3G.getIntValue() + SRG.getIntValue() + U.getIntValue()
    + P.getIntValue() + I.getIntValue();

    context.addMeasure(NºSCG.key(), SCGates);
  }
}
