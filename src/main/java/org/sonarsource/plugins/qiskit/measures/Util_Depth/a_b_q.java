package main.java.org.sonarsource.plugins.qiskit.measures.Util_Depth;

import main.java.org.sonarsource.plugins.qiskit.measures.Util_Depth.buscar_qubits;


public class a_b_q {

    public static int[] calculate(int[] depths, String linea, String[] lines) {

        String ope, part0, part1;
        String[] parts, parts2;
        int qubit=0;

        parts = linea.split("\\(");
        part0 = parts[1];
        parts = part0.split("\\,");
        if(linea.contains("range")) {
            parts2 = linea.split("\\(");
            parts2 = parts2[2].split("\\)");
            ope = parts2[0];
            if(ope.matches("[+-]?\\d*(\\.\\d+)?")){
                qubit = Integer.valueOf(ope);
            } else {
                ope = buscar_qubits.qubits(lines, ope);
                qubit = Integer.valueOf(ope);
            }
            for(int i=0; i<qubit; i++) {
                depths[i] = depths[i] + 1;
            }
        } else if(parts.length == 3){
            if(parts[2].contains("[")){
                parts2 = parts[2].split("\\[");
                part1 = parts2[1];
                parts2 = part1.split("\\]");
                ope = parts2[0];
            } else {
                parts2 = parts[2].split("\\)");
                ope = parts2[0];
            }
            if(ope.matches("[+-]?\\d*(\\.\\d+)?")){
                qubit = Integer.valueOf(ope);
                depths[qubit] = depths[qubit] + 1;
            } else {
                ope = buscar_qubits.qubits(lines, ope);
                qubit = Integer.valueOf(ope);
                for(int i=0; i<qubit; i++){
                    depths[i] = depths[i] + 1;
                }
            }
        } else {
            for(int i=2; i<parts.length; i++) {
                if(parts[i].contains("[")){
                    parts2 = parts[i].split("\\[");
                    if(parts2.length == 2){
                        part1 = parts2[1];
                    } else {
                        part1 = parts2[2];
                    }
                    if(part1.contains("]")){
                        parts2 = part1.split("\\]");
                        ope = parts2[0];
                    } else {
                    ope = part1;
                }
                } else if(parts[i].contains("]") && !parts[i].contains("[")) {
                    parts2 = parts[i].split("\\]");
                    ope = parts2[0];
                } else {
                    ope = parts[i];
                }
                qubit = Integer.valueOf(ope);
                depths[qubit] = depths[qubit] + 1;
            }
        }
        return depths;
    }
}