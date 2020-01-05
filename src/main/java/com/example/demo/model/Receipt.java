package com.example.demo.model;

import CsLinkedList.CsLinkedList;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import java.util.Date;
import java.util.List;

@ApiModel
@Getter
public class Receipt {

    @ApiModelProperty(required = true)
    private String storeName;
    @ApiModelProperty(required = true)
    private String storeNumber;
    @ApiModelProperty(required = true, allowableValues = "range(0, infinity]")
    private Double totalPrice;
    @ApiModelProperty(required = true, allowableValues = "range(0, infinity]")
    private Integer totalItems;
    private Date date;
    @ApiModelProperty(dataType = "")
    private CsLinkedList<Item> itemList;

    public Receipt(String storeName, String storeNumber) {
        this.storeName = storeName;
        this.storeNumber = storeNumber;
        itemList = new CsLinkedList<Item>();
        totalItems = 0;
        totalPrice = 0.0;
        date = new Date();
    }

    public void add(Item itemToScan) throws IllegalAccessException {
        itemList.add(itemToScan);
        totalPrice += itemToScan.getPrice();
        totalItems++;
    }

    @Override

    public String toString() {

        int counter = 1;
        String itemListString = "";
        while (itemList.size() > 0) {
            try {
                Item item = itemList.getAndRemove(0);
                itemListString += "" + counter + ". " + item.getDescription() + "... " + item.getPrice() + "\n";
                counter++;
            } catch (IllegalAccessException e) {
                //e.printStackTrace();
            }
        }

        return "Thank you for shopping at " + storeName + " " + storeNumber + ".\n " +
                date + "\n" +
                itemListString + "    " + "\n" +
                "Total price " + totalPrice + "\n" +
                "Please come again.";
    }

}
