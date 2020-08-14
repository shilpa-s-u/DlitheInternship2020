package dlite.intern.twenty.campusconnectjava;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
@SpringBootTest
@RunWith(SpringRunner.class)

class CampusconnectjavaApplicationTests {
	@MockBean
	CampusRepo repo;
	@Autowired
	CampusService service;


	@Test
	public void testInsert()
	{
		candidates cand=new candidates(876556787656L, 8654567656L, "Shilpa", "Computers", "Java", "Nill", "Core", "Not Placed", "shilpa@gmail.ocm", 9.1, 8.9, 0.0, 8.9);
		when(repo.save(cand)).thenReturn(cand);
		candidates can=new candidates
				(876788778722L,9677520692L,"Richard","Computers","Java","Nill","IT","Not Placed","razzaksr@gmail.com",9.1,8.1,0.0,9.1);
		assertEquals(cand, service.insert(cand));
		assertTrue(service.insert(cand).getCgpa()>=9.1);
		assertNotEquals(can.getCertifications(),service.insert(cand));
	}

	@Test
	public void testInserting()
	{
		candidates cand=null;//new Candidates(876556787656L, 8654567656L, "Shilpa", "Computers", "Java", "Nill", "Core", "Not Placed", "shilpa@gmail.ocm", 9.1, 8.9, 0.0, 8.9);
		when(repo.save(cand)).thenReturn(cand);
		/*Candidates can=new Candidates
				(876788778722L,9677520692L,"Richard","Computers","Java","Nill","IT","Not Placed","razzaksr@gmail.com",9.1,8.1,0.0,9.1);*/
		//assertNotNull(service.insert(cand));
		assertNull(service.insert(cand));
	}@Test
	public void fetchTest()
	{
		/*
		 * 
				Stream.of
				(new Candidates
				(876788778722L,9677520692L,"Richard","Computers","Java","Nill","IT","Not Placed","razzaksr@gmail.com",9.1,8.1,0.0,9.1),
				new Candidates
				(111112222111L,8667002959L,"Razak","Electronics","Android","Nill","IT","Not Placed","razzaksr@gmail.com",9.1,8.1,0.0,9.1)
				).collect(Collectors.toList())
		 */
		List<candidates> hai=new Vector<candidates>();
		hai.add(new candidates
				(876788778722L,9677520692L,"Richard","Computers","Java","Nill","IT","Not Placed","razzaksr@gmail.com",9.1,8.1,0.0,9.1));
		hai.add(new candidates
				(111112222111L,8667002959L,"Razak","Electronics","Android","Nill","IT","Not Placed","razzaksr@gmail.com",9.1,8.1,0.0,9.1));
		when(repo.findAll()).thenReturn(hai);
		assertEquals(2, service.showAll().size());
	
	}
	@Test
	public void fetchingTest()
	{
		List<candidates> hai=new Vector<candidates>();
		hai.add(new candidates
				(876788778722L,9677520692L,"Richard","Computers","Java","Nill","IT","Not Placed","razzaksr@gmail.com",9.1,8.1,0.0,9.1));
		hai.add(new candidates
				(111112222111L,8667002959L,"Razak","Electronics","Android","Nill","IT","Not Placed","razzaksr@gmail.com",9.1,8.1,0.0,9.1));
		when(repo.findAll()).thenReturn(hai);
		assertFalse(service.showAll().get(1).getCareer().equals("Core"));
	}
	@Test
	public void readTest()
	{
		candidates can=new candidates
				(111112222111L,8667002959L,"Razak","Electronics","Android","Nill","IT","Not Placed","razzaksr@gmail.com",9.1,8.1,0.0,9.1);
		when(repo.getOne(111112222111L)).thenReturn(can);
		assertNotNull(service.readOne(111112222111L));
	}
	@Test
	public void updateTest()
	{
		candidates cand=new candidates
				(876788778722L,9677520692L,"Richard","Computers","Java","Nill","IT","Not Placed","razzaksr@gmail.com",9.1,8.1,0.0,9.1);
		candidates can=new candidates
				(111112222111L,8667002959L,"Razak","Electronics","Android","Nill","IT","Not Placed","razzaksr@gmail.com",9.1,8.1,0.0,9.1);
		when(repo.save(can)).thenReturn(can);
		assertEquals(can,service.change(can));
	}
	@Test
	public void testDelete()
	{
		candidates cand=new candidates
				(876788778722L,9677520692L,"Richard","Computers","Java","Nill","IT","Not Placed","razzaksr@gmail.com",9.1,8.1,0.0,9.1);
		candidates can=new candidates
				(111112222111L,8667002959L,"Razak","Electronics","Android","Nill","IT","Not Placed","razzaksr@gmail.com",9.1,8.1,0.0,9.1);
		assertSame(cand.getName(), cand.getName(), service.erase(cand));
	}
	@Test
	public void testReadsByDepartment()
	{
		List<candidates> hai=new Vector<candidates>();
		candidates cand=new candidates
				(876788778722L,9677520692L,"Richard","Computers","Java","Nill","IT","Not Placed","razzaksr@gmail.com",9.1,8.1,0.0,9.1);
		candidates can=new candidates
				(111112222111L,8667002959L,"Razak","Electronics","Android","Nill","IT","Not Placed","razzaksr@gmail.com",9.1,8.1,0.0,9.1);
		hai.add(cand);hai.add(can);
		when(repo.getBydepartment("Computers")).thenReturn(hai);
		assertEquals(cand,service.fetchViadepartment("Computers").get(0));
	}
}
