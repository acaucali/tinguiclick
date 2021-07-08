import { TipoIdentificacion } from "../parametros/tablas/tipoIdentificacion";

export class Usuario{
    usuarioId: number;
    nombres: string;
    apellidos: string;
    identificacion: number;
    tipo: TipoIdentificacion;
    telefono: number;
    direccion: string;
    email: string;
    tipoUsuario: number;
    username: string;
    password: string;
    habilitado: boolean;
    roles: string[]=[];
}