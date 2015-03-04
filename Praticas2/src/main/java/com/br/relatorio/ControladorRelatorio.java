package com.br.relatorio;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.br.DataSourceRelatorio.DataSourceFuncionario;
import com.br.DataSourceRelatorio.DataSourcePrefeitura;
import edu.ifpb.beans.Funcionario;
import edu.ifpb.beans.Prefeitura;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 *
 * @author Sergio Diniz
 */
@Named(value = "controladorRelatorio")
@RequestScoped
public class ControladorRelatorio implements ControladorRelatorioIT {

    @Inject
    private FacesContext facesContext;
    @Inject
    private ExternalContext externalContext;

    public ControladorRelatorio() {
    }

    @Override
    public void downloadFuncionario(List<Funcionario> funcionarios) throws IOException {
        try {
            InputStream inputStream = externalContext.getResourceAsStream("/WEB-INF/relatorios/funcionario.jasper");
            JasperReport relatorio = (JasperReport) JRLoader.loadObject(inputStream);
            DataSourceFuncionario source = new DataSourceFuncionario(funcionarios);
            JasperPrint impressao = JasperFillManager.fillReport(relatorio, null, source);
            byte[] bytes = JasperExportManager.exportReportToPdf(impressao);
            realizarDownload(bytes);
        } catch (JRException ex) {
            Logger.getLogger(null).log(Level.SEVERE, null, ex);
        }

    }

    public void realizarDownload(byte[] bytes) throws IOException {
        HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();
        response.reset();
        response.setContentType("application/pdf");
        response.setHeader("Content-disposition", "attachment; filename=\"name.pdf\"");

        try (OutputStream output = response.getOutputStream()) {
            output.write(bytes);
            output.flush();
        }

        facesContext.responseComplete();
    }

    @Override
    public void downloadPrefeitura(List<Prefeitura> prefeituras) throws IOException {
        try {
            InputStream inputStream = externalContext.getResourceAsStream("/WEB-INF/relatorios/prefeitura.jasper");
            JasperReport relatorio = (JasperReport) JRLoader.loadObject(inputStream);
            DataSourcePrefeitura source = new DataSourcePrefeitura(prefeituras);
            JasperPrint impressao = JasperFillManager.fillReport(relatorio, null, source);
            byte[] bytes = JasperExportManager.exportReportToPdf(impressao);
            realizarDownload(bytes);
        } catch (JRException ex) {
            Logger.getLogger(null).log(Level.SEVERE, null, ex);
        }
    }

}
