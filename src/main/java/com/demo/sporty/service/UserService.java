package com.demo.sporty.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.demo.sporty.entity.add2cart.Add2Cart;
import com.demo.sporty.entity.add2cart.Add2CartList;
import com.demo.sporty.entity.purchased.CurrentPurchased;
import com.demo.sporty.entity.purchased.Purchased;
import com.demo.sporty.entity.purchased.PurchasedList;
import com.demo.sporty.entity.shoes.Shoes;
import com.demo.sporty.entity.User;
import com.demo.sporty.exception.ApiException;
import com.demo.sporty.repository.ShoesRepository;
import com.demo.sporty.repository.UserRepository;
import com.demo.sporty.payload.ShowPurchasedList;
import com.demo.sporty.repository.ShowPurchasedListRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository ur;

	@Autowired
	private ShoesRepository sr;
	
	@Autowired
	private ShowPurchasedListRepository splr;

	// saving selected items to cart
	public Add2CartList saveToCart(Add2CartList p) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Object pricipal = auth.getPrincipal();
		String userEmail = ((User) pricipal).getEmail();
		System.out.println("Hii from view cart: "+userEmail);
		
		Optional<User> u = ur.findByEmail(userEmail);
		System.out.println("From User Service");
		System.out.println(p.getItemName());
		System.out.println(p.getId());
		List<Add2CartList> list = u.get().getAdd2Cart().getList();
		list.add(p);
		u.get().getAdd2Cart().setList(list);
		ur.save(u.get());
		return p;
	}

	// view items in cart
	public List<Add2CartList> viewCart() {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Object pricipal = auth.getPrincipal();
		String userEmail = ((User) pricipal).getEmail();
		System.out.println("Hii from view cart: "+userEmail);
		
		Optional<User> u = ur.findByEmail(userEmail);
		Add2Cart ac = u.get().getAdd2Cart();
		List<Add2CartList> l1 = ac.getList();
		
		if(l1.isEmpty()) {
			throw new ApiException("Your Cart is Empty. Shop to add items.");
		}
		
		return l1;
	}

	// purchasing items in cart
	public CurrentPurchased purchaseFromCart() {
		
		CurrentPurchased cp = new CurrentPurchased();

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Object pricipal = auth.getPrincipal();
		String userEmail = ((User) pricipal).getEmail();
		System.out.println("Hii from purchasing items in cart: "+userEmail);
		
		Optional<User> u = ur.findByEmail(userEmail);
		Add2Cart ac = u.get().getAdd2Cart();

		List<Add2CartList> list = ac.getList();

		if (list.size() == 0) {
			throw new ApiException("Your Cart is Empty. Shop to add items.");
		}

		List<PurchasedList> list1 = new ArrayList<>();

		double total = 0;
		for (Add2CartList item : list) {
			PurchasedList p = new PurchasedList();
			ShowPurchasedList spl = new ShowPurchasedList();
			Shoes s = sr.findByShoesName(item.getItemName());
			p.setItemName(item.getItemName());
			p.setItemPrice(s.getPrice());
			p.setCategory(s.getCategoryName());
			p.setEmail(u.get().getEmail());
			Date date = new Date();
			p.setDate(date);
			spl.setCategory(s.getCategoryName());
			spl.setDate(date);
			spl.setEmail(userEmail);
			spl.setItemName(s.getShoesName());
			spl.setPrice(s.getPrice());
			splr.save(spl);
			total += s.getPrice();
			list1.add(p);
		}
		cp.setList(list1);
		cp.setTotal(total);

		Purchased p = u.get().getPurchased();
		List<PurchasedList> list2 = p.getItems();
		list2.addAll(list1);
		double totalSpent = total + p.getTotalPrice();

		list.clear();
		ac.setList(list);

		p.setItems(list2);
		p.setTotalPrice(totalSpent);
		u.get().setAdd2Cart(ac);
		u.get().setPurchased(p);
		ur.save(u.get());
		return cp;
	}
	
	// remove all items from cart
	public void emptyCart() {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Object pricipal = auth.getPrincipal();
		String userEmail = ((User) pricipal).getEmail();
		System.out.println("Hii from purchasing items in cart: "+userEmail);
		
		Optional<User> u = ur.findByEmail(userEmail);
		Add2Cart ac = u.get().getAdd2Cart();

		List<Add2CartList> list = ac.getList();

		if (list.size() == 0) {
			throw new ApiException("Nothing to delete from cart.");
		}
		list.clear();
		ac.setList(list);
		u.get().setAdd2Cart(ac);
		ur.save(u.get());
	}

	// viewing purchased history
	public Purchased viewHistory() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Object pricipal = auth.getPrincipal();
		String userEmail = ((User) pricipal).getEmail();
		System.out.println("Hii from view cart: "+userEmail);
		
		Optional<User> u = ur.findByEmail(userEmail);
		return u.get().getPurchased();
	}
}