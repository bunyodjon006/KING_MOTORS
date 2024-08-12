package avtosalon.example.King_Motors.repository;

import avtosalon.example.King_Motors.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

//    Page<User> findAllByNameContainsIgnoreOrId(String key, Long id, Pageable pageable);
}
