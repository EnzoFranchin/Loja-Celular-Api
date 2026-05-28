package com.example.lojacelularapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/web/produtos")
public class ProdutoWebController {

    @Autowired
    private ProdutoRepository repository;

    @GetMapping
    public String listarProdutos(Model model, @RequestParam(required = false) Long editarId) {
        model.addAttribute("produtos", repository.findAll());

        if (editarId != null) {
            Optional<Produto> produto = repository.findById(editarId);
            model.addAttribute("produtoObjeto", produto.orElse(new Produto()));
        } else {
            model.addAttribute("produtoObjeto", new Produto());
        }

        return "index";
    }

    @PostMapping
    public String salvarProduto(@ModelAttribute Produto produto) {
        repository.save(produto);
        return "redirect:/web/produtos";
    }

    @GetMapping("/deletar/{id}")
    public String deletarProduto(@PathVariable Long id) {
        repository.deleteById(id);
        return "redirect:/web/produtos";
    }
}