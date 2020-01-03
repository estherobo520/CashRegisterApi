package com.example.demo.controller;

import com.example.demo.model.Item;
import com.example.demo.model.ItemDetails;
import com.example.demo.model.Receipt;
import com.example.demo.service.CustomerCheckout;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/cashRegister/api")
public class CashRegisterController {
    private static CustomerCheckout customerCheckout;

    // @ApiOperation()//(description = This endpoint will get the store name and number.")
    @GetMapping("/customerCheckout/{store}/{number}")
    public void customerCheckout(@ApiParam(required = true) @PathVariable(value = "store") String storeName, @PathVariable(value = "number") String storeNumber) {
        customerCheckout = new CustomerCheckout(storeName, storeNumber);
    }

    //(description = "This endpoint will scan in your items, one by one, based on the JSON request body documented here.")
    @PostMapping("/scanAnItem")
    public void scanAnItem(@ApiParam(required = true) @RequestBody ItemDetails itemDetails) throws IllegalAccessException {
        Item item = new Item(itemDetails.itemPrice, itemDetails.itemDescription);
        if (itemDetails.amount != 0) {
            customerCheckout.scanAnItem(item, itemDetails.amount);
        } else {
            customerCheckout.scanAnItem(item);
        }
    }

    //(description = "This endpoint ends the transaction and returns a full receipt.")
    @GetMapping("/endTransaction")
    public Receipt endTransaction() {
        return customerCheckout.endTransaction();
    }

}
