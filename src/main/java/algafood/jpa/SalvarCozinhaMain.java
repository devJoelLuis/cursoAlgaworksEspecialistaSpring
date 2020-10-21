package algafood.jpa;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import algafood.AlgafoodApplication;
import algafood.domain.entidades.Cozinha;
import algafood.domain.repository.CozinhaRepository;

public class SalvarCozinhaMain {

	public static void main(String[] args) {
		/*
	   ApplicationContext appc = new SpringApplicationBuilder(AlgafoodApplication.class)
			   .web(WebApplicationType.NONE)
			   .run(args);
	   
	   
	   CozinhaRepository cc = appc.getBean(CozinhaRepository.class);
	   
	   Cozinha cozinha1 = new Cozinha();
	   cozinha1.setNome("Mineira");
	   
	   Cozinha cozinha2 = new Cozinha();
	   cozinha2.setNome("Brazileira");
	   
	   
	   cozinha1 = cc.adicionar(cozinha1);
	   cozinha2 = cc.adicionar(cozinha2);
	   
	   System.out.println("id cozinha1: " + cozinha1.getId() + ", id cozinha2: "+ cozinha2.getId());
	  */
	   
	}//fecha metodo main

}// fecha classe
