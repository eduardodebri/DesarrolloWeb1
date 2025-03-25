import { PedidoModel } from "./PedidoModel";

export interface DomiciliarioModel {
  id: number;
  nombre: string;
  celular: string;
  cedula?: string;
  disponibilidad: boolean;
  pedidos?: PedidoModel[];
}
