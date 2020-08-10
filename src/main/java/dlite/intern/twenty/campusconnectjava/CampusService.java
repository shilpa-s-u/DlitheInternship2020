package dlite.intern.twenty.campusconnectjava;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service

public class CampusService {
	@Autowired
	CampusRepo repo;
	public candidates insert(candidates candidates)
	{
		return repo.save(candidates);
	}
	public List<candidates> showAll()
	{
		return repo.findAll();
	}

}
