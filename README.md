Lenguajes Formales - Entrega 1

Student Info

-   Name: Samuel Ramirez Noreña
-   Class number: C2566-SI2002-5464
-   OS: macOS Sequoia 15.3.2
-   Programming Language: Java 23 (Oracle OpenJDK 23.0.2, aarch64)
-   Tools used: IntelliJ IDEA, javac, java

------------------------------------------------------------------------

Description

This project is an implementation of the DFA minimization algorithm

The program finds states that are equivalent in a given DFA and prints all pairs of equivalent states.

------------------------------------------------------------------------

Algorithm (summary)

The algorithm follows the table-filling method:

1.   Mark all pairs (p, q) where one state is final and the other is not.
2. Checks each unmarked pair (p, q).
3. If there exists a symbol a such that the transitions (δ(p,a), δ(q,a)) are distinguishable, then (p, q) is also marked.
    -   Repeat until no new pairs are marked.
4. After all the pairs have been analized, the remaining unmarked pairs (p, q) are equivalent states.

------------------------------------------------------------------------

Input Format

-   First line: number of cases c.
-   For each case:
    1.  Number of states n.
    2.  The alphabet symbols (separated by spaces).
    3.  The final states (separated by spaces).
    4.  Transition table with n rows, one row per state.

- (Look the example cases bellow for better understanding) 
------------------------------------------------------------------------

Output Format

-   For each case, the program prints out a single line with the equivalent states separated by spaces, like this
  "(1,2) (1,3)"
-   If there is no equivalent states, the program prints out a blank line

------------------------------------------------------------------------

Test Cases

Case 1 – With equivalent states

Input

    1
    4
    a b
    3
    1 2
    3 3
    3 3
    3 3

Output

    (1,2)

------------------------------------------------------------------------

Case 2 – No equivalent states

Input

    1
    3
    a b
    2
    0 1
    2 2
    2 2

Output

(blank line)

------------------------------------------------------------------------

Case 3 – Multiple equivalent pairs

Input

    1
    5
    a b
    4
    1 2
    4 4
    4 4
    4 4
    4 4

Output

    (1,2) (1,3) (2,3)

------------------------------------------------------------------------
