package com.paymentchain.billing.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Este modelo contiene las propiedades para productos Variant")
public class BaseOption {

	@ApiModelProperty(name = "tamaño",required = false,example = "S", value = "El valor de esta propiedad es tamaño que posee si es Variant")
	private String tamaño;
	@ApiModelProperty(name = "color",required = false,example = "Azul", value = "El valor de esta propiedad es color que posee si es Variant")
	private String color;
}
