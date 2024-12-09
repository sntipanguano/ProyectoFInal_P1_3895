package com.soap.soap.converter;

import com.soap.soap.gen.Reportes;  // Asumiendo que tienes esta clase generada
import com.soap.soap.model.ReportesModel;
import org.springframework.stereotype.Component;

@Component
public class ReportesConverter {

    // Convierte un objeto Reporte a un modelo ReportesModel
    public ReportesModel convertReporteToReportesModel(Reportes reporte) {
        ReportesModel reportesModel = new ReportesModel();
        reportesModel.setId(reporte.getId());
        reportesModel.setTiporeporte(reporte.getTiporeporte());
        reportesModel.setContenido(reporte.getContenido()); // Asumiendo que es de tipo JSON o String
        return reportesModel;
    }

    // Convierte un modelo ReportesModel a un objeto Reporte
    public Reportes convertReportesModelToReporte(ReportesModel reportesModel) {
        Reportes reporte = new Reportes();
        reporte.setId(reportesModel.getId());
        reporte.setTiporeporte(reportesModel.getTiporeporte());
        reporte.setContenido(reportesModel.getContenido()); // Asumiendo que es de tipo JSON o String
        return reporte;
    }
}
