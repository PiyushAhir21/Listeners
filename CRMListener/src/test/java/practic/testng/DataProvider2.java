package practic.testng;

import org.testng.annotations.Test;

public class DataProvider2 {
	@Test(dataProvider = "getData")
	public void createContactTest(String firstName, String lastName, long phoneNum) {
		System.out.println("firstName : " + firstName + " , LastName : " + lastName + ", PhoneNum : " + phoneNum);
	}

	@org.testng.annotations.DataProvider
	public Object[][] getData() {
		Object[][] objArr = new Object[3][3];
		// 3 -> num of times, 2 num of args
		objArr[0][0] = "Vasu";
		objArr[0][1] = "Ahir";
		objArr[0][2] = 987654321l;

		objArr[1][0] = "Mahi";
		objArr[1][1] = "07";
		objArr[1][2] = 876543219l;

		objArr[2][0] = "Hello";
		objArr[2][1] = "World";
		objArr[2][2] = 98765412l;

		return objArr;
	}
}
