package seedu.address.model.pet;

/**
 * Represents the color pattern of a pet. For example, stripped, grid, dark.
 */
public class ColorPattern implements Comparable<ColorPattern> {

    private final String value;

    /**
     * Constructs a ColorPattern object.
     *
     * @param value The string representation of color pattern.
     */
    public ColorPattern(String value) {
        if (value == null) {
            this.value = "";
        } else {
            this.value = value;
        }
    }

    /**
     * Gets the color pattern of a pet.
     *
     * @return The color pattern.
     */
    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else if (other instanceof ColorPattern) {
            ColorPattern otherColorPattern = (ColorPattern) other;
            return value.equals(otherColorPattern.getValue());
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public String toString() {
        return "Color pattern: " + value;
    }

    @Override
    public int compareTo(ColorPattern o) {
        return this.value.compareTo(o.value);
    }
}
