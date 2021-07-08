import { Aliados } from "src/app/aliados/model/aliados";
import { Domiciliarios } from "src/app/domiciliarios/model/domiciliarios";

export class Pedido{

    pedidoId: number;
    nombreCliente: string;
    apellidoCliente: string;
    direccionCliente: string;
    numeroCelular: BigInteger;
    telefono: BigInteger;
    municipio: string;
    ciudad: string;
    metodoPago: number;
    detalle: string;
    observacion: string;
    valor: string;
    alerta: string;
    estado: number;
    tipo: number;
    tarifa: BigInteger;
    aliado: Aliados;
    domiciliario: Domiciliarios;
}