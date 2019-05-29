package br.com.casadocodigo.loja.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.postgresql.jdbc2.optional.SimpleDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import br.com.casadocodigo.loja.dao.ProdutoDAO;
import br.com.casadocodigo.loja.models.Produto;

@Controller

public class RelatorioProdutosController {
	
	@Autowired
	private ProdutoDAO dao;
	
	
	@RequestMapping(method=RequestMethod.GET, value="/relatorio-produtos", produces="application/json")
	@ResponseBody
	public List<Produto> detalheJson(@RequestParam(value="data", required=false)String datadeLancamento ) throws ParseException{
		List<Produto> produto;
		
		if (datadeLancamento != null) {
			SimpleDateFormat formatData = new SimpleDateFormat("yyyy-MM-dd");
			Date dataFormatada = formatData.parse(datadeLancamento);
            Calendar data = Calendar.getInstance();
            data.setTime(dataFormatada);
			  produto = (List<Produto>) dao.findComParametros( data);
		} else {
			 produto = (List<Produto>) dao.findSemParametros();
		}
		
	    
		return produto;
	}
	
	
	//relatorio-produtos?data=2017-04-04
	
	/*
	 * @RequestMapping(method=RequestMethod.GET,value="",
	 * produces="application/json")
	 * 
	 * @ResponseBody public List<Produto>
	 * detalheJsonComParametro(@RequestParam(value="id") Integer data){
	 * 
	 * List<Produto> produto = (List<Produto>) dao.findComParametros( data);
	 * 
	 * return produto; }
	 */	
	
	
	/*
	 * /home/name?person=xyz /home/name
	 * 
	 * 
	 * @RestController
	 * 
	 * @RequestMapping("/home") public class IndexController {
	 * 
	 * @RequestMapping(value = "/name") String getName(@RequestParam(value =
	 * "person", defaultValue = "John") String personName) { return
	 * "Required element of request param"; } }
	 */
	
}
