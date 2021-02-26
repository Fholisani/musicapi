package za.co.newplant.musicapi.util;

import org.springframework.data.jpa.domain.Specification;
import za.co.newplant.musicapi.model.ContentFile;
import za.co.newplant.musicapi.model.ContentSpecification;

import java.util.ArrayList;
import java.util.List;

public final class ContentSpecificationsBuilder {

    private final List<SpecSearchCriteria> params;

    public ContentSpecificationsBuilder() {
        params = new ArrayList<>();
    }

    // API

    public final ContentSpecificationsBuilder with(final String key, final String operation, final Object value, final String prefix, final String suffix) {
        return with(null, key, operation, value, prefix, suffix);
    }

    public final ContentSpecificationsBuilder with(final String orPredicate, final String key, final String operation, final Object value, final String prefix, final String suffix) {
        SearchOperation op = SearchOperation.getSimpleOperation(operation.charAt(0));
        if (op != null) {
            if (op == SearchOperation.EQUALITY) { // the operation may be complex operation
                final boolean startWithAsterisk = prefix != null && prefix.contains(SearchOperation.ZERO_OR_MORE_REGEX);
                final boolean endWithAsterisk = suffix != null && suffix.contains(SearchOperation.ZERO_OR_MORE_REGEX);

                if (startWithAsterisk && endWithAsterisk) {
                    op = SearchOperation.CONTAINS;
                } else if (startWithAsterisk) {
                    op = SearchOperation.ENDS_WITH;
                } else if (endWithAsterisk) {
                    op = SearchOperation.STARTS_WITH;
                }
            }
            params.add(new SpecSearchCriteria(orPredicate, key, op, value));
        }
        return this;
    }

    public Specification<ContentFile> build() {
        if (params.size() == 0)
            return null;

        Specification<ContentFile> result = new ContentSpecification(params.get(0));

        for (int i = 1; i < params.size(); i++) {
            result = params.get(i).isOrPredicate()
              ? Specification.where(result).or(new ContentSpecification(params.get(i)))
              : Specification.where(result).and(new ContentSpecification(params.get(i)));
        }

        return result;
    }

    public final ContentSpecificationsBuilder with(ContentSpecification spec) {
        params.add(spec.getCriteria());
        return this;
    }

    public final ContentSpecificationsBuilder with(SpecSearchCriteria criteria) {
        params.add(criteria);
        return this;
    }
}
