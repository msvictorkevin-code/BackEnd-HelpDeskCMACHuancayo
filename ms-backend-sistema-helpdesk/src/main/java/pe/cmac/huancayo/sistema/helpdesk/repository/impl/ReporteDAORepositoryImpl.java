package pe.cmac.huancayo.sistema.helpdesk.repository.impl;

import com.lowagie.text.*;
import com.lowagie.text.pdf.CMYKColor;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.util.StringUtil;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import pe.cmac.huancayo.sistema.helpdesk.dto.RestResponse;
import pe.cmac.huancayo.sistema.helpdesk.dto.reporte.EstadoTicketDTOResponse;
import pe.cmac.huancayo.sistema.helpdesk.dto.reporte.ReporteConsultaRequest;
import pe.cmac.huancayo.sistema.helpdesk.dto.reporte.ReporteConsultaResponse;
import pe.cmac.huancayo.sistema.helpdesk.dto.reporte.UsuarioDTOResponse;
import pe.cmac.huancayo.sistema.helpdesk.entity.Usuario;
import pe.cmac.huancayo.sistema.helpdesk.mapper.ItemTicket;
import pe.cmac.huancayo.sistema.helpdesk.mapper.TicketMapper;
import pe.cmac.huancayo.sistema.helpdesk.repository.TicketRepository;
import pe.cmac.huancayo.sistema.helpdesk.repository.UsuarioRepository;
import pe.cmac.huancayo.sistema.helpdesk.utils.Messages;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.apache.poi.ss.util.CellUtil.createCell;

@Slf4j
@AllArgsConstructor
@Repository
public class ReporteDAORepositoryImpl implements ReporteDAORepository {

    private final UsuarioRepository usuarioRepository;

    private final TicketRepository ticketRepository;

    private JdbcTemplate jdbcTemplate;

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    @Override
    public RestResponse<List<EstadoTicketDTOResponse>> listarEstados() {
        RestResponse<List<EstadoTicketDTOResponse>> response = new RestResponse<>();
        List<EstadoTicketDTOResponse> list = new ArrayList<>();
        list.add(new EstadoTicketDTOResponse("Iniciado"));
        list.add(new EstadoTicketDTOResponse("En proceso"));
        list.add(new EstadoTicketDTOResponse("Asignado"));
        list.add(new EstadoTicketDTOResponse("Manual"));
        list.add(new EstadoTicketDTOResponse("Finalizado"));
        response.setData(list);
        response.setMessage(Messages.MSG_LISTADO_OK);
        response.setStatus(true);
        return response;
    }

    @Override
    public RestResponse<List<UsuarioDTOResponse>> listarUsuarios() {
        RestResponse<List<UsuarioDTOResponse>> response = new RestResponse<>();
        List<Usuario> list = usuarioRepository.findAll();
        List<UsuarioDTOResponse> usuarioDTOResponseList = list.stream().map(u -> {
                    UsuarioDTOResponse usuarioDTOResponse = new UsuarioDTOResponse();
                    usuarioDTOResponse.setIdUsuario(u.getId());
                    usuarioDTOResponse.setUsername(u.getUsername());
                    return usuarioDTOResponse;
                }
        ).toList();
        response.setData(usuarioDTOResponseList);
        response.setMessage(Messages.MSG_LISTADO_OK);
        response.setStatus(true);
        return response;
    }

    @Override
    public RestResponse<List<ReporteConsultaResponse>> listado(ReporteConsultaRequest request) {
        RestResponse<List<ReporteConsultaResponse>> response = new RestResponse<>();
        List<ItemTicket> list = consultar(request);
        List<ReporteConsultaResponse> consultaResponse = list.stream().map(x -> {
            ReporteConsultaResponse response1 = new ReporteConsultaResponse();
            response1.setIdTicket(x.getIdTicket());
            response1.setDescripcion(x.getDescripcion());
            response1.setEstado(x.getEstado());
            response1.setFechaGenerada(x.getFechaGenerada());
            response1.setTipo(x.getTipo());
            response1.setCategoria(x.getCategoria());
            response1.setUsername(x.getUsername());
            return response1;
        }).toList();

        response.setData(consultaResponse);
        response.setMessage(Messages.MSG_LISTADO_OK);
        response.setStatus(true);
        return response;
    }

    private List<ItemTicket> consultar(ReporteConsultaRequest request) {
        StringBuilder builder = new StringBuilder();
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        builder.append("select id_ticket , t.descripcion ,estado, fecha_generada,prioridad, ");
        builder.append("tr.nombre as tipo , cr.nombre  as categoria,u.username ");
        builder.append("from Ticket t ");
        builder.append("inner join tipo_requisicion tr on t.id_tipo = tr.id_tipo ");
        builder.append("inner join categoria_requisicion cr  on t.id_categoria = cr.id_categoria ");
        builder.append("inner join usuario u on t.id_usuario = u.id_usuario ");
        builder.append("where ");
        String andOp = "";


        if (StringUtils.isNotEmpty(request.getIdTicket())) {

            builder.append(andOp);
            builder.append(" ");
            builder.append("id_ticket");
            builder.append("=:id_ticket");
            andOp = " AND ";
            parameters.addValue("id_ticket", Integer.parseInt(request.getIdTicket()));

        }

        if (StringUtils.isNotEmpty(request.getIdCategoria())) {
            builder.append(andOp);
            builder.append(" ");
            builder.append("t.id_categoria");
            builder.append("=:id_categoria");
            andOp = " AND ";
            parameters.addValue("id_categoria",  Integer.parseInt(request.getIdCategoria()));

        }
        if (StringUtils.isNotEmpty(request.getIdtipo())) {
            builder.append(andOp);
            builder.append(" ");
            builder.append("t.id_tipo");
            builder.append("=:id_tipo");
            andOp = " AND ";
            parameters.addValue("id_tipo",  Integer.parseInt(request.getIdtipo()));

        }

        if (StringUtils.isNotEmpty(request.getIdUsuario())) {
            builder.append(andOp);
            builder.append(" ");
            builder.append("t.id_usuario");
            builder.append("=:idUsuario");
            andOp = " AND ";
            parameters.addValue("idUsuario",  Integer.parseInt(request.getIdUsuario()));

        }

        if (StringUtils.isNotEmpty(request.getEstado())) {
            builder.append(andOp);
            builder.append(" ");
            builder.append("estado");
            builder.append("=:estado");
            andOp = " AND ";
            parameters.addValue("estado", request.getEstado());

        }

        if (StringUtils.isNotEmpty(request.getFechaInicio()) && StringUtils.isNotEmpty(request.getFechaFin())) {
            builder.append(andOp);
            builder.append(" ");
            builder.append("fecha_generada ");
            builder.append("between :fechaIni and :fechaFin");
            parameters.addValue("fechaIni", request.getFechaInicio());
            parameters.addValue("fechaFin", request.getFechaFin());

        }
        log.info("SQL=>" + builder.toString());
        List<ItemTicket> list = null;
        try {
            list = namedParameterJdbcTemplate.query(builder.toString(), parameters, new TicketMapper());
        } catch (Exception ex) {
            log.error("ex".concat(ex.getMessage()));
        }

        return list;

    }

    @Override
    public void exportarPdf(ReporteConsultaRequest request, HttpServletResponse response) throws IOException {
        generarPdf(request, response);
    }


    @Override
    public void exportarXlsx(ReporteConsultaRequest request, HttpServletResponse response) throws IOException {
        generarExcel(request, response);
    }


    private void generarPdf(ReporteConsultaRequest request, HttpServletResponse response) throws IOException {
        // Creating the Object of Document
        Document document = new Document(PageSize.A4);
        // Getting instance of PdfWriter
        PdfWriter.getInstance(document, response.getOutputStream());
        // Opening the created document to change it
        document.open();
        // Creating font
        // Setting font style and size
        Font fontTiltle = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        fontTiltle.setSize(20);
        // Creating paragraph
        Paragraph paragraph1 = new Paragraph("Reporte de Tickets", fontTiltle);
        // Aligning the paragraph in the document
        paragraph1.setAlignment(Paragraph.ALIGN_CENTER);
        // Adding the created paragraph in the document
        document.add(paragraph1);
        // Creating a table of the 4 columns
        PdfPTable table = new PdfPTable(7);
        // Setting width of the table, its columns and spacing
        table.setWidthPercentage(100f);
        table.setWidths(new int[]{2, 3, 3, 3, 3, 3, 3});
        table.setSpacingBefore(10);
        // Create Table Cells for the table header
        PdfPCell cell = new PdfPCell();
        // Setting the background color and padding of the table cell
        cell.setBackgroundColor(CMYKColor.BLUE);
        cell.setPadding(5);
        // Creating font
        // Setting font style and size
        Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        font.setColor(CMYKColor.WHITE);
        // Adding headings in the created table cell or  header
        // Adding Cell to table
        cell.setPhrase(new Phrase("ID", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Descripcion", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Estado", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Fecha Generada", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Tipo", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Categoria", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Usuario", font));
        table.addCell(cell);
        List<ItemTicket> list = consultar(request);
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        // Iterating the list of students
        for (ItemTicket ticket : list) {
            // Adding ticket getIdTicket
            table.addCell(String.valueOf(ticket.getIdTicket()));
            // Adding ticket getDescripcion
            table.addCell(ticket.getDescripcion());
            // Adding ticket getEstado
            table.addCell(ticket.getEstado());
            // Adding ticket getFechaGenerada
            table.addCell(format.format(ticket.getFechaGenerada()));
            // Adding ticket getTipo
            table.addCell(ticket.getTipo());
            // Adding ticket getCategoria
            table.addCell(ticket.getCategoria());
            // Adding ticket getUsername
            table.addCell(ticket.getUsername());
        }
        // Adding the created table to the document
        document.add(table);
        // Closing the document
        document.close();


    }


    private void generarExcel(ReporteConsultaRequest request, HttpServletResponse response) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Reporte");
        XSSFRow row = sheet.createRow(0);
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);
        createCell(row, 0, "ID", style);
        createCell(row, 1, "Descripcion", style);
        createCell(row, 2, "Estado", style);
        createCell(row, 3, "Fecha Generada", style);
        createCell(row, 4, "Tipo", style);
        createCell(row, 5, "Categoria", style);
        createCell(row, 6, "Usuario", style);

        writeRecord(workbook, sheet, request);
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();


    }

    private void writeRecord(XSSFWorkbook workbook, XSSFSheet sheet, ReporteConsultaRequest request) {
        int rowCount = 1;
        List<ItemTicket> list = consultar(request);
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);
        for (ItemTicket ticket : list) {
            XSSFRow row = sheet.createRow(rowCount++);
            int columnCount = 0;
            createCell(row, columnCount++, String.valueOf(ticket.getIdTicket()), style);
            createCell(row, columnCount++, ticket.getDescripcion(), style);
            createCell(row, columnCount++, ticket.getEstado(), style);
            createCell(row, columnCount++, format.format(ticket.getFechaGenerada()), style);
            createCell(row, columnCount++, ticket.getTipo(), style);
            createCell(row, columnCount++, ticket.getCategoria(), style);
            createCell(row, columnCount++, ticket.getUsername(), style);
        }
    }


}
