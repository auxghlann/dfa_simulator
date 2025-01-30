package dfa;

public class DFA {

    public DFA() {

    }

    public boolean check_if_initial_state(String state, String[] init_states) {

        for (String a : init_states) {
            if (state.equals(a)) return true;
        }
        return false;
    }

    public boolean check_if_final_state(String state, String[] fin_states) {

        for (String a : fin_states) {
            if (state.equals(a)) return true;
        }
        return false;
    }

    public boolean validate_string(String input, String[] alpha, int[][] matrix, String[] fin_states) {

        // zero at default because we always start at the init state
        int row_pointer = 0;

        // Convert input to numerical
        int[] converted_in = convert_input_to_numeric(input, alpha);

        for (int i : converted_in) {
            row_pointer = matrix[row_pointer][i];
        }

        return check_if_final_state(String.valueOf(row_pointer), fin_states);
    }

    // Helper function for validate_string

    public int[] convert_input_to_numeric(String input, String[] alpha) {

        String converted_alpha = String.join("", alpha);
        int[] converted_input = new int[input.length()];
        for (int i = 0; i < input.length(); i++) {

            converted_input[i] = converted_alpha.indexOf(input.charAt(i));
        }

        return converted_input;
    }

//    public static boolean next_row() {
//        return false;
//    }

    public void display_matrix(int[][] matrix, String[] alpha, String[] init_states, String[] fin_states) {
        System.out.print("states ");
        for (String a: alpha) {
            System.out.printf(" %s  ", a);
        }
        System.out.println();

        int counter = 0;
        for (int[] integers : matrix) {
            // Check for initial and final states for displaying
            if (check_if_initial_state(String.valueOf(counter), init_states)) {
                System.out.printf(" -q%d  |", counter);
            }
            else if (check_if_final_state(String.valueOf(counter), fin_states)) {
                System.out.printf(" +q%d  |", counter);
            }
            else {
                System.out.printf("  q%d  |", counter);
            }

            for (int j = 0; j < matrix[1].length; j++) {
                System.out.printf(" %s |", integers[j]);
            }
            System.out.println();
            counter++;
        }
    }

}
