package practic.testng;

import org.testng.annotations.Test;

public class DependsOnMethos {
	@Test
	public void createContactTest() {
		System.out.println("Execute createContact with --> HDFC");
	}

	@Test(dependsOnMethods = "createContactTest")
	public void modifyContactTest() {
		System.out.println("execute modifyContactTest --> HDFC -> ICICI");
	}

	@Test(dependsOnMethods = "modifyContactTest")
	public void deleteContactTest() {
		System.out.println("Executed deleteContactTest ICICI");
	}
}
