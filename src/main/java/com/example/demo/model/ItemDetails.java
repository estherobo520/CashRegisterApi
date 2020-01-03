package com.example.demo.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class ItemDetails {
    @ApiModelProperty(required = true)
    public String itemDescription;
    @ApiModelProperty(required = true, allowableValues = "range(0, infinity]")
    public Double itemPrice;
    @ApiModelProperty(allowableValues = "range(1, infinity]", allowEmptyValue = true)
    public int amount;
}
