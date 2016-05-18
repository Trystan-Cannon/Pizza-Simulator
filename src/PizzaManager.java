import java.util.Scanner;

/** 
 * PizzaManager Skeleton File
 * CSS 162, Final Project
 * 
 * This class serves as the driver and main entry point for the
 * Pizza Simulator program. All user interaction and output occurs here.
 * 
 * The main program is simply an infinite loop that displays a series of
 * instructions stored in <code>String</code> constant called
 * <code>INSTRUCTIONS</code>.
 * 
 * All input is gathered using a <code>java.util.Scanner</code> object and
 * is parsed and distributed from the <code>start</code> method to the
 * appropriate method, if any.
 * 
 * This class supports adding random <code>Pizza</code> objects to a list;
 * sorting them by price, size, or calorie count; searching for a pizza
 * with a given calorie count; and eating an amount from a pizza at a given
 * index. See the respectively labeled method for details on how this
 * functionality is accomplished.
 * 
 * @author: Rob Nash with edits by Johnny Lin and Trystan Cannon
 */
public class PizzaManager {
    
    /**
     * The list in which all of the <code>Pizza</code> objects created
     * will be stored.
     */
    private final ArrayList<Pizza> pizzas = new ArrayList<>();
    
    /**
     * This method behaves as the main program loop with which the user
     * interacts.
     * 
     * Here, input is gathered using a <code>Scanner</code> object and
     * is parsed to determine which function should be called. All of the
     * possible options are displayed to the user through the
     * <code>displayInstructions</code> method, then the user presses the
     * appropriate key, and the main switch statement within this method
     * calls the appropriate function, if any.
     * 
     * The console interface is defined in the start method 
     * You can exit or extend the code below to accomplish all of 
     * the outcomes defined in the homework document
     */
    public void start() {
        char selection = 'q';
        
        Scanner foo = new Scanner(System.in);
        
        while(true) {
            displayAllPizzas();
            displayInstructions();
            
            selection = foo.nextLine().substring(0, 1).toCharArray()[0];
            
            switch(selection) {
                case 'A':    
                case 'a':
                    System.out.println("Adding a random pizza to th" +
                                       "e ArrayList<Pizza>.");
                    addRandomPizza();
                    break;
                    
                case 'H':    
                case 'h':
                    System.out.println("Adding one hundred random p" +
                                       "izzas to the ArrayList<Pizz" +
                                       "a>.");
                    
                    for(int index = 0; index < 100; index++) {
                        addRandomPizza();
                    }
                    
                    break;
                    
                case 'E':    
                case 'e':
                    eatSomePizza(foo);
                    break;
                    
                case 'P':    
                case 'p':
                    System.out.println("Sorting pizzas by (P)rice");
                    sortByPrice();
                    break;
                    
                case 'S':    
                case 's':
                    System.out.println("Sorting pizzas by (S)ize");
                    sortBySize();
                    break;
                    
                case 'C':    
                case 'c':
                    System.out.println("Sorting pizzas by (C)alor" +
                                       "ies");
                    sortByCalories();
                    break;
                    
                case 'B':
                case 'b':
                    System.out.println(
                        "(B)inary search over pizzas by calories(int" +
                        ").  Sorting first.  What calorie count are " +
                        "you looking for?"
                    );
                    
                    try {
                        int numCalories = foo.nextInt();
                        
                        if(numCalories <= 0) {
                        System.out.println(numCalories + " is an invalid " +
                                           "number of calories.");
                        } else {
                            int foundIndex =
                                    binarySearchByCalories(numCalories);

                            if(foundIndex == -1) {
                                System.out.println("Could not find a pizza " +
                                                   "with " + numCalories + 
                                                   " calories.");
                            } else {
                                System.out.println("Found a pizza with " +
                                                   numCalories + " calories at"
                                                   + " index = " + foundIndex);
                            }
                        }
                    } catch(Exception inputMismatch) {
                        System.out.println("Invalid number of calories.");
                    }
                    
                    // Catch the newline that comes from pressing enter.
                    foo.nextLine();
                    break;
                    
                case 'Q':
                case 'q':
                    System.out.println("(Q)uitting!" );
                    return;
                    
                default:
                    System.out.println("Unrecognized input - try again");
            }
        }

    }

    /**
     * Eats a specified fractional amount of pizza from the pizza specified
     * by a given index, removing the pizza from the list if its size
     * reaches zero.
     * 
     * If there are no pizzas available to eat, then this method simply
     * states that fact and returns.
     * 
     * However, given an invalid amount or invalid index, this method will
     * not throw an exception but rather inform the user of their error,
     * returning them to the main menu loop.
     * 
     * Given a <code>null</code> <code>Scanner</code> object, this method
     * will crash, as this is not expected.
     * 
     * @param keys The <code>Scanner</code> object with which the input
     *             for the amount and index will be garnered.
     */
    private void eatSomePizza(Scanner keys) {
        if(pizzas.size() == 0) {
            System.out.println("There are currently no pizzas to be eaten.");
            return;
        }
        
        System.out.println("Please enter the index of the pizza you would " +
                           "like to eat from (valid indexes: 0-" +
                           (pizzas.size() - 1) + "):");
        
        int pizzaIndex = keys.nextInt();
        keys.nextLine();
        
        if(pizzaIndex < 0 || pizzaIndex >= pizzas.size()) {
            System.out.println(pizzaIndex + " is not a valid index.");
            return;
        }
        
        Pizza pizza = pizzas.get(pizzaIndex);
        
        System.out.println("\nPlease enter the fractional amount of pizza " +
                           "you would like to eat from the remaining " +
                           pizza.getRemaining() + " (format a/b):");
        
        String line = keys.nextLine();
        
        if(line.indexOf('/') == -1) {
            System.out.println("\"" + line + "\" is not valid input.");
            return;
        }
        
        int separatorIndex = line.indexOf('/');
        int numerator      = 0;
        int denominator    = 0;
        Fraction amount    = null;
        
        try {
            numerator   = Integer.parseInt(line.substring(0, separatorIndex));
            denominator = Integer.parseInt(line.substring(separatorIndex + 1));
            
            amount = new Fraction(numerator, denominator);
        } catch(Exception parseFailure) {
            System.out.println("Could not parse a valid fraction from \"" +
                               line + "\". Please try again.");
            return;
        }
        
        try {
            pizza.eatSomePizza(amount);
        } catch(PizzaException error) {
            // This could be a call to remove the pizza. Let's find out.
            if(pizza.getRemaining().getNumerator() == 0) {
                pizzas.remove(pizzaIndex);
            } else {
                System.out.println(error.getMessage());
            }
        }
    }
    
    /**
     * Creates and adds a random pizza to the <code>ArrayList</code> of
     * pizzas.
     * 
     * Because <code>Pizza</code>'s constructor already does this, this method
     * merely inserts a freshly instantiated ("baked", hehe) pizza into the
     * list.
     */
    private void addRandomPizza() {
        pizzas.add(new Pizza());
    }

    /**
     * Displays all of the <code>Pizza</code> objects in the
     * <code>pizzas</code> <code>ArrayList</code> in their current order.
     * 
     * By current order, it is meant that this method does no kind of sorting
     * operations on the list. Rather, it merely iterates over the contents
     * of the list, printing each element to the console.
     */
    private void displayAllPizzas() {
        for(int index = 0; index < pizzas.size(); index++) {
            System.out.println(pizzas.get(index));
        }
    }

    /**
     * Sorts all of the pizzas in the <code>ArrayList</code> by price, meaning
     * that the cheapest pizzas are sorted into the lower indexes, and the
     * most expensive pizzas are sorted into the higher indexes.
     * 
     * The sorting in this method is done by using an O(n^2) selection sort,
     * using the comparison values returned by <code>Pizza</code>'s
     * <code>compareTo</code> method.
     */
    private void sortByPrice() {
        for(int i = 0; i < pizzas.size(); i++) {
            int smallestIndex = i;
            
            for(int j = i; j < pizzas.size(); j++) {
                if(pizzas.get(j).compareTo(pizzas.get(smallestIndex)) == -1) {
                    smallestIndex = j;
                }
            }
            
            Pizza temp = pizzas.set(pizzas.get(smallestIndex), i);
            pizzas.set(temp, smallestIndex);
        }
    }
    
    /**
     * Sorts all of the pizzas in the <code>ArrayList</code> by size, meaning
     * that the pizzas with the least remaining size are sorted into the lower
     * indexes, and the pizzas with the most are sorted into the higher
     * indexes.
     * 
     * The sorting in this method is done by using an O(n^2) selection sort,
     * using the comparison values returned by <code>Pizza</code>'s
     * <code>compareTo</code> method.
     */
    private void sortBySize() {
        for(int i = 0; i < pizzas.size(); i++) {
            int smallestIndex = i;
            
            for(int j = i; j < pizzas.size(); j++) {
                if(pizzas.get(j).compareToBySize(pizzas.get(smallestIndex))
                   == 1) {
                    smallestIndex = j;
                }
            }
            
            Pizza temp = pizzas.set(pizzas.get(smallestIndex), i);
            pizzas.set(temp, smallestIndex);
        }
    }
    
    /**
     * Sorts all of the pizzas in the <code>ArrayList</code> by calories,
     * meaning that the pizzas with the least calories are sorted into the
     * lower indexes, and the pizzas with the most are sorted into the higher
     * indexes.
     * 
     * The sorting in this method is done by using an O(n^2) selection sort,
     * using the comparison values returned by <code>Pizza</code>'s
     * <code>compareTo</code> method.
     */
    private void sortByCalories() {
        for(int i = 0; i < pizzas.size(); i++) {
            int smallestIndex = i;
            
            for(int j = i; j < pizzas.size(); j++) {
                if(pizzas.get(j).compareToByCalories(pizzas.get(smallestIndex))
                   == -1) {
                    smallestIndex = j;
                }
            }
            
            Pizza temp = pizzas.set(pizzas.get(smallestIndex), i);
            pizzas.set(temp, smallestIndex);
        }
    }
    
    /**
     * Searches the <code>ArrayList</code> of pizzas for the first
     * <code>Pizza</code> found with the given number of calories.
     * 
     * If no pizzas were found with the given number of calories, then
     * <code>-1</code> is returned.
     * 
     * This method uses an O(logn) binary search and the comparison results
     * from <code>Pizzas</code> <code>compareToByCalories</code> method.
     * 
     * @param cals The number of calories of the <code>Pizza</code> object
     *             whose index will be searched for.
     * 
     * @return The index of the first <code>Pizza</code> object found in
     *         the <code>ArrayList</code> with the given number of calories;
     *         <code>-1</code> is returned otherwise.
     */
    private int binarySearchByCalories(int cals) {
        if(pizzas.size() == 0) {
            return -1;
        } else if(pizzas.size() == 1) {
            return pizzas.get(0).getCalories() == cals ? 0 : -1;
        }
        
        int highIndex = pizzas.size() - 1;
        int lowIndex  = 0;
        
        while(lowIndex <= highIndex) {
            int midIndex = (highIndex + lowIndex) / 2;
            int midCals  = pizzas.get(midIndex).getCalories();
            
            if(midCals > cals) {
                highIndex = midIndex - 1;
            } else if(midCals == cals) {
                return midIndex;
            } else {
                lowIndex = midIndex + 1;
            }
        }
        
        // Edge case. Sorry, I'm bad at programming.
        if(pizzas.get(pizzas.size() - 1).getCalories() == cals) {
            return pizzas.size() - 1;
        }
        
        return -1;
    }
    
    /**
     * This value contains all of the instructions that the user will
     * be prompted with at the beginning of each loop of the main function.
     * 
     * No need to edit functions below this line, unless extending the menu or
     * changing the instructions.
     */
    private static final String INSTRUCTIONS =
            "-----------------------\nWelcome to PizzaManager\n-------------" +
            "----------\n(A)dd a random pizza\nAdd a (H)undred random pizzas" +
            "\n(E)at a fraction of a pizza\nSort pizzas by (P)rice\nSort piz" +
            "zas by (S)ize\nSort pizzas by (C)alories\n(B)inary Search pizza" +
            "s by calories\n(Q)uit\n";

    /**
     * Prompts the user of all possible options, acting as the main
     * interface between the user and the program.
     * 
     * The exact <code>String</code> of instructions can be found in this
     * class's constant, <code>INSTRUCTIONS</code>.
     */
    private void displayInstructions() {
        System.out.println(INSTRUCTIONS);    
    }

    /**
     * Calls the main program loop function after instantiating
     * the program's main <code>PizzaManager</code> instance.
     * 
     * This method exits when quit is entered as the user's option,
     * returning from <code>start</code>.
     * 
     * @param args Command line arguments not used.
     */
    public static void main(String[] args) {
        new PizzaManager().start();
    }
}