package br.com.orange.vacinabrasil.exeptions;

public class ValidacaoAplicacaoVacina extends Exception {
	
	private static final long serialVersionUID = 1L;
		
    public ValidacaoAplicacaoVacina(String msg){
        super(msg);
    }
    
    public ValidacaoAplicacaoVacina(String msg, Throwable cause){
        super(msg, cause);
    }

}
