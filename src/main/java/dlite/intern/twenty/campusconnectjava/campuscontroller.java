package dlite.intern.twenty.campusconnectjava;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import java.util.Vector;

@Controller
public class campuscontroller {
@Autowired
CampusService camp;
List<candidates> temp;
HttpSession session;
@RequestMapping("/begin")
public ModelAndView initiate()
{
	ModelAndView mod=new ModelAndView("one");
	mod.addObject("user", "shilpa s u");
	long mobile=9876545674l;
	mod.addObject("mob",mobile);
	return mod;
}
@RequestMapping("/second")
public ModelAndView second()
{
	return new ModelAndView("second");
}
@RequestMapping("/add")
public ModelAndView askEnroll()
{
	return new ModelAndView("enroll");
}
@RequestMapping(value="/added",method=RequestMethod.POST)
public ModelAndView enrolled(@Validated candidates candidates,BindingResult res)
{
	if(res.hasErrors()) {return new ModelAndView("enroll"); }
	camp.insert(candidates);
	return new ModelAndView("enroll").addObject("msg", "Candidates Enrolled");
}

@RequestMapping("/list")
public ModelAndView display()
{
	List<candidates> temp=camp.showAll();
	return new ModelAndView("show").addObject("every", temp);
}
@RequestMapping("/update")
public ModelAndView info(@RequestParam("reg") Long reg)
{
	return new ModelAndView("letting").addObject("fetched", camp.readOne(reg));
}
@RequestMapping(value="/alter",method=RequestMethod.POST)
public ModelAndView alter(candidates candidates)
{
	camp.change(candidates);
	return display().addObject("msg", candidates.getName()+" Updated SuccessFully");
}
@RequestMapping("/remove")
public ModelAndView flush(@RequestParam("reg") Long reg)
{
	candidates can=camp.readOne(reg);
	String got=camp.erase(can);
	return display().addObject("msg", got+" Deleted Successfully");
}
@RequestMapping("/find")
public ModelAndView search()
{
	return new ModelAndView("search");
}
@RequestMapping(value="/fetch",method=RequestMethod.POST)
public ModelAndView reads(@RequestParam("regno") String regno,@RequestParam("department") String department,@RequestParam("career") String career,@RequestParam("status") String status)
{
	temp=new Vector<candidates>();
	if(!regno.equals("")&&department.equals("Select Any Department")&&career.equals("Select Any Career")&&status.equals("Select Any Status"))
	{
		candidates tmp=camp.readOne(Long.parseLong(regno));
		temp.add(tmp);
	}
	else if(regno.equals("")&&!department.equals("Select Any Department")&&career.equals("Select Any Career")&&status.equals("Select Any Status"))
	{
		temp=camp.fetchViadepartment(department);
		//camp.fetchViaDepartment(department).forEach(temp::add);
	}
	else if(regno.equals("")&&department.equals("Select Any Department")&&!career.equals("Select Any Career")&&status.equals("Select Any Status"))
	{
		temp=camp.fetchViacareer(career);
	}
	else if(regno.equals("")&&department.equals("Select Any Department")&&career.equals("Select Any Career")&&!status.equals("Select Any Status"))
	{
		temp=camp.fetchViastatus(status);
	}
	return new ModelAndView("show").addObject("every", temp);
}
@RequestMapping("/report")
public ModelAndView rep(@RequestParam("form") String form)
{
		ModelAndView mod=new ModelAndView("show");
		System.out.println("Before report calls"+temp);
		String get=camp.generate(temp, form);
		mod.addObject("every", temp);
		mod.addObject("msg", get);
		System.out.println("Done in report navigate back to show with "+temp);
		return mod;
	
}
}
