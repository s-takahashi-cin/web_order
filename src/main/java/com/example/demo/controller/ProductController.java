package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;


import com.example.demo.entity.LowCategory;
import com.example.demo.entity.MidCategory;
import com.example.demo.entity.Products;

import com.example.demo.repo.LowCategoryRepo;
import com.example.demo.repo.MidCategoryRepo;
import com.example.demo.repo.ProductRepo;
import com.example.demo.repo.TopCategoryRepo;



import org.springframework.security.core.Authentication;



@Controller
public class ProductController {

    @Autowired
    private final TopCategoryRepo topCategoryRepo;
    private final MidCategoryRepo midCategoryRepo;
    private final LowCategoryRepo lowCategoryRepo;
    private final ProductRepo productRepo;



    public ProductController(TopCategoryRepo topCategoryRepo, MidCategoryRepo midCategoryRepo, LowCategoryRepo lowCategoryRepo, ProductRepo productRepo) {
        this.topCategoryRepo = topCategoryRepo;
        this.midCategoryRepo = midCategoryRepo;
        this.lowCategoryRepo = lowCategoryRepo;
        this.productRepo = productRepo;
    }

    @GetMapping("/top_categories")
    public String topCategory(Model model) {
        model.addAttribute("top_categories", topCategoryRepo.findAll());
        return "top_categories";
    }

    @GetMapping("/mid_categories/{category_id}")
    public String midCategory(@PathVariable("category_id") Long categoryId, Model model) {
        List<MidCategory> midCategories = midCategoryRepo.findByTopCategoryId(categoryId);
        model.addAttribute("mid_categories", midCategories);
        return "mid_categories";
    }

    @GetMapping("/low_categories/{sub_category_id}")
    public String lowCategory(@PathVariable("sub_category_id") Long subCategoryId, Model model) {
        List<LowCategory> lowCategories = lowCategoryRepo.findBySubCategoryId(subCategoryId);
        model.addAttribute("low_categories", lowCategories);
        return "low_categories";
    }
    @GetMapping("/products/{product_category_id}")
    public String products(@PathVariable("product_category_id") Long productCategoryId, Model model, Authentication authentication) {
        List<Products> products = productRepo.findByProductCategoryId(productCategoryId);
        if (products.isEmpty()) {
            return "noItem";
        }
        model.addAttribute("products", products);

        return "products";
    }
}
