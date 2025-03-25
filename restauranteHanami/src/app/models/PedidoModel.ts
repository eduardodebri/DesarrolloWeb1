import { OperadorModel } from "./OperadorModel";
import { DomiciliarioModel } from "./DomiciliarioModel";

export interface PedidoModel {
  id: number;
  estado: string;
  fechaCreacion: Date;
  fechaEntrega: Date;
  operador?: OperadorModel;
  domiciliario?: DomiciliarioModel;
}
