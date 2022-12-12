package com.paymentchain.billing.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModelProperty.AccessMode;
import lombok.Data;

@ApiModel(description = "Este modelo contiene los datos de Cart, los productos, resultado de la operacion y el mensaje del resultado del proceso")
@Data
public class CartItems {

	@ApiModelProperty(name = "success",required = true,example = "true", value = "El valor de esta propiedad corresponde a lo siguiente:\n\n true: `Si el proceso de actualizacion finaliza con exito`\nfalse: `En caso de que se presente cualquier error`")
	private boolean success;
	@ApiModelProperty(name = "message",required = true,example = "La cantidad del producto ha sido actualizada.", value = "El valor del mensaje indica el resultado final del proceso de actualizar dependiendo si es exitoso o fallido puede variar")
	private String message;
	@ApiModelProperty(name = "carData",required = false,value = "Contiene toda la informacion de los productos del cart")
	private CartData carData;

}
