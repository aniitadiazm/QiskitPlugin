/*
 * Example Plugin for SonarQube
 * Copyright (C) 2009-2020 SonarSource SA
 * mailto:contact AT sonarsource DOT com
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package org.sonarsource.plugins.qiskit.measures;

import static java.util.Arrays.asList;
import java.util.List;

import org.sonar.api.measures.Metric;
import org.sonar.api.measures.Metrics;

public class QiskitMetrics implements Metrics {
  
    public static final Metric<Integer> WIDTH =
		new Metric.Builder("WIDTH", "Total qubits", Metric.ValueType.INT)
	    .setDescription("Total number of qubits")
	    .setDirection(Metric.DIRECTION_NONE)
	    .setQualitative(false)
	    .setDomain("Qiskit - Code Size")
	    .create();

	public static final Metric<Integer> DEPTH =
		new Metric.Builder("DEPTH", "Max doors on a qubit", Metric.ValueType.INT)
		.setDescription("Maximum number of gates on a qubit")
		.setDirection(Metric.DIRECTION_NONE)
		.setQualitative(false)
		.setDomain("Qiskit - Code Size")
		.create();
	
	public static final Metric<Integer> NºNOT =
		new Metric.Builder("NºNOT", "X gates", Metric.ValueType.INT)
		.setDescription("Number of X gates")
		.setDirection(Metric.DIRECTION_NONE)
		.setQualitative(false)
		.setDomain("Qiskit - 1st Level Gates")
		.create();
	
	public static final Metric<Integer> NºY =
		new Metric.Builder("NºY", "Y gates", Metric.ValueType.INT)
		.setDescription("Number of Y gates")
		.setDirection(Metric.DIRECTION_NONE)
		.setQualitative(false)
		.setDomain("Qiskit - 1st Level Gates")
		.create();

	public static final Metric<Integer> NºZ =
		new Metric.Builder("NºZ", "Z gates", Metric.ValueType.INT)
		.setDescription("Number of Z gates")
		.setDirection(Metric.DIRECTION_NONE)
		.setQualitative(false)
		.setDomain("Qiskit - 1st Level Gates")
		.create();
	
	public static final Metric<Integer> NºI =
		new Metric.Builder("NºI", "Identity gates", Metric.ValueType.INT)
		.setDescription("Number of Identity gates")
		.setDirection(Metric.DIRECTION_NONE)
		.setQualitative(false)
		.setDomain("Qiskit - 1st Level Gates")
		.create();

	public static final Metric<Integer> NºU =
		new Metric.Builder("NºU", "U gates", Metric.ValueType.INT)
		.setDescription("Numer of U gates")
		.setDirection(Metric.DIRECTION_NONE)
		.setQualitative(false)
		.setDomain("Qiskit - 1st Level Gates")
		.create();
	
	public static final Metric<Integer> NºP =
		new Metric.Builder("NºP", "P gates", Metric.ValueType.INT)
		.setDescription("Number of P gates")
		.setDirection(Metric.DIRECTION_NONE)
		.setQualitative(false)
		.setDomain("Qiskit - 1st Level Gates")
		.create();

	public static final Metric<Integer> NºH =
		new Metric.Builder("NºH", "Hadamard gates", Metric.ValueType.INT)
		.setDescription("Number of Hadamard gates")
		.setDirection(Metric.DIRECTION_NONE)
		.setQualitative(false)
		.setDomain("Qiskit - 1st Level Gates")
		.create();
	
	public static final Metric<Integer> NºS =
		new Metric.Builder("NºS", "S gates", Metric.ValueType.INT)
		.setDescription("Number of S gates")
		.setDirection(Metric.DIRECTION_NONE)
		.setQualitative(false)
		.setDomain("Qiskit - 1st Level Gates")
		.create();

	public static final Metric<Integer> NºIS =
		new Metric.Builder("NºIS", "Inverse S gates", Metric.ValueType.INT)
		.setDescription("Number of inverse S gates")
		.setDirection(Metric.DIRECTION_NONE)
		.setQualitative(false)
		.setDomain("Qiskit - 1st Level Gates")
		.create();

	public static final Metric<Integer> NºT =
		new Metric.Builder("NºT", "T gates", Metric.ValueType.INT)
		.setDescription("Number of T gates")
		.setDirection(Metric.DIRECTION_NONE)
		.setQualitative(false)
		.setDomain("Qiskit - 1st Level Gates")
		.create();

	public static final Metric<Integer> NºIT =
		new Metric.Builder("NºIT", "Inverse T gates", Metric.ValueType.INT)
		.setDescription("Number of inverse T gates")
		.setDirection(Metric.DIRECTION_NONE)
		.setQualitative(false)
		.setDomain("Qiskit - 1st Level Gates")
		.create();
		
	public static final Metric<Integer> NºRX =
		new Metric.Builder("NºRX", "RX gates", Metric.ValueType.INT)
		.setDescription("Number of RX gates")
		.setDirection(Metric.DIRECTION_NONE)
		.setQualitative(false)
		.setDomain("Qiskit - 1st Level Gates")
		.create();

	public static final Metric<Integer> NºRY =
		new Metric.Builder("NºRY", "RY gates", Metric.ValueType.INT)
		.setDescription("Number of RY gates")
		.setDirection(Metric.DIRECTION_NONE)
		.setQualitative(false)
		.setDomain("Qiskit - 1st Level Gates")
		.create();

	public static final Metric<Integer> NºRZ =
		new Metric.Builder("NºRZ", "RZ gates", Metric.ValueType.INT)
		.setDescription("Number of RZ gates")
		.setDirection(Metric.DIRECTION_NONE)
		.setQualitative(false)
		.setDomain("Qiskit - 1st Level Gates")
		.create();

	public static final Metric<Integer> NºCNOT =
		new Metric.Builder("NºCNOT", "CX gates", Metric.ValueType.INT)
		.setDescription("Number of CX gates")
		.setDirection(Metric.DIRECTION_NONE)
		.setQualitative(false)
		.setDomain("Qiskit - 1st Level Gates")
		.create();

	public static final Metric<Integer> NºCY =
		new Metric.Builder("NºCY", "CY gates", Metric.ValueType.INT)
		.setDescription("Number of CY gates")
		.setDirection(Metric.DIRECTION_NONE)
		.setQualitative(false)
		.setDomain("Qiskit - 1st Level Gates")
		.create();

	public static final Metric<Integer> NºCZ =
		new Metric.Builder("NºCZ", "CZ gates", Metric.ValueType.INT)
		.setDescription("Number of CZ gates")
		.setDirection(Metric.DIRECTION_NONE)
		.setQualitative(false)
		.setDomain("Qiskit - 1st Level Gates")
		.create();
	
	public static final Metric<Integer> NºCU =
		new Metric.Builder("NºCU", "CU gates", Metric.ValueType.INT)
		.setDescription("Number of CU gates")
		.setDirection(Metric.DIRECTION_NONE)
		.setQualitative(false)
		.setDomain("Qiskit - 1st Level Gates")
		.create();
	public static final Metric<Integer> NºCH =
		new Metric.Builder("NºCH", "CH gates", Metric.ValueType.INT)
		.setDescription("Number of CH gates")
		.setDirection(Metric.DIRECTION_NONE)
		.setQualitative(false)
		.setDomain("Qiskit - 1st Level Gates")
		.create();

	public static final Metric<Integer> NºCRZ =
		new Metric.Builder("NºCRZ", "CRZ gates", Metric.ValueType.INT)
		.setDescription("Number of CRZ gates")
		.setDirection(Metric.DIRECTION_NONE)
		.setQualitative(false)
		.setDomain("Qiskit - 1st Level Gates")
		.create();

	public static final Metric<Integer> NºCP =
		new Metric.Builder("NºCP", "CP gates", Metric.ValueType.INT)
		.setDescription("Number of CP gates")
		.setDirection(Metric.DIRECTION_NONE)
		.setQualitative(false)
		.setDomain("Qiskit - 1st Level Gates")
		.create();

	public static final Metric<Integer> NºCU3 =
		new Metric.Builder("NºCU3", "CU3 gates", Metric.ValueType.INT)
		.setDescription("Number of CU3 gates")
		.setDirection(Metric.DIRECTION_NONE)
		.setQualitative(false)
		.setDomain("Qiskit - 1st Level Gates")
		.create();

	public static final Metric<Integer> NºSWAP =
		new Metric.Builder("NºSWAP", "SWAP gates", Metric.ValueType.INT)
		.setDescription("Number of SWAP gates")
		.setDirection(Metric.DIRECTION_NONE)
		.setQualitative(false)
		.setDomain("Qiskit - 1st Level Gates")
		.create();

	public static final Metric<Integer> NºToff =
		new Metric.Builder("NºToff", "Toffoli gates", Metric.ValueType.INT)
		.setDescription("Number of CCX gates")
		.setDirection(Metric.DIRECTION_NONE)
		.setQualitative(false)
		.setDomain("Qiskit - 1st Level Gates")
		.create();

	public static final Metric<Integer> NºFred =
		new Metric.Builder("NºFred", "Fredkin gates", Metric.ValueType.INT)
		.setDescription("Number of CSWAP gates")
		.setDirection(Metric.DIRECTION_NONE)
		.setQualitative(false)
		.setDomain("Qiskit - 1st Level Gates")
		.create();

	public static final Metric<Integer> NºTG =
		new Metric.Builder("NºTG", "Total gates", Metric.ValueType.INT)
		.setDescription("Total number of gates")
		.setDirection(Metric.DIRECTION_NONE)
		.setQualitative(false)
		.setDomain("Qiskit - 2nd Level Gates")
		.create();
	
	public static final Metric<Integer> NºPG =
		new Metric.Builder("NºPG", "Pauli gates", Metric.ValueType.INT)
		.setDescription("Number of Pauli gates")
		.setDirection(Metric.DIRECTION_NONE)
		.setQualitative(false)
		.setDomain("Qiskit - 2nd Level Gates")
		.create();
	
	public static final Metric<Integer> NºCliffG =
		new Metric.Builder("NºCliffG", "Clifford gates", Metric.ValueType.INT)
		.setDescription("Number of Clifford gates")
		.setDirection(Metric.DIRECTION_NONE)
		.setQualitative(false)
		.setDomain("Qiskit - 2nd Level Gates")
		.create();
	
	public static final Metric<Integer> NºC3G =
		new Metric.Builder("NºC3G", "C3 gates", Metric.ValueType.INT)
		.setDescription("Number of C3 gates")
		.setDirection(Metric.DIRECTION_NONE)
		.setQualitative(false)
		.setDomain("Qiskit - 2nd Level Gates")
		.create();

	public static final Metric<Integer> NºSRG =
		new Metric.Builder("NºSRG", "Standard rotations", Metric.ValueType.INT)
		.setDescription("Number of standard rotation gates")
		.setDirection(Metric.DIRECTION_NONE)
		.setQualitative(false)
		.setDomain("Qiskit - 2nd Level Gates")
		.create();
	
	public static final Metric<Integer> NºSCG =
		new Metric.Builder("NºSCG", "Single-qubit gates", Metric.ValueType.INT)
		.setDescription("Number of single-qubit gates")
		.setDirection(Metric.DIRECTION_NONE)
		.setQualitative(false)
		.setDomain("Qiskit - 2nd Level Gates")
		.create();
	
	public static final Metric<Integer> NºCPG =
		new Metric.Builder("NºCPG", "Controlled Pauli gates", Metric.ValueType.INT)
		.setDescription("Number of controlled Pauli gates")
		.setDirection(Metric.DIRECTION_NONE)
		.setQualitative(false)
		.setDomain("Qiskit - 2nd Level Gates")
		.create();
	
	public static final Metric<Integer> Nº2CG =
		new Metric.Builder("Nº2CG", "Two-qubit gates", Metric.ValueType.INT)
		.setDescription("Number of two-qubit gates")
		.setDirection(Metric.DIRECTION_NONE)
		.setQualitative(false)
		.setDomain("Qiskit - 2nd Level Gates")
		.create();
	
	public static final Metric<Integer> Nº3CG =
		new Metric.Builder("Nº3CG", "Three-qubit gates", Metric.ValueType.INT)
		.setDescription("Number of three-qubit gates")
		.setDirection(Metric.DIRECTION_NONE)
		.setQualitative(false)
		.setDomain("Qiskit - 2nd Level Gates")
		.create();
	
	public static final Metric<Integer> NºMCG =
		new Metric.Builder("NºMCG", "Multi-qubit gates", Metric.ValueType.INT)
		.setDescription("Number of multi-qubit gates")
		.setDirection(Metric.DIRECTION_NONE)
		.setQualitative(false)
		.setDomain("Qiskit - 2nd Level Gates")
		.create();
	
	public static final Metric<Integer> PorcSCG =
		new Metric.Builder("PorcSCG", "% Single-qubit gates", Metric.ValueType.PERCENT)
		.setDescription("Percentage of single-qubit gates")
		.setDirection(Metric.DIRECTION_NONE)
		.setQualitative(true)
		.setDomain("Qiskit - 2nd Level Gates")
		.create();
	
	public static final Metric<Integer> PorcMCG =
		new Metric.Builder("PorcMCG", "% Multi-qubit gates", Metric.ValueType.PERCENT)
		.setDescription("Percentage of multi-qubit gates")
		.setDirection(Metric.DIRECTION_NONE)
		.setQualitative(true)
		.setDomain("Qiskit - 2nd Level Gates")
		.create();
	
	public static final Metric<Integer> NºCM =
		new Metric.Builder("NºCM", "Qubits with Measure", Metric.ValueType.INT)
		.setDescription("Number of qubits with some operation Measure")
		.setDirection(Metric.DIRECTION_BETTER)
		.setQualitative(false)
		.setDomain("Qiskit - Measurements")
		.create();
	
	public static final Metric<Integer> PorcCM =
		new Metric.Builder("PorcCM", "% Qubits with Measure", Metric.ValueType.PERCENT)
		.setDescription("Percentage of qubits with Measure")
		.setDirection(Metric.DIRECTION_NONE)
		.setQualitative(true)
		.setDomain("Qiskit - Measurements")
		.create();
	
	public static final Metric<Integer> NºCReset =
		new Metric.Builder("NºCReset", "Qubits with Reset", Metric.ValueType.INT)
		.setDescription("Number of qubits with some operation Reset")
		.setDirection(Metric.DIRECTION_NONE)
		.setQualitative(false)
		.setDomain("Qiskit - Reset & Initialize")
		.create();
	
	public static final Metric<Integer> NºCInit =
		new Metric.Builder("NºCInit", "Nº de cúbits con alguna operación Initialize", Metric.ValueType.INT)
		.setDescription("Number of qubits with some operation Initialize")
		.setDirection(Metric.DIRECTION_NONE)
		.setQualitative(false)
		.setDomain("Qiskit - Reset & Initialize")
		.create();
	
	public static final Metric<Integer> PorcCReset =
		new Metric.Builder("PorcCReset", "% Qubits with Reset", Metric.ValueType.PERCENT)
		.setDescription("Percentage of qubits with Reset")
		.setDirection(Metric.DIRECTION_NONE)
		.setQualitative(true)
		.setDomain("Qiskit - Reset & Initialize")
		.create();

	public static final Metric<Integer> PorcCInit =
		new Metric.Builder("PorcCInit", "% Qubits with Initialize", Metric.ValueType.PERCENT)
		.setDescription("Percentage of qubits with Initialize")
		.setDirection(Metric.DIRECTION_NONE)
		.setQualitative(true)
		.setDomain("Qiskit - Reset & Initialize")
		.create();
	
	public static final Metric<Integer> NºOif =
		new Metric.Builder("NºOif", "If operations", Metric.ValueType.INT)
		.setDescription("Number of If operations")
		.setDirection(Metric.DIRECTION_NONE)
		.setQualitative(false)
		.setDomain("Qiskit - Conditionals operations")
		.create();
	
	public static final Metric<Integer> NºOC =
		new Metric.Builder("NºOC", "Controlled operations", Metric.ValueType.INT)
		.setDescription("Number of controlled operations")
		.setDirection(Metric.DIRECTION_NONE)
		.setQualitative(false)
		.setDomain("Qiskit - Conditionals operations")
		.create();
	
	public static final Metric<Integer> NºSif =
		new Metric.Builder("NºSif", "Conditional if statements", Metric.ValueType.INT)
		.setDescription("Number of conditional if statements")
		.setDirection(Metric.DIRECTION_NONE)
		.setQualitative(false)
		.setDomain("Qiskit - Conditionals operations")
		.create();
	
	public static final Metric<Integer> PorcOC =
		new Metric.Builder("PorcOC", "% Conditional operations", Metric.ValueType.PERCENT)
		.setDescription("Percentage of conditional operations")
		.setDirection(Metric.DIRECTION_NONE)
		.setQualitative(true)
		.setDomain("Qiskit - Conditionals operations")
		.create();

	@Override
	public List<Metric> getMetrics() {
		return asList(WIDTH, DEPTH, NºNOT, NºY, NºZ, NºI, NºU, NºP, NºH, NºS, NºIS, NºT, NºIT, NºRX, NºRY, NºRZ,
		NºCNOT, NºCY, NºCZ, NºCU, NºCH, NºCRZ, NºCP, NºCU3, NºSWAP, NºToff, NºFred, NºTG, NºPG, NºCliffG, NºC3G, NºSRG,
		NºSCG, NºCPG, Nº2CG, Nº3CG, NºMCG, NºCM, NºCReset, NºCInit, NºOif, NºOC, NºSif, PorcSCG, PorcMCG, PorcCM,
		PorcCReset, PorcCInit, PorcOC);
	}
}
