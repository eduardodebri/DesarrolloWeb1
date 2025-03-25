import { ClienteModel } from "./ClienteModel";
import { SeleccionarProductosModel } from "./SeleccionarProductosModel";

export interface CarritoComprasModel {
  id: number;
  precioTotal: number;
  clienteModel?: ClienteModel;
  productosSeleccionados?: SeleccionarProductosModel[];
}
