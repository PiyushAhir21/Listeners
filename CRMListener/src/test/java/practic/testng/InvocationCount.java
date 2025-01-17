package practic.testng;

import org.testng.annotations.Test;

public class InvocationCount {
	@Test(enabled = true)
	public void createContactTest() {
		System.out.println("Execute createContact with --> HDFC");
	}

	@Test(invocationCount = 12)
	public void modifyContactTest() {
		System.out.println("execute modifyContactTest --> HDFC -> ICICI");
	}

	@Test(enabled = false)
	public void deleteContactTest() {
		System.out.println("Executed deleteContactTest ICICI");
	}
}
