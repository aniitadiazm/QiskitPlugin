package org.sonarsource.plugins.qiskit.measures;

import org.sonar.api.ce.measure.Measure;
import org.sonar.api.ce.measure.MeasureComputer;

import static org.sonarsource.plugins.qiskit.measures.QiskitMetrics.NºTG;
import static org.sonarsource.plugins.qiskit.measures.QiskitMetrics.NºNOT;
import static org.sonarsource.plugins.qiskit.measures.QiskitMetrics.NºY;
import static org.sonarsource.plugins.qiskit.measures.QiskitMetrics.NºZ;
import static org.sonarsource.plugins.qiskit.measures.QiskitMetrics.NºI;
import static org.sonarsource.plugins.qiskit.measures.QiskitMetrics.NºU;
import static org.sonarsource.plugins.qiskit.measures.QiskitMetrics.NºP;
import static org.sonarsource.plugins.qiskit.measures.QiskitMetrics.NºH;
import static org.sonarsource.plugins.qiskit.measures.QiskitMetrics.NºS;
import static org.sonarsource.plugins.qiskit.measures.QiskitMetrics.NºIS;
import static org.sonarsource.plugins.qiskit.measures.QiskitMetrics.NºT;
import static org.sonarsource.plugins.qiskit.measures.QiskitMetrics.NºIT;
import static org.sonarsource.plugins.qiskit.measures.QiskitMetrics.NºRX;
import static org.sonarsource.plugins.qiskit.measures.QiskitMetrics.NºRY;
import static org.sonarsource.plugins.qiskit.measures.QiskitMetrics.NºRZ;
import static org.sonarsource.plugins.qiskit.measures.QiskitMetrics.NºCNOT;
import static org.sonarsource.plugins.qiskit.measures.QiskitMetrics.NºCY;
import static org.sonarsource.plugins.qiskit.measures.QiskitMetrics.NºCZ;
import static org.sonarsource.plugins.qiskit.measures.QiskitMetrics.NºCU;
import static org.sonarsource.plugins.qiskit.measures.QiskitMetrics.NºCH;
import static org.sonarsource.plugins.qiskit.measures.QiskitMetrics.NºCRZ;
import static org.sonarsource.plugins.qiskit.measures.QiskitMetrics.NºCP;
import static org.sonarsource.plugins.qiskit.measures.QiskitMetrics.NºCU3;
import static org.sonarsource.plugins.qiskit.measures.QiskitMetrics.NºSWAP;
import static org.sonarsource.plugins.qiskit.measures.QiskitMetrics.NºToff;
import static org.sonarsource.plugins.qiskit.measures.QiskitMetrics.NºFred;

public class NºTGmeasure implements MeasureComputer {

  @Override
  public MeasureComputerDefinition define(MeasureComputerDefinitionContext def) {
        return def.newDefinitionBuilder()
        .setInputMetrics(NºNOT.key(), NºY.key(), NºZ.key(), NºI.key(), NºU.key(), NºP.key(), NºH.key(), NºS.key(),
        NºIS.key(), NºT.key(), NºIT.key(), NºRX.key(), NºRY.key(), NºRZ.key(), NºCNOT.key(), NºCY.key(),
        NºCZ.key(), NºCU.key(), NºCH.key(), NºCRZ.key(), NºCP.key(), NºCU3.key(), NºSWAP.key(), NºToff.key(), NºFred.key())
        .setOutputMetrics(NºTG.key())
        .build();
  }

  public void compute(MeasureComputerContext context) {
        Measure NOT = context.getMeasure(NºNOT.key());
        Measure Y = context.getMeasure(NºY.key());
        Measure Z = context.getMeasure(NºZ.key());
        Measure I = context.getMeasure(NºI.key());
        Measure U = context.getMeasure(NºU.key());
        Measure P = context.getMeasure(NºP.key());
        Measure H = context.getMeasure(NºH.key());
        Measure S = context.getMeasure(NºS.key());
        Measure IS = context.getMeasure(NºIS.key());
        Measure T = context.getMeasure(NºT.key());
        Measure IT = context.getMeasure(NºIT.key());
        Measure RX = context.getMeasure(NºRX.key());
        Measure RY = context.getMeasure(NºRY.key());
        Measure RZ = context.getMeasure(NºRZ.key());
        Measure CNOT = context.getMeasure(NºCNOT.key());
        Measure CY = context.getMeasure(NºCY.key());
        Measure CZ = context.getMeasure(NºCZ.key());
        Measure CU = context.getMeasure(NºCU.key());
        Measure CH = context.getMeasure(NºCH.key());
        Measure CRZ = context.getMeasure(NºCRZ.key());
        Measure CP = context.getMeasure(NºCP.key());
        Measure CU3 = context.getMeasure(NºCU3.key());
        Measure SWAP = context.getMeasure(NºSWAP.key());
        Measure Toff = context.getMeasure(NºToff.key());
        Measure Fred = context.getMeasure(NºFred.key());
        
        int TotalGates = NOT.getIntValue() + Y.getIntValue() + Z.getIntValue() + I.getIntValue() + U.getIntValue() +
        P.getIntValue() + H.getIntValue() + S.getIntValue() + IS.getIntValue() + T.getIntValue() + IT.getIntValue() +
        RX.getIntValue() + RY.getIntValue() + RZ.getIntValue() + CNOT.getIntValue() + CY.getIntValue() + CZ.getIntValue()
        + CU.getIntValue() + CH.getIntValue() + CRZ.getIntValue() + CP.getIntValue() + CU3.getIntValue() + SWAP.getIntValue()
        + Toff.getIntValue() + Fred.getIntValue();

        context.addMeasure(NºTG.key(), TotalGates);
    }
}
