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
	public candidates readOne(Long regno)
	{
		return repo.getOne(regno);
	}
	public candidates change(candidates candidates)
	{
		return repo.save(candidates);
	}
	public String erase(candidates candidates)
	{
		String get=candidates.getName();
		repo.delete(candidates);
		return get;
	}
	public List<candidates> fetchViadepartment(String dept)
	{
		return repo.getBydepartment(dept);
	}
	public List<candidates> fetchViacareer(String career)
	{
		return repo.getBycareer(career);
	}
	public List<candidates> fetchViastatus(String status)
	{
		return repo.getBystatus(status);
	}
	
}
