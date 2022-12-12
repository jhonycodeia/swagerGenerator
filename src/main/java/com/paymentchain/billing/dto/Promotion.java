package com.paymentchain.billing.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Este modelo contiene todos atributos relacionados a las promociones")
public class Promotion {
	
	@ApiModelProperty(name = "flagIsKasado",required = true,example = "false", value = "El valor de esta propiedad indica si es un Producto Kasado")
	private boolean flagIsKasado;	
	@ApiModelProperty(name = "flagKasadoKombo",required = true,example = "false", value = "El valor de esta propiedad indica si es un Producto Kasado con combo")
	private boolean flagKasadoKombo;	
	@ApiModelProperty(name = "hasAvailablePromo",required = true,example = "true", value = "Indica si el producto tiene promociones")
	private boolean hasAvailablePromo;	
	@ApiModelProperty(name = "komboMessageHtml",required = false,example = "", value = "mensaje html del kombo de la promocion")
	private String komboMessageHtml;	
	@ApiModelProperty(name = "komboMessage",required = false,example = "kombo.message.plural.number", value = "Indica el codigo de la propiedad del mensaje")
	private String komboMessage;	
	@ApiModelProperty(name = "promoRelatedQuantity",required = false,example = "2", value = "Indica el numero de promociones relacionadas")
	private String promoRelatedQuantity;

}
