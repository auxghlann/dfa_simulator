package dfa;
import java.util.Scanner;

public class DFA {

    protected final int states;
    protected final String[] input_alpha;
    protected String[] init_states;
    protected String[] fin_states;
    protected int[][] matrix;
//    protected String input;

    public DFA(int states, String[] input_alpha, String[] init_states, String[] fin_states) {
        this.states = states;
        this.input_alpha = input_alpha;
        this.init_states = init_states;
        this.fin_states = fin_states;
    }

    public DFA(int states, String[] input_alpha) {
        this.states = states;
        this.input_alpha = input_alpha;
        this.init_states = new String[]{"0"};
    }


    protected boolean check_if_initial_state(String state) {

        for (String a : this.init_states) {
            if (state.equals(a)) return true;
        }
        return false;
    }

    protected boolean check_if_final_state(String state) {

        for (String a : this.fin_states) {
            if (state.equals(a)) return true;
        }
        return false;
    }

    public boolean validate_string(String input) {

        // zero at default because we always start at the init state
        int row_pointer = 0;

        // Convert input to numerical
        int[] converted_in = convert_input_to_numeric(input);

        for (int i = 0; i < converted_in.length; i++) {
            row_pointer = this.matrix[row_pointer][converted_in[i]];
        }

        return check_if_final_state(String.valueOf(row_pointer));
    }

    // Helper function for validate_string

    protected int[] convert_input_to_numeric(String input) {

        String converted_alpha = String.join("", this.input_alpha);
        int[] converted_input = new int[input.length()];
        for (int i = 0; i < input.length(); i++) {

            converted_input[i] = converted_alpha.indexOf(input.charAt(i));
        }

        return converted_input;
    }

    public void fill_matrix() {

        this.matrix = new int[states][input_alpha.length];
        Scanner in = new Scanner(System.in);
        System.out.printf("Row: %d \nCol: %d\n", this.matrix.length, this.matrix[0].length);

        for (int i = 0; i < this.matrix.length; i++) {
            for (int j = 0; j < this.matrix[1].length; j++) {
                System.out.printf("Transition (" +  i + "," + this.input_alpha[j] +"): ");
                this.matrix[i][j] = in.nextInt();
            }
        }
    }

    public void display_matrix() {

        if (matrix == null) {
            throw new NullPointerException("Matrix should not be empty!");
        }

        System.out.print("states ");
        for (int i = 0; i < this.input_alpha.length; i++) {
            String a = this.input_alpha[i];
            System.out.print(" " + a + "  ");
        }
        System.out.println();


        int counter = 0;
        for (int i = 0; i < this.matrix.length; i++) {
            int[] integers = this.matrix[i];

            // Check for initial and final states for displaying
            if (check_if_initial_state(String.valueOf(counter))) {
                System.out.print(" -q" + counter + "  |");
            } else if (check_if_final_state(String.valueOf(counter))) {
                System.out.print(" +q" + counter + "  |");
            } else {
                System.out.print("  q" + counter + "  |");
            }

            for (int j = 0; j < this.matrix[0].length; j++) { // Use matrix[0].length for consistency
                System.out.print(" " + integers[j] + " |");
            }
            System.out.println();
            counter++;
        }

    }

}
