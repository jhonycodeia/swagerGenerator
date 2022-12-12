package com.paymentchain.billing.dto;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Este modelo contiene los datos de Cart y los productos")
public class CartData {
	
	@ApiModelProperty(name = "code",required = true,example = "600024200", value = "El valor de esta propiedad corresponde al codigo del Cart")
	private String code;
	@ApiModelProperty(name = "total",required = true,example = "$3.657.800", value = "El valor de esta propiedad corresponde al total del costo de todos los productos")
	private String total;
	@ApiModelProperty(name = "products",required = false, value = "Lista de productos")	
	private List<Product> products;

}
