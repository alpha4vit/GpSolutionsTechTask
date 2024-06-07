package by.gurinovich.cryptologos.gpsolutionstechtask.repository;

import by.gurinovich.cryptologos.gpsolutionstechtask.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {

    Optional<Brand> findByName(String name);

    @Query(value = "select b.name, count(h.id) from brands b join public.hotels h on h.brand_id = b.id group by b.name", nativeQuery = true)
    List<Object[]> groupBrands();

}
