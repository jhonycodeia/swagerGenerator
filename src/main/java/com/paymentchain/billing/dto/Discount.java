package com.paymentchain.billing.dto;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Este modelo contiene todos atributos de cada descuento")
public class Discount {

	@ApiModelProperty(name = "code",required = false,example = "GH1296_6901443047635", value = "Identificador del descuento")
	private String code;
	@ApiModelProperty(name = "paymentMethod",required = false,example = "visa", value = "Indica el metodo de pago")
	private String paymentMethod;
	@ApiModelProperty(name = "type",required = false,example = "PERCENTAGE", value = "Indica el tipo de descuento")
	private String type;
	@ApiModelProperty(name = "image",required = false, value = "Datos del icono del medio de pago")
	private Image image;	
	@ApiModelProperty(name = "value",required = false,example = "$179.900", value = "El valor del descuento")
	private String value;
	@ApiModelProperty(name = "percentage",required = false,example = "1", value = "El porcentaje de descuento aplica para algunos descuentos")
	private String percentage;
	@ApiModelProperty(name = "discountedValue",required = false,example = "$179.900", value = "El valor del descuento")
	private String discountedValue;
	@ApiModelProperty(name = "category",required = false,example = "HIPERDESCUENTOS", value = "Corresponde al tipo de descuento")
	private String category;
	@ApiModelProperty(name = "message",required = false,example = "Para pagos con tarjeta de crédito", value = "propiedad del mensaje")
	private String message;
	
	
	
}
