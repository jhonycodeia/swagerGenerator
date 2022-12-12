package com.paymentchain.billing.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@ApiModel(description = "Este modelo contiene los precios del Cart")
@Data
public class Prices {

	@ApiModelProperty(name = "subTotal",required = true,example = "$1.874.700", value = "El valor de esta propiedad corresponde al precio  de los Productos en el cart")
	private String subTotal;
	@ApiModelProperty(name = "deliveryCost",required = true,example = "$10", value = "El valor de esta propiedad corresponde al precio  del costo de envio")
	private String deliveryCost;
	@ApiModelProperty(name = "totalDiscounts",required = true,example = "$0", value = "El valor de esta propiedad corresponde al valor de los descuentos")
	private String totalDiscounts;
	@ApiModelProperty(name = "totalPriceWithoutBono",required = true,example = "$1.849.700", value = "El valor de esta propiedad corresponde al precio  de los Productos con descuentos del valor del bono ")
	private String totalPriceWithoutBono;
	@ApiModelProperty(name = "bonoValue",required = true,example = "$25.000", value = "El valor de esta propiedad corresponde al valor  del bono")
	private String bonoValue;
	@ApiModelProperty(name = "totalPrice",required = true,example = "$1.874.700", value = "El valor de esta propiedad corresponde al precio  de los Productos con descuentos y costos de envio ")
	private String totalPrice;
	@ApiModelProperty(name = "priceDiscount",required = false, value = "Lista de priceDiscount")	
	private List<PriceDiscount> priceDiscount;
}
