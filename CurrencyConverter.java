/**
 * Core currency conversion logic
 */
public class CurrencyConverter {
    
    /**
     * Convert amount from source currency to target currency
     * @param amount Amount to convert
     * @param fromCurrency Source currency
     * @param toCurrency Target currency
     * @return Converted amount
     */
    public static double convert(double amount, Currency fromCurrency, Currency toCurrency) {
        if (fromCurrency == null || toCurrency == null) {
            throw new IllegalArgumentException("Currency cannot be null");
        }
        
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }
        
        // If same currency, return same amount
        if (fromCurrency == toCurrency) {
            return amount;
        }
        
        // Get exchange rates relative to USD
        double fromRate = ExchangeRateProvider.getExchangeRate(fromCurrency);
        double toRate = ExchangeRateProvider.getExchangeRate(toCurrency);
        
        // Convert to USD first, then to target currency
        double usdAmount = amount / fromRate;
        double convertedAmount = usdAmount * toRate;
        
        return convertedAmount;
    }
    
    /**
     * Format amount to 2 decimal places
     * @param amount Amount to format
     * @return Formatted string
     */
    public static String formatAmount(double amount) {
        return String.format("%.2f", amount);
    }
    
    /**
     * Get conversion rate between two currencies
     * @param fromCurrency Source currency
     * @param toCurrency Target currency
     * @return Conversion rate
     */
    public static double getConversionRate(Currency fromCurrency, Currency toCurrency) {
        if (fromCurrency == null || toCurrency == null) {
            throw new IllegalArgumentException("Currency cannot be null");
        }
        
        if (fromCurrency == toCurrency) {
            return 1.0;
        }
        
        double fromRate = ExchangeRateProvider.getExchangeRate(fromCurrency);
        double toRate = ExchangeRateProvider.getExchangeRate(toCurrency);
        
        return toRate / fromRate;
    }
}
