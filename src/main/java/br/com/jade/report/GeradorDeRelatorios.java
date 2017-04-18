package br.com.jade.report;

import java.sql.Connection;
import java.util.Map;

import javax.faces.context.FacesContext;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

public class GeradorDeRelatorios {

    private Connection conexao;

    public GeradorDeRelatorios(Connection conexao) {
        this.conexao = conexao;
    }

    public void geraPdf(String jrxml, Map<String, Object> parametros) {

        try {

            // compila jrxml em memoria
        	String absolutePath= FacesContext.getCurrentInstance().getExternalContext().getRealPath(jrxml);
            JasperReport jasper = JasperCompileManager.compileReport(absolutePath);

            // preenche relatorio
            JasperPrint print = JasperFillManager.fillReport(jasper, parametros, this.conexao);
            JasperViewer.viewReport(print, false); // exibe na tela para impressão ou salvar (melhor solução)
            
//             exporta para pdf (não preciso agora mas vou deixar comentado
//            JRExporter exporter = new JRPdfExporter();
//            exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
//            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, saida);
//            exporter.exportReport();

        } catch (Exception e) {
            throw new RuntimeException("Erro ao gerar relatório", e);
        }
    }   
}
