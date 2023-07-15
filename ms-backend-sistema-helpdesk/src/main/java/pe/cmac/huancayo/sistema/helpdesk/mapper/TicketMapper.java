package pe.cmac.huancayo.sistema.helpdesk.mapper;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class TicketMapper implements RowMapper<ItemTicket> {

    @Override
    public ItemTicket mapRow(ResultSet rs, int rowNum) throws SQLException {
        ItemTicket itemTicket = new ItemTicket();
        itemTicket.setIdTicket(rs.getInt("id_ticket"));
        itemTicket.setDescripcion(rs.getString("descripcion"));
        itemTicket.setEstado(rs.getString("estado"));
        itemTicket.setFechaGenerada(rs.getDate("fecha_generada"));
        itemTicket.setTipo(rs.getString("tipo"));
        itemTicket.setCategoria(rs.getString("categoria"));
        itemTicket.setUsername(rs.getString("username"));

        return itemTicket;
    }
}
