
package app.adapter.in.validators;

public abstract class SimpleValidator {

    public String stringValidator(String element, String value) throws Exception {
        if (value == null || value.trim().isEmpty()) {
            throw new Exception(element + " no puede tener un valor vacío.");
        }
        return value.trim();
    }

    public int integerValidator(String element, int value) throws Exception
    {
        if (value < 0) {
            throw new Exception(element + " no puede ser negativo.");
        }
        return value;
    }

    public long longValidator(String element, String value) throws Exception {
        stringValidator(element, value);
        try {
            return Long.parseLong(value);
        } catch (NumberFormatException e) {
            throw new Exception(element + " debe ser un valor numérico largo.");
        }
    }

    public long dateValidator(String element, String value) throws Exception {
        stringValidator(element, value);
        try {
            return Long.parseLong(value);
        } catch (NumberFormatException e) {
            throw new Exception(element + " debe ser un valor numérico de fecha (timestamp).");
        }
    }

    public boolean booleanValidator(String element, String value) throws Exception {
        stringValidator(element, value);
        String lowerValue = value.trim().toLowerCase();

        if (lowerValue.equals("true") || lowerValue.equals("false")) {
            return Boolean.parseBoolean(lowerValue);
        } else if (lowerValue.equals("1")) {
            return true;
        } else if (lowerValue.equals("2")) {
            return false;
        } else {
            throw new Exception(element + " debe ser un valor booleano válido (true/false o 1/2).");
        }
    }
}
