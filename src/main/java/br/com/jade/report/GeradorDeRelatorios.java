package br.com.jade.report;

import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.sql.Connection;
import java.util.Map;

import javax.faces.context.FacesContext;

import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.view.JasperViewer;

public class GeradorDeRelatorios {

    private Connection conexao;

    public GeradorDeRelatorios(Connection conexao) {
        this.conexao = conexao;
    }

    public void geraPdf(String jrxml, Map<String, Object> parametros, OutputStream saida) {

        try {

            // compila jrxml em memoria
        	String relativePath=jrxml;
        	String absolutePath= FacesContext.getCurrentInstance().getExternalContext().getRealPath(relativePath);
        	File file = new File(absolutePath);
            JasperReport jasper = JasperCompileManager.compileReport(file.getAbsolutePath());

            // preenche relatorio
            JasperPrint print = JasperFillManager.fillReport(jasper, parametros, this.conexao);
            JasperViewer.viewReport(print, false);
            // exporta para pdf
//            JRExporter exporter = new JRPdfExporter();
//            exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
//            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, saida);
//
//            exporter.exportReport();

        } catch (Exception e) {
            throw new RuntimeException("Erro ao gerar relatório", e);
        }
    }   
}
