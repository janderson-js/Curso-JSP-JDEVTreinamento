package util;

import java.io.File;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import jakarta.servlet.ServletContext;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@SuppressWarnings({"unchecked","rawtypes"})
public class ReportUtil implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public byte[] geraRelatorioPDF(List listaDados, String nomeRelatorio, ServletContext servletContext) throws Exception{
		//cria a lista de dados que vem da consulta SQL do banco
		JRBeanCollectionDataSource jrbcds = new JRBeanCollectionDataSource(listaDados);
		
		String caminhoJasper = servletContext.getRealPath("relatorio") + File.separator + nomeRelatorio + ".jasper";
		
		
		JasperPrint impressoaraJasper = JasperFillManager.fillReport(caminhoJasper, new HashMap(), jrbcds);
		
		return JasperExportManager.exportReportToPdf(impressoaraJasper);
		
	}

}
