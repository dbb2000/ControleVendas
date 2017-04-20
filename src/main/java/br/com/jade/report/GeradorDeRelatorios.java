package br.com.jade.report;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;

public class GeradorDeRelatorios {

	private Connection conexao;

	public GeradorDeRelatorios(Connection conexao) {
		this.conexao = conexao;
	}

	public void geraPdf(Map<String, Object> parametros, HttpServletResponse response, String absolutePath) throws JRException {

		try {
			JasperReport jasper = JasperCompileManager.compileReport(absolutePath);
			Charset latin1Charset = Charset.forName("ISO-8859-1");
			CharBuffer charBuffer = latin1Charset.decode(ByteBuffer.wrap(JasperRunManager.runReportToPdf(jasper, parametros, conexao)));
			char[] chars= charBuffer.array();

			response.reset();
			response.resetBuffer();
			response.setContentType("application/pdf");
			response.setContentLength(chars.length);
			
			PrintWriter printWriter = response.getWriter();
			printWriter.write(chars, 0, chars.length);
			printWriter.flush();
			printWriter.close();

		} catch (IOException e) {
			throw new RuntimeException("Erro ao gerar relatório", e);
		}
	}   
}
