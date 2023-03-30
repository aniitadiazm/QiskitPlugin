package org.sonarsource.plugins.qiskit.measures;

import static java.util.Arrays.asList;
import java.util.List;

import org.sonar.api.measures.Metric;
import org.sonar.api.measures.Metrics;

public class QiskitMetrics implements Metrics {
  
    public static final Metric<Integer> WIDTH =
		new Metric.Builder("WIDTH", "Total qubits", Metric.ValueType.INT)
	    .setDescription("Total qubits")
	    .setDirection(Metric.DIRECTION_NONE)
	    .setQualitative(false)
	    .setDomain("Circuit width")
	    .create();

	public static final Metric<Integer> DEPTH =
		new Metric.Builder("DEPTH", "Maximum gates in a qubit", Metric.ValueType.INT)
		.setDescription("Maximum gates in a qubit")
		.setDirection(Metric.DIRECTION_NONE)
		.setQualitative(false)
		.setDomain("Circuit depth")
		.create();
	
	public static final Metric<Integer> LCG =
		new Metric.Builder("LCG", "Low complexity gates", Metric.ValueType.INT)
		.setDescription("Low complexity gates")
		.setDirection(Metric.DIRECTION_NONE)
		.setQualitative(false)
		.setDomain("Complexity of circuit gates")
		.create();
	
	public static final Metric<Integer> MCG =
		new Metric.Builder("MCG", "Medium complexity gates", Metric.ValueType.INT)
		.setDescription("Medium complexity gates")
		.setDirection(Metric.DIRECTION_NONE)
		.setQualitative(false)
		.setDomain("Complexity of circuit gates")
		.create();
	
	public static final Metric<Integer> HCG =
		new Metric.Builder("HCG", "High complexity gates", Metric.ValueType.INT)
		.setDescription("High complexity gates")
		.setDirection(Metric.DIRECTION_NONE)
		.setQualitative(false)
		.setDomain("Complexity of circuit gates")
		.create();

	public static final Metric<Integer> IFINST =
		new Metric.Builder("IFInstructions", "Conditional instructions", Metric.ValueType.INT)
		.setDescription("Conditional instructions")
		.setDirection(Metric.DIRECTION_NONE)
		.setQualitative(false)
		.setDomain("Conditional circuit instructions")
		.create();

	public static final Metric<Integer> QCC =
		new Metric.Builder("QCC", "Quantum cyclomatic complexity", Metric.ValueType.INT)
		.setDescription("Quantum cyclomatic complexity")
		.setDirection(Metric.DIRECTION_NONE)
		.setQualitative(false)
		.setDomain("Quantum cyclomatic circuit complexity")
		.create();

	public static final Metric<Integer> MEASURE =
		new Metric.Builder("Measures", "Measure operations", Metric.ValueType.INT)
		.setDescription("Measure operations")
		.setDirection(Metric.DIRECTION_NONE)
		.setQualitative(false)
		.setDomain("Circuit measure operations")
		.create();

	public static final Metric<Integer> INITRESET =
		new Metric.Builder("InitReset", "Initialize and Reset operations", Metric.ValueType.INT)
		.setDescription("Initialize and Reset operations")
		.setDirection(Metric.DIRECTION_NONE)
		.setQualitative(false)
		.setDomain("Initialize and Reset operations")
		.create();

	public static final Metric<Integer> AUXQUBITS =
		new Metric.Builder("AuxQubits", "Auxiliary qubits", Metric.ValueType.INT)
		.setDescription("Auxiliary qubits")
		.setDirection(Metric.DIRECTION_NONE)
		.setQualitative(false)
		.setDomain("Auxiliary qubits")
		.create();

	@Override
	public List<Metric> getMetrics() {
		return asList(WIDTH, DEPTH, LCG, MCG, HCG, IFINST, QCC, MEASURE, INITRESET, AUXQUBITS);
	}
}
