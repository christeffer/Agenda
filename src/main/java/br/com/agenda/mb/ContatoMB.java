package br.com.agenda.mb;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import br.com.agenda.dao.ContatoDAO;
import br.com.agenda.dao.ContatoDAOImp;
import br.com.agenda.entity.Contato;


@ManagedBean
@SessionScoped
public class ContatoMB {
	private Contato contato;	
	private String nomePesquisa;	
	private DataModel<Contato> listaContatos;
	private ArrayList<Contato> listaContatosFiltered;	
	

	public String preparaAlteracao(Contato contact) {
		System.out.println("altera ");
		contato = contact;		
		return "cadCliente";
	}
	
	public String recarregaTodos(){
		return "index";
	}

	public DataModel<Contato> getListarContatos() {
		List<Contato> lista = new ContatoDAOImp().list();
		listaContatos = new ListDataModel<Contato>(lista);
		return listaContatos;
	}	
	
	public void ImprimeContatos(){
		try {
            /* User home directory location */
            String userHomeDirectory = System.getProperty("user.home");
            /* Output file location */
            String outputFile = userHomeDirectory + File.separatorChar + "TesteRelatorio.pdf";

            /* List to hold Items */
            List<Contato> listContatos = new ArrayList<Contato>();

            /* Create Items */
            Contato pessoaContato = new Contato();
            pessoaContato.setNome("Nome1");
            pessoaContato.setEmail("Nome1@hotmail.com");
            pessoaContato.setEndereco("Rua 2 n°1453 Centro");

            Contato outroContato = new Contato();
            outroContato.setNome("Outro contato");
            outroContato.setEmail("outro@hotmail.com");
            outroContato.setEndereco("Rua 5 n°127 Jd Novo 1");
            outroContato.setTelefone("19 35223311");
            
            /* Add Items to List */
            listContatos.add(pessoaContato);
            listContatos.add(outroContato);

            /* Convert List to JRBeanCollectionDataSource */
            JRBeanCollectionDataSource itemsJRBean = new JRBeanCollectionDataSource(listContatos);

            /* Map to hold Jasper report Parameters */
            Map<String, Object> parameters = new HashMap<String, Object>();
            parameters.put("ItemDataSource", itemsJRBean);

            /* Using compiled version(.jasper) of Jasper report to generate PDF */
            JasperPrint jasperPrint = JasperFillManager.fillReport("C:/Users/Chris Sethy/workspace/AgendaHib/src/main/webapp/resources/report.jasper", parameters, new JREmptyDataSource());

            /* outputStream to create PDF */
            OutputStream outputStream = new FileOutputStream(new File(outputFile));
            /* Write content to PDF file */
            JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);

            System.out.println("File Generated");
        } catch (JRException ex) {
            ex.printStackTrace();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
	}
//	public DataModel<Contato> pesquisaContato() {
//		List<Contato> lista = new ContatoDAOImp().getPesquisa(nomePesquisa);
//		listaContatos = new ListDataModel<Contato>(lista);
//		return listaContatos;
//	}
	
	public String prepararAdicionarContato() {
		System.out.println("add ");
		contato = new Contato();
		return "cadCliente";
	}

	public String excluirContato() {
		Contato clienteTemp = (Contato)(listaContatos.getRowData());
		ContatoDAO dao = new ContatoDAOImp();
		dao.remove(clienteTemp);
		return "index";
	}

	public String adicionarContato() {		
		ContatoDAO dao = new ContatoDAOImp();
		dao.save(contato);
		return "index";
	}

	public String alterarContato() {		
		ContatoDAO dao = new ContatoDAOImp();
		dao.update(contato);
		return "index";
	}
	
	
	public Contato getContato() {
		return contato;
	}

	public void setContato(Contato contato) {
		this.contato = contato;
	}

	public DataModel<Contato> getListaContatos() {
		return listaContatos;
	}

	public void setListaContatos(DataModel<Contato> listaContatos) {
		this.listaContatos = listaContatos;
	}	
	
	public String getNomePesquisa() {
		return nomePesquisa;
	}

	public void setNomePesquisa(String nomePesquisa) {
		this.nomePesquisa = nomePesquisa;
	}

	public ArrayList<Contato> getListaContatosFiltered() {
		return listaContatosFiltered;
	}

	public void setListaContatosFiltered(ArrayList<Contato> listaContatosFiltered) {
		this.listaContatosFiltered = listaContatosFiltered;
	}

}
