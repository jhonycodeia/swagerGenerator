package com.paymentchain.billing.dto;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Este modelo contiene los requeridos para generar datalayer")
public class DataLayer {

	@ApiModelProperty(name = "brand",required = false,example = "MOTOROLA", value = "El valor de esta propiedad corresponde al marca del producto")
	private String brand;
	@ApiModelProperty(name = "name",required = true,example = "Celular MOTOROLA G22 128GB Blanco", value = "El valor de esta propiedad corresponde al nombre del Producto")
	private String name;
	@ApiModelProperty(name = "id",required = true,example = "840023231704", value = "El valor de esta propiedad corresponde al identificardor del Producto")
	private String id;
	@ApiModelProperty(name = "price",required = true,example = "649900", value = "El valor de esta propiedad corresponde al precio del Producto del si posee descuento")
	private String price;
	@ApiModelProperty(name = "category",required = false,example = "celulares\\/telefonos-celulares\\/motorola", value = "El valor de esta propiedad corresponde a las categorias que posee el Producto")
	private String category;
	@ApiModelProperty(name = "variant",required = false,example = "color: BLANCO", value = "El valor de esta propiedad corresponde a la informacion de variantes si el producto posee")
	private String variant;
	@ApiModelProperty(name = "previousPrice",required = false,example = "1199900", value = "El valor de esta propiedad corresponde a al precio sin descuento en caso de que el producto posea")
	private String previousPrice;
	@ApiModelProperty(name = "quantity",required = true,example = "1", value = "El valor de esta propiedad corresponde a cantidad de items en el evento")
	private String quantity;
}
