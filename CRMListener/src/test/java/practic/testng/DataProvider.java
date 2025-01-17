package practic.testng;

import org.testng.annotations.Test;

public class DataProvider {
	@Test(dataProvider = "getData")
	public void createContactTest(String firstName, String lastName) {
		System.out.println("firstName : " + firstName + " , LastName : " + lastName);
	}

	@org.testng.annotations.DataProvider
	public Object[][] getData() {
		Object[][] objArr = new Object[3][2];
							// 3 -> num of times, 2 num of args
		objArr[0][0] = "Vasu";
		objArr[0][1] = "Ahir";

		objArr[1][0] = "Mahi";
		objArr[1][1] = "07";

		objArr[2][0] = "Hello";
		objArr[2][1] = "World";

		return objArr;
	}

}
