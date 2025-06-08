import java.util.Scanner;
import utils.InputValidator;

/**
 * Main class handling console input/output and program flow
 */
public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        System.out.println("=================================");
        System.out.println("   CURRENCY CONVERTER");
        System.out.println("=================================");
        
        boolean continueProgram = true;
        
        while (continueProgram) {
            try {
                performConversion();
                continueProgram = askToContinue();
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
                continueProgram = askToContinue();
            }
        }
        
        System.out.println("\nThank you for using Currency Converter!");
        scanner.close();
    }
    
    /**
     * Perform a single currency conversion
     */
    private static void performConversion() {
        System.out.println("\n--- New Conversion ---");
        
        // Display supported currencies
        displaySupportedCurrencies();
        
        // Get source currency
        Currency fromCurrency = selectCurrency("Enter source currency number: ");
        if (fromCurrency == null) return;
        
        // Get target currency
        Currency toCurrency = selectCurrency("Enter target currency number: ");
        if (toCurrency == null) return;
        
        // Get amount to convert
        double amount = getAmountInput();
        if (amount <= 0) return;
        
        // Perform conversion
        double convertedAmount = CurrencyConverter.convert(amount, fromCurrency, toCurrency);
        double conversionRate = CurrencyConverter.getConversionRate(fromCurrency, toCurrency);
        
        // Display results
        displayConversionResult(amount, fromCurrency, convertedAmount, toCurrency, conversionRate);
    }
    
    /**
     * Display list of supported currencies
     */
    private static void displaySupportedCurrencies() {
        System.out.println("\nSupported Currencies:");
        Currency[] currencies = ExchangeRateProvider.getSupportedCurrencies();
        
        for (int i = 0; i < currencies.length; i++) {
            System.out.println((i + 1) + ". " + currencies[i]);
        }
        System.out.println();
    }
    
    /**
     * Allow user to select a currency
     * @param prompt Prompt message
     * @return Selected Currency or null if invalid
     */
    private static Currency selectCurrency(String prompt) {
        Currency[] currencies = ExchangeRateProvider.getSupportedCurrencies();
        int maxAttempts = 3;
        int attempts = 0;
        
        while (attempts < maxAttempts) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            
            if (InputValidator.isValidChoice(input, 1, currencies.length)) {
                int choice = InputValidator.parseChoice(input);
                return currencies[choice - 1];
            } else {
                attempts++;
                System.out.println("Invalid selection. Please enter a number between 1 and " + 
                                 currencies.length + ". (" + (maxAttempts - attempts) + " attempts remaining)");
            }
        }
        
        System.out.println("Too many invalid attempts. Returning to main menu.");
        return null;
    }
    
    /**
     * Get amount input from user
     * @return Amount to convert or -1 if invalid
     */
    private static double getAmountInput() {
        int maxAttempts = 3;
        int attempts = 0;
        
        while (attempts < maxAttempts) {
            System.out.print("Enter amount to convert: ");
            String input = scanner.nextLine().trim();
            
            if (InputValidator.isValidAmount(input)) {
                return InputValidator.parseAmount(input);
            } else {
                attempts++;
                System.out.println("Invalid amount. Please enter a positive number. (" + 
                                 (maxAttempts - attempts) + " attempts remaining)");
            }
        }
        
        System.out.println("Too many invalid attempts. Returning to main menu.");
        return -1;
    }
    
    /**
     * Display conversion results
     */
    private static void displayConversionResult(double amount, Currency fromCurrency, 
                                              double convertedAmount, Currency toCurrency, 
                                              double conversionRate) {
        System.out.println("\n=== CONVERSION RESULT ===");
        System.out.println("Amount: " + CurrencyConverter.formatAmount(amount) + " " + fromCurrency.getCode());
        System.out.println("Converted to: " + CurrencyConverter.formatAmount(convertedAmount) + " " + toCurrency.getCode());
        System.out.println("Exchange Rate: 1 " + fromCurrency.getCode() + " = " + 
                          CurrencyConverter.formatAmount(conversionRate) + " " + toCurrency.getCode());
        System.out.println("========================");
    }
    
    /**
     * Ask user if they want to continue
     * @return true if user wants to continue, false otherwise
     */
    private static boolean askToContinue() {
        while (true) {
            System.out.print("\nDo you want to perform another conversion? (y/n): ");
            String input = scanner.nextLine().trim().toLowerCase();
            
            if (input.equals("y") || input.equals("yes")) {
                return true;
            } else if (input.equals("n") || input.equals("no")) {
                return false;
            } else {
                System.out.println("Please enter 'y' for yes or 'n' for no.");
            }
        }
    }
}
