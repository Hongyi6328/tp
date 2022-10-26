package seedu.address.model.order.predicates;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import seedu.address.model.order.Order;

/**
 * Tests that a {@code Order}'s {@code AdditionalRequest} matches any of the keywords given.
 */
public class AdditionalRequestPredicate<T extends Order> implements Predicate<T> {
    private final List<String> keywords;

    public AdditionalRequestPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(T order) {
        List<String> additionalRequests = order.getAdditionalRequests().getAdditionalRequestsToString();
        Set<String> result = additionalRequests.stream()
                .distinct()
                .filter(keywords::contains)
                .collect(Collectors.toSet());
        return result.size() > 0;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AdditionalRequestPredicate // instanceof handles nulls
                && keywords.equals(((AdditionalRequestPredicate) other).keywords)); // state check
    }
}