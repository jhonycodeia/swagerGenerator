package com.paymentchain.billing.dto;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Este modelo contiene todos atributos relacionados a los metodos de envio")
public class DeliveryMode {

	@ApiModelProperty(name = "code", required = true, example = "ship-to-address", value = "Indica el metodo de envio que utiliza el producto")
	private String code;
	@ApiModelProperty(name = "deliveryCity", required = false, example = "Cali", value = "Indica ciudad en la cual se realizara la instalacion o envio")
	private String deliveryCity;
	@ApiModelProperty(name = "storeName", required = false, example = "Ktronix Unicentro", value = "Solo aplica para el metodo de envio recoge en tienda, indica el nombre de la tienda que se ha selecionado")
	private String storeName;
	@ApiModelProperty(name = "availableForPickup", required = false, example = "false", value = "Propiedad del producto indica si es Disponible para envíos")
	private boolean availableForPickup;
	@ApiModelProperty(name = "deliveryMethods", required = true, example = "[\"pickup-in-alkosto\",\"pickup-in-alkomprar\",\"same-day-delivery\"]", value = "Lista de metodos de envio que soporta el producto")
	private List<String> deliveryMethods;

}
