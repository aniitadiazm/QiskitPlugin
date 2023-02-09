package org.sonarsource.plugins.qiskit.measures;

import org.sonar.api.ce.measure.Measure;
import org.sonar.api.ce.measure.MeasureComputer;

import static org.sonarsource.plugins.qiskit.measures.QiskitMetrics.Nº2CG;
import static org.sonarsource.plugins.qiskit.measures.QiskitMetrics.NºCNOT;
import static org.sonarsource.plugins.qiskit.measures.QiskitMetrics.NºCY;
import static org.sonarsource.plugins.qiskit.measures.QiskitMetrics.NºCZ;
import static org.sonarsource.plugins.qiskit.measures.QiskitMetrics.NºCU;
import static org.sonarsource.plugins.qiskit.measures.QiskitMetrics.NºCH;
import static org.sonarsource.plugins.qiskit.measures.QiskitMetrics.NºCRZ;
import static org.sonarsource.plugins.qiskit.measures.QiskitMetrics.NºCP;
import static org.sonarsource.plugins.qiskit.measures.QiskitMetrics.NºCU3;
import static org.sonarsource.plugins.qiskit.measures.QiskitMetrics.NºSWAP;

public class Nº2CGmeasure implements MeasureComputer {

  @Override
  public MeasureComputerDefinition define(MeasureComputerDefinitionContext def) {
    return def.newDefinitionBuilder()
      .setInputMetrics(NºCNOT.key(), NºCY.key(), NºCZ.key(), NºCU.key(), NºCH.key(), NºCRZ.key(), NºCP.key(), NºCU3.key(),
        NºSWAP.key())
      .setOutputMetrics(Nº2CG.key())
      .build();
  }

  public void compute(MeasureComputerContext context) {
    Measure CNOT = context.getMeasure(NºCNOT.key());
    Measure CY = context.getMeasure(NºCY.key());
    Measure CZ = context.getMeasure(NºCZ.key());
    Measure CU = context.getMeasure(NºCU.key());
    Measure CH = context.getMeasure(NºCH.key());
    Measure CRZ = context.getMeasure(NºCRZ.key());
    Measure CP = context.getMeasure(NºCP.key());
    Measure CU3 = context.getMeasure(NºCU3.key());
    Measure SWAP = context.getMeasure(NºSWAP.key());
    
    int C2Gates = CNOT.getIntValue() + CY.getIntValue() + CZ.getIntValue() + CU.getIntValue() +CH.getIntValue() +
    CRZ.getIntValue() + CP.getIntValue() + CU3.getIntValue() + SWAP.getIntValue();

    context.addMeasure(Nº2CG.key(), C2Gates);
  }
}
