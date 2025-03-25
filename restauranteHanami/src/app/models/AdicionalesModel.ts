import { ProductoModel } from "./ProductoModel";

export interface AdicionalesModel {
  id: number;
  nombre: string;
  precio: number;
  productos?: ProductoModel[];
}
