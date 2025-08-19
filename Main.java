import java.util.*;

public class Main {

    static class DFA {
        int n;
        List<Character> alphabet;
        Set<Integer> finalStates;
        int[][] transitions;

        DFA(int n, List<Character> alphabet, Set<Integer> finalStates, int[][] transitions) {
            this.n = n;
            this.alphabet = alphabet;
            this.finalStates = finalStates;
            this.transitions = transitions;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int cases = Integer.parseInt(sc.nextLine().trim());
        for (int t = 0; t < cases; t++) {
            int n = Integer.parseInt(sc.nextLine().trim());

            String[] alphaTokens = sc.nextLine().trim().split(" ");
            List<Character> alphabet = new ArrayList<>();
            for (String token : alphaTokens) {
                alphabet.add(token.charAt(0));
            }

            String[] finalTokens = sc.nextLine().trim().split(" ");
            Set<Integer> finalStates = new HashSet<>();
            for (String token : finalTokens) {
                if (!token.isEmpty()) {
                    finalStates.add(Integer.parseInt(token));
                }
            }

            int[][] transitions = new int[n][alphabet.size()];
            for (int i = 0; i < n; i++) {
                String[] row = sc.nextLine().trim().split(" ");
                for (int j = 0; j < alphabet.size(); j++) {
                    transitions[i][j] = Integer.parseInt(row[j]);
                }
            }

            DFA dfa = new DFA(n, alphabet, finalStates, transitions);
            List<String> equivalentPairs = minimize(dfa);

            for (int i = 0; i < equivalentPairs.size(); i++) {
                if (i > 0) System.out.print(" ");
                System.out.print(equivalentPairs.get(i));
            }
            System.out.println();
        }

        sc.close();
    }

    private static List<String> minimize(DFA dfa) {
        int n = dfa.n;

        boolean[][] distinguishable = new boolean[n][n];

        for (int p = 0; p < n; p++) {
            for (int q = p + 1; q < n; q++) {
                boolean pFinal = dfa.finalStates.contains(p);
                boolean qFinal = dfa.finalStates.contains(q);
                if (pFinal != qFinal) {
                    distinguishable[p][q] = true;
                }
            }
        }

        boolean changed = true;
        while (changed) {
            changed = false;
            for (int p = 0; p < n; p++) {
                for (int q = p + 1; q < n; q++) {
                    if (!distinguishable[p][q]) {
                        for (int a = 0; a < dfa.alphabet.size(); a++) {
                            int pNext = dfa.transitions[p][a];
                            int qNext = dfa.transitions[q][a];
                            int x = Math.min(pNext, qNext);
                            int y = Math.max(pNext, qNext);
                            if (distinguishable[x][y]) {
                                distinguishable[p][q] = true;
                                changed = true;
                                break;
                            }
                        }
                    }
                }
            }
        }

        List<String> equivalentPairs = new ArrayList<>();
        for (int p = 0; p < n; p++) {
            for (int q = p + 1; q < n; q++) {
                if (!distinguishable[p][q]) {
                    equivalentPairs.add("(" + p + "," + q + ")");
                }
            }
        }

        Collections.sort(equivalentPairs);
        return equivalentPairs;
    }
}
