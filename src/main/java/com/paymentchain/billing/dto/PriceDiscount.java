package com.paymentchain.billing.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "Este modelo contiene los precios del Cart")
@Data
public class PriceDiscount {
	
	@ApiModelProperty(name = "entryNumber",required = true,example = "0", value = "El valor de esta propiedad corresponde al número de la entry")
	private String entryNumber;
	@ApiModelProperty(name = "nameProduct",required = true,example = "Celular LG K8 4G Single SIM Negro Azul +SD 16Gb", value = "El valor de esta propiedad corresponde al nombre del producto")
	private String nameProduct;
	@ApiModelProperty(name = "discountedValue",required = true,example = "$0", value = "El valor de esta propiedad corresponde al valor de los descuentos")
	private String discountedValue;
	@ApiModelProperty(name = "percentage",required = true,example = "%", value = "El valor de esta propiedad corresponde al símbolo de porcentaje ")
	private String percentage;
	@ApiModelProperty(name = "discountType",required = true,example = "PERCENTAGE", value = "El valor de esta propiedad corresponde al tipo de descuento ")
	private String discountType;
	@ApiModelProperty(name = "flagKasadoKombo",required = true,example = "", value = "El valor de esta propiedad corresponde si el producto posee flagkasadoKombo ")
	private boolean flagKasadoKombo;
	@ApiModelProperty(name = "discountCategory",required = true,example = "HIPERDESCUENTOS", value = "El valor de esta propiedad corresponde a la categoría del descuento ")
	private String discountCategory;
	@ApiModelProperty(name = "value",required = false,example = "$0", value = "El valor de esta propiedad corresponde al valor de los descuentos")
	private String value;
	
}
