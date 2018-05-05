package trng.imcs.hib.jason;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

;

public class JaksonMapper {

	
	private static String FILE_PATH = "src\\main\\resources\\customer.json";
	
	public static void main(String[] args) throws JsonGenerationException, JsonMappingException, IOException, DatatypeConfigurationException {
		serialize();
		deserialize();
	 
	}
	
	public static Customer createCustomer() throws DatatypeConfigurationException {
		
		ObjectFactory fac = new ObjectFactory();
		Address address = fac.createAddress();

		address.setCity("Irving");
		address.setState("Texas");
		address.setStreet("Colonial Grand");
		address.setZipcode("76263");

		GregorianCalendar c1 = new GregorianCalendar(2017,02,02);
		GregorianCalendar c2 = new GregorianCalendar(2017,04,04);
		GregorianCalendar c3 = new GregorianCalendar(1990,04,04);
	
		XMLGregorianCalendar dateTo = DatatypeFactory.newInstance().newXMLGregorianCalendar(c1);
		XMLGregorianCalendar dateFrom = DatatypeFactory.newInstance().newXMLGregorianCalendar(c2);
		XMLGregorianCalendar dob = DatatypeFactory.newInstance().newXMLGregorianCalendar(c3);
		
		Payment payment = new Payment();
		payment.setCardName("Harman");
		payment.setCardNumber(11112222);
		payment.setCardtype("CREDITCARD");
		payment.setDateFrom(dateFrom);
		payment.setDateTo(dateTo);

		List<Payment> list = new ArrayList<>();
		list.add(payment);

		Customer customer = new Customer();
		customer.setCustomerId(1);
		customer.setName("Harman");
		customer.setDob(dob);
		customer.setSalary(1500);
		customer.setAddress(address);
		customer.setPayment(list);
		
		return customer;
	}
	
	
	private static void serialize() throws JsonGenerationException, JsonMappingException, IOException, DatatypeConfigurationException {
        ObjectMapper objectMapper = new ObjectMapper();
      
        Customer emp = createCustomer();
        
      
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
         
       
        objectMapper.writeValue(System.out, emp);
       // objectMapper.writeValue(new PrintWriter("src\\main\\resources\\customer.json"), emp);
	}

	public static void deserialize() throws IOException {
         
        
        byte[] jsonData = Files.readAllBytes(Paths.get(FILE_PATH));
         
       
        ObjectMapper objectMapper = new ObjectMapper();
         
        
        Customer emp = objectMapper.readValue(jsonData, Customer.class);
         
        System.out.println("Employee Object\n"+emp);
    }
}
