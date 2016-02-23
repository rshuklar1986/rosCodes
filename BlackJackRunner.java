package blackjack;

import java.util.Stack;

public class BlackJackRunner {
    static Stack<Integer> stack = new Stack<Integer>();
    
    public static void main(String args[]) {
        BlackJackImpl game = new BlackJackImpl(new GameAssessorImpl());
        
        generateNumbers();
        int pop = stack.pop();
        int pop2 = stack.pop();
        int pop3 = stack.pop();
        int pop4 = stack.pop();
        int pop5 = stack.pop();
        int pop6 = stack.pop();
        System.out.println(pop);
        System.out.println(pop2);
        System.out.println(pop3);
        System.out.println(pop4);
        System.out.println(pop5);
        System.out.println(pop6);

        game.deal(pop, pop2);
        game.deal(pop3, pop4);
        game.deal(pop5, pop6);

        String showDown = game.showDown();
        System.out.println(showDown);
    }

    private static void generateNumbers() {
            while(stack.size() < 13) {
            double random = Math.ceil(Math.random() * 100);
            if (random > 0 && random <= 13 && !stack.contains((int)random)) {
                    stack.push((int)random);
            }
            }
        }
}
