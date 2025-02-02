public class Program {
    Instruction[] instructions;
    int currentLine;
    int[] variables;
    int maxX;
    int maxZ;
    int yIndex;

    Program(Instruction[] instructions) {
        this.instructions = instructions;
        this.currentLine = 1;
        findMaxX();
        findMaxZ();
        this.yIndex = this.maxX + this.maxZ;
        variables = new int[this.maxX + this.maxZ + 1];
    }

    void execute(String[] inputs) {
        updateVariables(inputs);
        while (currentLine <= instructions.length) {
            printSnapshot();
            executeInstruction(instructions[currentLine - 1]);
        }
        printSnapshot();
        System.out.println("Execution Finished");
    }

    void executeInstruction(Instruction instruction) {
        if (instruction.command == 0) {
          this.currentLine++;
        } else if (instruction.command == 1) {
            handleAddition(instruction);
        } else if (instruction.command == 2) {
            handleSubtraction(instruction);
        } else {
            handleGoto(instruction);
        }
    }

    void handleAddition(Instruction instruction) {
        int index = findVariableIndex(instruction.variable);
        this.variables[index]++;
        this.currentLine++;
    }

    void handleSubtraction(Instruction instruction) {
        int index = findVariableIndex(instruction.variable);
        if (this.variables[index] > 0) {
            this.variables[index]--;
        }
        this.currentLine++;
    }

    void handleGoto(Instruction instruction) {
        int index = findVariableIndex(instruction.variable);
        if (this.variables[index] != 0) {
            this.currentLine = findLabelLine(instruction.gotoLabel);
        } else {
            this.currentLine++;
        }
    }

    int findLabelLine(String label) {
        for (int i = 0; i < instructions.length; i++) {
            if (label.equals(instructions[i].label)) {
                return i + 1;
            }
        }
        return instructions.length + 1;
    }

    void updateVariables(String[] inputs) {
        for (int i = 0 ; i < inputs.length; i++) {
            if (i < this.maxX) {
                this.variables[i] = Integer.parseInt(inputs[i]);
            }
        }
    }

    int findVariableIndex(String variable) {
        if (variable.contains("X")) {
            int index = Integer.parseInt(variable.substring(1));
            return index - 1;
        } else if (variable.contains("Z")) {
            int index = Integer.parseInt(variable.substring(1));
            return this.maxX + index - 1;
        } else {
            return yIndex;
        }
    }

    void findMaxX () {
        int max = 0;
        for (Instruction instruction: this.instructions) {
            if (instruction.variable.contains("X")) {
                int index = Integer.parseInt(instruction.variable.substring(1));
                if (index > max) {
                    max = index;
                }
            }
        }
        this.maxX = max;
    }

    void findMaxZ () {
        int max = 0;
        for (Instruction instruction: this.instructions) {
            if (instruction.variable.contains("Z")) {
                int index = Integer.parseInt(instruction.variable.substring(1));
                if (index > max) {
                    max = index;
                }
            }
        }
        maxZ = max;
    }

    void printSnapshot() {
        System.out.print(currentLine + " ");
        for (int variable: variables) {
            System.out.print(variable + " ");
        }
        System.out.println();
    }
}
