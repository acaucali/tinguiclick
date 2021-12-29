import { Pedido } from "src/app/pedidos/model/pedido";

export class Aliados{

    aliadoId: number;
    razonSocial: string;
    nit: string;
    nombre: string;
    telefono: BigInteger;
    cuentaNequi: string;
    cuentaDaviplata: string;
    cuentaBancaria: string;
    direccionFactura: string;
    emailFactura: string;
    categoriaPrincipal: string;
    documentoId: number;
    tipoCuentaBancaria: number;
    nombreBanco: string;
}