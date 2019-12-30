package benkinmat.github.io.springbootexceptionhandler.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest()
@AutoConfigureMockMvc
public class GeekResourceTest {

	private String validGeekBodyRequest;
	private String invalidGeekBodyRequest;
	
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private DataSource datasource;
	
	@Before
	public void init() {
		validGeekBodyRequest = "{\"firstName\":\"firstname\",\"lastName\":\"lastname\",\"phone\":\"0339087842\",\"userId\":\"A12345678\",\"email\":\"flyflowflown@yahoo.com\",\"status\":\"InActive\"}";
		invalidGeekBodyRequest = "{}";
	}
	
	@After
	public void tearDown() throws SQLException {
		clearDatabase();
	}

	public void clearDatabase() throws SQLException {
	    Connection c = datasource.getConnection();
	    Statement s = c.createStatement();

	    // Disable FK
	    s.execute("SET REFERENTIAL_INTEGRITY FALSE");

	    // Find all tables and truncate them
	    Set<String> tables = new HashSet<String>();
	    ResultSet rs = s.executeQuery("SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES  where TABLE_SCHEMA='PUBLIC'");
	    while (rs.next()) {
	        tables.add(rs.getString(1));
	    }
	    rs.close();
	    for (String table : tables) {
	        s.executeUpdate("TRUNCATE TABLE " + table);
	    }

	    // Idem for sequences
	    Set<String> sequences = new HashSet<String>();
	    rs = s.executeQuery("SELECT SEQUENCE_NAME FROM INFORMATION_SCHEMA.SEQUENCES WHERE SEQUENCE_SCHEMA='PUBLIC'");
	    while (rs.next()) {
	        sequences.add(rs.getString(1));
	    }
	    rs.close();
	    for (String seq : sequences) {
	        s.executeUpdate("ALTER SEQUENCE " + seq + " RESTART WITH 1");
	    }

	    // Enable FK
	    s.execute("SET REFERENTIAL_INTEGRITY TRUE");
	    s.close();
	    c.close();
	}

	@Test
	public void whenInputIsInvalid_thenReturnsStatus400() throws Exception {
		mvc.perform(post("/geeks")
				.contentType("application/json")
				.content(invalidGeekBodyRequest)).andExpect(status().isBadRequest())
				.andDo(print())
				.andExpect(jsonPath("$.statusCode", is(400)))
                .andExpect(jsonPath("$.errors").isArray())
                .andExpect(jsonPath("$.reasonPhrase", is("Bad Request")))
                .andExpect(jsonPath("$.errors", hasSize(5)));
	}
	
	@Test
	public void whenInputValid_thenReturnsStatus201AndUriAtLocation() throws Exception {
		mvc.perform(post("/geeks")
				.contentType("application/json")
				.content(validGeekBodyRequest)).andExpect(status().isCreated())
				.andDo(print())
        		.andExpect(header().string("Location", containsString("http://localhost/geeks")));
	}
	
	@Test
	public void whenInputDoubleValid_thenReturnsStatus400() throws Exception {
		mvc.perform(post("/geeks")
				.contentType("application/json")
				.content(validGeekBodyRequest)).andExpect(status().isCreated())
				.andDo(print());
		
		mvc.perform(post("/geeks")
				.contentType("application/json")
				.content(validGeekBodyRequest)).andExpect(status().isBadRequest())
				.andDo(print())
				.andExpect(jsonPath("$.statusCode", is(400)))
		        .andExpect(jsonPath("$.errors").isArray())
		        .andExpect(jsonPath("$.reasonPhrase", is("Bad Request")))
		        .andExpect(jsonPath("$.errors", hasSize(1)));
	}

}
