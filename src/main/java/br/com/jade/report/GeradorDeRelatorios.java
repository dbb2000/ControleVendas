package br.com.jade.report;

import java.io.IOException;
import java.sql.Connection;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

public class GeradorDeRelatorios extends HttpServlet {

//    private Connection conexao;
//
//    public GeradorDeRelatorios(Connection conexao) {
//        this.conexao = conexao;
//    }
//
//    public void geraPdf(String jrxml, Map<String, Object> parametros) {
//
//        try {
//
//            // compila jrxml em memoria
//        	String absolutePath= FacesContext.getCurrentInstance().getExternalContext().getRealPath(jrxml);
//            JasperReport jasper = JasperCompileManager.compileReport(absolutePath);
//
//            // preenche relatorio
//            JasperPrint print = JasperFillManager.fillReport(jasper, parametros, this.conexao);
//           // JasperViewer.viewReport(print, false); // exibe na tela para impressão ou salvar (melhor solução)
//           JasperViewer jasperViewer = new JasperViewer(print);
//           jasperViewer.setVisible(true);
//            
////             exporta para pdf (não preciso agora mas vou deixar comentado
////            JRExporter exporter = new JRPdfExporter();
////            exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
////            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, saida);
////            exporter.exportReport();
//
//        } catch (Exception e) {
//            throw new RuntimeException("Erro ao gerar relatório", e);
//        }
//    }   
	
	public void lala ( HttpServletResponse response, byte[] bytes) throws IOException{
		response.reset();
	    response.resetBuffer();
	    response.setContentType("application/pdf");
	    response.setContentLength(bytes.length);
	    ServletOutputStream ouputStream = response.getOutputStream();
		ouputStream.write(bytes, 0, bytes.length);
		ouputStream.flush();
		ouputStream.close();
	}
}
