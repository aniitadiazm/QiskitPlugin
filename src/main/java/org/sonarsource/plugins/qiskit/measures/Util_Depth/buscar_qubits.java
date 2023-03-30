package main.java.org.sonarsource.plugins.qiskit.measures.Util_Depth;


public class buscar_qubits {

    public static String qubits(String[] lines, String qubits) {
        String part1;
        String[] parts;
        for (String line : lines) {
            if (line.contains(qubits) && line.contains("=")) {
                String build_qubits = line.replace(" ", "");
                if(build_qubits.contains("QuantumRegister")) {
                    parts = build_qubits.split("\\(");
                    part1 = parts[1];
                    if (part1.contains(",")) {
                        parts = part1.split("\\,");
                        qubits = parts[0];                        
                    } else {
                        parts = part1.split("\\)");
                        qubits = parts[0];
                    }
                } else {
                    parts = build_qubits.split("\\=");
                    qubits = parts[1];
                }
                break;
            }
        }
        return qubits;
    }

}