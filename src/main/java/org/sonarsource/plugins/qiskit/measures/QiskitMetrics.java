package org.sonarsource.plugins.qiskit.measures;

import static java.util.Arrays.asList;
import java.util.List;

import org.sonar.api.measures.Metric;
import org.sonar.api.measures.Metrics;

public class QiskitMetrics implements Metrics {
  
    public static final Metric<Integer> WIDTH =
		new Metric.Builder("circuit_width", "Total qubits", Metric.ValueType.INT)
	    .setDescription("Total qubits")
	    .setDirection(Metric.DIRECTION_NONE)
	    .setQualitative(false)
	    .setDomain("Circuit width")
	    .create();

	public static final Metric<Integer> DEPTH =
		new Metric.Builder("circuit_depth", "Maximum gates in qubits", Metric.ValueType.INT)
		.setDescription("Maximum gates in qubits")
		.setDirection(Metric.DIRECTION_NONE)
		.setQualitative(false)
		.setDomain("Circuit depth")
		.create();
	
	public static final Metric<Integer> LCG =
		new Metric.Builder("low_complexity_gates", "Low complexity gates", Metric.ValueType.INT)
		.setDescription("Low complexity gates")
		.setDirection(Metric.DIRECTION_NONE)
		.setQualitative(false)
		.setDomain("Complexity of circuit gates")
		.create();
	
	public static final Metric<Integer> MCG =
		new Metric.Builder("medium_complexity_gates", "Medium complexity gates", Metric.ValueType.INT)
		.setDescription("Medium complexity gates")
		.setDirection(Metric.DIRECTION_NONE)
		.setQualitative(false)
		.setDomain("Complexity of circuit gates")
		.create();
	
	public static final Metric<Integer> HCG =
		new Metric.Builder("high_complexity_gates", "High complexity gates", Metric.ValueType.INT)
		.setDescription("High complexity gates")
		.setDirection(Metric.DIRECTION_NONE)
		.setQualitative(false)
		.setDomain("Complexity of circuit gates")
		.create();

	public static final Metric<Integer> IFINST =
		new Metric.Builder("conditional_instructions", "Conditional instructions", Metric.ValueType.INT)
		.setDescription("Conditional instructions")
		.setDirection(Metric.DIRECTION_NONE)
		.setQualitative(false)
		.setDomain("Conditional instructions")
		.create();

	public static final Metric<Integer> QCC =
		new Metric.Builder("quantum_cyclomatic_complexity", "Quantum cyclomatic complexity", Metric.ValueType.INT)
		.setDescription("Quantum cyclomatic complexity")
		.setDirection(Metric.DIRECTION_NONE)
		.setQualitative(false)
		.setDomain("Quantum cyclomatic complexity")
		.create();

	public static final Metric<Integer> MEASURE =
		new Metric.Builder("measure_operations", "Measure operations", Metric.ValueType.INT)
		.setDescription("Measure operations")
		.setDirection(Metric.DIRECTION_NONE)
		.setQualitative(false)
		.setDomain("Measure operations")
		.create();

	public static final Metric<Integer> INITRESET =
		new Metric.Builder("initialize_and_reset", "Initialize and Reset operations", Metric.ValueType.INT)
		.setDescription("Initialize and Reset operations")
		.setDirection(Metric.DIRECTION_NONE)
		.setQualitative(false)
		.setDomain("Initialize and Reset operations")
		.create();

	public static final Metric<Integer> AUXQUBITS =
		new Metric.Builder("auxiliary_qubits", "Auxiliary qubits", Metric.ValueType.INT)
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
