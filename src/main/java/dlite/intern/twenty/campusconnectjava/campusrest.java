package dlite.intern.twenty.campusconnectjava;
import java.util.List;
import java.util.Vector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	@PutMapping("/modify")
	public candidates modify(@RequestBody candidates candidates)
	{
		return service.change(candidates);
	}
	@DeleteMapping("/del")
	public String clean(@RequestBody candidates candidates)
	{
		return service.erase(candidates);
	}
	@GetMapping("/fetch/{constrain}/{data}")// /fetch/department/Computers
	public List<candidates> find(@PathVariable("constrain") String constrain,@PathVariable("data") String data)
	{
		List<candidates> temp=new Vector<candidates>();
		if(constrain.equalsIgnoreCase("regno"))
		{
			temp.add(service.readOne(Long.parseLong(data)));
		}
		else if(constrain.equalsIgnoreCase("department"))
		{
			temp=service.fetchViadepartment(data);
		}
		else if(constrain.equalsIgnoreCase("career"))
		{
			temp=service.fetchViacareer(data);
		}
		else if(constrain.equalsIgnoreCase("status"))
		{
			temp=service.fetchViastatus(data);
		}
		return temp;
	}
	@GetMapping("/fetch/{constrain}/{data}/report/{format}")// /fetch/department/Computers/report/pdf
	public String finding(@PathVariable("constrain") String constrain,@PathVariable("data") String data,@PathVariable("format") String format)
	{
		List<candidates> temp=new Vector<candidates>();
		if(constrain.equalsIgnoreCase("regno"))
		{
			temp.add(service.readOne(Long.parseLong(data)));
		}
		else if(constrain.equalsIgnoreCase("department"))
		{
			temp=service.fetchViadepartment(data);
		}
		else if(constrain.equalsIgnoreCase("career"))
		{
			temp=service.fetchViacareer(data);
		}
		else if(constrain.equalsIgnoreCase("status"))
		{
			temp=service.fetchViastatus(data);
		}
		return service.generate(temp, format);
	}

}
