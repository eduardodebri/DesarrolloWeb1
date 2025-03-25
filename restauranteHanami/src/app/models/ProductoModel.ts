import { AdicionalesModel } from "./AdicionalesModel";

export interface ProductoModel {
  id: number;
  nombre: string;
  precio: number;
  descripcion: string;
  imagenUrl: string;
  adicionales?: AdicionalesModel[];
}
