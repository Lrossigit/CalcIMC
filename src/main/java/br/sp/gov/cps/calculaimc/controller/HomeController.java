package br.sp.gov.cps.calculaimc.controller;


import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Service
@Controller
@RequestMapping("/")
public class HomeController {
    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/api/imc/calcular")
    @ResponseBody
    public Map<String, Object> calcularIMC(@RequestBody Map<String, Object> payload) {
        String nome = String.valueOf(payload.get("nome"));
        double peso = ((Number) payload.get("peso")).doubleValue();
        double altura = ((Number) payload.get("altura")).doubleValue();
        double imc = peso / (altura * altura);

        String classificacao;

        if (imc < 18.5) classificacao = "Você está um pouco abaixo do peso, por favor procure acompanhamento";
        else if (imc < 25) classificacao = "Você está com o peso normal, parábens!";
        else if (imc < 30) classificacao = "Você está em Sobrepeso, por precaução cuide-se";
        else if (imc < 35) classificacao = "Você está em Obesidade grau I, recomendo que procure um médico";
        else if (imc < 40) classificacao = "Você está em Obesidade grau II, por favor cuide-se e procure um médico";
        else classificacao = "Você está em Obesidade grau III, procure um médico o quanto antes, dê valor a sua vida";

        Map<String, Object> resposta = new HashMap<>();
        String mensagem = "Olá " + nome + ", conforme seu peso (" + peso + ") e altura (" + altura + "), repassaremos o resultado de seu IMC:";
        resposta.put("mensagem" , mensagem);
        resposta.put("imc", imc);
        resposta.put("classificacao", classificacao);

        return resposta;
    }
}
