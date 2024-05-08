package com.techelevator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

import java.util.List;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PurchaseTests {

	private Application application;
	private VendingMachine vendingMachine;

	@Before
	public void setUp() {

		Application.moneyProvided = 0.00;

	}


	@Test
	public void testBalanceAfterFeedingMoney() {
		//Arrange
		Application.addMoneyToMoneyProvided(25.00);

		//Act
		double expectedResult = 25.00;
		double actualResult = Application.getMoneyProvided();

		//Assert
		Assert.assertEquals(expectedResult, actualResult, 0.00);
	}

	@Test
	public void testBalanceAfterFeedingMoneyTwoTimes() {
		//Arrange
		Application.addMoneyToMoneyProvided(25.00);
		Application.addMoneyToMoneyProvided(5.00);

		//Act
		double expectedResult = 30.00;
		double actualResult = Application.getMoneyProvided();

		//Assert
		Assert.assertEquals(expectedResult, actualResult, 0.00);
	}

	@Test
	public void afterFinishTransactionBalanceZeroRemaining() {
		//Arrange
		Application.moneyProvided = 30.0;
		VendingMachine.returnChangeToUser();
		Application.getMoneyProvidedAfterChange();

		//Act
		double expectedResult = 0.00;
		double actualResult = Application.getMoneyProvided();

		//Assert
		Assert.assertEquals(expectedResult, actualResult, 0.00);
	}

	@Test
	public void changeInCoinsIsCorrect() {
		//Arrange
		Application.moneyProvided = 10.15;
		VendingMachine.returnChangeToUser();

		//Act
		int[] arrayCoinsActual = new int[3];
		arrayCoinsActual[0] = VendingMachine.getQuaters();
		arrayCoinsActual[1] = VendingMachine.getDimes();
		arrayCoinsActual[2] = VendingMachine.getNickels();

		int[] arrayCoinsExpected = {40, 1, 1};

		//Assert
		Assert.assertArrayEquals(arrayCoinsExpected, arrayCoinsActual);

		System.out.println("Actual result: ");
		for (int element : arrayCoinsActual) {
			System.out.print(element + " ");
		}
		System.out.println("\nExpected result: ");
		for (int element : arrayCoinsExpected) {
			System.out.print(element + " ");
		}
	}

	@Test
	public void amountMoneyProvidedAfterDispensing() {
		//Arrange
		Application.moneyProvided = 10;
		VendingMachine.loadFileToInventory();

		int indexItem = 0;
		List<Product> listItems = VendingMachine.getListOfProductForSale();
		VendingMachine.dispensingItem(indexItem);

		//Act
		double expectedResult = 10 - listItems.get(indexItem).getPrice();
		double actualResult = Application.getMoneyProvided();

		//Assert
		Assert.assertEquals(expectedResult, actualResult, 0.00);

	}
}
