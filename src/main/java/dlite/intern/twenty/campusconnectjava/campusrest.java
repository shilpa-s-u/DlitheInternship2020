package dlite.intern.twenty.campusconnectjava;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class campusrest {
	@Autowired
	CampusService service;
	@PostMapping("/addViaBody")
	public candidates insViaBody(@RequestBody candidates candidates)
	{
		return service.insert(candidates);
	}
	@GetMapping("/display")
	public List<candidates> listing()
	{
		return service.showAll();
	}
	@GetMapping(value="/displayAsXml",produces="application/xml")
	public List<candidates> listingAsXml()
	{
		return service.showAll();
	}
	

}
