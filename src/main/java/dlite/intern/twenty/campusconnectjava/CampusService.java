package dlite.intern.twenty.campusconnectjava;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.util.ResourceUtils;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
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
	public String generate(List<candidates> can,String format)
	{
		File fgen=null;
        String hai="";
		try
		{
			File file = ResourceUtils.getFile("classpath:campusconnectorreport.jrxml");
	        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
	        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(can);
	        Map<String, Object> parameters = new HashMap<>();
	        parameters.put("createdBy", "Shilpa S U");
	        parameters.put("createdFor", "DLithe Consultancy Services");
	        System.out.println("Received @ report end before writing "+can);
	        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
	        if (format.equalsIgnoreCase("html")) {
	        	fgen=new File("dlithe.html");
	            JasperExportManager.exportReportToHtmlFile(jasperPrint, fgen.getAbsolutePath());
	        }
	        if (format.equalsIgnoreCase("pdf")) {
	        	fgen=new File("dlithe.pdf");
	            JasperExportManager.exportReportToPdfFile(jasperPrint, fgen.getAbsolutePath());
	        }
	        hai="Report generated @ "+fgen.getAbsolutePath();
	        System.out.println("Received @ report end after writing "+can);
		}
		catch(JRException j)
		{j.printStackTrace();} 
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return hai;
	}
	
}
