//package benkinmat.github.io.springbootexceptionhandler.service;
//
//import static org.junit.Assert.assertEquals;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import benkinmat.github.io.springbootexceptionhandler.error.ServiceException;
//import benkinmat.github.io.springbootexceptionhandler.model.Geek;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class GeekServiceTest {
//
//	@Autowired
//	GeekService geekService;
//
//	@Test(expected = ServiceException.class)
//	public void whenInputDoubleValid_thenThrowServiceExeption() {
//		Geek firstGeek = new Geek("firstname", "lastname", 1);
//		Geek secondGeek = new Geek("firstname", "lastname", 1);
//
//		Geek firstGeekResult = geekService.register(firstGeek);
//		assertEquals(firstGeek, firstGeekResult);
//
//		geekService.register(secondGeek);
//	}
//	
//	@Test
//	public void whenInputValid_thenReturnSameEntity() {
//		Geek geek = new Geek("firstname", "lastname", 2);
//
//		Geek firstGeekResult = geekService.register(geek);
//		assertEquals(geek, firstGeekResult);
//	}
//
//}
