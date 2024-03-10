package BookMyShow.repository;

import BookMyShow.model.BmsShow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowRepository extends JpaRepository<BmsShow, Integer> {
}
