package com.paymentchain.billing.dto;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Este modelo contiene todos atributos relacionados a la pre-venta")
public class PreSale {

	@ApiModelProperty(name = "preSale",required = true,example = "false", value = "El valor de esta propiedad indica si es un Producto es Preventa")
	private boolean preSale;
	@ApiModelProperty(name = "preSaleDate",required = false,example = "1 de abril de 2022", value = "El valor de esta propiedad indica la fecha de inicio de venta")
	private String preSaleDate;
	
}
