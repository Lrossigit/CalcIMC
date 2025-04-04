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
    public Map<String, Object> calcularIMC(@RequestBody Map<String, Double> payload) {
        double peso = payload.get("peso");
        double altura = payload.get("altura");
        double imc = peso / (altura * altura);

        String classificacao;

        if (imc < 18.5) classificacao = "Abaixo do peso";
        else if (imc < 25) classificacao = "Peso normal";
        else if (imc < 30) classificacao = "Sobrepeso";
        else if (imc < 35) classificacao = "Obesidade grau I";
        else if (imc < 40) classificacao = "Obesidade grau II";
        else classificacao = "Obesidade grau III";

        Map<String, Object> resposta = new HashMap<>();
        resposta.put("imc", imc);
        resposta.put("classificacao", classificacao);

        return resposta;
    }
}
