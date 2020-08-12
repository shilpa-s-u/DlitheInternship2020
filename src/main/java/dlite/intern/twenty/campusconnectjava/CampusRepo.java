package dlite.intern.twenty.campusconnectjava;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
@Repository
public interface CampusRepo extends JpaRepository<candidates, Long> {

	@Query("from candidates where department= :dep")
	public List<candidates> getBydepartment(String dep);
	@Query("from candidates where career= :car")
	public List<candidates> getBycareer(String car);
	@Query("from candidates where status= :state")
	public List<candidates> getBystatus(String state);
}
