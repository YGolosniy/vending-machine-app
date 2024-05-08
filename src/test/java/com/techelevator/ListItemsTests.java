package com.techelevator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static com.techelevator.Application.finishTransaction;

public class ListItemsTests {

    private final int MAX_NUMBER_OF_ITEM_IN_SLOT = 5;
    List<Product> productList;

    @Before
    public void setUp() {
        VendingMachine.loadFileToInventory();
    }

    @Test
    public void afterStartEveryProductStockedToMaximumAmount() {

        //Arrange
        int[] arrayProductList = new int[16];
        productList = VendingMachine.getListOfProductForSale();

        //Act
        for (int i = 0; i < productList.size(); i++) {
            arrayProductList[i] = productList.get(i).getLeftItemInSlot();
        }
        int[] expectedResult = {5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5};

        //Assert
        Assert.assertArrayEquals(expectedResult, arrayProductList);

        System.out.print("Expected result: ");
        for (int arrayExp : expectedResult) {
            System.out.print(arrayExp + " ");
        }

        System.out.println();
        System.out.print("Actual result: ");
        for (int arrayAct : arrayProductList) {
            System.out.print(arrayAct + " ");
        }

    }

    @Test
    public void afterPurchaseQuantityOfItemsDecreases() {

        //Arrange
        Application.addMoneyToMoneyProvided(10.00);
        String nameOfSlot = "d2";
        int quantityOfSelectedItem = 0;
        int indexItem = 0;

        List<Product> listItems = VendingMachine.getListOfProductForSale();
        for (int i = 0; i < listItems.size(); i++) {
            if (listItems.get(i).getSlot().equalsIgnoreCase(nameOfSlot)) {
                indexItem = i;
            }
        }
        VendingMachine.dispensingItem(indexItem);

        //Act
        productList = VendingMachine.getListOfProductForSale();
        for (Product product : productList) {
            if (product.getSlot().equalsIgnoreCase(nameOfSlot)) {
                quantityOfSelectedItem = product.getLeftItemInSlot();
            }
        }

        int expectedResult = 4;
        int actualResult = quantityOfSelectedItem;

        //Assert
        Assert.assertEquals(expectedResult, actualResult);

    }
}
