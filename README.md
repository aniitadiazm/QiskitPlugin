# SonarQube Custom Plugin Qiskit

This repository contains a custom SonarQube plugin designed to analyze Qiskit-based projects.  
It includes a set of Java-based metrics, plugin settings, frontend resources, and localization files required to integrate Qiskit-related measurements into SonarQube 9.x.

---

## 🧩 Component Overview

### 🔹 Backend (Java)

Located under:

`src/main/java/org/sonarsource/plugins/qiskit/`

#### Plugin registration
- **QiskitPlugin.java** – Registers the plugin inside SonarQube.

#### Metric implementations (`measures/`)
The following classes compute Qiskit-related measurements:

- AuxiliaryQubits.java
- ConditionalInstructions.java  
- Depth.java  
- HighComplexityGates.java  
- InitResetOperations.java  
- LowComplexityGates.java  
- MeasureOperations.java  
- MediumComplexityGates.java
- QuantumCyclomaticComplexity.java
- Width.java  

### 🔹 Frontend (JavaScript)

Located under:

`src/main/js/`

Contains JavaScript resources used for extending or displaying UI components inside SonarQube.

---

## 🛠️ Building the Plugin

Compile the plugin using Maven:

`mvn clean package`

---

## 🚀 Installing in SonarQube

Once the JAR is generated, install the plugin in your SonarQube instance:

1. Build the plugin:

`mvn clean package`

2. Copy the generated file into the SonarQube plugins directory:

`$SONARQUBE_HOME/extensions/plugins/`

3. Restart SonarQube so the plugin is loaded.

After restarting, the plugin will appear inside the SonarQube environment.

---

## 📖 License

This project is licensed under the European Union Public Licence, version 1.2 (EUPL-1.2).
See the LICENSE file for details.
