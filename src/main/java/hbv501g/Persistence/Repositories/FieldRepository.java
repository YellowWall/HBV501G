package hbv501g.Persistence.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import hbv501g.Persistence.Entities.Field;

public interface FieldRepository extends JpaRepository<Field,Long> {

    Field findByFieldname();
}
