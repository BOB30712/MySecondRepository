package com.example.Controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.Repository.BossRepository;
import com.example.entity.Boss;
import com.example.entity.Employee;

@Controller
@RequestMapping("/boss")
public class BossController {

	@Autowired
	private BossRepository bossrepo;

	@RequestMapping("/")
	public String index(Model model,@ModelAttribute Boss boss) {
		model.addAttribute("_method","POST");
		model.addAttribute("bosses",bossrepo.findAll());
		return "boss";
	}
	
	@GetMapping("/{id}")
	public String get(@PathVariable("id") Long id, Model model) {
		model.addAttribute("_method", "PUT");
		model.addAttribute("boss", bossrepo.getById(id));
		model.addAttribute("bosses",bossrepo.findAll());
		return "boss";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") Long id) {
		bossrepo.deleteById(id);
		return "redirect:../";
	}
	
	@PostMapping("/")
	public String add(Model model,@Valid @ModelAttribute Boss boss,BindingResult result) {
		//一旦傳出錯誤，返回原先頁面
		if(result.hasErrors()) {
			model.addAttribute("_method","POST");
			model.addAttribute("bosses",bossrepo.findAll());
			return "boss";
		}
		//將新增表單的內容傳到資料庫儲存
		bossrepo.save(boss);
		return "redirect:./";
	}
	
	@PutMapping("/")
	public String update(@Valid @ModelAttribute Boss boss, BindingResult result, Model model) {
		if(result.hasErrors()) {
			model.addAttribute("_method", "PUT");
			model.addAttribute("bosses",bossrepo.findAll());
			return "boss";
		}
		bossrepo.save(boss);
		return "redirect:./";
	}
}
