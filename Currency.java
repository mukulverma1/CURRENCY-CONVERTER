/**
 * Enum representing supported currencies with their codes and names
 */
public enum Currency {
    USD("USD", "US Dollar"),
    EUR("EUR", "Euro"),
    INR("INR", "Indian Rupee"),
    JPY("JPY", "Japanese Yen"),
    GBP("GBP", "British Pound");
    
    private final String code;
    private final String name;
    
    Currency(String code, String name) {
        this.code = code;
        this.name = name;
    }
    
    public String getCode() {
        return code;
    }
    
    public String getName() {
        return name;
    }
    
    @Override
    public String toString() {
        return code + " - " + name;
    }
    
    /**
     * Get Currency enum from string code
     * @param code Currency code (e.g., "USD")
     * @return Currency enum or null if not found
     */
    public static Currency fromCode(String code) {
        for (Currency currency : Currency.values()) {
            if (currency.getCode().equalsIgnoreCase(code)) {
                return currency;
            }
        }
        return null;
    }
}
