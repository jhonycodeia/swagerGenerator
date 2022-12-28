package com.paymentchain.billing.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Este modelo contiene los datos de Cart, los productos y datalayer")
public class CartItemsDataLayer extends CartItems{
	
	@ApiModelProperty(name = "datalayerEvent",required = true,example = "addToCart", value = "Contiene el tipo de evento puede ser {addToCart,removeFromCart}")
	private String datalayerEvent;
	@ApiModelProperty(name = "datalayer",required = true,value = "Contiene toda la informacion requerida para generar el datalayer")
	private DataLayer datalayer;
	

}
