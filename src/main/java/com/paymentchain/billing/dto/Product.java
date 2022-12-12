package com.paymentchain.billing.dto;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Este modelo contiene todos atributos de cada producto")
public class Product {
	
	@ApiModelProperty(name = "code",required = true,example = "\"888462657204\"", value = "El valor de esta propiedad corresponde al codigo del Producto")
	private String code;
	@ApiModelProperty(name = "name",required = true,example = "Cel 4G iPhone 6s 16GB Gr", value = "El valor de esta propiedad corresponde al nombre del Producto")
	private String name;
	@ApiModelProperty(name = "entryNumber",required = true,example = "\"1\"", value = "El valor de esta propiedad corresponde al identificardor del Producto en el Cart")
	private String entryNumber;
	@ApiModelProperty(name = "url",required = true,example = "/cel-4g-iphone-6s-16gb-gr/p/888462657204", value = "El valor de esta propiedad corresponde el url del Producto")
	private String url;
	@ApiModelProperty(name = "quantity",required = true,example = "\"2\"", value = "El valor de esta propiedad corresponde el la cantidad de items del Producto")
	private String quantity;
	@ApiModelProperty(name = "maximumAmountAvailable",required = true,example = "\"4\"", value = "El valor de esta propiedad corresponde al limite de cantidad de items por el Producto")
	private String maximumAmountAvailable;
	@ApiModelProperty(name = "basePrice",required = true,example = "$1.499.000", value = "El valor de esta propiedad corresponde al precio base del Producto")
	private String basePrice;
	@ApiModelProperty(name = "salePrice",required = true,example = "$1.499.000", value = "El valor de esta propiedad corresponde al precio de venta del Producto")
	private String salePrice;
	@ApiModelProperty(name = "percentageDiscount",required = false,example = "2", value = "El Solo aplica para descuentos de geo de tipo `percentage`, muestra el valor del descuento sin formato solo el numero")
	private String percentageDiscount;	
	@ApiModelProperty(name = "updateable",required = true,example = "true", value = "El valor de esta propiedad indica si permite el producto permite modificaciones")
	private boolean updateable;	
	@ApiModelProperty(name = "installationService",required = true,example = "false", value = "Indica si es un producto tipo instalaccion")
	private boolean installationService;
	@ApiModelProperty(name = "productService",required = true,example = "false", value = "Indica si es un producto tipo servicio")
	private boolean productService;		
	@ApiModelProperty(name = "stock",required = true,example = "Con inventario", value = "**Nota:** Solo los productos variant responde con stock,debido a un bug en mapeo actual\n\nEl valor de esta propiedad indica\n\n Con inventario: `tiene stock`\nSin inventario `no tiene stock`")
	private String stock;	
	
	
	@ApiModelProperty(name = "paymentDiscounts",required = false, value = "Solo aplica para producto que tengan descuento en metodos de pago")
	private List<Discount> paymentDiscounts;
	@ApiModelProperty(name = "discounts",required = false, value = "Solo aplica para producto que tengan descuento")
	private List<Discount> discounts;
	
	@ApiModelProperty(name = "deliveryMode",required = true, value = "Datos de envio")
	private DeliveryMode deliveryMode;	
	@ApiModelProperty(name = "preSale",required = true, value = "Datos de pre-venta")
	private PreSale preSale;
	@ApiModelProperty(name = "promotion",required = false, value = "Datos de promociones potenciales")
	private Promotion promotion;
	@ApiModelProperty(name = "image",required = true, value = "Datos de la imagen")
	private Image image;	
	
	@ApiModelProperty(name = "baseOptions",required = false, value = "Solo aplica para productos que son Variant")
	private List<BaseOption> baseOptions;	
	@ApiModelProperty(name = "supportedActions",required = true,example = "[REMOVE]", value = "Lista de operaciones que soporta")
	private List<String> supportedActions;	
	@ApiModelProperty(name = "extendedWarranty",required = false, value = "Solo aplica para productos con garantia extendida")
	private ExtendedWarranty extendedWarranty;
	
	
	
	
	
}
