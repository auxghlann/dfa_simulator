package moore_machine;

import dfa.DFA;

import java.util.Scanner;

public class Moore extends DFA {

    private final String[] output_alpha;
    private String[] state_output;


    public Moore(int states, String[] input_alpha, String[] output_alpha) {
        super(states, input_alpha);
        this.output_alpha = output_alpha;
    }


    @Override
    public void fill_matrix() {

        //Matrices/List
        super.matrix = new int[super.states][super.input_alpha.length];

        this.state_output = new String[super.states];

        Scanner in = new Scanner(System.in);

        System.out.println("Row: "+ super.matrix.length);
        System.out.println("Col: " + super.matrix[0].length);


        for (int i = 0; i < super.states; i++) {
            System.out.print("Output q" + i + ": ");
            this.state_output[i] = in.next();
        }
        for (int i = 0; i < super.matrix.length; i++) {

            for (int j = 0; j < super.matrix[1].length; j++) {
                System.out.print("Transition (q" +  i + ","+ super.input_alpha[j] + "): ");
                super.matrix[i][j] = in.nextInt();

            }

        }
        // >> Debug
//        System.out.println(this.matrix.length);
//        System.out.println(this.matrix[0].length);
//
//
//        for (int i = 0; i < this.output_matrix.length; i++) {
//            for (int j = 0; j < this.output_matrix[0].length; j++) {
//                System.out.print(output_matrix[i][j] + " ");
//            }
//            System.out.println();
//        }

        // <<
    }

    public String get_output(String input) {

        int row_pointer = 0;
        StringBuilder output_builder = new StringBuilder();
        int[] converted_input = super.convert_input_to_numeric(input);

        // Initial output at initial state

        output_builder.append(output_alpha[0]);

        for (int i = 0; i < converted_input.length; i++) {
            row_pointer = super.matrix[row_pointer][converted_input[i]];
            output_builder.append(this.state_output[row_pointer]);
        }

        return output_builder.toString();
    }

    @Override
    public void display_matrix() {
        if (matrix == null) {
            throw new NullPointerException("Moore Machine matrix should not be empty!");
        }

        System.out.print("states ");
        for (int i = 0; i < this.input_alpha.length; i++) {
            String a = this.input_alpha[i];
            System.out.print(" " + a + "   ");
        }
        System.out.print(" output ");
        System.out.println();


        int counter = 0;
        for (int i = 0; i < this.matrix.length; i++) {
            int[] transitions = this.matrix[i];

            // Check for initial states for displaying
            if (super.check_if_initial_state(String.valueOf(counter))) {
                System.out.print(" -q" + counter + "  |");
            }  else {
                System.out.print("  q" + counter + "  |");
            }

            for (int j = 0; j < this.matrix[0].length; j++) { // Use matrix[0].length for consistency
                System.out.print(" q" + transitions[j] + " |");
            }

            System.out.print("   " + state_output[i] + "   |");
            System.out.println();
            counter++;
        }
    }


}
