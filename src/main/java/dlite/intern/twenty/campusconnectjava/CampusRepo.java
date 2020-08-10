package dlite.intern.twenty.campusconnectjava;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface CampusRepo extends JpaRepository<candidates, Long> {

}
