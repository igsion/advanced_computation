public class Instruction {
    int command;
    String variable;
    String label;
    String gotoLabel;

    Instruction(int command, String variable, String label) {
        this.command = command;
        this.variable = variable;
        this.label = label;

        if (command > 2) {
            this.gotoLabel = Decoder.findLabel(command - 2);
        }
    }
}