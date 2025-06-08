import java.util.HashMap;
import java.util.Map;

/**
 * Provides exchange rates for currency conversion
 * Uses USD as base currency for all conversions
 */
public class ExchangeRateProvider {
    private static final Map<Currency, Double> exchangeRates = new HashMap<>();
    
    // Static block to initialize exchange rates (USD as base)
    static {
        exchangeRates.put(Currency.USD, 1.0);      // Base currency
        exchangeRates.put(Currency.EUR, 0.92);     // 1 USD = 0.92 EUR
        exchangeRates.put(Currency.INR, 83.2);     // 1 USD = 83.2 INR
        exchangeRates.put(Currency.JPY, 151.1);    // 1 USD = 151.1 JPY
        exchangeRates.put(Currency.GBP, 0.79);     // 1 USD = 0.79 GBP
    }
    
    /**
     * Get exchange rate for a currency relative to USD
     * @param currency Target currency
     * @return Exchange rate (1 USD = rate * currency)
     */
    public static double getExchangeRate(Currency currency) {
        return exchangeRates.getOrDefault(currency, 1.0);
    }
    
    /**
     * Get all supported currencies
     * @return Array of supported currencies
     */
    public static Currency[] getSupportedCurrencies() {
        return Currency.values();
    }
    
    /**
     * Check if currency is supported
     * @param currency Currency to check
     * @return true if supported, false otherwise
     */
    public static boolean isSupported(Currency currency) {
        return exchangeRates.containsKey(currency);
    }
}
