import { CarritoComprasModel } from "./CarritoComprasModel";
import { ProductoModel } from "./ProductoModel";

export interface SeleccionarProductosModel {
  id: number;
  cantidad: number;
  carrito?: CarritoComprasModel;
  producto?: ProductoModel;
}
