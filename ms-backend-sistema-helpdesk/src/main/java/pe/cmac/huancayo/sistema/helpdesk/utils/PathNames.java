package pe.cmac.huancayo.sistema.helpdesk.utils;

public class PathNames {

    public static final String URL_API_BASE = "api";

    public static final String URL_API_AUTENTICAR_ACCESO = "autenticar/acceso";

    // endpoints colaborador
    public static final String URL_API_COLABORADOR_REGISTRAR = "/colaborador/registrar";

    public static final String URL_API_COLABORADOR_ACTUALIZAR = "/colaborador/actualizar";

    public static final String URL_API_COLABORADOR_LISTAR = "/colaborador/listar";

    public static final String URL_API_COLABORADOR_OBTENER = "/colaborador/obtener/{idColaborador}/{idUsuario}";

    public static final String URL_API_COLABORADOR_DESHABILITAR = "/colaborador/deshabilitar/{idColaborador}";

    // endpoints ticket
    public static final String URL_API_TICKET_REGISTRAR = "/ticket/registrar";

    public static final String URL_API_TICKET_ACTUALIZAR = "/ticket/actualizar";

    public static final String URL_API_TICKET_LISTAR = "/ticket/listar";

    public static final String URL_API_TICKET_OBTENER = "/ticket/obtener/{idTicket}";

    // endpoints TipoRequisicion
    public static final String URL_API_TIPO_REQUISICION_LISTAR = "/tipo/requisicion/listar";

    // endpoints CategoriaRequisicion 
    public static final String URL_API_CATEGORIA_REQUISICION_LISTAR = "/categoria/requisicion/listar/{idTipo}";

    // endpoints reportes
    public static final String URL_API_REPORTE_LISTADO = "/reporte/listado";
    public static final String URL_API_REPORTE_LISTAR_ESTADOS = "/reporte/listar/estados";
    public static final String URL_API_REPORTE_LISTAR_USUARIOS = "/reporte/listar/usuarios";

    public static final String URL_API_REPORTE_EXPORTAR_PDF = "/reporte/exportar-pdf";

    public static final String URL_API_REPORTE_EXPORTAR_XLSX = "/reporte/exportar-xlsx";

}
