package by.gurinovich.cryptologos.gpsolutionstechtask.filter;

import by.gurinovich.cryptologos.gpsolutionstechtask.dto.HotelFilterDTO;
import by.gurinovich.cryptologos.gpsolutionstechtask.entity.Amenity;
import by.gurinovich.cryptologos.gpsolutionstechtask.entity.Hotel;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

import java.util.Arrays;
import java.util.stream.Stream;

public class HotelSpecification {

    public static Specification<Hotel> getFilterSpec(HotelFilterDTO filter){
        return Specification
                .where(filter.name() != null && !filter.name().isEmpty() ? inName(getStringStream(filter.name())) : null)
                .and(filter.city() != null && !filter.city().isEmpty() ? inCity(getStringStream(filter.city())) : null)
                .and(filter.country() != null && !filter.country().isEmpty() ? inCountry(getStringStream(filter.country())) : null)
                .and(filter.amenities() != null && !filter.amenities().isEmpty() ? inAmenities(getStringStream(filter.amenities())) : null)
                .and(filter.brand() != null && !filter.brand().isEmpty() ? inBrand(getStringStream(filter.brand())) : null);
    }


    private static Stream<String> getStringStream(String val){
        return Arrays.stream(val.split(","))
                .map(el -> el.trim().toLowerCase());
    }

    private static Specification<Hotel> inName(Stream<String> name){
        return (root, query, criteriaBuilder) -> {
            CriteriaBuilder.In<String> inClause = criteriaBuilder.in(criteriaBuilder.lower(root.get("name")));
            name.forEach(inClause::value);
            return inClause;
        };
    }

    private static Specification<Hotel> inCity(Stream<String> city){
        return (root, query, criteriaBuilder) -> {
            CriteriaBuilder.In<String> inClause = criteriaBuilder.in(criteriaBuilder.lower(root.get("address").get("city")));
            city.forEach(inClause::value);
            return inClause;
        };
    }

    private static Specification<Hotel> inCountry(Stream<String> country){
        return (root, query, criteriaBuilder) -> {
            CriteriaBuilder.In<String> inClause = criteriaBuilder.in(criteriaBuilder.lower(
                    root.get("address")
                            .get("country")
                            .get("name")
            ));
            country.forEach(inClause::value);
            return inClause;
        };
    }

    private static Specification<Hotel> inAmenities(Stream<String> amenities){
        return (root, query, criteriaBuilder) -> {
            Subquery<Long> subquery = query.subquery(Long.class);
            Root<Hotel> subRoot = subquery.from(Hotel.class);
            Join<Hotel, Amenity> subJoin = subRoot.join("amenities");

            Predicate[] predicates = amenities
                    .map(amenity -> criteriaBuilder.equal(criteriaBuilder.lower(subJoin.get("name")), amenity))
                    .toArray(Predicate[]::new);

            subquery.select(subRoot.get("id"))
                    .where(criteriaBuilder.or(predicates));

            return criteriaBuilder.in(root.get("id")).value(subquery);
        };
    }

    private static Specification<Hotel> inBrand(Stream<String> brand){
        return (root, query, criteriaBuilder) -> {
            CriteriaBuilder.In<String> inClause = criteriaBuilder.in(criteriaBuilder.lower(
                    root.get("brand")
                            .get("name")
            ));
            brand.forEach(inClause::value);
            return inClause;
        };
    }
}
