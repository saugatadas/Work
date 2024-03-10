package BookMyShow.repository;

import BookMyShow.model.Actor;
import org.hibernate.query.criteria.JpaSearchedCase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Integer> {
}
