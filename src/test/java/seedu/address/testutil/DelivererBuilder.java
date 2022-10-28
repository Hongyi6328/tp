package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.order.Order;
import seedu.address.model.person.Address;
import seedu.address.model.person.Deliverer;
import seedu.address.model.person.Email;
import seedu.address.model.person.Location;
import seedu.address.model.person.Name;
import seedu.address.model.person.PersonCategory;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Deliverer objects.
 */
public class DelivererBuilder {

    public static final String DEFAULT_PERSON_CATEGORY = "Deliverer";
    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";

    private PersonCategory personCategory;
    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private Location location;
    private Set<Tag> tags;
    private Set<Order> orders;

    /**
     * Creates a {@code DelivererBuilder} with the default details.
     */
    public DelivererBuilder() {
        personCategory = PersonCategory.getFromString(DEFAULT_PERSON_CATEGORY); // Person Category instantiated later
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        tags = new HashSet<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code delivererToCopy}.
     */
    public DelivererBuilder(Deliverer delivererToCopy) {
        personCategory = delivererToCopy.getPersonCategory();
        name = delivererToCopy.getName();
        phone = delivererToCopy.getPhone();
        email = delivererToCopy.getEmail();
        address = delivererToCopy.getAddress();
        tags = new HashSet<>(delivererToCopy.getTags());
    }

    /**
     * Sets the {@code PersonCategory} of the {@code Deliverer} that we are building.
     */
    public DelivererBuilder withPersonCategory(String personCategory) {
        this.personCategory = PersonCategory.getFromString(personCategory);
        return this;
    }

    /**
     * Sets the {@code Name} of the {@code Deliverer} that we are building.
     */
    public DelivererBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Deliverer} that we are building.
     */
    public DelivererBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Deliverer} that we are building.
     */
    public DelivererBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Deliverer} that we are building.
     */
    public DelivererBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Deliverer} that we are building.
     */
    public DelivererBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code Location} of the {@code Deliverer} that we are building.
     */
    public DelivererBuilder withLocation(String loc) {
        this.location = new Location(loc);
        return this;
    }

    public Deliverer build() {
        return new Deliverer(name, phone, email, address, tags, null);
    }
}

