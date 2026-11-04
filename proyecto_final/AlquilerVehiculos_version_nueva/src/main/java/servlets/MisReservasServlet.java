package servlets;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dao.AlquilerDaoImpl;
import dao.IAlquilerDao;
import modelo.Alquiler;

@WebServlet("/misReservas")
public class MisReservasServlet extends HttpServlet {
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String idClienteParam = request.getParameter("id_cliente");
        response.setContentType("application/json; charset=UTF-8");
        
        IAlquilerDao dao = new AlquilerDaoImpl();
        List<Alquiler> listaReservas = new java.util.ArrayList<>();

        try {
            if (idClienteParam != null && !idClienteParam.isEmpty()) {
                int idCliente = Integer.parseInt(idClienteParam);
                listaReservas = dao.listarPorCliente(idCliente);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append("[");
        for (int i = 0; i < listaReservas.size(); i++) {
            Alquiler r = listaReservas.get(i);
            double monto = (r.getMontoTotal() != null) ? r.getMontoTotal().doubleValue() : 0.0;
            
            jsonBuilder.append("{");
            jsonBuilder.append("\"idAlquiler\":").append(r.getIdAlquiler()).append(",");
            jsonBuilder.append("\"idVehiculo\":").append(r.getIdVehiculo()).append(",");
            jsonBuilder.append("\"idCliente\":").append(r.getIdCliente()).append(",");
            jsonBuilder.append("\"fechaInicioPautada\":\"").append(r.getFechaInicioPautada() != null ? r.getFechaInicioPautada().toString() : "").append("\",");
            jsonBuilder.append("\"fechaFinPautada\":\"").append(r.getFechaFinPautada() != null ? r.getFechaFinPautada().toString() : "").append("\",");
            jsonBuilder.append("\"estadoReserva\":\"").append(r.getEstadoReserva() != null ? r.getEstadoReserva() : "").append("\",");
            jsonBuilder.append("\"montoTotal\":").append(monto);
            jsonBuilder.append("}");
            if (i < listaReservas.size() - 1) {
                jsonBuilder.append(",");
            }
        }
        jsonBuilder.append("]");

        response.getWriter().write(jsonBuilder.toString());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String accion = request.getParameter("accion");
        
        if ("cancelar".equals(accion)) {
            int idAlquiler = Integer.parseInt(request.getParameter("idAlquiler"));
            
            IAlquilerDao dao = new AlquilerDaoImpl();
            boolean exito = dao.cancelarReserva(idAlquiler);
            
            response.setContentType("application/json; charset=UTF-8");
            if (exito) {
                response.getWriter().write("{\"status\": \"success\", \"message\": \"Reserva cancelada correctamente.\"}");
            } else {
                response.getWriter().write("{\"status\": \"error\", \"message\": \"No se pudo cancelar la reserva.\"}");
            }
        }
    }
}