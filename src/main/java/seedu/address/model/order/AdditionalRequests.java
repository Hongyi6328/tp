package seedu.address.model.order;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Arrays;

public class AdditionalRequests {

    private final ArrayList<String> additionalRequests = new ArrayList<>();

    public AdditionalRequests(String... descriptions) {
        requireNonNull(descriptions);
        additionalRequests.addAll(Arrays.asList(descriptions));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else if (other instanceof AdditionalRequests) {
            AdditionalRequests otherAdditionalRequests = (AdditionalRequests) other;
            return additionalRequests.equals(otherAdditionalRequests.additionalRequests);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return additionalRequests.hashCode();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        int i = 1;
        for (String description : additionalRequests) {
            builder.append(i).append(". ").append(description).append(System.lineSeparator());
            i++;
        }
        return builder.toString();
    }

}