import { PedidoModel } from "./PedidoModel";

export interface OperadorModel {
  id?: number;
  nombre: string;
  usuarioOp: string;
  contraseña: string;
  pedidos?: PedidoModel[];
}
