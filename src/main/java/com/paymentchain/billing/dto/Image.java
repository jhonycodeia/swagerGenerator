package com.paymentchain.billing.dto;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Este modelo contiene todos atributos de la imagen del producto")
public class Image {
	
	@ApiModelProperty(name = "url",required = false,example = "/_ui/responsive/theme-alkosto/images/missing_product_EN_300x300.jpg", value = "El valor de esta propiedad es la ruta de la imagen")
	private String url;
	@ApiModelProperty(name = "altText",required = false,example = "&#034;Celular HUAWEI G7 Gris - &#034;", value = "El valor de esta propiedad es el texto de la imagen")
	private String altText;
	
}
